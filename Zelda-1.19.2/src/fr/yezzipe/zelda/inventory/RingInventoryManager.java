package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.items.RingBuilder;
import fr.yezzipe.zelda.items.enums.Ring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class RingInventoryManager extends InventoryManager {
    
    private PlayerData PData;
    
    private int max;
    
    private int delta;
    
    private Collection<Integer> blockedSlots = new ArrayList<Integer>();
    
    private Collection<Integer> restrictedSlots = new ArrayList<Integer>();

    public RingInventoryManager(Player p) {
	this.PData = PlayerData.getData(p);
	createInventory(null,  9, "Rings", CustomInventoryType.RINGS);
	populateInventory();
    }

    public void handleClick(InventoryClickEvent e) {
	boolean shift = e.isShiftClick();
	ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
	ItemStack nextItem = (e.getCursor() == null) ? new ItemStack(Material.AIR) : e.getCursor();
	boolean iscurrItemRing = RingBuilder.isRing(currItem);
	boolean isnextItemRing = RingBuilder.isRing(nextItem);
	if (blockedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
	    e.setCancelled(true);
	} else if (shift && !iscurrItemRing && nextItem.getType() == Material.AIR) {
	    e.setCancelled(true);
	} else if (restrictedSlots.contains(Integer.valueOf(e.getRawSlot())) && !isnextItemRing
		&& nextItem.getType() != Material.AIR) {
	    e.setCancelled(true);
	} else if (e.getClick() == ClickType.DOUBLE_CLICK && currItem.getType() == Material.AIR) {
	    e.setCancelled(true);
	}
    }

    public void handleDrag(InventoryDragEvent e) {
	ItemStack nextItem = (e.getOldCursor() == null) ? new ItemStack(Material.AIR) : e.getOldCursor();
	Set<Integer> slots = e.getRawSlots();
	boolean containBlocked = isContained(slots, blockedSlots);
	boolean containRestricted = isContained(slots, restrictedSlots);
	if (containBlocked) {
	    e.setCancelled(true);
	} else if (containRestricted) {
	    boolean isNextItemRing = RingBuilder.isRing(nextItem);
	    if (!isNextItemRing)
		e.setCancelled(true);
	}
    }

    public void handleClose(InventoryCloseEvent e) {
	List<Ring> rings = new ArrayList<>();
	for (Integer i : restrictedSlots) {
	    ItemStack item = e.getInventory().getItem(i.intValue());
	    if (item != null) rings.add(RingBuilder.getRingFromItem(item));
	}
	PData.setRings(rings);
    }

    protected void populateInventory() {
	max = PData.getRingsNumber();
	System.out.println(max);
	delta = (9-max)/2;
	ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	for (int i = 0; i < delta; i++) {
	    inv.setItem(i, pane);
	    blockedSlots.add(Integer.valueOf(i));
	    inv.setItem(9-1-i, pane);
	    blockedSlots.add(Integer.valueOf(9-1-i));
	}
	List<Ring> rings = PData.getRings();
	for (int i = 0; i < max; i++) {
	    ItemStack r = i < rings.size() ? RingBuilder.build(rings.get(i)) : new ItemStack(Material.AIR);
	    inv.setItem(i+delta, r);
	    restrictedSlots.add(Integer.valueOf(i+delta));
	}
    }
    
    private static boolean isContained(Set<Integer> set, Collection<Integer> coll) {
	for (Integer i : set) {
	    for (Integer i2 : coll) {
		if (i.intValue() == i2.intValue()) return true;
	    }
	}
	return false;
    }
}
