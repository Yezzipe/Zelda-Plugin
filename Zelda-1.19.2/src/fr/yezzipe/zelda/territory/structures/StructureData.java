package fr.yezzipe.zelda.territory.structures;

import de.tr7zw.nbtapi.NBTBlock;
import fr.yezzipe.zelda.Listener;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import fr.yezzipe.zelda.entity.npc.NPCMemory;
import fr.yezzipe.zelda.territory.Waypoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Fence;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;

public class StructureData {
  private final Material[][][] blocks;
  
  private final BlockFace[][][] data;
  
  private final BlockFaces[][][] faces;
  
  private final Bed.Part[][][] bedparts;
  
  private final Stairs.Shape[][][] shapes;
  
  private final Bisected.Half[][][] halfs;
  
  private final int sizex;
  
  private final int sizey;
  
  private final int sizez;
  
  private StructureType type = StructureType.NONE;
  
  private static String folderPrefix = "structures/";
  
  public StructureData(Material[][][] matrix, BlockFace[][][] data, BlockFaces[][][] faces, Bed.Part[][][] bedparts, Stairs.Shape[][][] shapes, Bisected.Half[][][] halfs, int sizex, int sizey, int sizez) {
    this.blocks = matrix;
    this.data = data;
    this.faces = faces;
    this.bedparts = bedparts;
    this.sizex = sizex;
    this.sizey = sizey;
    this.sizez = sizez;
    this.shapes = shapes;
    this.halfs = halfs;
  }
  
  public void save(String name) {
    Main.write(String.valueOf(folderPrefix) + name, this);
  }
  
  public static void update(String name) {
    StructureData data1 = load(name, BlockFace.EAST);
    data1.setType((data1.getType() == null) ? StructureType.NONE : data1.getType());
    Main.write(String.valueOf(folderPrefix) + name + "/" + BlockFace.EAST.toString() + "/struc", data1);
    StructureData data2 = load(name, BlockFace.WEST);
    data2.setType((data2.getType() == null) ? StructureType.NONE : data2.getType());
    Main.write(String.valueOf(folderPrefix) + name + "/" + BlockFace.WEST.toString() + "/struc", data2);
    StructureData data3 = load(name, BlockFace.SOUTH);
    data3.setType((data3.getType() == null) ? StructureType.NONE : data3.getType());
    Main.write(String.valueOf(folderPrefix) + name + "/" + BlockFace.SOUTH.toString() + "/struc", data3);
    StructureData data4 = load(name, BlockFace.NORTH);
    data4.setType((data4.getType() == null) ? StructureType.NONE : data4.getType());
    Main.write(String.valueOf(folderPrefix) + name + "/" + BlockFace.NORTH.toString() + "/struc", data4);
  }
  
  private void blockBuild(Location origin, int i, int j, int k, int startx, int starty, int startz) {
    Material mat = this.blocks[j][i][k];
    Block block = origin.getWorld().getBlockAt(j + startx, i + starty, k + startz);
    Material newMat = PaletteManager.get(origin, this.type, mat, block);
    newMat = (newMat == null) ? mat : newMat;
    if (PaletteManager.isBed(newMat) && !PaletteManager.canBed(origin)) {
      Bed.Part bedPart = this.bedparts[j][i][k];
      PaletteManager.handleBed(origin, this.type, block, bedPart);
    } else {
      block.setType(newMat, false);
      if (block.getBlockData() instanceof Directional) {
        Directional d = (Directional)block.getBlockData();
        BlockFace Bface = (this.data[j][i][k] == null) ? getRandomFace() : this.data[j][i][k];
        d.setFacing(Bface);
        block.setBlockData((BlockData)d, false);
        if (block.getBlockData() instanceof Bed) {
          Bed b = (Bed)block.getBlockData();
          Bed.Part part = this.bedparts[j][i][k];
          b.setPart(part);
          block.setBlockData((BlockData)b, false);
        } 
        if (block.getBlockData() instanceof Stairs) {
          Stairs s = (Stairs)block.getBlockData();
          Stairs.Shape shape = this.shapes[j][i][k];
          Bisected.Half half = this.halfs[j][i][k];
          s.setShape(shape);
          s.setHalf(half);
          block.setBlockData((BlockData)s, false);
        } 
      } 
      if (block.getBlockData() instanceof Fence) {
        Fence f = (Fence)block.getBlockData();
        Set<BlockFace> Ffaces = (this.faces[j][i][k]).faces;
        for (BlockFace Fface : Ffaces)
          f.setFace(Fface, true); 
        block.setBlockData((BlockData)f);
      } 
    } 
    if (newMat != Material.AIR) {
      NBTBlock nbt = new NBTBlock(block);
      nbt.getData().setBoolean("hasStable", Boolean.valueOf(true));
    } 
  }
  
