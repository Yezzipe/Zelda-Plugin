package fr.yezzipe.zelda.items;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Drop;

import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DropBuilder {
  public static ItemStack build(Drop item) {
    ItemStack heart;
    ItemMeta meta;
    NBTItem nbt;
    ItemStack customItem = null;
    switch (item) {
      case HEART:
        heart = new ItemStack(Material.SLIME_BALL);
        meta = heart.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(1));
        meta.setDisplayName("§cHeart");
        heart.setItemMeta(meta);
        nbt = new NBTItem(heart);
        nbt.setString("DropType", Drop.HEART.toString());
        nbt.setString("AntiStack", UUID.randomUUID().toString());
        customItem = nbt.getItem();
        break;
    } 
    return customItem;
  }
}

