package fr.yezzipe.zelda.items.enums;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public enum Drop {
    HEART(1, "§cHeart");
    
    private int modelData;
    private String displayName;
    
    private Drop(int modelData, String displayName) {
	this.modelData = modelData;
	this.displayName = displayName;
    }
    
    public ItemStack getDrop() {
	ItemStack item = new ItemStack(Material.SLIME_BALL);
	ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(modelData));
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        NBTItem nbt = new NBTItem(item);
        nbt.setString("DropType", this.toString());
        nbt.setString("AntiStack", UUID.randomUUID().toString());
        return nbt.getItem();
    }
}
