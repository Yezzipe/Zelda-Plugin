package fr.yezzipe.zelda.inventory;

import fr.yezzipe.zelda.items.RupeeBuilder;
import fr.yezzipe.zelda.items.enums.Rupees;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryManager {
  public static HashMap<Inventory, UUID> InventoryUUID = new HashMap<>();
  
  public static HashMap<UUID, CustomInventoryType> UUIDTypes = new HashMap<>();
  
  public static Inventory createInventory(InventoryHolder holder, int size, String title, CustomInventoryType type) {
    Inventory inv = Bukkit.createInventory(holder, size, title);
    UUID uuid = UUID.randomUUID();
    InventoryUUID.put(inv, uuid);
    UUIDTypes.put(uuid, type);
    return inv;
  }
  
  public static ItemStack guiItemBuilder(Material material, String displayName) {
    ItemStack item = new ItemStack(material);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayName);
    item.setItemMeta(meta);
    return item;
  }
  
  public static void populateExchange(Inventory inv, Player p) {
    ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    ItemStack green = RupeeBuilder.build(Rupees.GREEN);
    ItemStack blue = RupeeBuilder.build(Rupees.BLUE);
    ItemStack yellow = RupeeBuilder.build(Rupees.YELLOW);
    ItemStack red = RupeeBuilder.build(Rupees.RED);
    ItemStack purple = RupeeBuilder.build(Rupees.PURPLE);
    ItemStack silver = RupeeBuilder.build(Rupees.SILVER);
    ItemStack gold = RupeeBuilder.build(Rupees.GOLD);
    inv.setItem(0, pane);
    inv.setItem(1, green);
    inv.setItem(2, blue);
    inv.setItem(3, yellow);
    inv.setItem(4, red);
    inv.setItem(5, purple);
    inv.setItem(6, silver);
    inv.setItem(7, gold);
    inv.setItem(8, pane);
    p.openInventory(inv);
  }
}

