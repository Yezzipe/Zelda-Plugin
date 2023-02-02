package fr.yezzipe.zelda.territory.structures;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Fence;
import org.bukkit.block.data.type.Stairs;

public class StructureWriter {
  private final int startx;
  
  private final int stopx;
  
  private final int starty;
  
  private final int stopy;
  
  private final int startz;
  
  private final int stopz;
  
  private final World world;
  
  public StructureWriter(int x1, int y1, int z1, int x2, int y2, int z2, World world) {
    this.startx = Math.min(x1, x2);
    this.stopx = Math.max(x1, x2);
    this.starty = Math.min(y1, y2);
    this.stopy = Math.max(y1, y2);
    this.startz = Math.min(z1, z2);
    this.stopz = Math.max(z1, z2);
    this.world = world;
  }
  
  public void save(String name) {
    int sizex = this.stopx - this.startx + 1;
    int sizey = this.stopy - this.starty + 1;
    int sizez = this.stopz - this.startz + 1;
    Material[][][] matrix = new Material[sizex][sizey][sizez];
    BlockFace[][][] data = new BlockFace[sizex][sizey][sizez];
    BlockFaces[][][] faces = new BlockFaces[sizex][sizey][sizez];
    Bed.Part[][][] parts = new Bed.Part[sizex][sizey][sizez];
    Stairs.Shape[][][] shapes = new Stairs.Shape[sizex][sizey][sizez];
    Bisected.Half[][][] halfs = new Bisected.Half[sizex][sizey][sizez];
    for (int i = this.starty; i <= this.stopy; i++) {
      for (int j = this.startx; j <= this.stopx; j++) {
        for (int k = this.startz; k <= this.stopz; k++) {
          Block block = this.world.getBlockAt(j, i, k);
          matrix[j - this.startx][i - this.starty][k - this.startz] = block.getType();
          if (block.getBlockData() instanceof Directional) {
            Directional d = (Directional)block.getBlockData();
            data[j - this.startx][i - this.starty][k - this.startz] = d.getFacing();
            if (block.getBlockData() instanceof Bed) {
              Bed b = (Bed)block.getBlockData();
              parts[j - this.startx][i - this.starty][k - this.startz] = b.getPart();
            } 
            if (block.getBlockData() instanceof Stairs) {
              Stairs s = (Stairs)block.getBlockData();
              shapes[j - this.startx][i - this.starty][k - this.startz] = s.getShape();
              halfs[j - this.startx][i - this.starty][k - this.startz] = s.getHalf();
            } 
          } 
          if (block.getBlockData() instanceof Fence) {
            Fence f = (Fence)block.getBlockData();
            BlockFaces Ffaces = new BlockFaces();
            Ffaces.faces = f.getFaces();
            faces[j - this.startx][i - this.starty][k - this.startz] = Ffaces;
          } 
        } 
      } 
    } 
    StructureData struc = new StructureData(matrix, data, faces, parts, shapes, halfs, sizex, sizey, sizez);
    struc.save(name);
  }
}
