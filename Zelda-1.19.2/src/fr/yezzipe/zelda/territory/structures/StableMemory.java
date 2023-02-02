package fr.yezzipe.zelda.territory.structures;

import de.tr7zw.nbtapi.NBTBlock;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.territory.HTTPChunk;
import fr.yezzipe.zelda.territory.TerritoryChunk;
import fr.yezzipe.zelda.territory.TerritoryUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class StableMemory {
  public static HashMap<UUID, StableMemory> Stables = new HashMap<>();
  
  private static String folderPrefix = "stables/";
  
  private static Queue<Thread> InfluenceTask = new LinkedList<>();
  
  private int startx;
  
  private int starty;
  
  private int startz;
  
  private int sizex;
  
  private int sizey;
  
  private int sizez;
  
  private int centerx;
  
  private int centery;
  
  private int centerz;
  
  private final UUID uuid;
  
  private UUID mainNPC;
  
  private List<UUID> secondaryNPCs;
  
  private final UUID ownerUUID;
  
  private final Biome biome;
  
  private int rupees;
  
  private StructureType type;
  
  private final Race race;
  
  private final BlockFace direction;
  
  private final String world;
  
  public static List<StableMemory> getAllStables() {
    List<StableMemory> stables = new ArrayList<>();
    for (UUID uuid : Stables.keySet())
      stables.add(Stables.get(uuid)); 
    return stables;
  }
  
  private List<UUID> discoveredPlayers = new ArrayList<>();
  
  public StableMemory(int startx, int starty, int startz, int sizex, int sizey, int sizez, Player player, StructureType type, Biome biome, BlockFace direction, World world) {
    this.startx = startx;
    this.starty = starty;
    this.startz = startz;
    this.sizex = sizex;
    this.sizey = sizey;
    this.sizez = sizez;
    this.centerx = startx + (sizex - 1) / 2;
    this.centery = starty + (sizey - 1) / 2;
    this.centerz = startz + (sizez - 1) / 2;
    this.uuid = UUID.randomUUID();
    this.ownerUUID = player.getUniqueId();
    this.rupees = 0;
    this.type = type;
    this.biome = biome;
    this.direction = direction;
    this.world = world.getName();
    PlayerData PData = PlayerData.getData(player);
    this.race = PData.getCurrentRace();
    PData.discoverStable(this);
    final Chunk centerChunk = world.getBlockAt(this.centerx, this.centery, this.centerz).getChunk();
    final int x = centerChunk.getX();
    final int z = centerChunk.getZ();
    Thread thread = new Thread(new Runnable() {
          public void run() {
            List<TerritoryChunk> chunks = new ArrayList<>();
            for (int i = -7; i < 8; i++) {
              for (int j = -7; j < 8; j++) {
                Chunk c = centerChunk.getWorld().getChunkAt(x + i, z + j);
                TerritoryChunk t = new TerritoryChunk(c);
                t.addInfluence(StableMemory.this.race);
                chunks.add(t);
              } 
            } 
            List<HTTPChunk> list = new ArrayList<>();
            for (TerritoryChunk t : chunks) {
              HTTPChunk h = new HTTPChunk(t);
              list.add(h);
            } 
            TerritoryUtil.sendMultipleChunks(list);
            if (!StableMemory.InfluenceTask.isEmpty()) {
              Thread nextThread = StableMemory.InfluenceTask.remove();
              nextThread.start();
            } 
          }
        });
    if (InfluenceTask.isEmpty()) {
      thread.start();
    } else {
      InfluenceTask.add(thread);
    } 
    register();
  }
  
  public void register() {
    Stables.put(this.uuid, this);
  }
  
  public void unregister() {
    Stables.remove(this.uuid);
    final Chunk centerChunk = Bukkit.getWorld(this.world).getBlockAt(this.centerx, this.centery, this.centerz).getChunk();
    final int x = centerChunk.getX();
    final int z = centerChunk.getZ();
    Main.delete(String.valueOf(folderPrefix) + this.uuid.toString());
    World world = Bukkit.getWorld(this.world);
    for (int i = 0; i < this.sizey; i++) {
      for (int j = 0; j < this.sizex; j++) {
        for (int k = 0; k < this.sizez; k++) {
          Block block = world.getBlockAt(j + this.startx, i + this.starty, k + this.startz);
          if (block.getType() != Material.AIR) {
            NBTBlock nbt = new NBTBlock(block);
            if (nbt.getData().getKeys().contains("hasStable"))
              nbt.getData().removeKey("hasStable"); 
          } 
        } 
      } 
    } 
    for (UUID pUUID : this.discoveredPlayers) {
      Player p = Bukkit.getPlayer(pUUID);
      if (p != null) {
    	PlayerData PData = PlayerData.getData(p);
    	PData.removeStable(this);
        continue;
      } 
      OfflinePlayer oP = Bukkit.getOfflinePlayer(pUUID);
      if (oP != null) {
        PlayerData PData = PlayerData.getData(oP);
        PData.removeStable(this);
      } 
    } 
    Thread thread = new Thread(new Runnable() {
          public void run() {
            List<TerritoryChunk> chunks = new ArrayList<>();
            for (int i = -7; i < 8; i++) {
              for (int j = -7; j < 8; j++) {
                Chunk c = centerChunk.getWorld().getChunkAt(x + i, z + j);
                TerritoryChunk t = new TerritoryChunk(c);
                t.removeInfluence(StableMemory.this.race);
                chunks.add(t);
              } 
            } 
            List<HTTPChunk> list = new ArrayList<>();
            for (TerritoryChunk t : chunks) {
              HTTPChunk h = new HTTPChunk(t);
              list.add(h);
            } 
            TerritoryUtil.sendMultipleChunks(list);
            if (!StableMemory.InfluenceTask.isEmpty()) {
              Thread nextThread = StableMemory.InfluenceTask.remove();
              nextThread.start();
            } 
          }
        });
    if (InfluenceTask.isEmpty()) {
      thread.start();
    } else {
      InfluenceTask.add(thread);
    } 
  }
  
  public void save() {
    Main.write(String.valueOf(folderPrefix) + this.uuid.toString(), this);
  }
  
  public int getStartx() {
    return this.startx;
  }
  
  public void setStartx(int startx) {
    this.startx = startx;
  }
  
  public int getStarty() {
    return this.starty;
  }
  
  public void setStarty(int starty) {
    this.starty = starty;
  }
  
  public int getStartz() {
    return this.startz;
  }
  
  public void setStartz(int startz) {
    this.startz = startz;
  }
  
  public int getSizex() {
    return this.sizex;
  }
  
  public void setSizex(int sizex) {
    this.sizex = sizex;
  }
  
  public int getSizey() {
    return this.sizey;
  }
  
  public void setSizey(int sizey) {
    this.sizey = sizey;
  }
  
  public int getSizez() {
    return this.sizez;
  }
  
  public void setSizez(int sizez) {
    this.sizez = sizez;
  }
  
  public int getCenterx() {
    return this.centerx;
  }
  
  public void setCenterx(int centerx) {
    this.centerx = centerx;
  }
  
  public int getCentery() {
    return this.centery;
  }
  
  public void setCentery(int centery) {
    this.centery = centery;
  }
  
  public int getCenterz() {
    return this.centerz;
  }
  
  public void setCenterz(int centerz) {
    this.centerz = centerz;
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
  
  public CustomNPC getMainNPC() {
    NPCMemory memory = (NPCMemory)NPCMemory.NPCUUIDToMemory.get(this.mainNPC);
    CustomNPC brain = (CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory);
    return brain;
  }
  
  public NPCMemory getMainNPCMemory() {
    NPCMemory memory = (NPCMemory)NPCMemory.NPCUUIDToMemory.get(this.mainNPC);
    return memory;
  }
  
  public void setMainNPC(UUID mainNPC) {
    this.mainNPC = mainNPC;
  }
  
  public List<CustomNPC> getSecondaryNPCs() {
    List<NPCMemory> memories = new ArrayList<>();
    List<CustomNPC> brains = new ArrayList<>();
    for (UUID uuid : this.secondaryNPCs)
      memories.add((NPCMemory)NPCMemory.NPCUUIDToMemory.get(uuid)); 
    for (NPCMemory memory : memories)
      brains.add((CustomNPC)NPCHandler.NPCMemoryToBrain.get(memory)); 
    return brains;
  }
  
  public void setSecondaryNPCs(List<UUID> secondaryNPCs) {
    this.secondaryNPCs = secondaryNPCs;
  }
  
  public static void saveAll() {
    List<UUID> list = new ArrayList<>();
    for (UUID uuid : Stables.keySet())
      list.add(uuid); 
    Main.write(String.valueOf(folderPrefix) + "list", list);
  }
  
  public static void loadAll() {
    List<UUID> uuids = Main.read(String.valueOf(folderPrefix) + "list");
    if (uuids != null)
      for (UUID uuid : uuids)
        load(uuid);  
  }
  
  private static void load(UUID uuid2) {
    StableMemory stable = Main.read(String.valueOf(folderPrefix) + uuid2.toString());
    stable.register();
  }
  
  public int getRupees() {
    return this.rupees;
  }
  
  public void setRupees(int rupees) {
    this.rupees = rupees;
  }
  
  public UUID getOwnerUUID() {
    return this.ownerUUID;
  }
  
  public StructureType getType() {
    return this.type;
  }
  
  public boolean isOwner(Player p) {
    if (p.getUniqueId().equals(this.ownerUUID))
      return true; 
    return false;
  }
  
  public Biome getBiome() {
    return this.biome;
  }
  
  public BlockFace getDirection() {
    return this.direction;
  }
  
  public String getWorld() {
    return this.world;
  }
  
  public void teleport(Player p) {
    Location loc = new Location(Bukkit.getWorld(this.world), this.centerx + 0.5D, (this.starty + 1), this.centerz + 0.5D);
    if (this.direction == BlockFace.EAST)
      loc.setX(loc.getX() - 1.0D); 
    if (this.direction == BlockFace.WEST)
      loc.setX(loc.getX() + 1.0D); 
    if (this.direction == BlockFace.NORTH)
      loc.setZ(loc.getZ() + 1.0D); 
    if (this.direction == BlockFace.SOUTH)
      loc.setZ(loc.getZ() - 1.0D); 
    p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
  }
  
  public void addRupees(int i) {
    this.rupees += i;
  }
  
  public void removeRupees(int i) {
    this.rupees -= i;
  }
  
  public void addDiscoveredPlayer(Player p) {
    if (!this.discoveredPlayers.contains(p.getUniqueId()))
      this.discoveredPlayers.add(p.getUniqueId()); 
  }
  
  public void removeDiscoveredPlayer(Player p) {
    if (this.discoveredPlayers.contains(p.getUniqueId()))
      this.discoveredPlayers.remove(p.getUniqueId()); 
  }
  
  public void addSecondaryNPC(UUID uuid2) {
    if (this.secondaryNPCs == null)
      this.secondaryNPCs = new ArrayList<>(); 
    this.secondaryNPCs.add(uuid2);
  }
}
