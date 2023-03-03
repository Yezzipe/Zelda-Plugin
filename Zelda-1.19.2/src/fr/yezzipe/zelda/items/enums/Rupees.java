package fr.yezzipe.zelda.items.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public enum Rupees {
    GREEN(1, "§aGreen Rupee"), BLUE(2, "§9Blue Rupee"), YELLOW(3, "§eYellow Rupee"), RED(4, "§4Red Rupee"),
    PURPLE(5, "§5Purple Rupee"), SILVER(6, "§7Silver Rupee"), GOLD(7, "§6Gold Rupee");

    private int modelData;
    private String displayName;

    private Rupees(int modelData, String displayName) {
	this.modelData = modelData;
	this.displayName = displayName;
    }

    public ItemStack getRupee() {
	ItemStack item = new ItemStack(Material.EMERALD);
	ItemMeta meta = item.getItemMeta();
	meta.setCustomModelData(Integer.valueOf(this.modelData));
	meta.setDisplayName(this.displayName);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("RupeeType", this.toString());
	return nbt.getItem();
    }
    
    public static boolean isRupee(ItemStack item) {
	if (item == null || item.getType() == Material.AIR) return false;
	NBTItem nbt = new NBTItem(item);
	return nbt.getKeys().contains("RupeeType");
    }
}
