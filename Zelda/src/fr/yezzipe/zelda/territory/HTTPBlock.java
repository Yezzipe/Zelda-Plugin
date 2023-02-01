package fr.yezzipe.zelda.territory;

import org.bukkit.Material;
import org.bukkit.block.Biome;

public class HTTPBlock {
  private final Material material;
  
  private final Biome biome;
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  public HTTPBlock(Material type, Biome biome, int x, int y, int z) {
    this.z = z;
    this.y = y;
    this.x = x;
    this.biome = biome;
    this.material = type;
  }
  
  public Material getMaterial() {
    return this.material;
  }
  
  public Biome getBiome() {
    return this.biome;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
}
