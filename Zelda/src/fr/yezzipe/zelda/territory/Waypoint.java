package fr.yezzipe.zelda.territory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Waypoint {
  private String worldName;
  
  private double x;
  
  private double y;
  
  private double z;
  
  private float pitch;
  
  private float yaw;
  
  public Waypoint(Location loc) {
    this.worldName = loc.getWorld().getName();
    this.x = loc.getX();
    this.y = loc.getY();
    this.z = loc.getZ();
    this.pitch = loc.getPitch();
    this.yaw = loc.getYaw();
  }
  
  public Waypoint(World world, double x, double y, double z, float pitch, float yaw) {
    this.worldName = world.getName();
    this.x = x;
    this.y = y;
    this.z = z;
    this.pitch = pitch;
    this.yaw = yaw;
  }
  
  public Location getLocation() {
    return new Location(Bukkit.getWorld(this.worldName), this.x, this.y, this.z, this.yaw, this.pitch);
  }
  
  public String getName() {
    Location loc = new Location(Bukkit.getWorld(this.worldName), this.x, this.y, this.z, this.yaw, this.pitch);
    World.Environment env = loc.getWorld().getEnvironment();
    String world = "";
    switch (env) {
      case NETHER:
        world = "Nether";
        break;
      case NORMAL:
        world = "Overworld";
        break;
      case THE_END:
        world = "End";
        break;
      default:
		break;
    } 
    String s = String.valueOf(world) + " : " + Integer.toString(loc.getBlockX()) + " ; " + Integer.toString(loc.getBlockY()) + " ; " + Integer.toString(loc.getBlockZ());
    return s;
  }
  
  public World getWorld() {
    return Bukkit.getWorld(this.worldName);
  }
  
  public void setWorld(World world) {
    this.worldName = world.getName();
  }
  
  public double getX() {
    return this.x;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public double getZ() {
    return this.z;
  }
  
  public void setZ(double z) {
    this.z = z;
  }
  
  public float getPitch() {
    return this.pitch;
  }
  
  public void setPitch(float pitch) {
    this.pitch = pitch;
  }
  
  public float getYaw() {
    return this.yaw;
  }
  
  public void setYaw(float yaw) {
    this.yaw = yaw;
  }
}
