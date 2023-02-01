package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.items.RupeeBuilder;
import fr.yezzipe.zelda.items.enums.Rupees;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExchangeManager extends InventoryManager {
  public static boolean isExchangeInventory(Inventory inv) {
    UUID uuid = InventoryUUID.get(inv);
    if (uuid == null)
      return false; 
    if (UUIDTypes.get(uuid) != CustomInventoryType.EXCHANGE)
      return false; 
    return true;
  }
  
  public static void handleClick(InventoryClickEvent e) {
    Player p = (Player)e.getWhoClicked();
    List<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(8) });
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
          blue = RupeeBuilder.build(Rupees.BLUE);
          if (p.getInventory().containsAtLeast(blue, 1)) {
            p.getInventory().removeItem(new ItemStack[] { blue });
            ItemStack green = RupeeBuilder.build(Rupees.GREEN);
            green.setAmount(5);
            p.getInventory().addItem(new ItemStack[] { green });
          } 
          break;
        case 2:
          e.setCancelled(true);
          if (e.getClick() == ClickType.LEFT) {
            ItemStack green = RupeeBuilder.build(Rupees.GREEN);
            if (p.getInventory().containsAtLeast(green, 5)) {
              green.setAmount(5);
              p.getInventory().removeItem(new ItemStack[] { green });
              blue = RupeeBuilder.build(Rupees.BLUE);
              p.getInventory().addItem(new ItemStack[] { blue });
            } 
            break;
          } 
          if (e.getClick() == ClickType.RIGHT) {
            ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
            if (p.getInventory().containsAtLeast(yellow, 1)) {
              p.getInventory().removeItem(new ItemStack[] { yellow });
              blue = RupeeBuilder.build(Rupees.BLUE);
              blue.setAmount(2);
              p.getInventory().addItem(new ItemStack[] { blue });
            } 
          } 
          break;
        case 3:
          e.setCancelled(true);
          if (e.getClick() == ClickType.LEFT) {
            blue = RupeeBuilder.build(Rupees.BLUE);
            if (p.getInventory().containsAtLeast(blue, 2)) {
              blue.setAmount(2);
              p.getInventory().removeItem(new ItemStack[] { blue });
              ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
              p.getInventory().addItem(new ItemStack[] { yellow });
            } 
            break;
          } 
          if (e.getClick() == ClickType.RIGHT) {
            ItemStack red = RupeeBuilder.build(Rupees.RED);
            if (p.getInventory().containsAtLeast(red, 1)) {
              p.getInventory().removeItem(new ItemStack[] { red });
              ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
              yellow.setAmount(2);
              p.getInventory().addItem(new ItemStack[] { yellow });
            } 
          } 
          break;
        case 4:
          e.setCancelled(true);
          if (e.getClick() == ClickType.LEFT) {
            ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
            if (p.getInventory().containsAtLeast(yellow, 2)) {
              yellow.setAmount(2);
              p.getInventory().removeItem(new ItemStack[] { yellow });
              ItemStack red = RupeeBuilder.build(Rupees.RED);
              p.getInventory().addItem(new ItemStack[] { red });
            } 
            break;
          } 
          if (e.getClick() == ClickType.RIGHT) {
            ItemStack purple = RupeeBuilder.build(Rupees.PURPLE);
            if (p.getInventory().containsAtLeast(purple, 1)) {
              p.getInventory().removeItem(new ItemStack[] { purple });
              ItemStack red = RupeeBuilder.build(Rupees.RED);
              ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
              red.setAmount(2);
              p.getInventory().addItem(new ItemStack[] { yellow });
              p.getInventory().addItem(new ItemStack[] { red });
            } 
          } 
          break;
        case 5:
          e.setCancelled(true);
          if (e.getClick() == ClickType.LEFT) {
            ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
            ItemStack red = RupeeBuilder.build(Rupees.RED);
            if (p.getInventory().containsAtLeast(yellow, 1) && p.getInventory().containsAtLeast(red, 2)) {
              red.setAmount(2);
              p.getInventory().removeItem(new ItemStack[] { yellow });
              p.getInventory().removeItem(new ItemStack[] { red });
              ItemStack purple = RupeeBuilder.build(Rupees.PURPLE);
              p.getInventory().addItem(new ItemStack[] { purple });
            } 
            break;
          } 
          if (e.getClick() == ClickType.RIGHT) {
            ItemStack itemStack = RupeeBuilder.build(Rupees.SILVER);
            if (p.getInventory().containsAtLeast(itemStack, 1)) {
              p.getInventory().removeItem(new ItemStack[] { itemStack });
              ItemStack purple = RupeeBuilder.build(Rupees.PURPLE);
              purple.setAmount(2);
              p.getInventory().addItem(new ItemStack[] { purple });
            } 
          } 
          break;
        case 6:
          e.setCancelled(true);
          if (e.getClick() == ClickType.LEFT) {
            ItemStack purple = RupeeBuilder.build(Rupees.PURPLE);
            if (p.getInventory().containsAtLeast(purple, 2)) {
              purple.setAmount(2);
              p.getInventory().removeItem(new ItemStack[] { purple });
              ItemStack itemStack = RupeeBuilder.build(Rupees.SILVER);
              p.getInventory().addItem(new ItemStack[] { itemStack });
            } 
            break;
          } 
          if (e.getClick() == ClickType.RIGHT) {
            ItemStack gold = RupeeBuilder.build(Rupees.GOLD);
            if (p.getInventory().containsAtLeast(gold, 1)) {
              p.getInventory().removeItem(new ItemStack[] { gold });
              ItemStack itemStack = RupeeBuilder.build(Rupees.SILVER);
              itemStack.setAmount(3);
              p.getInventory().addItem(new ItemStack[] { itemStack });
            } 
          } 
          break;
        case 7:
          e.setCancelled(true);
          silver = RupeeBuilder.build(Rupees.SILVER);
          if (p.getInventory().containsAtLeast(silver, 3)) {
            silver.setAmount(3);
            p.getInventory().removeItem(new ItemStack[] { silver });
            ItemStack gold = RupeeBuilder.build(Rupees.GOLD);
            p.getInventory().addItem(new ItemStack[] { gold });
          } 
          break;
      } 
    } 
  }
  
  public static void handleDrag(InventoryDragEvent e) {
    Set<Integer> slots = e.getRawSlots();
    List<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8) });
    for (Iterator<Integer> iterator = slots.iterator(); iterator.hasNext(); ) {
      int slot = ((Integer)iterator.next()).intValue();
      if (blockedSlots.contains(Integer.valueOf(slot))) {
        e.setCancelled(true);
        break;
      } 
    } 
  }
}
