package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.items.RingBuilder;
import fr.yezzipe.zelda.items.enums.Ring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RingManager extends InventoryManager {
  public static boolean isRingInventory(Inventory inv) {
    UUID uuid = InventoryUUID.get(inv);
    if (uuid == null)
      return false; 
    if (UUIDTypes.get(uuid) != CustomInventoryType.RINGS)
      return false; 
    return true;
  }
  
  public static void handleClick(InventoryClickEvent e) {
    boolean shift = e.isShiftClick();
    Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8) });
    Collection<Integer> restrictedSlots = Arrays.asList(new Integer[] { Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5) });
    ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
    ItemStack nextItem = (e.getCursor() == null) ? new ItemStack(Material.AIR) : e.getCursor();
    boolean iscurrItemRing = RingBuilder.isRing(currItem);
    boolean isnextItemRing = RingBuilder.isRing(nextItem);
    if (blockedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
      e.setCancelled(true);
    } else if (shift && !iscurrItemRing && nextItem.getType() == Material.AIR) {
      e.setCancelled(true);
    } else if (restrictedSlots.contains(Integer.valueOf(e.getRawSlot())) && !isnextItemRing && nextItem.getType() != Material.AIR) {
      e.setCancelled(true);
    } else if (e.getClick() == ClickType.DOUBLE_CLICK && currItem.getType() == Material.AIR) {
      e.setCancelled(true);
    } 
  }
  
  public static void handleDrag(InventoryDragEvent e) {
    ItemStack nextItem = (e.getOldCursor() == null) ? new ItemStack(Material.AIR) : e.getOldCursor();
    Set<Integer> slots = e.getRawSlots();
    if (slots.contains(Integer.valueOf(0)) || slots.contains(Integer.valueOf(1)) || slots.contains(Integer.valueOf(2)) || slots.contains(Integer.valueOf(6)) || slots.contains(Integer.valueOf(7)) || slots.contains(Integer.valueOf(8))) {
      e.setCancelled(true);
    } else if (slots.contains(Integer.valueOf(3)) || slots.contains(Integer.valueOf(4)) || slots.contains(Integer.valueOf(5))) {
      boolean isNextItemRing = RingBuilder.isRing(nextItem);
      if (!isNextItemRing)
        e.setCancelled(true); 
    } 
  }
  
  public static void handleClose(InventoryCloseEvent e) {
    Player p = (Player)e.getPlayer();
    PlayerData PData = PlayerData.getData(p);
    ItemStack item1 = e.getInventory().getItem(3);
    ItemStack item2 = e.getInventory().getItem(4);
    ItemStack item3 = e.getInventory().getItem(5);
    Ring r1 = (item1 == null) ? null : RingBuilder.getRingFromItem(item1);
    Ring r2 = (item2 == null) ? null : RingBuilder.getRingFromItem(item2);
    Ring r3 = (item3 == null) ? null : RingBuilder.getRingFromItem(item3);
    List<Ring> rings = new ArrayList<>();
    if (r1 != null)
      rings.add(r1); 
    if (r2 != null)
      rings.add(r2); 
    if (r3 != null)
      rings.add(r3); 
    PData.setRings(rings);
  }
  
  public static void populateRings(Inventory inv, Player p) {
    PlayerData PData = PlayerData.getData(p);
    ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    inv.setItem(0, pane);
    inv.setItem(1, pane);
    inv.setItem(2, pane);
    inv.setItem(6, pane);
    inv.setItem(7, pane);
    inv.setItem(8, pane);
    List<Ring> rings = PData.getRings();
    ItemStack i1 = new ItemStack(Material.AIR);
    ItemStack i2 = new ItemStack(Material.AIR);
    ItemStack i3 = new ItemStack(Material.AIR);
    if (rings != null)
      if (rings.size() >= 3) {
        i1 = RingBuilder.build(rings.get(0));
        i2 = RingBuilder.build(rings.get(1));
        i3 = RingBuilder.build(rings.get(2));
      } else if (rings.size() == 2) {
        i1 = RingBuilder.build(rings.get(0));
        i2 = RingBuilder.build(rings.get(1));
      } else if (rings.size() == 1) {
        i1 = RingBuilder.build(rings.get(0));
      }  
    inv.setItem(3, i1);
    inv.setItem(4, i2);
    inv.setItem(5, i3);
    p.openInventory(inv);
  }
}

