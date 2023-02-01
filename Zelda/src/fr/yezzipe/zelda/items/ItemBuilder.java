package fr.yezzipe.zelda.items;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Item;

import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
  public static ItemStack build(Item item) {
    ItemStack grappling_item;
    ItemMeta meta;
    NBTItem nbt;
    ItemStack dark_bow, electric_bow, fire_bow, ice_bow, light_bow, heart_piece, heart_container, crystal, customItem = null;
    switch (item) {
      case GRAPPLING_HOOK:
        grappling_item = new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK);
        meta = grappling_item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(Integer.valueOf(1));
        meta.setDisplayName("Grappling Hook");
        grappling_item.setItemMeta(meta);
        nbt = new NBTItem(grappling_item);
        nbt.setString("ItemType", "Grappling");
        customItem = nbt.getItem();
        break;
      case DARK_BOW:
        dark_bow = new ItemStack(Material.BOW);
        meta = dark_bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(Integer.valueOf(2));
        meta.setDisplayName("§5Dark Bow");
        dark_bow.setItemMeta(meta);
        nbt = new NBTItem(dark_bow);
        nbt.setString("ItemType", "Dark_Bow");
        customItem = nbt.getItem();
        break;
      case ELECTRIC_BOW:
        electric_bow = new ItemStack(Material.BOW);
        meta = electric_bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(Integer.valueOf(4));
        meta.setDisplayName("§eElectric Bow");
        electric_bow.setItemMeta(meta);
        nbt = new NBTItem(electric_bow);
        nbt.setString("ItemType", "Electric_Bow");
        customItem = nbt.getItem();
        break;
      case FIRE_BOW:
        fire_bow = new ItemStack(Material.BOW);
        meta = fire_bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(Integer.valueOf(5));
        meta.setDisplayName("§cFire Bow");
        fire_bow.setItemMeta(meta);
        nbt = new NBTItem(fire_bow);
        nbt.setString("ItemType", "Fire_Bow");
        customItem = nbt.getItem();
        break;
      case ICE_BOW:
        ice_bow = new ItemStack(Material.BOW);
        meta = ice_bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(Integer.valueOf(3));
        meta.setDisplayName("§bIce Bow");
        ice_bow.setItemMeta(meta);
        nbt = new NBTItem(ice_bow);
        nbt.setString("ItemType", "Ice_Bow");
        customItem = nbt.getItem();
        break;
      case LIGHT_BOW:
        light_bow = new ItemStack(Material.BOW);
        meta = light_bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setCustomModelData(Integer.valueOf(1));
        meta.setDisplayName("§6Light Bow");
        light_bow.setItemMeta(meta);
        nbt = new NBTItem(light_bow);
        nbt.setString("ItemType", "Light_Bow");
        customItem = nbt.getItem();
        break;
      case HEART_PIECE:
        heart_piece = new ItemStack(Material.SLIME_BALL);
        meta = heart_piece.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(2));
        meta.setDisplayName("§cHeart Piece");
        heart_piece.setItemMeta(meta);
        nbt = new NBTItem(heart_piece);
        nbt.setString("ItemType", "Heart_Piece");
        customItem = nbt.getItem();
        break;
      case HEART_CONTAINER:
        heart_container = new ItemStack(Material.SLIME_BALL);
        meta = heart_container.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(3));
        meta.setDisplayName("§cHeart Container");
        heart_container.setItemMeta(meta);
        nbt = new NBTItem(heart_container);
        nbt.setString("ItemType", "Heart_Container");
        customItem = nbt.getItem();
        break;
      case SHADOW_CRYSTAL:
        crystal = new ItemStack(Material.PHANTOM_MEMBRANE);
        meta = crystal.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(1));
        meta.setDisplayName("§8Shadow Crystal");
        crystal.setItemMeta(meta);
        nbt = new NBTItem(crystal);
        nbt.setString("ItemType", "Shadow_Crystal");
        nbt.setString("AntiStack", UUID.randomUUID().toString());
        customItem = nbt.getItem();
        break;
    } 
    return customItem;
  }
}
