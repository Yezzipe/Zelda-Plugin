
package fr.yezzipe.zelda.items;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Ring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RingBuilder {

   public static ItemStack build(Ring ring) {
      ItemStack ringItem = new ItemStack(Material.IRON_NUGGET);
      ItemMeta meta = ringItem.getItemMeta();
      meta.setUnbreakable(true);
      List<String> lore = new ArrayList<String>();
      switch(ring) {
      case ARMOR_RING_1:
         meta.setDisplayName("§aArmor Ring I");
         lore.add("§aReduces Physical Damage by 5%");
         meta.setCustomModelData(2);
         break;
      case AGILITY_RING:
         meta.setDisplayName("§aAgility Ring");
         lore.add("§aGrants Speed");
         meta.setCustomModelData(1);
         break;
      case ARMOR_RING_2:
         meta.setDisplayName("§eArmor Ring II");
         lore.add("§eReduces Physical Damage by 10%");
         meta.setCustomModelData(3);
         break;
      case INVISIBILITY_RING:
         meta.setDisplayName("§eInvisibility Ring");
         lore.add("§eGrants Invisibility");
         meta.setCustomModelData(7);
         break;
      case ARMOR_RING_3:
         meta.setDisplayName("§6Armor Ring III");
         lore.add("§6Reduces Physical Damage by 15%");
         meta.setCustomModelData(4);
         break;
      case STRENGTH_RING_1:
         meta.setDisplayName("§aStrength Ring I");
         lore.add("§aBoosts Physical Damage by 5%");
         meta.setCustomModelData(9);
         break;
      case STRENGTH_RING_2:
         meta.setDisplayName("§eStrength Ring II");
         lore.add("§eBoosts Physical Damage by 10%");
         meta.setCustomModelData(10);
         break;
      case STRENGTH_RING_3:
         meta.setDisplayName("§6Strength Ring III");
         lore.add("§6Boosts Physical Damage by 15%");
         meta.setCustomModelData(11);
         break;
      case FEATHER_RING:
         meta.setDisplayName("§6Feather Ring");
         lore.add("§6Negate 50% of Fall Damage");
         meta.setCustomModelData(5);
         break;
      case ZORA_RING:
         meta.setDisplayName("§cZora Ring");
         lore.add("§cGrants Dolphin's Grace");
         meta.setCustomModelData(12);
         break;
      case GORON_RING:
         meta.setDisplayName("§cGoron Ring");
         lore.add("§cGrants Fire Resistance");
         meta.setCustomModelData(6);
         break;
      case KOKIRI_RING:
         meta.setDisplayName("§cKokiri Ring");
         lore.add("§cGrants Regeneration");
         meta.setCustomModelData(8);
         break;
      case HASTE_RING_1:
         meta.setDisplayName("§eHaste Ring I");
         lore.add("§eGrants Haste");
         meta.setCustomModelData(17);
         break;
      case HASTE_RING_2:
         meta.setDisplayName("§6Haste Ring II");
         lore.add("§6Grants Haste 2 Times");
         meta.setCustomModelData(18);
         break;
      case LIGHTNING_RING_1:
         meta.setDisplayName("§aLightning Ring I");
         lore.add("§aBoosts Electric Damage by 15%");
         meta.setCustomModelData(27);
         break;
      case LIGHTNING_RING_2:
         meta.setDisplayName("§eLightning Ring II");
         lore.add("§eBoosts Electric Damage by 30%");
         meta.setCustomModelData(28);
         break;
      case STATIC_RING_1:
         meta.setDisplayName("§aStatic Ring I");
         lore.add("§aReduces Electric Damage by 15%");
         meta.setCustomModelData(35);
         break;
      case STATIC_RING_2:
         meta.setDisplayName("§eStatic Ring II");
         lore.add("§eReduces Electric Damage by 30%");
         meta.setCustomModelData(36);
         break;
      case FREEZING_RING_1:
         meta.setDisplayName("§aFreezing Ring I");
         lore.add("§aBoosts Ice Damage by 15%");
         meta.setCustomModelData(23);
         break;
      case FREEZING_RING_2:
         meta.setDisplayName("§eFreezing Ring II");
         lore.add("§eBoosts Ice Damage by 30%");
         meta.setCustomModelData(24);
         break;
      case FROST_RING_1:
         meta.setDisplayName("§aFrost Ring I");
         lore.add("§aRecudes Ice Damage by 15%");
         meta.setCustomModelData(25);
         break;
      case FROST_RING_2:
         meta.setDisplayName("§eFrost Ring II");
         lore.add("§eRecudes Ice Damage by 30%");
         meta.setCustomModelData(26);
         break;
      case FLAME_RING_1:
         meta.setDisplayName("§aFlame Ring I");
         lore.add("§aBoosts Fire Damage by 15%");
         meta.setCustomModelData(21);
         break;
      case FLAME_RING_2:
         meta.setDisplayName("§eFlame Ring II");
         lore.add("§eBoosts Fire Damage by 30%");
         meta.setCustomModelData(22);
         break;
      case BURNING_RING_1:
         meta.setDisplayName("§aBurning Ring I");
         lore.add("§aReduces Fire Damage by 15%");
         meta.setCustomModelData(13);
         break;
      case BURNING_RING_2:
         meta.setDisplayName("§eBurning Ring II");
         lore.add("§eReduces Fire Damage by 30%");
         meta.setCustomModelData(14);
         break;
      case LIGHT_RING_1:
         meta.setDisplayName("§eLight Ring I");
         lore.add("§eBoosts Light Damage by 15%");
         meta.setCustomModelData(29);
         break;
      case LIGHT_RING_2:
         meta.setDisplayName("§6Light Ring II");
         lore.add("§6Boosts Light Damage by 30%");
         meta.setCustomModelData(30);
         break;
      case RADIANT_RING_1:
         meta.setDisplayName("§eRadiant Ring I");
         lore.add("§eReduces Light Damage by 15%");
         meta.setCustomModelData(33);
         break;
      case RADIANT_RING_2:
         meta.setDisplayName("§6Radiant Ring II");
         lore.add("§6Reduces Light Damage by 30%");
         meta.setCustomModelData(34);
         break;
      case EVIL_RING_1:
         meta.setDisplayName("§eEvil Ring I");
         lore.add("§eBoosts Dark Damage by 15%");
         meta.setCustomModelData(19);
         break;
      case EVIL_RING_2:
         meta.setDisplayName("§6Evil Ring II");
         lore.add("§6Boosts Dark Damage by 30%");
         meta.setCustomModelData(20);
         break;
      case OBSCURE_RING_1:
         meta.setDisplayName("§eObscure Ring I");
         lore.add("§eReduces Dark Damage by 15%");
         meta.setCustomModelData(31);
         break;
      case OBSCURE_RING_2:
         meta.setDisplayName("§6Obscure Ring II");
         lore.add("§6Reduces Dark Damage by 30%");
         meta.setCustomModelData(32);
         break;
      case BURST_RING_1:
         meta.setDisplayName("§6Burst Ring I");
         lore.add("§6Reduces Explosion Damage by 20%");
         meta.setCustomModelData(15);
         break;
      case BURST_RING_2:
         meta.setDisplayName("§cBurst Ring II");
         lore.add("§cReduces Explosion Damage by 40%");
         meta.setCustomModelData(16);
         break;
      case TENACITY_RING_1:
         meta.setDisplayName("§6Tenacity Ring I");
         lore.add("§6Reduces Kinetic Damage by 15%");
         meta.setCustomModelData(37);
         break;
      case TENACITY_RING_2:
         meta.setDisplayName("§cTenacity Ring II");
         lore.add("§cReduces Kinetic Damage by 30%");
         meta.setCustomModelData(38);
      }

      meta.setLore(lore);
      ringItem.setItemMeta(meta);
      NBTItem nbt = new NBTItem(ringItem);
      nbt.setString("ItemType", ring.toString());
      nbt.setString("AntiStack", UUID.randomUUID().toString());
      ringItem = nbt.getItem();
      return ringItem;
   }

   public static boolean isRing(ItemStack item) {
	    if (item == null || item.getType() == Material.AIR)
	      return false; 
	    NBTItem nbt = new NBTItem(item);
	    HashSet<String> values = new HashSet<>();
	    byte b;
	    int i;
	    Ring[] arrayOfRing;
	    for (i = (arrayOfRing = Ring.values()).length, b = 0; b < i; ) {
	      Ring c = arrayOfRing[b];
	      values.add(c.toString());
	      b++;
	    } 
	    if (nbt.getKeys().contains("ItemType") && 
	      values.contains(nbt.getString("ItemType")))
	      return true; 
	    return false;
	  }

   public static Ring getRingFromItem(ItemStack item) {
      NBTItem nbt = new NBTItem(item);
      return (Ring)Ring.valueOf(Ring.class, nbt.getString("ItemType"));
   }
}