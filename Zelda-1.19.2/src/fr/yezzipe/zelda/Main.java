package fr.yezzipe.zelda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

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
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.entity.PacketReader;
import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.items.ItemBuilder;
import fr.yezzipe.zelda.items.ItemTable;
import fr.yezzipe.zelda.items.enums.Item;
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
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
  private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
  
  private boolean isRunning = false;
  
  public static Listener listener;
  
  public static String resourceHash = "1d678d714fe8e1f889691718c1e4739b3f1bb1f3";
  
  private static int toDigit(char hexChar) {
    int digit = Character.digit(hexChar, 16);
    if (digit == -1)
      throw new IllegalArgumentException(
          "Invalid Hexadecimal Character: " + hexChar); 
    return digit;
  }
  
  private static byte hexToByte(String hexString) {
    int firstDigit = toDigit(hexString.charAt(0));
    int secondDigit = toDigit(hexString.charAt(1));
    return (byte)((firstDigit << 4) + secondDigit);
  }
  
  public static byte[] getResourceHash() {
    if (resourceHash.length() % 2 == 1)
      throw new IllegalArgumentException(
          "Invalid hexadecimal String supplied."); 
    byte[] bytes = new byte[resourceHash.length() / 2];
    for (int i = 0; i < resourceHash.length(); i += 2)
      bytes[i / 2] = hexToByte(resourceHash.substring(i, i + 2)); 
    return bytes;
  }
  
  public void onEnable() {
    ItemTable.init();
    EntityManager.init();
    ItemStack grappling_item = ItemBuilder.build(Item.GRAPPLING_HOOK);
    ShapedRecipe grappling = new ShapedRecipe(new NamespacedKey((Plugin)this, "grappling-hook"), grappling_item);
    grappling.shape(new String[] { " IA", "ISI", "FI " });
    grappling.setIngredient('I', Material.IRON_INGOT);
    grappling.setIngredient('A', Material.ARROW);
    grappling.setIngredient('S', Material.SLIME_BALL);
    grappling.setIngredient('F', Material.FISHING_ROD);
    getServer().addRecipe((Recipe)grappling);
    ItemStack light_bow_item = ItemBuilder.build(Item.LIGHT_BOW);
    ShapedRecipe light_bow = new ShapedRecipe(new NamespacedKey((Plugin)this, "light-bow"), light_bow_item);
    light_bow.shape(new String[] { "ENG", "BAN", "IBE" });
    light_bow.setIngredient('E', Material.END_ROD);
    light_bow.setIngredient('N', Material.NETHERITE_SCRAP);
    light_bow.setIngredient('G', Material.GLOWSTONE);
    light_bow.setIngredient('B', Material.GLOW_BERRIES);
    light_bow.setIngredient('A', Material.BOW);
    light_bow.setIngredient('I', Material.IRON_BLOCK);
    getServer().addRecipe((Recipe)light_bow);
    ItemStack dark_bow_item = ItemBuilder.build(Item.DARK_BOW);
    ShapedRecipe dark_bow = new ShapedRecipe(new NamespacedKey((Plugin)this, "dark-bow"), dark_bow_item);
    dark_bow.shape(new String[] { "ENG", "BAN", "IBE" });
    dark_bow.setIngredient('E', Material.SHULKER_SHELL);
    dark_bow.setIngredient('N', Material.NETHERITE_SCRAP);
    dark_bow.setIngredient('G', Material.WITHER_SKELETON_SKULL);
    dark_bow.setIngredient('B', Material.CHORUS_FRUIT);
    dark_bow.setIngredient('A', Material.BOW);
    dark_bow.setIngredient('I', Material.IRON_BLOCK);
    getServer().addRecipe((Recipe)dark_bow);
    ItemStack ice_bow_item = ItemBuilder.build(Item.ICE_BOW);
    ShapedRecipe ice_bow = new ShapedRecipe(new NamespacedKey((Plugin)this, "ice-bow"), ice_bow_item);
    ice_bow.shape(new String[] { "ENG", "BAN", "IBE" });
    ice_bow.setIngredient('E', Material.SNOWBALL);
    ice_bow.setIngredient('N', Material.NETHERITE_SCRAP);
    ice_bow.setIngredient('G', Material.PACKED_ICE);
    ice_bow.setIngredient('B', Material.SNOW_BLOCK);
    ice_bow.setIngredient('A', Material.BOW);
    ice_bow.setIngredient('I', Material.IRON_BLOCK);
    getServer().addRecipe((Recipe)ice_bow);
    ItemStack electric_bow_item = ItemBuilder.build(Item.ELECTRIC_BOW);
    ShapedRecipe electric_bow = new ShapedRecipe(new NamespacedKey((Plugin)this, "electric-bow"), electric_bow_item);
    electric_bow.shape(new String[] { "ENB", "BAN", "IBE" });
    electric_bow.setIngredient('E', Material.LIGHTNING_ROD);
    electric_bow.setIngredient('N', Material.NETHERITE_SCRAP);
    electric_bow.setIngredient('B', Material.RAW_GOLD_BLOCK);
    electric_bow.setIngredient('A', Material.BOW);
    electric_bow.setIngredient('I', Material.IRON_BLOCK);
    getServer().addRecipe((Recipe)electric_bow);
    ItemStack fire_bow_item = ItemBuilder.build(Item.FIRE_BOW);
    ShapedRecipe fire_bow = new ShapedRecipe(new NamespacedKey((Plugin)this, "fire-bow"), fire_bow_item);
    fire_bow.shape(new String[] { "ENG", "BAN", "IBE" });
    fire_bow.setIngredient('E', Material.BLAZE_ROD);
    fire_bow.setIngredient('N', Material.NETHERITE_SCRAP);
    fire_bow.setIngredient('G', Material.CRYING_OBSIDIAN);
    fire_bow.setIngredient('B', Material.FIRE_CHARGE);
    fire_bow.setIngredient('A', Material.BOW);
    fire_bow.setIngredient('I', Material.IRON_BLOCK);
    getServer().addRecipe((Recipe)fire_bow);
    ItemStack heart_container = ItemBuilder.build(Item.HEART_CONTAINER);
    ShapedRecipe heart_container_recipe_1 = new ShapedRecipe(new NamespacedKey((Plugin)this, "heart-container-1"), heart_container);
    ShapedRecipe heart_container_recipe_2 = new ShapedRecipe(new NamespacedKey((Plugin)this, "heart-container-2"), heart_container);
    ShapedRecipe heart_container_recipe_3 = new ShapedRecipe(new NamespacedKey((Plugin)this, "heart-container-3"), heart_container);
    ShapedRecipe heart_container_recipe_4 = new ShapedRecipe(new NamespacedKey((Plugin)this, "heart-container-4"), heart_container);
    heart_container_recipe_1.shape(new String[] { "   ", " PP", " PP" });
    heart_container_recipe_1.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
    heart_container_recipe_2.shape(new String[] { "   ", "PP ", "PP " });
    heart_container_recipe_2.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
    heart_container_recipe_3.shape(new String[] { "PP ", "PP ", "   " });
    heart_container_recipe_3.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
    heart_container_recipe_4.shape(new String[] { " PP", " PP", "   " });
    heart_container_recipe_4.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
    getServer().addRecipe((Recipe)heart_container_recipe_1);
    getServer().addRecipe((Recipe)heart_container_recipe_2);
    getServer().addRecipe((Recipe)heart_container_recipe_3);
    getServer().addRecipe((Recipe)heart_container_recipe_4);
    ItemStack shadow_crystal = ItemBuilder.build(Item.SHADOW_CRYSTAL);
    ShapedRecipe shadow_crystal_recipe = new ShapedRecipe(new NamespacedKey((Plugin)this, "shadow-crystal"), shadow_crystal);
    shadow_crystal_recipe.shape(new String[] { "DOD", "CSC", "POP" });
    shadow_crystal_recipe.setIngredient('D', Material.ORANGE_DYE);
    shadow_crystal_recipe.setIngredient('O', Material.CRYING_OBSIDIAN);
    shadow_crystal_recipe.setIngredient('C', Material.END_CRYSTAL);
    shadow_crystal_recipe.setIngredient('S', Material.NETHER_STAR);
    shadow_crystal_recipe.setIngredient('P', Material.ENDER_PEARL);
    getServer().addRecipe((Recipe)shadow_crystal_recipe);
    listener = new Listener();
    getServer().getPluginManager().registerEvents(listener, (Plugin)this);
    Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
    for (Player player : players) {
      PlayerData PData = PlayerData.getData(player);
      PData.register();
      PacketReader reader = new PacketReader();
      reader.inject(player);
    } 
    getCommand("playerdata").setExecutor((CommandExecutor)new PlayerDataCommand());
    getCommand("playerdata").setTabCompleter((TabCompleter)new PlayerDataCommandCompleter());
    getCommand("rings").setExecutor((CommandExecutor)new RingCommand());
    getCommand("spawnnpc").setExecutor((CommandExecutor)new SpawnNPCCommand());
    getCommand("spawnnpc").setTabCompleter((TabCompleter)new SpawnNPCCommandCompleter());
    getCommand("exchange").setExecutor((CommandExecutor)new ExchangeCommand());
    getCommand("givecustom").setExecutor((CommandExecutor)new ItemCommand());
    getCommand("givecustom").setTabCompleter((TabCompleter)new ItemCommandCompleter());
    getCommand("drop").setExecutor((CommandExecutor)new DropCommand());
    getCommand("drop").setTabCompleter((TabCompleter)new DropCommandCompleter());
    getCommand("writestruc").setExecutor((CommandExecutor)new StructureWriteCommand());
    getCommand("writestruc").setTabCompleter((TabCompleter)new StructureWriteCommandCompleter());
    getCommand("buildstruc").setExecutor((CommandExecutor)new StructureBuildCommand());
    getCommand("updatestruc").setExecutor((CommandExecutor)new StructureUpdateCommand());
    getCommand("clearinfluence").setExecutor((CommandExecutor)new ClearInfluenceCommand());
    getCommand("refreshchunks").setExecutor((CommandExecutor)new RefreshChunksCommand());
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
                  if (memories != null && 
                    !memories.isEmpty())
                    for (NPCMemory memory : memories) {
                      CustomNPC brain = (CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory);
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
        if (((Boolean)Listener.chargingNPCs.get(p)).booleanValue()) {
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
      }).runTaskTimer((Plugin)getPlugin(Main.class), 0L, 18000L);
    (new BukkitRunnable() {
        public void run() {
          TerritoryUtil.sendPlayerPositions();
        }
      }).runTaskTimer((Plugin)getPlugin(Main.class), 0L, 50L);
    (new BukkitRunnable() {
        public void run() {
          (new Thread(new Runnable() {
                public void run() {
                  TerritoryUtil.updatePlayerNearbyChunks();
                }
              })).start();
        }
      }).runTaskTimer((Plugin)getPlugin(Main.class), 0L, 300L);
  }
  
  public void onDisable() {
    this.isRunning = false;
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
            CustomNPC brain = (CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory);
            List<EntityPlayer> npcs = NPCHandler.getShells(brain);
            if (npcs != null)
              for (EntityPlayer npc : npcs) {
                PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { npc.ae() });
                Player player = NPCHandler.getPlayerFromShell(npc);
                CraftPlayer cp = (CraftPlayer)player;
                (cp.getHandle()).b.a((Packet<?>)packet);
                PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { brain.ae() });
                (cp.getHandle()).b.a((Packet<?>)packet2);
              }  
            brain.ah();
          }  
      } 
    } 
    for (String key : Listener.hooks.keySet()) {
      Arrow arr = Listener.hooks.get(key);
      for (Entity ent : arr.getPassengers())
        ent.remove(); 
      arr.remove();
    } 
    write("npc/list", NPCMemory.NPCMemories);
    StableMemory.saveAll();
    for (UUID uuid : StableMemory.Stables.keySet()) {
      StableMemory stable = (StableMemory)StableMemory.Stables.get(uuid);
      stable.save();
    } 
  }
  
  public static void write(String fileName, Object object) {
	    Path path = Paths.get((getPlugin(Main.class)).getDataFolder() + "/" + fileName + ".json", new String[0]);
	    try {
	    	if (!Files.exists(path, new java.nio.file.LinkOption[0])) {
	    		Files.createDirectories(path.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
	    		Files.createFile(path, (FileAttribute<?>[])new FileAttribute[0]);
	    	} 
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } 
	    try (Writer writer = new FileWriter(path.toString())){
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
				Files.createDirectories(path.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
				Files.createFile(path, (FileAttribute<?>[])new FileAttribute[0]);
			} 
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } 
	    try {
	    	BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
	    	JsonReader jsonReader = new JsonReader(bufferedReader);
	    	return (T)GSON.fromJson(jsonReader, type);
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

