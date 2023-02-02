package fr.yezzipe.zelda.territory;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.enums.Race;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class TerritoryChunk {
  private static NamespacedKey raceKey = new NamespacedKey((Plugin)Main.getPlugin(Main.class), "Race");
  
  private final int x;
  
  private final int z;
  
  private Race owningRace;
  
  private final String world;
  
  public TerritoryChunk(Chunk chunk) {
    PersistentDataContainer data = chunk.getPersistentDataContainer();
    if (data.has(raceKey, PersistentDataType.STRING)) {
      this.owningRace = Race.valueOf((String)data.get(raceKey, PersistentDataType.STRING));
    } else {
      this.owningRace = Race.NONE;
    } 
    this.x = chunk.getX();
    this.z = chunk.getZ();
    this.world = chunk.getWorld().getName();
    byte b;
    int i;
    Race[] arrayOfRace;
    for (i = (arrayOfRace = Race.values()).length, b = 0; b < i; ) {
      Race r = arrayOfRace[b];
      NamespacedKey k = new NamespacedKey((Plugin)Main.getPlugin(Main.class), r.toString());
      if (!data.has(k, PersistentDataType.INTEGER))
        data.set(k, PersistentDataType.INTEGER, Integer.valueOf(0)); 
      b++;
    } 
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public void removeInfluence(Race race) {
    Chunk chunk = Bukkit.getWorld(this.world).getChunkAt(this.x, this.z);
    NamespacedKey k = new NamespacedKey((Plugin)Main.getPlugin(Main.class), race.toString());
    PersistentDataContainer data = chunk.getPersistentDataContainer();
    if (data.has(k, PersistentDataType.INTEGER)) {
      int i = ((Integer)data.get(k, PersistentDataType.INTEGER)).intValue();
      data.set(k, PersistentDataType.INTEGER, Integer.valueOf(i - 10));
    } 
    calculateNewOwningRace();
  }
  
  private void calculateNewOwningRace() {
    Chunk chunk = Bukkit.getWorld(this.world).getChunkAt(this.x, this.z);
    PersistentDataContainer data = chunk.getPersistentDataContainer();
    int i = 1;
    Race owning = Race.NONE;
    byte b;
    int j;
    Race[] arrayOfRace;
    for (j = (arrayOfRace = Race.values()).length, b = 0; b < j; ) {
      Race r = arrayOfRace[b];
      NamespacedKey k = new NamespacedKey((Plugin)Main.getPlugin(Main.class), r.toString());
      if (data.has(k, PersistentDataType.INTEGER)) {
        int m = ((Integer)data.get(k, PersistentDataType.INTEGER)).intValue();
        if (m > i) {
          i = m;
          owning = r;
        } 
      } 
      b++;
    } 
    this.owningRace = owning;
    data.set(raceKey, PersistentDataType.STRING, owning.toString());
  }
  
  public void addInfluence(Race race) {
    Chunk chunk = Bukkit.getWorld(this.world).getChunkAt(this.x, this.z);
    NamespacedKey k = new NamespacedKey((Plugin)Main.getPlugin(Main.class), race.toString());
    PersistentDataContainer data = chunk.getPersistentDataContainer();
    if (data.has(k, PersistentDataType.INTEGER)) {
      int i = ((Integer)data.get(k, PersistentDataType.INTEGER)).intValue();
      data.set(k, PersistentDataType.INTEGER, Integer.valueOf(i + 10));
    } else {
      data.set(k, PersistentDataType.INTEGER, Integer.valueOf(10));
    } 
    calculateNewOwningRace();
  }
  
  public Race getOwningRace() {
    return this.owningRace;
  }
  
  public String getWorld() {
    return this.world;
  }
  
  public void resetInfluence() {
    Chunk chunk = Bukkit.getWorld(this.world).getChunkAt(this.x, this.z);
    PersistentDataContainer data = chunk.getPersistentDataContainer();
    byte b;
    int i;
    Race[] arrayOfRace;
    for (i = (arrayOfRace = Race.values()).length, b = 0; b < i; ) {
      Race r = arrayOfRace[b];
      NamespacedKey k = new NamespacedKey((Plugin)Main.getPlugin(Main.class), r.toString());
      data.set(k, PersistentDataType.INTEGER, Integer.valueOf(0));
      b++;
    } 
    data.set(raceKey, PersistentDataType.STRING, Race.NONE.toString());
  }
}
