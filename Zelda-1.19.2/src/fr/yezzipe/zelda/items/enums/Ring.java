package fr.yezzipe.zelda.items.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public enum Ring {
    ARMOR_RING_1(2, "§aArmor Ring I", "§aReduces Physical Damage by 5%"),
    AGILITY_RING(1, "§aAgility Ring", "§aGrants Speed"),
    ARMOR_RING_2(3, "§eArmor Ring II", "§eReduces Physical Damage by 10%"),
    INVISIBILITY_RING(7, "§eInvisibility Ring", "§eGrants Invisibility"),
    ARMOR_RING_3(4, "§6Armor Ring III", "§6Reduces Physical Damage by 15%"),
    STRENGTH_RING_1(9, "§aStrength Ring I", "§aBoosts Physical Damage by 5%"),
    STRENGTH_RING_2(10, "§eStrength Ring II", "§eBoosts Physical Damage by 10%"),
    STRENGTH_RING_3(11, "§6Strength Ring III", "§6Boosts Physical Damage by 15%"),
    FEATHER_RING(5, "§6Feather Ring", "§6Negate 50% of Fall Damage"),
    ZORA_RING(12, "§cZora Ring", "§cGrants Dolphin's Grace"), GORON_RING(6, "§cGoron Ring", "§cGrants Fire Resistance"),
    KOKIRI_RING(8, "§cKokiri Ring", "§cGrants Regeneration"), HASTE_RING_1(17, "§eHaste Ring I", "§eGrants Haste"),
    HASTE_RING_2(18, "§6Haste Ring II", "§6Grants Haste 2 Times"),
    LIGHTNING_RING_1(27, "§aLightning Ring I", "§aBoosts Electric Damage by 15%"),
    LIGHTNING_RING_2(28, "§eLightning Ring II", "§eBoosts Electric Damage by 30%"),
    STATIC_RING_1(35, "§aStatic Ring I", "§aReduces Electric Damage by 15%"),
    STATIC_RING_2(36, "§eStatic Ring II", "§eReduces Electric Damage by 30%"),
    FREEZING_RING_1(23, "§aFreezing Ring I", "§aBoosts Ice Damage by 15%"),
    FREEZING_RING_2(24, "§eFreezing Ring II", "§eBoosts Ice Damage by 30%"),
    FROST_RING_1(25, "§aFrost Ring I", "§aRecudes Ice Damage by 15%"),
    FROST_RING_2(26, "§eFrost Ring II", "§eRecudes Ice Damage by 30%"),
    FLAME_RING_1(21, "§aFlame Ring I", "§aBoosts Fire Damage by 15%"),
    FLAME_RING_2(22, "§eFlame Ring II", "§eBoosts Fire Damage by 30%"),
    BURNING_RING_1(13, "§aBurning Ring I", "§aReduces Fire Damage by 15%"),
    BURNING_RING_2(14, "§eBurning Ring II", "§eReduces Fire Damage by 30%"),
    LIGHT_RING_1(29, "§eLight Ring I", "§eBoosts Light Damage by 15%"),
    LIGHT_RING_2(30, "§6Light Ring II", "§6Boosts Light Damage by 30%"),
    RADIANT_RING_1(33, "§eRadiant Ring I", "§eReduces Light Damage by 15%"),
    RADIANT_RING_2(34, "§6Radiant Ring II", "§6Reduces Light Damage by 30%"),
    EVIL_RING_1(19, "§eEvil Ring I", "§eBoosts Dark Damage by 15%"),
    EVIL_RING_2(20, "§6Evil Ring II", "§6Boosts Dark Damage by 30%"),
    OBSCURE_RING_1(31, "§eObscure Ring I", "§eReduces Dark Damage by 15%"),
    OBSCURE_RING_2(32, "§6Obscure Ring II", "§6Reduces Dark Damage by 30%"),
    BURST_RING_1(15, "§6Burst Ring I", "§6Reduces Explosion Damage by 20%"),
    BURST_RING_2(16, "§cBurst Ring II", "§cReduces Explosion Damage by 40%"),
    TENACITY_RING_1(37, "§6Tenacity Ring I", "§6Reduces Kinetic Damage by 15%"),
    TENACITY_RING_2(38, "§cTenacity Ring II", "§cReduces Kinetic Damage by 30%");

    private int modelData;
    private String displayName;
    private String lore;

    private Ring(int modelData, String displayName, String lore) {
	this.modelData = modelData;
	this.displayName = displayName;
	this.lore = lore;
    }

    public static boolean isRing(ItemStack item) {
	if (item == null || item.getType() == Material.AIR)
	    return false;
	NBTItem nbt = new NBTItem(item);
	return nbt.getKeys().contains("RingType");
    }

    public static Ring getRingFromItem(ItemStack item) {
	NBTItem nbt = new NBTItem(item);
	return Ring.valueOf(nbt.getString("RingType"));
    }

    public ItemStack getRing() {
	ItemStack item = new ItemStack(Material.IRON_NUGGET);
	ItemMeta meta = item.getItemMeta();
	meta.setUnbreakable(true);
	List<String> lore = new ArrayList<String>();
	meta.setDisplayName(displayName);
	lore.add(this.lore);
	meta.setCustomModelData(modelData);
	meta.setLore(lore);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("RingType", this.toString());
	nbt.setString("AntiStack", UUID.randomUUID().toString());
	return nbt.getItem();
    }
}
