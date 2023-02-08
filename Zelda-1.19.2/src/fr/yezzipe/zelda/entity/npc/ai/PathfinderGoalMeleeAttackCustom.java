package fr.yezzipe.zelda.entity.npc.ai;

import java.util.EnumSet;

import fr.yezzipe.zelda.entity.npc.CustomNPC;
import fr.yezzipe.zelda.entity.npc.NPCHandler;
import net.minecraft.util.MathHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.IEntitySelector;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.level.pathfinder.PathEntity;

public class PathfinderGoalMeleeAttackCustom extends PathfinderGoal {
    protected final EntityCreature mob;

    private final double speedModifier;

    private final boolean followingTargetEvenIfNotSeen;

    private PathEntity path;

    private double pathedTargetX;

    private double pathedTargetY;

    private double pathedTargetZ;

    private int ticksUntilNextPathRecalculation;

    private int ticksUntilNextAttack;

    @SuppressWarnings("unused")
    private final int attackInterval = 20;

    private long lastCanUseCheck;

    @SuppressWarnings("unused")
    private static final long COOLDOWN_BETWEEN_CAN_USE_CHECKS = 20L;

    public PathfinderGoalMeleeAttackCustom(EntityCreature var0, double var1, boolean var3) {
	this.mob = var0;
	this.speedModifier = var1;
	this.followingTargetEvenIfNotSeen = var3;
	a(EnumSet.of(PathfinderGoal.Type.a, PathfinderGoal.Type.b));
    }

    public boolean a() {
	long var0 = this.mob.s.U();
	if (var0 - this.lastCanUseCheck < 20L)
	    return false;
	this.lastCanUseCheck = var0;
	EntityLiving var2 = this.mob.G();
	if (var2 == null)
	    return false;
	if (!var2.bl())
	    return false;
	this.path = this.mob.D().a((Entity) var2, 0);
	if (this.path != null)
	    return true;
	if (getAttackReachSqr(var2) >= this.mob.h(var2.df(), var2.dh(), var2.dl()))
	    return true;
	return false;
    }

    public boolean b() {
	EntityLiving var0 = this.mob.G();
	if (var0 == null)
	    return false;
	if (!var0.bl())
	    return false;
	if (!this.followingTargetEvenIfNotSeen)
	    return !this.mob.D().l();
	if (!this.mob.a(var0.aB()))
	    return false;
	if (var0 instanceof EntityHuman && (var0.B_() || ((EntityHuman) var0).f()))
	    return false;
	return true;
    }

    public void c() {
	this.mob.D().a(this.path, this.speedModifier);
	this.ticksUntilNextPathRecalculation = 0;
	this.ticksUntilNextAttack = 0;
    }

    public void d() {
	EntityLiving var0 = this.mob.G();
	if (!IEntitySelector.e.test(var0))
	    this.mob.h(var0);
	this.mob.D().n();
    }

    public boolean E_() {
	return true;
    }

    public void e() {
	EntityLiving var0 = this.mob.G();
	if (var0 == null)
	    return;
	this.mob.z().a((Entity) var0, 30.0F, 30.0F);
	double var1 = this.mob.h(var0.df(), var0.dh(), var0.dl());
	this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
	if ((this.followingTargetEvenIfNotSeen || this.mob.E().a((Entity) var0))
		&& this.ticksUntilNextPathRecalculation <= 0
		&& ((this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D)
			|| var0.h(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D
			|| this.mob.dQ().i() < 0.05F)) {
	    this.pathedTargetX = var0.df();
	    this.pathedTargetY = var0.dh();
	    this.pathedTargetZ = var0.dl();
	    this.ticksUntilNextPathRecalculation = 4 + this.mob.dQ().a(7);
	    if (var1 > 1024.0D) {
		this.ticksUntilNextPathRecalculation += 10;
	    } else if (var1 > 256.0D) {
		this.ticksUntilNextPathRecalculation += 5;
	    }
	    if (!this.mob.D().a((Entity) var0, this.speedModifier))
		this.ticksUntilNextPathRecalculation += 15;
	    this.ticksUntilNextPathRecalculation = a(this.ticksUntilNextPathRecalculation);
	}
	this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
	checkAndPerformAttack(var0, var1);
    }

    protected void checkAndPerformAttack(EntityLiving var0, double var1) {
	double var3 = getAttackReachSqr(var0);
	if (var1 <= var3 && this.ticksUntilNextAttack <= 0) {
	    h();
	    customHurt((Entity) this.mob.G());
	}
    }

    private void customHurt(Entity entity) {
	float f = 10.0F;
	float f1 = 1.0F;
	boolean flag = entity.a(DamageSource.c((EntityLiving) this.mob), f);
	if (flag && f1 > 0.0F && entity instanceof EntityLiving) {
	    ((EntityLiving) entity).p((f1 * 0.5F), MathHelper.a(this.mob.dq() * 0.017453292F),
		    -MathHelper.b(this.mob.dq() * 0.017453292F));
	    this.mob.g(this.mob.da().d(0.6D, 1.0D, 0.6D));
	    if (this.mob instanceof CustomNPC)
		NPCHandler.swingMainHand((CustomNPC) this.mob);
	}
    }

    protected void h() {
	this.ticksUntilNextAttack = a(20);
    }

    protected boolean i() {
	return (this.ticksUntilNextAttack <= 0);
    }

    protected int k() {
	return this.ticksUntilNextAttack;
    }

    protected int l() {
	return a(20);
    }

    protected double getAttackReachSqr(EntityLiving var0) {
	return (this.mob.cW() * 2.0F * this.mob.cW() * 2.0F + var0.cW());
    }
}
