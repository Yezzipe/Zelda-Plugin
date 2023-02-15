package fr.yezzipe.zelda.items;

import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import fr.yezzipe.zelda.Listener;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.Registry;
import fr.yezzipe.zelda.entity.EntityManager;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.entity.player.PacketManager;
import fr.yezzipe.zelda.entity.player.PlayerData;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutAttachEntity;
import net.minecraft.world.entity.Entity;

public class GrapplingHookManager extends ItemManager {

    private Arrow arrow;

    private org.bukkit.entity.Entity hitEntity;

    private static Registry<Player, GrapplingHookManager> hooks = new Registry<Player, GrapplingHookManager>();

    public static Registry<Arrow, GrapplingHookManager> arrows = new Registry<Arrow, GrapplingHookManager>();

    private BukkitTask extendTask;

    private BukkitTask retractTaskSound;

    private BukkitTask retractTaskBlock;

    private BukkitTask retractTaskEntity;

    public GrapplingHookManager(PlayerInteractEvent e) {
	e.setCancelled(true);
	p = e.getPlayer();
	PData = PlayerData.getData(p);
	if (PData.getCurrentRace() != Race.GORON) {
	    handleClick(e);
	}
    }

    @Override
    public void handleLeftClickBlock(PlayerInteractEvent e) {
	if (hooks.get(p) != null) {
	    GrapplingHookManager prev = hooks.get(p);
	    prev.cleanArrow();
	}
	if (PData.isAttachedToWall()) {
	    PData.detachFromWall();
	    p.setGravity(true);
	}

    }

    @Override
    public void handleLeftClickAir(PlayerInteractEvent e) {
	if (!PData.isNoLeftClickHook()) {
	    if (hooks.get(p) != null) {
		GrapplingHookManager prev = hooks.get(p);
		prev.cleanArrow();
	    }
	    if (PData.isAttachedToWall()) {
		PData.detachFromWall();
		p.setGravity(true);
	    }
	}
    }

