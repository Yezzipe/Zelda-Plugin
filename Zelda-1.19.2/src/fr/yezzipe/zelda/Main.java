package fr.yezzipe.zelda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import fr.yezzipe.zelda.blocks.BlockBuilder;
import fr.yezzipe.zelda.commands.ClearInfluenceCommand;
import fr.yezzipe.zelda.commands.DropCommand;
import fr.yezzipe.zelda.commands.DropCommandCompleter;
import fr.yezzipe.zelda.commands.ExchangeCommand;
import fr.yezzipe.zelda.commands.ItemCommand;
import fr.yezzipe.zelda.commands.ItemCommandCompleter;
import fr.yezzipe.zelda.commands.PlayerDataCommand;
import fr.yezzipe.zelda.commands.PlayerDataCommandCompleter;
import fr.yezzipe.zelda.commands.RefreshChunksCommand;
import fr.yezzipe.zelda.commands.RingCommand;
import fr.yezzipe.zelda.commands.SpawnNPCCommand;
import fr.yezzipe.zelda.commands.SpawnNPCCommandCompleter;
import fr.yezzipe.zelda.commands.StructureBuildCommand;
import fr.yezzipe.zelda.commands.StructureUpdateCommand;
import fr.yezzipe.zelda.commands.StructureWriteCommand;
import fr.yezzipe.zelda.commands.StructureWriteCommandCompleter;
import fr.yezzipe.zelda.entity.CustomBlock;
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.entity.PacketReader;
import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.entity.player.BiomeRegistry;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.items.GrapplingHookManager;
import fr.yezzipe.zelda.items.ItemBuilder;
import fr.yezzipe.zelda.items.ItemTable;
import fr.yezzipe.zelda.territory.TemperatureRegistry;
import fr.yezzipe.zelda.territory.TerritoryUtil;
import fr.yezzipe.zelda.territory.structures.StableMemory;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    private boolean isRunning = false;

    public static Listener listener;

    public static Main instance;

    public static Main getInstance() {
	return instance;
    }

    public void onEnable() {
	instance = this;
	ItemTable.init();
	EntityManager.init();
	BiomeRegistry.init();
	TemperatureRegistry.init();
	ItemBuilder.init();
	BlockBuilder.init();
	CustomBlock.initAll();

	listener = new Listener();
	getServer().getPluginManager().registerEvents(listener, (Plugin) this);
	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
	for (Player player : players) {
	    PlayerData PData = PlayerData.getData(player);
	    PData.register();
	    PacketReader reader = new PacketReader();
	    reader.inject(player);
	}
	getCommand("playerdata").setExecutor((CommandExecutor) new PlayerDataCommand());
	getCommand("playerdata").setTabCompleter((TabCompleter) new PlayerDataCommandCompleter());
	getCommand("rings").setExecutor((CommandExecutor) new RingCommand());
	getCommand("spawnnpc").setExecutor((CommandExecutor) new SpawnNPCCommand());
	getCommand("spawnnpc").setTabCompleter((TabCompleter) new SpawnNPCCommandCompleter());
	getCommand("exchange").setExecutor((CommandExecutor) new ExchangeCommand());
	getCommand("givecustom").setExecutor((CommandExecutor) new ItemCommand());
	getCommand("givecustom").setTabCompleter((TabCompleter) new ItemCommandCompleter());
	getCommand("drop").setExecutor((CommandExecutor) new DropCommand());
	getCommand("drop").setTabCompleter((TabCompleter) new DropCommandCompleter());
	getCommand("writestruc").setExecutor((CommandExecutor) new StructureWriteCommand());
	getCommand("writestruc").setTabCompleter((TabCompleter) new StructureWriteCommandCompleter());
	getCommand("buildstruc").setExecutor((CommandExecutor) new StructureBuildCommand());
	getCommand("updatestruc").setExecutor((CommandExecutor) new StructureUpdateCommand());
	getCommand("clearinfluence").setExecutor((CommandExecutor) new ClearInfluenceCommand());
	getCommand("refreshchunks").setExecutor((CommandExecutor) new RefreshChunksCommand());
	StableMemory.loadAll();
	this.isRunning = true;
	Type list = (new TypeToken<ArrayList<NPCMemory>>() {

	}).getType();
	List<NPCMemory> memories = read("npc/list", list);
	if (memories != null) {
	    for (NPCMemory memory : memories)
		NPCHandler.instantiateNPCMemory(memory);
	    for (World w : Bukkit.getWorlds()) {
		for (Chunk chunk : w.getLoadedChunks()) {
		    NPCHandler.mountBrains(chunk);
		}
	    }
	    for (Player p : players) {
		NPCHandler.createNPCs(p);
		NPCHandler.hideNPCBrains(p);
	    }
	    for (Player p : players) {
		Thread thread = new Thread(new Runnable() {
		    public void run() {
			Chunk chunk = p.getLocation().getChunk();
			int x = chunk.getX();
			int z = chunk.getZ();
			List<Chunk> chunks = new ArrayList<>();
			for (int i = -1; i < 2; i++) {
			    for (int j = -1; j < 2; j++)
				chunks.add(chunk.getWorld().getChunkAt(x + i, z + j));
			}
			for (Chunk c : chunks) {
			    List<NPCMemory> memories = NPCHandler.getLoadedNPCsInChunk(c);
			    if (memories != null && !memories.isEmpty())
				for (NPCMemory memory : memories) {
				    CustomNPC brain = (CustomNPC) NPCHandler.NPCMemoryToBrain.get(memory);
				    NPCHandler.hideNPCBrain(p, brain);
				}
			}
			if (!NPCHandler.allNPCLoaded(p)) {
			    NPCHandler.showNPCs(p);
			    NPCHandler.hideNPCs(p);
			}
			List<Thread> tasks = Listener.NPCTasks.get(p);
			if (tasks != null) {
			    if (!tasks.isEmpty()) {
				if (tasks.contains(Thread.currentThread()))
				    tasks.remove(Thread.currentThread());
				if (!tasks.isEmpty()) {
				    Thread nextThread = tasks.get(0);
				    nextThread.start();
				} else {
				    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
				}
			    } else {
				Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			    }
			} else {
			    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
			}
			Listener.NPCTasks.put(p, tasks);
		    }
		});
		if (Listener.chargingNPCs.get(p) == null)
		    Listener.chargingNPCs.put(p, Boolean.valueOf(false));
		if (((Boolean) Listener.chargingNPCs.get(p)).booleanValue()) {
		    List<Thread> tasks = Listener.NPCTasks.get(p);
		    if (tasks == null)
			tasks = new ArrayList<>();
		    tasks.add(thread);
		    Listener.NPCTasks.put(p, tasks);
		    continue;
		}
		Listener.chargingNPCs.put(p, Boolean.valueOf(true));
		thread.start();
	    }
	}
	(new BukkitRunnable() {
	    public void run() {
		if (Main.this.isRunning) {
		    Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
		    for (Player player : players) {
			PlayerData PData = PlayerData.getData(player);
			player.setScoreboard(EntityManager.board);
			PlayerData.applyAttributes(player, false);
			PlayerData.applyColors(player);
			PData.tick(true, true, false);
		    }
		} else {
		    cancel();
		}
	    }
	}).runTaskTimer((Plugin) getPlugin(Main.class), 0L, 18000L);
	(new BukkitRunnable() {
	    public void run() {
		TerritoryUtil.sendPlayerPositions();
	    }
	}).runTaskTimer((Plugin) getPlugin(Main.class), 0L, 50L);
	(new BukkitRunnable() {
	    public void run() {
		(new Thread(new Runnable() {
		    public void run() {
			TerritoryUtil.updatePlayerNearbyChunks();
		    }
		})).start();
	    }
	}).runTaskTimer((Plugin) getPlugin(Main.class), 0L, 300L);
    }

    public void onDisable() {
	this.isRunning = false;
	for (Arrow arr : GrapplingHookManager.arrows.keys()) {
	    GrapplingHookManager m = GrapplingHookManager.getFromArrow(arr);
	    if (m != null)
		m.cleanArrow();
	}
	CustomBlock.saveAll();
	Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
	for (Player player : players) {
	    PlayerData PData = PlayerData.getData(player);
	    PData.setHealth(player.getHealth());
	    PData.save();
	    PacketReader reader = new PacketReader();
	    reader.uninject(player);
	}
	for (World w : Bukkit.getWorlds()) {
	    for (Chunk chunk : w.getLoadedChunks()) {
		List<NPCMemory> memories = NPCHandler.getLoadedNPCsInChunk(chunk);
		if (memories != null)
		    for (NPCMemory memory : memories) {
			CustomNPC brain = (CustomNPC) NPCHandler.NPCMemoryToBrain.get(memory);
			List<EntityPlayer> npcs = NPCHandler.getShells(brain);
			if (npcs != null)
			    for (EntityPlayer npc : npcs) {
				PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(
					new int[] { npc.ae() });
				Player player = NPCHandler.getPlayerFromShell(npc);
				CraftPlayer cp = (CraftPlayer) player;
				(cp.getHandle()).b.a((Packet<?>) packet);
				PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(
					new int[] { brain.ae() });
				(cp.getHandle()).b.a((Packet<?>) packet2);
			    }
			brain.ah();
		    }
	    }
	}

	write("npc/list", NPCMemory.NPCMemories);
	StableMemory.saveAll();
	for (UUID uuid : StableMemory.Stables.keySet()) {
	    StableMemory stable = (StableMemory) StableMemory.Stables.get(uuid);
	    stable.save();
	}
    }

    public static void write(String fileName, Object object) {
	Path path = Paths.get((getPlugin(Main.class)).getDataFolder() + "/" + fileName + ".json", new String[0]);
	try {
	    if (!Files.exists(path, new java.nio.file.LinkOption[0])) {
		Files.createDirectories(path.getParent(), (FileAttribute<?>[]) new FileAttribute[0]);
		Files.createFile(path, (FileAttribute<?>[]) new FileAttribute[0]);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try (Writer writer = new FileWriter(path.toString())) {
	    GSON.toJson(object, writer);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void delete(String fileName) {
	Path path = Paths.get((getPlugin(Main.class)).getDataFolder() + "/" + fileName + ".json", new String[0]);
	if (Files.exists(path, new java.nio.file.LinkOption[0]))
	    try {
		Files.delete(path);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
    }

    @SuppressWarnings("unchecked")
    public static <T> T read(String fileName, Type type) {
	Path path = Paths.get((getPlugin(Main.class)).getDataFolder() + "/" + fileName + ".json", new String[0]);
	try {
	    if (!Files.exists(path, new java.nio.file.LinkOption[0])) {
		Files.createDirectories(path.getParent(), (FileAttribute<?>[]) new FileAttribute[0]);
		Files.createFile(path, (FileAttribute<?>[]) new FileAttribute[0]);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	    BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
	    JsonReader jsonReader = new JsonReader(bufferedReader);
	    return (T) GSON.fromJson(jsonReader, type);
	} catch (IOException e) {
	    return null;
	}
    }

    public static boolean exist(String fileName) {
	Path path = Paths.get((getPlugin(Main.class)).getDataFolder() + "/" + fileName + ".json", new String[0]);
	if (Files.exists(path, new java.nio.file.LinkOption[0]))
	    return true;
	return false;
    }
    
    public static void remove(String fileName) {
	Path path = Paths.get((getPlugin(Main.class)).getDataFolder() + "/" + fileName + ".json", new String[0]);
	try {
	    Files.deleteIfExists(path);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @SuppressWarnings("unused")
    public static boolean isInt(String string) {
	if (string == null || string.equals(""))
	    return false;
	try {
	    int intValue = Integer.parseInt(string);
	    return true;
	} catch (NumberFormatException numberFormatException) {
	    return false;
	}
    }
}
