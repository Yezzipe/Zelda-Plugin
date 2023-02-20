package fr.yezzipe.zelda.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.IngredientBuilder;
import fr.yezzipe.zelda.items.enums.Ingredient;

public class CookingInventoryManager extends InventoryManager {
    
    @SuppressWarnings("unused")
    private Player p;
    
    private Collection<Integer> blockedSlots = Arrays.asList(new Integer[] {Integer.valueOf(0),Integer.valueOf(1),Integer.valueOf(7),Integer.valueOf(8)});
    
    private Collection<Integer> restrictedSlots = Arrays.asList(new Integer[] {Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(4),Integer.valueOf(5),Integer.valueOf(6)});
    
    public CookingInventoryManager(Player p) {
	this.p = p;
	createInventory(null, 9, "Campfire", CustomInventoryType.COOKING);
	populateInventory();
    }

    protected void populateInventory() {
	ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	inv.setItem(0, pane);
	inv.setItem(1, pane);
	inv.setItem(7, pane);
	inv.setItem(8, pane);
    }
    
    public void handleClick(InventoryClickEvent e) {
	boolean shift = e.isShiftClick();
	ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
	ItemStack nextItem = (e.getCursor() == null) ? new ItemStack(Material.AIR) : e.getCursor();
	boolean iscurrItemRing = IngredientBuilder.isIngredient(currItem);
	boolean isnextItemRing = IngredientBuilder.isIngredient(nextItem);
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
	    boolean isNextItem = IngredientBuilder.isIngredient(nextItem);
	    if (!isNextItem)
		e.setCancelled(true);
	}
    }

    public void handleClose(InventoryCloseEvent e) {
	Collection<Ingredient> ingredients = new ArrayList<Ingredient>();
	for (int i = 2; i < 7; i++) {
	    ItemStack item = inv.getItem(i);
	    if (item == null) continue;
	    if (item.getType() == Material.AIR) continue;
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("IngredientType")) ingredients.add(Ingredient.valueOf(nbt.getString("IngredientType")));
	}
	// Call craft
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