    @Override
    public void handleLeftSneakClickBlock(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    @Override
    public void handleLeftSneakClickAir(PlayerInteractEvent e) {
	handleLeftClickAir(e);
    }

    @Override
    public void handleRightClickBlock(PlayerInteractEvent e) {
	GrapplingHookManager prev = hooks.get(p);
	if (prev != null)
	    prev.cleanArrow();
	initArrow();
	if (PData.isAttachedToWall()) {
	    PData.detachFromWall();
	    p.setGravity(true);
	}
	p.playSound(p.getLocation(), "zelda.clawshot.fire", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	PData.setNoLeftClickHook(true);
	(new BukkitRunnable() {
	    public void run() {
		PData.setNoLeftClickHook(false);
	    }
	}).runTaskLater((Plugin) Main.getInstance(), 5L);
	extend();
    }

    public void handleRightClickAir(PlayerInteractEvent e) {
	handleRightClickBlock(e);
    }

    public void handleRightSneakClickBlock(PlayerInteractEvent e) {
	handleRightClickBlock(e);
    }

    public void handleRightSneakClickAir(PlayerInteractEvent e) {
	handleRightClickBlock(e);
    }

    public void extend() {
	BukkitTask runnable = (new BukkitRunnable() {
	    public void run() {
		p.playSound(p.getLocation(), "zelda.clawshot.extend", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 13L);
	extendTask = runnable;
    }

    public void cleanArrow() {
	if (hitEntity != null) {
	    CraftEntity Cent = (CraftEntity) hitEntity;
	    final Entity ent = Cent.getHandle();
	    PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity(ent, null);
	    PacketManager.sendPacketToAll(packet);
	    hitEntity.setVelocity(new Vector(0, 0, 0));
	}
	for (org.bukkit.entity.Entity ent : arrow.getPassengers()) {
	    ent.remove();
	}
	arrows.unbind(arrow);
	arrow.remove();
	if (extendTask != null)
	    extendTask.cancel();
	extendTask = null;
	if (retractTaskSound != null)
	    retractTaskSound.cancel();
	retractTaskSound = null;
	if (retractTaskBlock != null)
	    retractTaskBlock.cancel();
	retractTaskBlock = null;
	if (retractTaskEntity != null)
	    retractTaskEntity.cancel();
	retractTaskEntity = null;
	p.playSound(p.getLocation(), "zelda.clawshot.end", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	p.stopSound("zelda.clawshot.extend", SoundCategory.PLAYERS);
	p.stopSound("zelda.clawshot.retract", SoundCategory.PLAYERS);
	hooks.unbind(p);
	p.setGliding(false);
	Listener.glideCanceled.remove(p);
	if (PData.isAttachedToWall()) {
	    PData.detachFromWall();
	    p.setGravity(true);
	}
    }

    private void initArrow() {
	arrow = (Arrow) p.launchProjectile(Arrow.class);
	arrow.setPickupStatus(PickupStatus.DISALLOWED);
	hooks.bind(p, this);
	arrows.bind(arrow, this);
	Rabbit rabbit = (Rabbit) p.getWorld().spawn(p.getLocation(), Rabbit.class);
	rabbit.setBaby();
	arrow.addPassenger((org.bukkit.entity.Entity) rabbit);
	arrow.setPersistent(true);
	rabbit.setAI(false);
	rabbit.setCollidable(false);
	rabbit.setGravity(false);
	rabbit.setSilent(true);
	rabbit.setVisualFire(false);
	EntityManager.noCollisionHandler.addEntity(rabbit.getUniqueId());
	rabbit.setInvisible(true);
	rabbit.setInvulnerable(true);
	CraftEntity Cent = (CraftEntity) rabbit;
	Entity ent = Cent.getHandle();
	PacketPlayOutAnimation packet1 = new PacketPlayOutAnimation((Entity) ((CraftPlayer) p).getHandle(), 0);
	PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity(ent, (Entity) ((CraftPlayer) p).getHandle());
	PacketManager.sendPacket(p, packet1);
	PacketManager.sendPacketToAll(packet);
    }

    public static GrapplingHookManager getFromPlayer(Player p) {
	return hooks.get(p);
    }

    public static boolean isHook(Arrow a) {
	return arrows.keys().contains(a);
    }

    public static GrapplingHookManager getFromArrow(Arrow a) {
	return arrows.get(a);
    }

    public static boolean isHook(Projectile entity) {
	if (entity instanceof Arrow)
	    return isHook((Arrow) entity);
	return false;
    }

    public void retract() {
	extendTask.cancel();
	extendTask = null;
	p.stopSound("zelda.clawshot.extend", SoundCategory.PLAYERS);
    }

    public void stopRetract(boolean resetSpeed, boolean detach) {
	Listener.glideCanceled.remove(p);
	retractTaskSound.cancel();
	retractTaskBlock.cancel();
	p.stopSound("zelda.clawshot.retract", SoundCategory.PLAYERS);
	p.setGliding(false);
	if (resetSpeed)
	    p.setVelocity(new Vector(0, 0, 0));
	if (PData.isAttachedToWall() && detach) {
	    PData.detachFromWall();
	    p.setGravity(true);
	}
    }

    public void retractBlock() {
	retract();
	BukkitTask runnable = (new BukkitRunnable() {
	    public void run() {
		p.playSound(p.getLocation(), "zelda.clawshot.retract", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 6L);
	retractTaskSound = runnable;
	Listener.glideCanceled.add(p);
	p.setGliding(true);
	BukkitTask runnable1 = (new BukkitRunnable() {
	    Location prevLoc = null;

	    Location prevLoc2 = null;

	    Location prevLoc3 = null;

	    boolean hasAttached = false;

	    public void run() {
		if (!arrow.isValid()) {
		    stopRetract(false, true);
		    return;
		}
		if (hooks.get(p) == null) {
		    stopRetract(false, true);
		    return;
		}
		arrow.setTicksLived(1);
		Location loc1 = arrow.getLocation();
		Location loc2 = p.getLocation();
		double dX = loc2.getX() - loc1.getX();
		double dY = loc2.getY() - loc1.getY();
		double dZ = loc2.getZ() - loc1.getZ();
		double yaw = Math.atan2(dZ, dX);
		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
		double X = Math.sin(pitch) * Math.cos(yaw);
		double Y = Math.sin(pitch) * Math.sin(yaw);
		double Z = Math.cos(pitch);
		Vector vector = new Vector(X, Z, Y);
		Vector v = new Vector(-dX, -dY, -dZ);
		if (this.prevLoc3 != null && !PData.isAttachedToWall()) {
		    Vector v2 = new Vector(this.prevLoc3.getX() - loc2.getX(), this.prevLoc3.getY() - loc2.getY(),
			    this.prevLoc3.getZ() - loc2.getZ());
		    if (v2.length() <= 0.1D) {
			stopRetract(true, true);
			cleanArrow();
		    } else if (v.length() > 1.0D && !PData.isAttachedToWall()) {
			p.setVelocity(vector);
		    } else if (!this.hasAttached) {
			this.hasAttached = true;
			PData.attachToWall();
			stopRetract(true, false);
		    }
		} else if (v.length() > 1.0D && !PData.isAttachedToWall()) {
		    p.setVelocity(vector);
		} else if (!this.hasAttached) {
		    this.hasAttached = true;
		    PData.attachToWall();
		    stopRetract(true, false);
		}
		this.prevLoc3 = this.prevLoc2;
		this.prevLoc2 = this.prevLoc;
		this.prevLoc = loc2;
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 2L);
	retractTaskBlock = runnable1;
    }
    
    public void stopRetractEntity() {
	CraftEntity Cent = (CraftEntity) hitEntity;
	final Entity ent = Cent.getHandle();
	retractTaskSound.cancel();
	p.stopSound("zelda.clawshot.retract", SoundCategory.PLAYERS);
	p.playSound(p.getLocation(), "zelda.clawshot.end", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	hooks.unbind(p);
	PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity(ent, null);
	PacketManager.sendPacketToAll(packet);
	hitEntity.setVelocity(new Vector(0, 0, 0));
    }

    public void retractEntity(org.bukkit.entity.Entity entity) {
	hitEntity = entity;
	retract();
	BukkitTask runnable = (new BukkitRunnable() {
	    public void run() {
		p.playSound(p.getLocation(), "zelda.clawshot.retract", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 6L);
	retractTaskSound = runnable;
	for (org.bukkit.entity.Entity ent : arrow.getPassengers()) {
	    ent.remove();
	}
	arrows.unbind(arrow);
	arrow.remove();
	if (hitEntity instanceof Player) {
	    Player otherP = (Player) hitEntity;
	    PlayerData PData2 = PlayerData.getData(otherP);
	    if (PData2.getCurrentRace() == Race.GORON)
		return;
	}
	CraftEntity Cent = (CraftEntity) hitEntity;
	final Entity ent = Cent.getHandle();
	PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity(ent, (Entity) ((CraftPlayer) p).getHandle());
	PacketManager.sendPacketToAll(packet);
	BukkitTask runnable1 = (new BukkitRunnable() {
	    Location prevLoc = null;

	    public void run() {
		Location loc1 = p.getLocation();
		Location loc2 = hitEntity.getLocation();
		double dX = loc2.getX() - loc1.getX();
		double dY = loc2.getY() - loc1.getY();
		double dZ = loc2.getZ() - loc1.getZ();
		double yaw = Math.atan2(dZ, dX);
		double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
		double X = Math.sin(pitch) * Math.cos(yaw);
		double Y = Math.sin(pitch) * Math.sin(yaw);
		double Z = Math.cos(pitch);
		Vector vector = new Vector(X, Z, Y);
		Vector v = new Vector(-dX, -dY, -dZ);
		if (hooks.get(p) == null) {
		    cancel();
		    retractTaskSound.cancel();
		    PacketPlayOutAttachEntity packet = new PacketPlayOutAttachEntity(ent, null);
		    PacketManager.sendPacketToAll(packet);
		    hitEntity.setVelocity(new Vector(0, 0, 0));
		    return;
		}
		if (this.prevLoc != null) {
		    Vector v2 = new Vector(this.prevLoc.getX() - loc2.getX(), this.prevLoc.getY() - loc2.getY(),
			    this.prevLoc.getZ() - loc2.getZ());
		    if (v2.length() <= 0.5D) {
			cancel();
			stopRetractEntity();
		    } else {
			this.prevLoc = loc2;
			if (v.length() > 1.5D) {
			    hitEntity.setVelocity(vector);
			} else {
			    cancel();
			    stopRetractEntity();
			}
		    }
		} else {
		    this.prevLoc = loc2;
		    if (v.length() > 1.5D) {
			hitEntity.setVelocity(vector);
		    } else {
			cancel();
			stopRetractEntity();
		    }
		}
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 2L);
	retractTaskEntity = runnable1;
    }

}
