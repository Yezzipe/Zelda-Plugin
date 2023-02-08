package fr.yezzipe.zelda.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.SoundCategory;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.yezzipe.zelda.inventory.ShadowCrystalInventoryManager;
import fr.yezzipe.zelda.territory.Waypoint;

public class ShadowCrystalManager extends ItemManager {

    public ShadowCrystalManager(PlayerInteractEvent e) {
	e.setCancelled(true);
	handleClick(e);
    }

    public void handleLeftClickBlock(PlayerInteractEvent e) {
	p.playSound(p.getLocation(), "zelda.midna.appear", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	ShadowCrystalInventoryManager manager = new ShadowCrystalInventoryManager(p);
	p.openInventory(manager.getInventory());
    }

    public void handleLeftClickAir(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleLeftSneakClickBlock(PlayerInteractEvent e) {
	List<Waypoint> waypoints = PData.getWaypoints();
	if (waypoints != null) {
	    if (waypoints.size() < 3) {
		Waypoint waypoint = new Waypoint(p.getLocation());
		waypoints.add(waypoint);
		PData.setWaypoints(waypoints);
		Random rand = new Random();
		int randomElement = rand.nextInt(2) + 1;
		p.playSound(p.getLocation(), "zelda.midna.mmm." + Integer.toString(randomElement),
			SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    } else {
		p.playSound(p.getLocation(), "zelda.midna.hey", SoundCategory.PLAYERS, 1000.0F, 1.0F);
	    }
	} else {
	    waypoints = new ArrayList<>();
	    Waypoint waypoint = new Waypoint(p.getLocation());
	    waypoints.add(waypoint);
	    PData.setWaypoints(waypoints);
	    Random rand = new Random();
	    int randomElement = rand.nextInt(2) + 1;
	    p.playSound(p.getLocation(), "zelda.midna.mmm." + Integer.toString(randomElement), SoundCategory.PLAYERS,
		    1000.0F, 1.0F);
	}
    }

    @Override
    public void handleLeftSneakClickAir(PlayerInteractEvent e) {
	handleLeftSneakClickBlock(e);
    }

    public void handleRightClickBlock(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    public void handleRightClickAir(PlayerInteractEvent e) {
	handleLeftClickBlock(e);
    }

    @Override
    public void handleRightSneakClickBlock(PlayerInteractEvent e) {
	handleLeftSneakClickBlock(e);
    }

    @Override
    public void handleRightSneakClickAir(PlayerInteractEvent e) {
	handleLeftSneakClickBlock(e);
    }

}