  private void instantiateNPC(Location npcMain, List<Location> npcSecondary, StableMemory memory) {
    npcMain.setX(npcMain.getX() + 0.5D);
    npcMain.setZ(npcMain.getZ() + 0.5D);
    Waypoint waypoint = new Waypoint(npcMain);
    NPCMemory memoryMain = new NPCMemory(waypoint, "Terry", "merchant2");
    memoryMain.setStable(memory);
    memory.setMainNPC(memoryMain.getUuid());
    NPCHandler.instantiateNPCMemory(memoryMain);
    NPCHandler.mountBrains(npcMain.getChunk());
    for (int i = 0; i < 3; i++) {
      int r = (int)Math.floor(Math.random() * npcSecondary.size());
      Location loc = npcSecondary.get(r);
      npcSecondary.remove(loc);
      loc.setX(loc.getX() + 0.5D);
      loc.setZ(loc.getZ() + 0.5D);
      Waypoint waypointSecondary = new Waypoint(loc);
      NPCMemory memorySecondary = new NPCMemory(waypointSecondary, "PNJ", "merchant4");
      memorySecondary.setStable(memory);
      memory.addSecondaryNPC(memorySecondary.getUuid());
      NPCHandler.instantiateNPCMemory(memorySecondary);
      NPCHandler.mountBrains(loc.getChunk());
    } 
    for (Player p : Bukkit.getOnlinePlayers()) {
      Chunk chunk = p.getLocation().getChunk();
      int x = chunk.getX();
      int z = chunk.getZ();
      List<Chunk> chunks = new ArrayList<>();
      for (int j = -3; j < 4; j++) {
        for (int k = -3; k < 4; k++)
          chunks.add(chunk.getWorld().getChunkAt(x + j, z + k)); 
      } 
      for (Chunk c : chunks)
        NPCHandler.mountBrains(c); 
      NPCHandler.createNPCs(p);
      NPCHandler.hideNPCBrains(p);
      Thread thread = new Thread(new Runnable() {
            public void run() {
              NPCHandler.showNPCs(p);
              NPCHandler.hideNPCs(p);
              List<Thread> tasks = (List<Thread>)Listener.NPCTasks.get(p);
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
        List<Thread> tasks = (List<Thread>)Listener.NPCTasks.get(p);
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
  
  public void build(Location origin, BlockFace face, Player owner) {
    Biome biome = origin.getBlock().getBiome();
    World world = origin.getWorld();
    if (face == BlockFace.EAST) {
      int startx = origin.getBlockX() + 5;
      int starty = origin.getBlockY() - 1;
      int startz = origin.getBlockZ() - 10;
      Location npcMain = null;
      List<Location> npcSecondary = new ArrayList<>();
      StableMemory memory = new StableMemory(startx, starty, startz, this.sizex, this.sizey, this.sizez, owner, this.type, biome, face, world);
      for (int i = 0; i < this.sizey; i++) {
        for (int j = 0; j < this.sizex; j++) {
          for (int k = 0; k < this.sizez; k++) {
            Block block = origin.getWorld().getBlockAt(j + startx, i + starty, k + startz);
            if (this.blocks[j][i][k] == Material.EMERALD_BLOCK)
              npcMain = block.getRelative(BlockFace.UP).getLocation(); 
            if (this.blocks[j][i][k] == Material.SEA_LANTERN)
              npcSecondary.add(block.getRelative(BlockFace.UP).getLocation()); 
            blockBuild(origin, i, j, k, startx, starty, startz);
          } 
        } 
      } 
      instantiateNPC(npcMain, npcSecondary, memory);
    } else if (face == BlockFace.SOUTH) {
      int startx = origin.getBlockX() - 10;
      int starty = origin.getBlockY() - 1;
      int startz = origin.getBlockZ() + 5;
      Location npcMain = null;
      List<Location> npcSecondary = new ArrayList<>();
      StableMemory memory = new StableMemory(startx, starty, startz, this.sizex, this.sizey, this.sizez, owner, this.type, biome, face, world);
      for (int i = 0; i < this.sizey; i++) {
        for (int j = 0; j < this.sizex; j++) {
          for (int k = 0; k < this.sizez; k++) {
            Block block = origin.getWorld().getBlockAt(j + startx, i + starty, k + startz);
            if (this.blocks[j][i][k] == Material.EMERALD_BLOCK)
              npcMain = block.getRelative(BlockFace.UP).getLocation(); 
            if (this.blocks[j][i][k] == Material.SEA_LANTERN)
              npcSecondary.add(block.getRelative(BlockFace.UP).getLocation()); 
            blockBuild(origin, i, j, k, startx, starty, startz);
          } 
        } 
      } 
      instantiateNPC(npcMain, npcSecondary, memory);
    } else if (face == BlockFace.WEST) {
      int startx = origin.getBlockX() - 5 - 20;
      int starty = origin.getBlockY() - 1;
      int startz = origin.getBlockZ() - 10;
      Location npcMain = null;
      List<Location> npcSecondary = new ArrayList<>();
      StableMemory memory = new StableMemory(startx, starty, startz, this.sizex, this.sizey, this.sizez, owner, this.type, biome, face, world);
      for (int i = 0; i < this.sizey; i++) {
        for (int j = 0; j < this.sizex; j++) {
          for (int k = 0; k < this.sizez; k++) {
            Block block = origin.getWorld().getBlockAt(j + startx, i + starty, k + startz);
            if (this.blocks[j][i][k] == Material.EMERALD_BLOCK)
              npcMain = block.getRelative(BlockFace.UP).getLocation(); 
            if (this.blocks[j][i][k] == Material.SEA_LANTERN)
              npcSecondary.add(block.getRelative(BlockFace.UP).getLocation()); 
            blockBuild(origin, i, j, k, startx, starty, startz);
          } 
        } 
      } 
      instantiateNPC(npcMain, npcSecondary, memory);
    } else if (face == BlockFace.NORTH) {
      int startx = origin.getBlockX() - 10;
      int starty = origin.getBlockY() - 1;
      int startz = origin.getBlockZ() - 25;
      Location npcMain = null;
      List<Location> npcSecondary = new ArrayList<>();
      StableMemory memory = new StableMemory(startx, starty, startz, this.sizex, this.sizey, this.sizez, owner, this.type, biome, face, world);
      for (int i = 0; i < this.sizey; i++) {
        for (int j = 0; j < this.sizex; j++) {
          for (int k = 0; k < this.sizez; k++) {
            Block block = origin.getWorld().getBlockAt(j + startx, i + starty, k + startz);
            if (this.blocks[j][i][k] == Material.EMERALD_BLOCK)
              npcMain = block.getRelative(BlockFace.UP).getLocation(); 
            if (this.blocks[j][i][k] == Material.SEA_LANTERN)
              npcSecondary.add(block.getRelative(BlockFace.UP).getLocation()); 
            blockBuild(origin, i, j, k, startx, starty, startz);
          } 
        } 
      } 
      instantiateNPC(npcMain, npcSecondary, memory);
    } 
  }
  
  private BlockFace getRandomFace() {
    Double d = Double.valueOf(Math.random() * 6.0D);
    if (d.doubleValue() < 1.0D)
      return BlockFace.DOWN; 
    if (d.doubleValue() < 2.0D)
      return BlockFace.EAST; 
    if (d.doubleValue() < 3.0D)
      return BlockFace.WEST; 
    if (d.doubleValue() < 4.0D)
      return BlockFace.NORTH; 
    if (d.doubleValue() < 5.0D)
      return BlockFace.SOUTH; 
    if (d.doubleValue() < 6.0D)
      return BlockFace.UP; 
    return null;
  }
  
  public static StructureData load(String name, BlockFace face) {
    return Main.read(String.valueOf(folderPrefix) + name + "/" + face.toString() + "/struc");
  }
  
  public StructureType getType() {
    return this.type;
  }
  
  public void setType(StructureType type) {
    this.type = type;
  }
}

