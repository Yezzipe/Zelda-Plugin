package fr.yezzipe.zelda.territory;

import java.util.UUID;
import org.bukkit.entity.Player;

public class HTTPPlayer {
  private final int x;
  
  private final int z;
  
  private final String world;
  
  private final String name;
  
  private final UUID uuid;
  
  public HTTPPlayer(Player p) {
    this.x = p.getLocation().getBlockX();
    this.z = p.getLocation().getBlockZ();
    this.world = p.getLocation().getWorld().getName();
    this.name = p.getName();
    this.uuid = p.getUniqueId();
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public String getWorld() {
    return this.world;
  }
  
  public String getName() {
    return this.name;
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
}
