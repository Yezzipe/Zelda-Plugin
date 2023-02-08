package fr.yezzipe.zelda.items;

import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.player.PlayerData;

public class HeartContainerManager extends ItemManager {

    public HeartContainerManager(PlayerInteractEvent e) {
	e.setCancelled(true);
	handleClick(e);
    }

    public void handleLeftClickBlock(PlayerInteractEvent e) {
	int bonusHealth = PData.getBonusHealth();
	final double lastKnownHealth = p.getHealth();
	if (bonusHealth + 1 > 10)
	    return;
	PData.setBonusHealth(bonusHealth + 1);
	PlayerData.applyAttributes(p, false);
	e.getItem().setAmount(e.getItem().getAmount() - 1);
	(new BukkitRunnable() {
	    public void run() {
		if (p.isDead()) {
		    PData.setHealth(lastKnownHealth);
		    cancel();
		    return;
		}
		if (!p.isOnline()) {
		    PData.setHealth(lastKnownHealth);
		    cancel();
		    return;
		}
		double currentHealth = p.getHealth();
		if (currentHealth == p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
		    PData.setHealth(currentHealth);
		    cancel();
		    return;
		}
		if (currentHealth + 2.0D > p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
		    p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		    p.playSound(p.getLocation(), "zelda.heart.get", SoundCategory.PLAYERS, 1000.0F, 1.0F);
		} else {
		    p.setHealth(currentHealth + 2.0D);
		    p.playSound(p.getLocation(), "zelda.heart.get", SoundCategory.PLAYERS, 1000.0F, 1.0F);
		}
	    }
	}).runTaskTimer((Plugin) Main.getInstance(), 0L, 5L);
    }

    public void handleLeftClickAir(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleLeftSneakClickBlock(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleLeftSneakClickAir(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleRightClickBlock(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleRightClickAir(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleRightSneakClickBlock(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleRightSneakClickAir(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

}
