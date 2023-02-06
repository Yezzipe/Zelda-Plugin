package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.entity.player.PlayerData;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RaceManager extends InventoryManager {

    public RaceManager() {
	createInventory(null, 9, "Race", CustomInventoryType.RACE);
	populateInventory();
    }

    protected void populateInventory() {
	inv.setItem(0, guiItemBuilder(Material.FEATHER, "Peuple Piaf"));
	inv.setItem(1, guiItemBuilder(Material.TRIDENT, "Peuple Zora"));
	inv.setItem(2, guiItemBuilder(Material.SANDSTONE, "Peuple Goron"));
	inv.setItem(3, guiItemBuilder(Material.DEAD_BUSH, "Peuple Gerudo"));
	inv.setItem(5, guiItemBuilder(Material.ENDER_PEARL, "Peuple Sheikah"));
	inv.setItem(6, guiItemBuilder(Material.BLACK_DYE, "Peuple Twili"));
	inv.setItem(7, guiItemBuilder(Material.SHIELD, "Peuple Hylien"));
	inv.setItem(8, guiItemBuilder(Material.FERN, "Peuple Kokiri"));
    }

    public void handleClose(InventoryCloseEvent e) {
	final Player p = (Player) e.getPlayer();
	PlayerData PData = PlayerData.getData(p);
	if (PData.getCurrentRace() == Race.NONE) {
	    (new BukkitRunnable() {
		public void run() {
		    p.openInventory(inv);
		    if (!p.isOnline())
			cancel();
		}
	    }).runTaskLater((Plugin) Main.getPlugin(Main.class), 2L);
	}
    }

    public void handleClick(InventoryClickEvent e) {
	Player p = (Player) e.getWhoClicked();
	e.setCancelled(true);
	PlayerData PData = PlayerData.getData(p);
	switch (e.getRawSlot()) {
	case 0:
	    PData.setCurrentRace(Race.PIAF);
	    p.closeInventory();
	    break;
	case 1:
	    PData.setCurrentRace(Race.ZORA);
	    p.closeInventory();
	    break;
	case 2:
	    PData.setCurrentRace(Race.GORON);
	    p.closeInventory();
	    break;
	case 3:
	    PData.setCurrentRace(Race.GERUDO);
	    p.closeInventory();
	    break;
	case 5:
	    PData.setCurrentRace(Race.SHEIKAH);
	    p.closeInventory();
	    break;
	case 6:
	    PData.setCurrentRace(Race.TWILI);
	    p.closeInventory();
	    break;
	case 7:
	    PData.setCurrentRace(Race.HYLIEN);
	    p.closeInventory();
	    break;
	case 8:
	    PData.setCurrentRace(Race.KOKIRI);
	    p.closeInventory();
	    break;
	}
    }

    public void handleDrag(InventoryDragEvent e) {
	Set<Integer> slots = e.getRawSlots();
	List<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1),
		Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6),
		Integer.valueOf(7), Integer.valueOf(8) });
	for (Iterator<Integer> iterator = slots.iterator(); iterator.hasNext();) {
	    int slot = ((Integer) iterator.next()).intValue();
	    if (blockedSlots.contains(Integer.valueOf(slot))) {
		e.setCancelled(true);
		break;
	    }
	}
    }
}
