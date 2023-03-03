package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.items.enums.Rupees;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import org.bukkit.inventory.ItemStack;

public class ExchangeInventoryManager extends InventoryManager {

    public ExchangeInventoryManager() {
	createInventory(null, 9, "Exchange", CustomInventoryType.EXCHANGE);
	populateInventory();
    }

    protected void populateInventory() {
	ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
	inv.setItem(0, pane);
	inv.setItem(1, Rupees.GREEN.getRupee());
	inv.setItem(2, Rupees.BLUE.getRupee());
	inv.setItem(3, Rupees.YELLOW.getRupee());
	inv.setItem(4, Rupees.RED.getRupee());
	inv.setItem(5, Rupees.PURPLE.getRupee());
	inv.setItem(6, Rupees.SILVER.getRupee());
	inv.setItem(7, Rupees.GOLD.getRupee());
	inv.setItem(8, pane);
    }

    public void handleClick(InventoryClickEvent e) {
	Player p = (Player) e.getWhoClicked();
	Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(8) });
	ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
	if (blockedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
	    e.setCancelled(true);
	} else if (e.getClick() == ClickType.DOUBLE_CLICK && currItem.getType() == Material.AIR) {
	    e.setCancelled(true);
	} else {
	    ItemStack blue;
	    ItemStack silver;
	    switch (e.getRawSlot()) {
	    case 1:
		e.setCancelled(true);
		blue = Rupees.BLUE.getRupee();
		if (p.getInventory().containsAtLeast(blue, 1)) {
		    p.getInventory().removeItem(new ItemStack[] { blue });
		    ItemStack green = Rupees.GREEN.getRupee();
		    green.setAmount(5);
		    p.getInventory().addItem(new ItemStack[] { green });
		}
		break;
	    case 2:
		e.setCancelled(true);
		if (e.getClick() == ClickType.LEFT) {
		    ItemStack green = Rupees.GREEN.getRupee();
		    if (p.getInventory().containsAtLeast(green, 5)) {
			green.setAmount(5);
			p.getInventory().removeItem(new ItemStack[] { green });
			blue = Rupees.BLUE.getRupee();
			p.getInventory().addItem(new ItemStack[] { blue });
		    }
		    break;
		}
		if (e.getClick() == ClickType.RIGHT) {
		    ItemStack yellow = Rupees.YELLOW.getRupee();
		    if (p.getInventory().containsAtLeast(yellow, 1)) {
			p.getInventory().removeItem(new ItemStack[] { yellow });
			blue = Rupees.BLUE.getRupee();
			blue.setAmount(2);
			p.getInventory().addItem(new ItemStack[] { blue });
		    }
		}
		break;
	    case 3:
		e.setCancelled(true);
		if (e.getClick() == ClickType.LEFT) {
		    blue = Rupees.BLUE.getRupee();
		    if (p.getInventory().containsAtLeast(blue, 2)) {
			blue.setAmount(2);
			p.getInventory().removeItem(new ItemStack[] { blue });
			ItemStack yellow = Rupees.YELLOW.getRupee();
			p.getInventory().addItem(new ItemStack[] { yellow });
		    }
		    break;
		}
		if (e.getClick() == ClickType.RIGHT) {
		    ItemStack red = Rupees.RED.getRupee();
		    if (p.getInventory().containsAtLeast(red, 1)) {
			p.getInventory().removeItem(new ItemStack[] { red });
			ItemStack yellow = Rupees.YELLOW.getRupee();
			yellow.setAmount(2);
			p.getInventory().addItem(new ItemStack[] { yellow });
		    }
		}
		break;
	    case 4:
		e.setCancelled(true);
		if (e.getClick() == ClickType.LEFT) {
		    ItemStack yellow = Rupees.YELLOW.getRupee();
		    if (p.getInventory().containsAtLeast(yellow, 2)) {
			yellow.setAmount(2);
			p.getInventory().removeItem(new ItemStack[] { yellow });
			ItemStack red = Rupees.RED.getRupee();
			p.getInventory().addItem(new ItemStack[] { red });
		    }
		    break;
		}
		if (e.getClick() == ClickType.RIGHT) {
		    ItemStack purple = Rupees.PURPLE.getRupee();
		    if (p.getInventory().containsAtLeast(purple, 1)) {
			p.getInventory().removeItem(new ItemStack[] { purple });
			ItemStack red = Rupees.RED.getRupee();
			ItemStack yellow = Rupees.YELLOW.getRupee();
			red.setAmount(2);
			p.getInventory().addItem(new ItemStack[] { yellow });
			p.getInventory().addItem(new ItemStack[] { red });
		    }
		}
		break;
	    case 5:
		e.setCancelled(true);
		if (e.getClick() == ClickType.LEFT) {
		    ItemStack yellow = Rupees.YELLOW.getRupee();
		    ItemStack red = Rupees.RED.getRupee();
		    if (p.getInventory().containsAtLeast(yellow, 1) && p.getInventory().containsAtLeast(red, 2)) {
			red.setAmount(2);
			p.getInventory().removeItem(new ItemStack[] { yellow });
			p.getInventory().removeItem(new ItemStack[] { red });
			ItemStack purple = Rupees.PURPLE.getRupee();
			p.getInventory().addItem(new ItemStack[] { purple });
		    }
		    break;
		}
		if (e.getClick() == ClickType.RIGHT) {
		    ItemStack itemStack = Rupees.SILVER.getRupee();
		    if (p.getInventory().containsAtLeast(itemStack, 1)) {
			p.getInventory().removeItem(new ItemStack[] { itemStack });
			ItemStack purple = Rupees.PURPLE.getRupee();
			purple.setAmount(2);
			p.getInventory().addItem(new ItemStack[] { purple });
		    }
		}
		break;
	    case 6:
		e.setCancelled(true);
		if (e.getClick() == ClickType.LEFT) {
		    ItemStack purple = Rupees.PURPLE.getRupee();
		    if (p.getInventory().containsAtLeast(purple, 2)) {
			purple.setAmount(2);
			p.getInventory().removeItem(new ItemStack[] { purple });
			ItemStack itemStack = Rupees.SILVER.getRupee();
			p.getInventory().addItem(new ItemStack[] { itemStack });
		    }
		    break;
		}
		if (e.getClick() == ClickType.RIGHT) {
		    ItemStack gold = Rupees.GOLD.getRupee();
		    if (p.getInventory().containsAtLeast(gold, 1)) {
			p.getInventory().removeItem(new ItemStack[] { gold });
			ItemStack itemStack = Rupees.SILVER.getRupee();
			itemStack.setAmount(3);
			p.getInventory().addItem(new ItemStack[] { itemStack });
		    }
		}
		break;
	    case 7:
		e.setCancelled(true);
		silver = Rupees.SILVER.getRupee();
		if (p.getInventory().containsAtLeast(silver, 3)) {
		    silver.setAmount(3);
		    p.getInventory().removeItem(new ItemStack[] { silver });
		    ItemStack gold = Rupees.GOLD.getRupee();
		    p.getInventory().addItem(new ItemStack[] { gold });
		}
		break;
	    }
	}
    }

    public void handleDrag(InventoryDragEvent e) {
	Set<Integer> slots = e.getRawSlots();
	Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1),
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

    public void handleClose(InventoryCloseEvent e) {
	// TODO Auto-generated method stub
    }
}
