package fr.yezzipe.zelda.entity.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.MineskinUtil;
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.territory.Waypoint;
import fr.yezzipe.zelda.territory.structures.StableMemory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutEntityTeleport;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.player.EntityHuman;
//import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.World;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class NPCHandler {
    private static List<CustomNPC> NPC = new ArrayList<>();

    private static HashMap<UUID, CustomNPC> NPCShellToBrain = new HashMap<>();

    public static HashMap<CustomNPC, NPCMemory> NPCMemories = new HashMap<>();

    public static HashMap<NPCMemory, CustomNPC> NPCMemoryToBrain = new HashMap<>();

    private static HashMap<CustomNPC, List<EntityPlayer>> NPCBrainToShells = new HashMap<>();

    private static HashMap<EntityPlayer, Player> NPCShellToPlayer = new HashMap<>();

    private static HashMap<Player, List<EntityPlayer>> NPCPlayerToVisibleShells = new HashMap<>();

    private static HashMap<Player, List<EntityPlayer>> NPCPlayerToHiddenShells = new HashMap<>();

    private static HashMap<EntityPlayer, GameProfile> NPCShellToGameProfile = new HashMap<>();

    private static HashMap<String, List<NPCMemory>> NPCChunkToLoadedMemory = new HashMap<>();

    private static HashMap<String, List<NPCMemory>> NPCChunkToUnloadedMemory = new HashMap<>();

    private static HashMap<NPCMemory, String> NPCMemoryToChunk = new HashMap<>();

    public static NPCMemory createNPCMemory(Waypoint waypoint, String name, String skin) {
	NPCMemory memory = new NPCMemory(waypoint, name, skin);
	waypoint.getLocation().getChunk().setForceLoaded(true);
	waypoint.getLocation().getChunk().load();
	memory.register();
	Location l = waypoint.getLocation();
	List<NPCMemory> memories = NPCChunkToUnloadedMemory.get(getChunkKey(l.getChunk()));
	if (memories == null)
	    memories = new ArrayList<>();
	memories.add(memory);
	NPCChunkToUnloadedMemory.put(getChunkKey(l.getChunk()), memories);
	NPCMemoryToChunk.put(memory, getChunkKey(l.getChunk()));
	return memory;
    }

    public static void instantiateNPCMemory(NPCMemory memory) {
	Waypoint waypoint = memory.getWaypoint();
	waypoint.getLocation().getChunk().setForceLoaded(true);
	waypoint.getLocation().getChunk().load();
	memory.register();
	Location l = waypoint.getLocation();
	List<NPCMemory> memories = NPCChunkToUnloadedMemory.get(getChunkKey(l.getChunk()));
	if (memories == null)
	    memories = new ArrayList<>();
	memories.add(memory);
	NPCChunkToUnloadedMemory.put(getChunkKey(l.getChunk()), memories);
	NPCMemoryToChunk.put(memory, getChunkKey(l.getChunk()));
    }

    public static List<CustomNPC> mountBrains(final Chunk chunk) {
	List<CustomNPC> npcBrains = new ArrayList<>();
	List<NPCMemory> memories = NPCChunkToUnloadedMemory.get(getChunkKey(chunk));
	if (memories != null) {
	    for (NPCMemory memory : memories) {
		final CustomNPC npcBrain;
		Waypoint waypoint = memory.getWaypoint();
		WorldServer world = ((CraftWorld) waypoint.getWorld()).getHandle();
		waypoint.getLocation().getChunk().setForceLoaded(true);
		waypoint.getLocation().getChunk().load();
		memory.register();
		if (memory.hasStable()) {
		    if (memory.isMainNPC()) {
			npcBrain = CustomNPC.CustomStableNPC(EntityTypes.aZ, (World) world, (Location) null);
		    } else {
			StableMemory stable = memory.getStable();
			Location origin = new Location(waypoint.getWorld(), stable.getCenterx(), stable.getCentery(),
				stable.getCenterz());
			npcBrain = CustomNPC.CustomGuardNPC(EntityTypes.aZ, (World) world, origin);
		    }
		} else {
		    npcBrain = CustomNPC.CustomStableNPC(EntityTypes.aZ, (World) world, (Location) null);
		}
		NPCMemories.put(npcBrain, memory);
		NPCMemoryToBrain.put(memory, npcBrain);
		Location l = waypoint.getLocation();
		npcBrain.b(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
		npcBrain.j(true);
		npcBrain.m(true);
		npcBrain.d(true);
		((CraftWorld) waypoint.getWorld()).getHandle().b((Entity) npcBrain);
		NPC.add(npcBrain);
		(new BukkitRunnable() {
		    public void run() {
			final Villager villager = (Villager) Bukkit.getEntity(npcBrain.co());
			if (villager != null) {
			    villager.setSilent(true);
			    villager.setInvulnerable(true);
			    villager.setInvisible(true);
			    villager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(10000.0D);
			    villager.setCollidable(false);
			    EntityManager.noCollisionHandler.addEntity(villager.getUniqueId());
			    (new BukkitRunnable() {
				public void run() {
				    if (!((List<NPCMemory>) NPCHandler.NPCChunkToLoadedMemory
					    .get(NPCHandler.getChunkKey(chunk))).contains(memory)) {
					cancel();
					return;
				    }
				    double x = villager.getLocation().getX();
				    double y = villager.getLocation().getY();
				    double z = villager.getLocation().getZ();
				    Waypoint w = memory.getWaypoint();
				    if (x != w.getX() || y != w.getY() || z != w.getZ()) {
					double diffx = x - w.getX();
					double diffy = y - w.getY();
					double diffz = z - w.getZ();
					w.setX(x);
					w.setY(y);
					w.setZ(z);
					NPCHandler.npcMove(npcBrain, diffx, diffy, diffz);
				    } else {
					NPCHandler.update(npcBrain);
				    }
				}
			    }).runTaskTimerAsynchronously((Plugin) Main.getPlugin(Main.class), 0L, 0L);
			    cancel();
			}
		    }
		}).runTaskTimer((Plugin) Main.getPlugin(Main.class), 10L, 10L);
		waypoint.getLocation().getChunk().setForceLoaded(false);
		npcBrains.add(npcBrain);
	    }
	    for (CustomNPC brain : npcBrains) {
		final NPCMemory memory = NPCMemories.get(brain);
		List<NPCMemory> list = NPCChunkToLoadedMemory.get(getChunkKey(chunk));
		if (list == null)
		    list = new ArrayList<>();
		if (!list.contains(memory))
		    list.add(memory);
		NPCChunkToLoadedMemory.put(getChunkKey(chunk), list);
		List<NPCMemory> list2 = NPCChunkToUnloadedMemory.get(getChunkKey(chunk));
		if (list2.contains(memory))
		    list2.remove(memory);
		NPCChunkToUnloadedMemory.put(getChunkKey(chunk), list2);
	    }
	}
	return npcBrains;
    }

    public static EntityPlayer createNPCShell(Player p, CustomNPC npcBrain) {
	DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
	Waypoint waypoint = ((NPCMemory) NPCMemories.get(npcBrain)).getWaypoint();
	String name = ((NPCMemory) NPCMemories.get(npcBrain)).getName();
	WorldServer world = ((CraftWorld) waypoint.getWorld()).getHandle();
	GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
	EntityPlayer npc = new EntityPlayer((MinecraftServer) dedicatedServer, world, gameProfile, null);
	Location l = waypoint.getLocation();
	npc.b(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());
	NPCShellToBrain.put(npc.co(), npcBrain);
	List<EntityPlayer> shells = NPCBrainToShells.get(npcBrain);
	if (shells == null)
	    shells = new ArrayList<>();
	shells.add(npc);
	NPCShellToPlayer.put(npc, p);
	List<EntityPlayer> shells2 = NPCPlayerToHiddenShells.get(p);
	if (shells2 == null || shells2.isEmpty())
	    shells2 = new ArrayList<>();
	NPCShellToGameProfile.put(npc, gameProfile);
	shells2.add(npc);
	NPCPlayerToHiddenShells.put(p, shells2);
	NPCBrainToShells.put(npcBrain, shells);
	return npc;
    }

    public static void showNPCs(Player p) {
	List<EntityPlayer> npcs = new ArrayList<>(
		(NPCPlayerToHiddenShells.get(p) == null) ? new ArrayList<>() : NPCPlayerToHiddenShells.get(p));
	List<EntityPlayer> toShow = new ArrayList<>();
	List<EntityPlayer> visibleNpcs = new ArrayList<>(
		(NPCPlayerToVisibleShells.get(p) == null) ? new ArrayList<>() : NPCPlayerToVisibleShells.get(p));
	if (npcs != null)
	    for (EntityPlayer npc : npcs) {
		CustomNPC brain = NPCShellToBrain.get(npc.co());
		NPCMemory memory = NPCMemories.get(brain);
		if (memory != null) {
		    Waypoint waypoint = memory.getWaypoint();
		    if (waypoint.getLocation().distance(p.getLocation()) <= 50.0D) {
			String skin = ((NPCMemory) NPCMemories.get(brain)).getSkin();
			String[] textures = MineskinUtil.getSkin(skin);
			Location l = waypoint.getLocation();
			npc.o(l.getX(), l.getY(), l.getZ());
			GameProfile gameProfile = NPCShellToGameProfile.get(npc);
			if (textures[0] != null && textures[1] != null) {
			    gameProfile.getProperties().put("textures",
				    new Property("textures", textures[0], textures[1]));
			    DataWatcher watcher = new DataWatcher((Entity) npc);
			    watcher.a(new DataWatcherObject<>(17, DataWatcherRegistry.a), Byte.valueOf((byte) 127));
			    PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
			    conn.a((Packet<?>) new PacketPlayOutPlayerInfo(
				    PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, new EntityPlayer[] { npc }));
			    conn.a((Packet<?>) new PacketPlayOutNamedEntitySpawn((EntityHuman) npc));
			    conn.a((Packet<?>) new PacketPlayOutEntityTeleport((Entity) npc));
			    conn.a((Packet<?>) new PacketPlayOutEntityHeadRotation((Entity) npc,
				    (byte) (int) (l.getYaw() * 256.0F / 360.0F)));
			    conn.a((Packet<?>) new PacketPlayOutEntityMetadata(npc.ae(), watcher, true));
			    ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
			    Pair<EnumItemSlot, net.minecraft.world.item.ItemStack> pair = new Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>(
				    EnumItemSlot.a, CraftItemStack.asNMSCopy(item));
			    List<Pair<EnumItemSlot, net.minecraft.world.item.ItemStack>> items = new ArrayList<>();
			    items.add(pair);
			    System.out.println("bababoi");
			    conn.a((Packet<?>) new PacketPlayOutEntityEquipment(npc.ae(), items));
			    toShow.add(npc);
			}
		    }
		}
	    }
	for (EntityPlayer npc : toShow) {
	    npcs.remove(npc);
	    visibleNpcs.add(npc);
	}
	NPCPlayerToHiddenShells.put(p, npcs);
	NPCPlayerToVisibleShells.put(p, visibleNpcs);
    }

    public static void hideNPCs(Player p) {
	PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
	for (EntityPlayer npc : NPCPlayerToVisibleShells.get(p)) {
	    conn.a((Packet<?>) new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e,
		    new EntityPlayer[] { npc }));
	}
    }

    public static void showNPC(Player p, EntityPlayer npc) {
	CustomNPC brain = NPCShellToBrain.get(npc.co());
	Waypoint waypoint = ((NPCMemory) NPCMemories.get(brain)).getWaypoint();
	String skin = ((NPCMemory) NPCMemories.get(brain)).getSkin();
	String[] textures = MineskinUtil.getSkin(skin);
	Location l = waypoint.getLocation();
	npc.o(l.getX(), l.getY(), l.getZ());
	GameProfile gameProfile = NPCShellToGameProfile.get(npc);
	if (waypoint.getLocation().distance(p.getLocation()) <= 50.0D && textures[0] != null && textures[1] != null) {
	    gameProfile.getProperties().put("textures", new Property("textures", textures[0], textures[1]));
	    DataWatcher watcher = new DataWatcher((Entity) npc);
	    watcher.a(new DataWatcherObject<>(17, DataWatcherRegistry.a), Byte.valueOf((byte) 127));
	    PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
	    conn.a((Packet<?>) new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a,
		    new EntityPlayer[] { npc }));
	    conn.a((Packet<?>) new PacketPlayOutNamedEntitySpawn((EntityHuman) npc));
	    conn.a((Packet<?>) new PacketPlayOutEntityTeleport((Entity) npc));
	    conn.a((Packet<?>) new PacketPlayOutEntityHeadRotation((Entity) npc,
		    (byte) (int) (l.getYaw() * 256.0F / 360.0F)));
	    conn.a((Packet<?>) new PacketPlayOutEntityMetadata(npc.ae(), watcher, true));
	    List<EntityPlayer> hiddenShells = NPCPlayerToHiddenShells.get(p);
	    List<EntityPlayer> visibleShells = NPCPlayerToVisibleShells.get(p);
	    if (hiddenShells.contains(npc)) {
		hiddenShells.remove(npc);
		NPCPlayerToHiddenShells.put(p, hiddenShells);
	    }
	    if (visibleShells == null)
		visibleShells = new ArrayList<>();
	    if (!visibleShells.contains(npc)) {
		visibleShells.add(npc);
		NPCPlayerToVisibleShells.put(p, visibleShells);
	    }
	}
    }

    public static void update(CustomNPC npcBrain) {
	List<EntityPlayer> npcs = NPCBrainToShells.get(npcBrain);
	if (npcs != null)
	    for (EntityPlayer npc : npcs) {
		float pitch = npcBrain.getBukkitEntity().getLocation().getPitch();
		float yaw = npcBrain.getBukkitEntity().getLocation().getYaw();
		Player p = NPCShellToPlayer.get(npc);
		if (p == null)
		    return;
		PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
		conn.a((Packet<?>) new PacketPlayOutEntityHeadRotation((Entity) npc,
			(byte) (int) (yaw % 360.0F * 255.0F / 360.0F)));
		conn.a((Packet<?>) new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.ae(),
			(byte) (int) (yaw % 360.0F * 255.0F / 360.0F), (byte) (int) (pitch % 360.0F * 255.0F / 360.0F),
			true));
	    }
    }

    public static void npcMove(CustomNPC npcBrain, double x, double y, double z) {
	List<EntityPlayer> npcs = NPCBrainToShells.get(npcBrain);
	if (npcs != null)
	    for (EntityPlayer npc : npcs) {
		float pitch = npcBrain.getBukkitEntity().getLocation().getPitch();
		float yaw = npcBrain.getBukkitEntity().getLocation().getYaw();
		Player p = NPCShellToPlayer.get(npc);
		if (p == null)
		    return;
		PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
		conn.a((Packet<?>) new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(npc.ae(),
			(short) (int) (x * 4096.0D), (short) (int) (y * 4096.0D), (short) (int) (z * 4096.0D),
			(byte) (int) (yaw % 360.0F * 255.0F / 360.0F), (byte) (int) (pitch % 360.0F * 255.0F / 360.0F),
			true));
		conn.a((Packet<?>) new PacketPlayOutEntityHeadRotation((Entity) npc,
			(byte) (int) (yaw % 360.0F * 255.0F / 360.0F)));
	    }
    }

    public static List<CustomNPC> getNPCs() {
	return NPC;
    }

    public static List<EntityPlayer> getShells(CustomNPC villager) {
	return NPCBrainToShells.get(villager);
    }

    public static Player getPlayerFromShell(EntityPlayer npc) {
	return NPCShellToPlayer.get(npc);
    }

    public static void createNPCs(Player p) {
	for (CustomNPC npcBrain : NPC)
	    createNPCShell(p, npcBrain);
    }

    public static void hideNPC(Player p, EntityPlayer npc) {
	PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
	CustomNPC brain = NPCShellToBrain.get(npc.co());
	PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { brain.ae() });
	conn.a((Packet<?>) new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e,
		new EntityPlayer[] { npc }));
	conn.a((Packet<?>) packet2);
    }

    public static void hideNPCBrain(Player p, CustomNPC brain) {
	PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
	PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { brain.ae() });
	conn.a((Packet<?>) packet);
    }

    public static void hideNPCBrains(Player p) {
	PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
	List<NPCMemory> memories = NPCChunkToLoadedMemory.get(getChunkKey(p.getLocation().getChunk()));
	if (memories != null)
	    for (NPCMemory memory : memories) {
		CustomNPC brain = NPCMemoryToBrain.get(memory);
		PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { brain.ae() });
		conn.a((Packet<?>) packet2);
	    }
    }

    public static List<NPCMemory> getLoadedNPCsInChunk(Chunk chunk) {
	return NPCChunkToLoadedMemory.get(getChunkKey(chunk));
    }

    public static boolean allNPCLoaded(Player p) {
	List<EntityPlayer> npcs = NPCPlayerToHiddenShells.get(p);
	if (npcs == null)
	    return true;
	if (npcs.isEmpty())
	    return true;
	return false;
    }

    public static void removeNPC(final CustomNPC brain, Player p, Chunk chunk) {
	System.out.println("removeNPC");
	EntityPlayer shell = null;
	List<EntityPlayer> shells = NPCBrainToShells.get(brain);
	for (EntityPlayer s : shells) {
	    if (((Player) NPCShellToPlayer.get(s)).equals(p))
		shell = s;
	}
	NPCShellToBrain.remove(shell.co());
	shells.remove(shell);
	NPCBrainToShells.put(brain, shells);
	NPCShellToPlayer.remove(shell);
	List<EntityPlayer> visibleShells = NPCPlayerToVisibleShells.get(p);
	if (visibleShells != null) {
	    if (visibleShells.contains(shell))
		visibleShells.remove(shell);
	} else {
	    visibleShells = new ArrayList<>();
	}
	NPCPlayerToVisibleShells.put(p, visibleShells);
	List<EntityPlayer> hiddenShells = NPCPlayerToHiddenShells.get(p);
	if (hiddenShells != null) {
	    if (hiddenShells.contains(shell))
		hiddenShells.remove(shell);
	} else {
	    hiddenShells = new ArrayList<>();
	}
	NPCPlayerToHiddenShells.put(p, hiddenShells);
	NPCShellToGameProfile.remove(shell);
	CraftPlayer cp = (CraftPlayer) p;
	PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { shell.ae() });
	PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { brain.ae() });
	(cp.getHandle()).b.a((Packet<?>) packet);
	(cp.getHandle()).b.a((Packet<?>) packet2);
	if (((List<EntityPlayer>) NPCBrainToShells.get(brain)).isEmpty())
	    NPCBrainToShells.remove(brain);
	NPCMemory memory = NPCMemories.get(brain);
	if (memory.hasStable())
	    if (memory.isMainNPC()) {
		List<CustomNPC> npcs = memory.getStable().getSecondaryNPCs();
		if (npcs != null) {
		    for (CustomNPC npc : npcs) {
			NPCMemory mem = NPCMemories.get(npc);
			mem.removeStable();
		    }
		    memory.getStable().unregister();
		}
	    } else {
		List<CustomNPC> npcs = memory.getStable().getSecondaryNPCs();
		if (npcs != null) {
		    npcs.remove(brain);
		    List<UUID> uuids = new ArrayList<>();
		    for (CustomNPC npc : npcs) {
			NPCMemory mem = NPCMemories.get(npc);
			uuids.add(mem.getUuid());
		    }
		    memory.getStable().setSecondaryNPCs(uuids);
		}
	    }
	NPC.remove(brain);
	NPCMemoryToBrain.remove(memory);
	memory.unregister();
	System.out.println("memory unregister");
	NPCMemories.remove(brain);
	List<NPCMemory> list = NPCChunkToLoadedMemory.get(getChunkKey(chunk));
	if (list.contains(memory))
	    list.remove(memory);
	NPCChunkToLoadedMemory.put(getChunkKey(chunk), list);
	List<NPCMemory> list2 = NPCChunkToUnloadedMemory.get(getChunkKey(chunk));
	if (list2.contains(memory))
	    list2.remove(memory);
	NPCMemoryToChunk.remove(memory);
	NPCChunkToUnloadedMemory.put(getChunkKey(chunk), list2);
	Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getPlugin(Main.class), new Runnable() {
	    public void run() {
		brain.ag();
	    }
	});
    }

    public static void dismountBrains(Chunk chunk) {
	List<NPCMemory> memories = NPCChunkToLoadedMemory.get(getChunkKey(chunk));
	if (memories != null)
	    for (NPCMemory memory : memories) {
		List<NPCMemory> list = NPCChunkToLoadedMemory.get(getChunkKey(chunk));
		if (list.contains(memory))
		    list.remove(memory);
		NPCChunkToLoadedMemory.put(getChunkKey(chunk), list);
		List<NPCMemory> list2 = NPCChunkToUnloadedMemory.get(getChunkKey(chunk));
		if (!list2.contains(memory))
		    list2.add(memory);
		NPCChunkToUnloadedMemory.put(getChunkKey(chunk), list2);
		CustomNPC brain = NPCMemoryToBrain.get(memory);
		List<EntityPlayer> shells = NPCBrainToShells.get(brain);
		if (shells != null)
		    for (EntityPlayer shell : shells) {
			Player p = NPCShellToPlayer.get(shell);
			CraftPlayer cp = (CraftPlayer) p;
			PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { shell.ae() });
			(cp.getHandle()).b.a((Packet<?>) packet);
			PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { brain.ae() });
			(cp.getHandle()).b.a((Packet<?>) packet2);
			NPCShellToPlayer.remove(shell);
			List<EntityPlayer> visibleShells = NPCPlayerToVisibleShells.get(p);
			if (visibleShells != null) {
			    if (visibleShells.contains(shell))
				visibleShells.remove(shell);
			} else {
			    visibleShells = new ArrayList<>();
			}
			NPCPlayerToVisibleShells.put(p, visibleShells);
			List<EntityPlayer> hiddenShells = NPCPlayerToHiddenShells.get(p);
			if (hiddenShells != null) {
			    if (hiddenShells.contains(shell))
				hiddenShells.remove(shell);
			} else {
			    hiddenShells = new ArrayList<>();
			}
			NPCPlayerToHiddenShells.put(p, hiddenShells);
			NPCShellToGameProfile.remove(shell);
		    }
		NPCBrainToShells.remove(brain);
		NPC.remove(brain);
		NPCMemoryToBrain.remove(memory);
		NPCMemories.remove(brain);
		brain.ag();
	    }
    }

    public static String getChunkKey(Chunk chunk) {
	return String.valueOf(Integer.toString(chunk.getX())) + ";" + Integer.toString(chunk.getZ());
    }

    public static Chunk getChunkFromBrain(CustomNPC brain) {
	NPCMemory memory = NPCMemories.get(brain);
	Waypoint w = memory.getWaypoint();
	String key = NPCMemoryToChunk.get(memory);
	return w.getWorld().getChunkAt(Integer.parseInt(key.split(";")[0]), Integer.parseInt(key.split(";")[1]));
    }

    public static void swingMainHand(CustomNPC brain) {
	List<EntityPlayer> npcs = NPCBrainToShells.get(brain);
	if (npcs != null)
	    for (EntityPlayer npc : npcs) {
		Player p = NPCShellToPlayer.get(npc);
		PlayerConnection conn = (((CraftPlayer) p).getHandle()).b;
		conn.a((Packet<?>) new PacketPlayOutAnimation((Entity) npc, 0));
		p.playSound((org.bukkit.entity.Entity) npc.getBukkitEntity(), Sound.ENTITY_PLAYER_ATTACK_SWEEP,
			SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    }
    }
}
