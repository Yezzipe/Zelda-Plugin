package fr.yezzipe.zelda.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.CustomBlock;
import fr.yezzipe.zelda.items.FoodBuilder;
import fr.yezzipe.zelda.items.FoodStatCalculator;
import fr.yezzipe.zelda.items.IngredientBuilder;
import fr.yezzipe.zelda.items.enums.Food;
import fr.yezzipe.zelda.items.enums.Ingredient;
import fr.yezzipe.zelda.items.recipe.Recipe;

public class CookingInventoryManager extends InventoryManager {

    @SuppressWarnings("unused")
    private Player p;
    
    private CustomBlock cb;

    private Collection<Integer> blockedSlots = Arrays
	    .asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(7), Integer.valueOf(8) });

    private Collection<Integer> restrictedSlots = Arrays.asList(new Integer[] { Integer.valueOf(2), Integer.valueOf(3),
	    Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6) });

    public CookingInventoryManager(Player p, CustomBlock cb) {
	this.cb = cb;
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
	} else if (nextItem.getAmount() > 1 && restrictedSlots.contains(Integer.valueOf(e.getRawSlot()))
		&& !iscurrItemRing) {
	    e.setCancelled(true);
	    currItem = nextItem.clone();
	    currItem.setAmount(1);
	    e.setCurrentItem(currItem);
	    nextItem.setAmount(nextItem.getAmount() - 1);
	} else if (iscurrItemRing && isnextItemRing && restrictedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
	    e.setCancelled(true);
	} else if (shift && iscurrItemRing && nextItem.getType() == Material.AIR
		&& !restrictedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
	    e.setCancelled(true);
	    System.out.println(currItem);
	    if (inv.firstEmpty() > -1) {
		ItemStack temp = currItem.clone();
		currItem.setAmount(currItem.getAmount() - 1);
		temp.setAmount(1);
		inv.setItem(inv.firstEmpty(), temp);
	    }
	}
    }

    public void handleDrag(InventoryDragEvent e) {
	ItemStack nextItem = (e.getOldCursor() == null) ? new ItemStack(Material.AIR) : e.getOldCursor();
	Set<Integer> slots = e.getRawSlots();
	boolean containBlocked = isContained(slots, blockedSlots);
	boolean containRestricted = isContained(slots, restrictedSlots);
	Map<Integer, ItemStack> map = e.getNewItems();
	if (containBlocked) {
	    e.setCancelled(true);
	} else if (containRestricted) {
	    boolean isNextItem = IngredientBuilder.isIngredient(nextItem);
	    
	    if (isNextItem) {
		int i = 0;
		for (int slot : map.keySet()) {
		    ItemStack temp = map.get(slot);
		    if (restrictedSlots.contains(slot)) {
			temp.setAmount(1);
			inv.setItem(slot, temp);
			i++;
		    }
		}
		nextItem.setAmount(nextItem.getAmount() - i);
		if (map.keySet().size() - i > 0) {
		    int x = nextItem.getAmount() / (map.keySet().size() - i);
		    int t = 0;
		    for (int slot : map.keySet()) {
			ItemStack temp = map.get(slot);
			if (!restrictedSlots.contains(slot)) {
			    if (temp.getAmount() > 1) {
				temp.setAmount(x);
			    	t += x;
			    } else {
				t++;
			    }
			    e.getView().getBottomInventory().setItem(slot, temp);
			}
		    }
		    nextItem.setAmount(nextItem.getAmount() - t);
		}
		System.out.println(nextItem.getAmount());
		System.out.println(nextItem);
		Bukkit.getScheduler().runTaskLater((Plugin) Main.getInstance(), new Runnable() {

		    @Override
		    public void run() {
			e.getView().setCursor(nextItem);
			
		    }
		    
		}, 1);
		e.setCancelled(true);
	    }

	}
    }

    public void handleClose(InventoryCloseEvent e) {
	Collection<Ingredient> ingredients = new ArrayList<Ingredient>();
	for (int i = 2; i < 7; i++) {
	    ItemStack item = inv.getItem(i);
	    if (item == null)
		continue;
	    if (item.getType() == Material.AIR)
		continue;
	    NBTItem nbt = new NBTItem(item);
	    if (nbt.getKeys().contains("IngredientType"))
		ingredients.add(Ingredient.valueOf(nbt.getString("IngredientType")));
	}
	Recipe r = Recipe.getRecipe(ingredients);
	if (r != null) {
	    cb.getBlock().getWorld().dropItemNaturally(cb.getBlock().getLocation(), FoodBuilder.build(r.getOutput()));
	    new FoodStatCalculator(ingredients, r.getOutput());
	} else {
	    for (Ingredient i : ingredients) {
		 cb.getBlock().getWorld().dropItemNaturally(cb.getBlock().getLocation(), IngredientBuilder.build(i));
	    }
	}
	// Play Sound
    }

    private static boolean isContained(Set<Integer> set, Collection<Integer> coll) {
	for (Integer i : set) {
	    for (Integer i2 : coll) {
		if (i.intValue() == i2.intValue())
		    return true;
	    }
	}
	return false;
    }

}
