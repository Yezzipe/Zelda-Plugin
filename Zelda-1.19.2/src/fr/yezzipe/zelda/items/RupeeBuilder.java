package fr.yezzipe.zelda.items;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Rupees;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RupeeBuilder {
  public static ItemStack build(Rupees rupee) {
    ItemStack blue;
    ItemMeta meta;
    NBTItem nbt;
    ItemStack gold, green, purple, red, silver, yellow, customItem = null;
    switch (rupee) {
      case BLUE:
        blue = new ItemStack(Material.EMERALD);
        meta = blue.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(2));
        meta.setDisplayName("§9Blue Rupee");
        blue.setItemMeta(meta);
        nbt = new NBTItem(blue);
        nbt.setString("ItemType", "Blue_Rupee");
        customItem = nbt.getItem();
        break;
      case GOLD:
        gold = new ItemStack(Material.EMERALD);
        meta = gold.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(7));
        meta.setDisplayName("§6Gold Rupee");
        gold.setItemMeta(meta);
        nbt = new NBTItem(gold);
        nbt.setString("ItemType", "Gold_Rupee");
        customItem = nbt.getItem();
        break;
      case GREEN:
        green = new ItemStack(Material.EMERALD);
        meta = green.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(1));
        meta.setDisplayName("§aGreen Rupee");
        green.setItemMeta(meta);
        nbt = new NBTItem(green);
        nbt.setString("ItemType", "Green_Rupee");
        customItem = nbt.getItem();
        break;
      case PURPLE:
        purple = new ItemStack(Material.EMERALD);
        meta = purple.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(5));
        meta.setDisplayName("§5Purple Rupee");
        purple.setItemMeta(meta);
        nbt = new NBTItem(purple);
        nbt.setString("ItemType", "Purple_Rupee");
        customItem = nbt.getItem();
        break;
      case RED:
        red = new ItemStack(Material.EMERALD);
        meta = red.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(4));
        meta.setDisplayName("§4Red Rupee");
        red.setItemMeta(meta);
        nbt = new NBTItem(red);
        nbt.setString("ItemType", "Red_Rupee");
        customItem = nbt.getItem();
        break;
      case SILVER:
        silver = new ItemStack(Material.EMERALD);
        meta = silver.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(6));
        meta.setDisplayName("§7Silver Rupee");
        silver.setItemMeta(meta);
        nbt = new NBTItem(silver);
        nbt.setString("ItemType", "Silver_Rupee");
        customItem = nbt.getItem();
        break;
      case YELLOW:
        yellow = new ItemStack(Material.EMERALD);
        meta = yellow.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(3));
        meta.setDisplayName("§eYellow Rupee");
        yellow.setItemMeta(meta);
        nbt = new NBTItem(yellow);
        nbt.setString("ItemType", "Yellow_Rupee");
        customItem = nbt.getItem();
        break;
    } 
    return customItem;
  }
}
