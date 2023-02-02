package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.entity.player.PlayerData;
import fr.yezzipe.zelda.territory.Waypoint;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ShadowCrystalManager extends InventoryManager {
  public static void handleClick(InventoryClickEvent e) {
    HumanEntity ent = e.getWhoClicked();
    final Player p = (Player)ent;
    PlayerData PData = PlayerData.getData(p);
    List<Waypoint> waypoints = PData.getWaypoints();
    Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8) });
    ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
    if (blockedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
      e.setCancelled(true);
    } else if (e.getClick() == ClickType.DOUBLE_CLICK && currItem.getType() == Material.AIR) {
      e.setCancelled(true);
    } else {
      e.setCancelled(true);
      switch (e.getRawSlot()) {
        case 3:
          if (e.getClick() == ClickType.LEFT) {
            if (waypoints != null && 
              waypoints.size() > 0) {
              final Waypoint waypoint = waypoints.get(0);
              teleportToWaypoint(p, waypoint);
            } 
            break;
          } 
          if (e.getClick() == ClickType.SHIFT_RIGHT && 
            waypoints != null && 
            waypoints.size() > 0) {
            waypoints.remove(0);
            PData.setWaypoints(waypoints);
            Inventory inv = e.getInventory();
            ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            inv.setItem(0, pane);
            inv.setItem(1, pane);
            inv.setItem(2, pane);
            inv.setItem(6, pane);
            inv.setItem(7, pane);
            inv.setItem(8, pane);
            waypoints = PData.getWaypoints();
            ItemStack i1 = new ItemStack(Material.AIR);
            ItemStack i2 = new ItemStack(Material.AIR);
            ItemStack i3 = new ItemStack(Material.AIR);
            if (waypoints != null)
              if (waypoints.size() >= 3) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
                i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
                i3 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(2)).getName());
              } else if (waypoints.size() == 2) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
                i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
              } else if (waypoints.size() == 1) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
              }  
            inv.setItem(3, i1);
            inv.setItem(4, i2);
            inv.setItem(5, i3);
            Random rand = new Random();
            int randomElement = rand.nextInt(2) + 1;
            p.playSound(p.getLocation(), "zelda.midna.mmm." + Integer.toString(randomElement), SoundCategory.PLAYERS, 1000.0F, 1.0F);
          } 
          break;
        case 4:
          if (e.getClick() == ClickType.LEFT) {
            if (waypoints != null && 
              waypoints.size() > 1) {
              final Waypoint waypoint = waypoints.get(1);
              teleportToWaypoint(p, waypoint);
            } 
            break;
          } 
          if (e.getClick() == ClickType.SHIFT_RIGHT && 
            waypoints != null && 
            waypoints.size() > 1) {
            waypoints.remove(1);
            PData.setWaypoints(waypoints);
            Inventory inv = e.getInventory();
            ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            inv.setItem(0, pane);
            inv.setItem(1, pane);
            inv.setItem(2, pane);
            inv.setItem(6, pane);
            inv.setItem(7, pane);
            inv.setItem(8, pane);
            waypoints = PData.getWaypoints();
            ItemStack i1 = new ItemStack(Material.AIR);
            ItemStack i2 = new ItemStack(Material.AIR);
            ItemStack i3 = new ItemStack(Material.AIR);
            if (waypoints != null)
              if (waypoints.size() >= 3) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
                i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
                i3 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(2)).getName());
              } else if (waypoints.size() == 2) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
                i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
              } else if (waypoints.size() == 1) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
              }  
            inv.setItem(3, i1);
            inv.setItem(4, i2);
            inv.setItem(5, i3);
            Random rand = new Random();
            int randomElement = rand.nextInt(2) + 1;
            p.playSound(p.getLocation(), "zelda.midna.mmm." + Integer.toString(randomElement), SoundCategory.PLAYERS, 1000.0F, 1.0F);
          } 
          break;
        case 5:
          if (e.getClick() == ClickType.LEFT) {
            if (waypoints != null && 
              waypoints.size() > 2) {
              final Waypoint waypoint = waypoints.get(2);
              teleportToWaypoint(p, waypoint);
            } 
            break;
          } 
          if (e.getClick() == ClickType.SHIFT_RIGHT && 
            waypoints != null && 
            waypoints.size() > 2) {
            waypoints.remove(2);
            PData.setWaypoints(waypoints);
            Inventory inv = e.getInventory();
            ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            inv.setItem(0, pane);
            inv.setItem(1, pane);
            inv.setItem(2, pane);
            inv.setItem(6, pane);
            inv.setItem(7, pane);
            inv.setItem(8, pane);
            waypoints = PData.getWaypoints();
            ItemStack i1 = new ItemStack(Material.AIR);
            ItemStack i2 = new ItemStack(Material.AIR);
            ItemStack i3 = new ItemStack(Material.AIR);
            if (waypoints != null)
              if (waypoints.size() >= 3) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
                i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
                i3 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(2)).getName());
              } else if (waypoints.size() == 2) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
                i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
              } else if (waypoints.size() == 1) {
                i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
              }  
            inv.setItem(3, i1);
            inv.setItem(4, i2);
            inv.setItem(5, i3);
            Random rand = new Random();
            int randomElement = rand.nextInt(2) + 1;
            p.playSound(p.getLocation(), "zelda.midna.mmm." + Integer.toString(randomElement), SoundCategory.PLAYERS, 1000.0F, 1.0F);
          } 
          break;
      } 
    } 
  }
  
  public static void handleDrag(InventoryDragEvent e) {
    Set<Integer> slots = e.getRawSlots();
    Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8) });
    for (Iterator<Integer> iterator = slots.iterator(); iterator.hasNext(); ) {
      int slot = ((Integer)iterator.next()).intValue();
      if (blockedSlots.contains(Integer.valueOf(slot))) {
        e.setCancelled(true);
        break;
      } 
    } 
  }
  
  public static void handleClose(InventoryCloseEvent e) {
    HumanEntity ent = e.getPlayer();
    Player p = (Player)ent;
    p.playSound(p.getLocation(), "zelda.midna.vanish", SoundCategory.PLAYERS, 1000.0F, 1.0F);
  }
  
  public static boolean isShadowCrystal(Inventory inv) {
    UUID uuid = InventoryUUID.get(inv);
    if (uuid == null)
      return false; 
    if (UUIDTypes.get(uuid) != CustomInventoryType.SHADOW_CRYSTAL)
      return false; 
    return true;
  }
  
  public static void populateShadowCrystal(Inventory inv, Player p) {
    UUID uuid = InventoryUUID.get(inv);
    if (uuid != null) {
      CustomInventoryType type = UUIDTypes.get(uuid);
      if (type == CustomInventoryType.SHADOW_CRYSTAL) {
        PlayerData PData = PlayerData.getData(p);
        ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        inv.setItem(0, pane);
        inv.setItem(1, pane);
        inv.setItem(2, pane);
        inv.setItem(6, pane);
        inv.setItem(7, pane);
        inv.setItem(8, pane);
        List<Waypoint> waypoints = PData.getWaypoints();
        ItemStack i1 = new ItemStack(Material.AIR);
        ItemStack i2 = new ItemStack(Material.AIR);
        ItemStack i3 = new ItemStack(Material.AIR);
        if (waypoints != null)
          if (waypoints.size() >= 3) {
            i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
            i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
            i3 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(2)).getName());
          } else if (waypoints.size() == 2) {
            i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
            i2 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(1)).getName());
          } else if (waypoints.size() == 1) {
            i1 = guiItemBuilder(Material.ENDER_EYE, ((Waypoint)waypoints.get(0)).getName());
          }  
        inv.setItem(3, i1);
        inv.setItem(4, i2);
        inv.setItem(5, i3);
        p.openInventory(inv);
      } 
    } 
  }
  
  public static void teleportToWaypoint(Player p, Waypoint w) {
      p.closeInventory();
      p.stopSound("zelda.midna.vanish", SoundCategory.PLAYERS);
      p.playSound(p.getLocation(), "zelda.warp.in", SoundCategory.PLAYERS, 1000.0F, 1.0F);
      PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 130, 255);
      PlayerData PData = PlayerData.getData(p);
      PData.addEffect(effect);
      p.addPotionEffect(effect);
      (new BukkitRunnable() {
          public void run() {
            p.teleport(w.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
            p.playSound(p.getLocation(), "zelda.warp.out", SoundCategory.PLAYERS, 1000.0F, 1.0F);
          }
        }).runTaskLater((Plugin)Main.getPlugin(Main.class), 60L);
  }
}

