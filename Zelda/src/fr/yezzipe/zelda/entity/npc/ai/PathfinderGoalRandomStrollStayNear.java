package fr.yezzipe.zelda.entity.npc.ai;

import java.util.EnumSet;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Location;
import org.bukkit.World;

public class PathfinderGoalRandomStrollStayNear extends PathfinderGoal {
  public static final int default_interval = 120;
  
  protected final EntityCreature mob;
  
  protected double originX;
  
  protected double originY;
  
  protected double originZ;
  
  protected World world;
  
  protected double wantedX;
  
  protected double wantedY;
  
  protected double wantedZ;
  
  protected final double speedModifier;
  
  protected int interval;
  
  protected boolean forceTrigger;
  
  protected double radius;
  
  private final boolean checkNoActionTime;
  
  protected boolean goingBack;
  
  public PathfinderGoalRandomStrollStayNear(EntityCreature mob, Location loc, double speedModifier, double radius) {
    this.mob = mob;
    this.originX = loc.getX();
    this.originY = loc.getY();
    this.originZ = loc.getZ();
    this.world = loc.getWorld();
    this.speedModifier = speedModifier;
    this.interval = 120;
    this.checkNoActionTime = true;
    this.radius = radius;
    this.goingBack = false;
    a(EnumSet.of(PathfinderGoal.Type.a));
  }
  
  public boolean a() {
    double currX = this.mob.dc();
    double currY = this.mob.de();
    double currZ = this.mob.di();
    Location loc = new Location(this.world, currX, currY, currZ);
    Location originLoc = new Location(this.world, this.originX, this.originY, this.originZ);
    if (this.mob.bG())
      return false; 
    if (!this.forceTrigger) {
      if (this.checkNoActionTime && this.mob.dQ() >= 100)
        return false; 
      if (this.mob.dL().nextInt(b(this.interval)) != 0)
        return false; 
    } 
    if (loc.distance(originLoc) >= this.radius) {
      System.out.println("should go back");
      this.wantedX = this.originX;
      this.wantedY = this.originY;
      this.wantedZ = this.originZ;
      this.goingBack = true;
    } else {
      Vec3D var0 = getRandomPostition();
      if (var0 == null)
        return false; 
      this.wantedX = var0.b;
      this.wantedY = var0.c;
      this.wantedZ = var0.d;
    } 
    this.forceTrigger = false;
    return true;
  }
  
  @Nullable
  private Vec3D getRandomPostition() {
    return LandRandomPos.a(this.mob, 10, 7);
  }
  
  public boolean b() {
    double currX = this.mob.dc();
    double currY = this.mob.de();
    double currZ = this.mob.di();
    Location loc = new Location(this.world, currX, currY, currZ);
    Location originLoc = new Location(this.world, this.originX, this.originY, this.originZ);
    if (this.goingBack)
      return (loc.distance(originLoc) >= this.radius && !this.mob.D().l() && !this.mob.bG()); 
    return (loc.distance(originLoc) < this.radius && !this.mob.D().l() && !this.mob.bG());
  }
  
  public void c() {
    this.mob.D().a(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
  }
  
  public void d() {
    this.goingBack = false;
    this.mob.D().n();
    super.d();
  }
  
  public void i() {
    this.forceTrigger = true;
  }
  
  public void c(int i) {
    this.interval = i;
  }
}

