package fr.yezzipe.zelda.items;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.yezzipe.zelda.entity.player.PlayerData;

public abstract class ItemManager {

    protected Player p;
    protected PlayerData PData;
    protected ItemStack item;

    public void handleClick(PlayerInteractEvent e) {
	item = e.getItem();
	p = e.getPlayer();
	PData = PlayerData.getData(p);
	Action action = e.getAction();
	boolean sneaking = e.getPlayer().isSneaking();
	switch (action) {
	case LEFT_CLICK_AIR:
	    if (sneaking)
		handleLeftSneakClickAir(e);
	    else
		handleLeftClickAir(e);
	    break;
	case LEFT_CLICK_BLOCK:
	    if (sneaking)
		handleLeftSneakClickBlock(e);
	    else
		handleLeftClickBlock(e);
	    break;
	case RIGHT_CLICK_AIR:
	    if (sneaking)
		handleRightSneakClickAir(e);
	    else
		handleRightClickAir(e);
	    break;
	case RIGHT_CLICK_BLOCK:
	    if (sneaking)
		handleRightSneakClickBlock(e);
	    else
		handleRightClickBlock(e);
	    break;
	case PHYSICAL:
	default:
	    break;

	}
    }

    public abstract void handleLeftClickBlock(PlayerInteractEvent e);

    public abstract void handleLeftClickAir(PlayerInteractEvent e);

    public abstract void handleLeftSneakClickBlock(PlayerInteractEvent e);

    public abstract void handleLeftSneakClickAir(PlayerInteractEvent e);

    public abstract void handleRightClickBlock(PlayerInteractEvent e);

    public abstract void handleRightClickAir(PlayerInteractEvent e);

    public abstract void handleRightSneakClickBlock(PlayerInteractEvent e);

    public abstract void handleRightSneakClickAir(PlayerInteractEvent e);
}
