package fr.yezzipe.zelda.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Ingredient;

public class IngredientBuilder {

    public static ItemStack build(Ingredient ingredient) {
	ItemStack item = new ItemStack(Material.BEEF), customItem = null;

	ItemMeta meta;
	NBTItem nbt;
	switch (ingredient) {
	case COOL_SAFFLINA:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(1));
	    meta.setDisplayName("Cool Safflina");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.COOL_SAFFLINA.toString());
	    customItem = nbt.getItem();
	    break;
	case WARM_SAFFLINA:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(2));
	    meta.setDisplayName("Warm Safflina");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.WARM_SAFFLINA.toString());
	    customItem = nbt.getItem();
	    break;
	case ELECTRIC_SAFFLINA:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(3));
	    meta.setDisplayName("Electric Safflina");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ELECTRIC_SAFFLINA.toString());
	    customItem = nbt.getItem();
	    break;
	case SWIFT_VIOLET:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(4));
	    meta.setDisplayName("Swift Violet");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SWIFT_VIOLET.toString());
	    customItem = nbt.getItem();
	    break;
	case MIGHTY_THISTLE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(5));
	    meta.setDisplayName("Mighty Thistle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MIGHTY_THISTLE.toString());
	    customItem = nbt.getItem();
	    break;
	case ARMORANTH:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(6));
	    meta.setDisplayName("Armoranth");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ARMORANTH.toString());
	    customItem = nbt.getItem();
	    break;
	case BLUE_NIGHTSHADE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(7));
	    meta.setDisplayName("Blue Nightshade");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BLUE_NIGHTSHADE.toString());
	    customItem = nbt.getItem();
	    break;
	case SILENT_PRINCESS:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(8));
	    meta.setDisplayName("Silent Princess");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SILENT_PRINCESS.toString());
	    customItem = nbt.getItem();
	    break;
	case CANE_SUGAR:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(9));
	    meta.setDisplayName("Cane Sugar");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.CANE_SUGAR.toString());
	    customItem = nbt.getItem();
	    break;
	case GOAT_BUTTER:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(10));
	    meta.setDisplayName("Goat Butter");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.GOAT_BUTTER.toString());
	    customItem = nbt.getItem();
	    break;
	case GORON_SPICE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(11));
	    meta.setDisplayName("Goron Spice");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.GORON_SPICE.toString());
	    customItem = nbt.getItem();
	    break;
	case ROCK_SALT:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(12));
	    meta.setDisplayName("Rock Salt");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ROCK_SALT.toString());
	    customItem = nbt.getItem();
	    break;
	case MONSTER_EXTRACT:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(13));
	    meta.setDisplayName("Monster Extract");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MONSTER_EXTRACT.toString());
	    customItem = nbt.getItem();
	    break;
	case STAR_FRAGMENT:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(14));
	    meta.setDisplayName("Star Fragment");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.STAR_FRAGMENT.toString());
	    customItem = nbt.getItem();
	    break;
	case DINRAALS_SCALE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(15));
	    meta.setDisplayName("Dinraals Scale");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.DINRAALS_SCALE.toString());
	    customItem = nbt.getItem();
	    break;
	case DINRAALS_CLAW:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(16));
	    meta.setDisplayName("Dinraals Claw");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.DINRAALS_CLAW.toString());
	    customItem = nbt.getItem();
	    break;
	case SHARD_OF_DINRAALS_FANG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(17));
	    meta.setDisplayName("Shard Of Dinraals Fang");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SHARD_OF_DINRAALS_FANG.toString());
	    customItem = nbt.getItem();
	    break;
	case SHARD_OF_DINRAALS_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(18));
	    meta.setDisplayName("Shard Of Dinraals Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SHARD_OF_DINRAALS_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case NAYDRAS_SCALE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(19));
	    meta.setDisplayName("Naydras Scale");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.NAYDRAS_SCALE.toString());
	    customItem = nbt.getItem();
	    break;
	case NAYDRAS_CLAW:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(20));
	    meta.setDisplayName("Naydras Claw");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.NAYDRAS_CLAW.toString());
	    customItem = nbt.getItem();
	    break;
	case SHARD_OF_NAYDRAS_FANG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(21));
	    meta.setDisplayName("Shard Of Naydras Fang");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SHARD_OF_NAYDRAS_FANG.toString());
	    customItem = nbt.getItem();
	    break;
	case SHARD_OF_NAYDRAS_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(22));
	    meta.setDisplayName("Shard Of Naydras Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SHARD_OF_NAYDRAS_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case FAROSHS_SCALE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(23));
	    meta.setDisplayName("Faroshs Scale");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FAROSHS_SCALE.toString());
	    customItem = nbt.getItem();
	    break;
	case FAROSHS_CLAW:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(24));
	    meta.setDisplayName("Faroshs Claw");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FAROSHS_CLAW.toString());
	    customItem = nbt.getItem();
	    break;
	case SHARD_OF_FAROSHS_FANG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(25));
	    meta.setDisplayName("Shard Of Faroshs Fang");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SHARD_OF_FAROSHS_FANG.toString());
	    customItem = nbt.getItem();
	    break;
	case SHARD_OF_FAROSHS_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(26));
	    meta.setDisplayName("Shard Of Faroshs Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SHARD_OF_FAROSHS_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case FAIRY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(27));
	    meta.setDisplayName("Fairy");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FAIRY.toString());
	    customItem = nbt.getItem();
	    break;
	case WINTERWING_BUTTERFLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(28));
	    meta.setDisplayName("Winterwing Butterfly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.WINTERWING_BUTTERFLY.toString());
	    customItem = nbt.getItem();
	    break;
	case SUMMERWING_BUTTERFLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(29));
	    meta.setDisplayName("Summerwing Butterfly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SUMMERWING_BUTTERFLY.toString());
	    customItem = nbt.getItem();
	    break;
	case THUNDERWING_BUTTERFLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(30));
	    meta.setDisplayName("Thunderwing Butterfly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.THUNDERWING_BUTTERFLY.toString());
	    customItem = nbt.getItem();
	    break;
	case SMOTHERWING_BUTTERFLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(31));
	    meta.setDisplayName("Smotherwing Butterfly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SMOTHERWING_BUTTERFLY.toString());
	    customItem = nbt.getItem();
	    break;
	case COLD_DARNER:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(32));
	    meta.setDisplayName("Cold Darner");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.COLD_DARNER.toString());
	    customItem = nbt.getItem();
	    break;
	case WARM_DARNER:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(33));
	    meta.setDisplayName("Warm Darner");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.WARM_DARNER.toString());
	    customItem = nbt.getItem();
	    break;
	case ELECTRIC_DARNER:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(34));
	    meta.setDisplayName("Electric Darner");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ELECTRIC_DARNER.toString());
	    customItem = nbt.getItem();
	    break;
	case RESTLESS_CRICKET:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(35));
	    meta.setDisplayName("Restless Cricket");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RESTLESS_CRICKET.toString());
	    customItem = nbt.getItem();
	    break;
	case BLADED_RHINO_BEETLE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(36));
	    meta.setDisplayName("Bladed Rhino Beetle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BLADED_RHINO_BEETLE.toString());
	    customItem = nbt.getItem();
	    break;
	case RUGGED_RHINO_BEETLE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(37));
	    meta.setDisplayName("Rugged Rhino Beetle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RUGGED_RHINO_BEETLE.toString());
	    customItem = nbt.getItem();
	    break;
	case ENERGETIC_RHINO_BEETLE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(38));
	    meta.setDisplayName("Energetic Rhino Beetle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ENERGETIC_RHINO_BEETLE.toString());
	    customItem = nbt.getItem();
	    break;
	case SUNSET_FIREFLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(39));
	    meta.setDisplayName("Sunset Firefly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SUNSET_FIREFLY.toString());
	    customItem = nbt.getItem();
	    break;
	case HOT_FOOTED_FROG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(40));
	    meta.setDisplayName("Hot-Footed Frog");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HOT_FOOTED_FROG.toString());
	    customItem = nbt.getItem();
	    break;
	case TIRELESS_FROG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(41));
	    meta.setDisplayName("Tireless Frog");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.TIRELESS_FROG.toString());
	    customItem = nbt.getItem();
	    break;
	case HIGHTAIL_LIZARD:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(42));
	    meta.setDisplayName("Hightail Lizard");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HIGHTAIL_LIZARD.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_LIZARD:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(43));
	    meta.setDisplayName("Hearty Lizard");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_LIZARD.toString());
	    customItem = nbt.getItem();
	    break;
	case FIREPROOF_LIZARD:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(44));
	    meta.setDisplayName("Fireproof Lizard");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FIREPROOF_LIZARD.toString());
	    customItem = nbt.getItem();
	    break;
	case FLINT:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(45));
	    meta.setDisplayName("Flint");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FLINT.toString());
	    customItem = nbt.getItem();
	    break;
	case AMBER:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(46));
	    meta.setDisplayName("Amber");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.AMBER.toString());
	    customItem = nbt.getItem();
	    break;
	case OPAL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(47));
	    meta.setDisplayName("Opal");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.OPAL.toString());
	    customItem = nbt.getItem();
	    break;
	case LUMINOUS_STONE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(48));
	    meta.setDisplayName("Luminous Stone");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LUMINOUS_STONE.toString());
	    customItem = nbt.getItem();
	    break;
	case TOPAZ:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(49));
	    meta.setDisplayName("Topaz");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.TOPAZ.toString());
	    customItem = nbt.getItem();
	    break;
	case RUBY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(50));
	    meta.setDisplayName("Ruby");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RUBY.toString());
	    customItem = nbt.getItem();
	    break;
	case SAPPHIRE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(51));
	    meta.setDisplayName("Sapphire");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SAPPHIRE.toString());
	    customItem = nbt.getItem();
	    break;
	case DIAMOND:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(52));
	    meta.setDisplayName("Diamond");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.DIAMOND.toString());
	    customItem = nbt.getItem();
	    break;
	case BOKOBLIN_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(53));
	    meta.setDisplayName("Bokoblin Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BOKOBLIN_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case BOKOBLIN_FANG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(54));
	    meta.setDisplayName("Bokoblin Fang");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BOKOBLIN_FANG.toString());
	    customItem = nbt.getItem();
	    break;
	case BOKOBLIN_GUTS:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(55));
	    meta.setDisplayName("Bokoblin Guts");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BOKOBLIN_GUTS.toString());
	    customItem = nbt.getItem();
	    break;
	case MOBLIN_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(56));
	    meta.setDisplayName("Moblin Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MOBLIN_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case MOBLIN_FANG:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(57));
	    meta.setDisplayName("Moblin Fang");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MOBLIN_FANG.toString());
	    customItem = nbt.getItem();
	    break;
	case MOBLIN_GUTS:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(58));
	    meta.setDisplayName("Moblin Guts");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MOBLIN_GUTS.toString());
	    customItem = nbt.getItem();
	    break;
	case LIZALFOS_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(59));
	    meta.setDisplayName("Lizalfos Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LIZALFOS_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case LIZALFOS_TALON:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(60));
	    meta.setDisplayName("Lizalfos Talon");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LIZALFOS_TALON.toString());
	    customItem = nbt.getItem();
	    break;
	case LIZALFOS_TAIL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(61));
	    meta.setDisplayName("Lizalfos Tail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LIZALFOS_TAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case ICY_LIZALFOS_TAIL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(62));
	    meta.setDisplayName("Icy Lizalfos Tail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ICY_LIZALFOS_TAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case RED_LIZALFOS_TAIL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(63));
	    meta.setDisplayName("Red Lizalfos Tail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RED_LIZALFOS_TAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case YELLOW_LIZALFOS_TAIL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(64));
	    meta.setDisplayName("Yellow Lizalfos Tail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.YELLOW_LIZALFOS_TAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case LYNEL_HORN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(65));
	    meta.setDisplayName("Lynel Horn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LYNEL_HORN.toString());
	    customItem = nbt.getItem();
	    break;
	case LYNEL_HOOF:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(66));
	    meta.setDisplayName("Lynel Hoof");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LYNEL_HOOF.toString());
	    customItem = nbt.getItem();
	    break;
	case LYNEL_GUTS:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(67));
	    meta.setDisplayName("Lynel Guts");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.LYNEL_GUTS.toString());
	    customItem = nbt.getItem();
	    break;
	case CHUCHU_JELLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(68));
	    meta.setDisplayName("Chuchu Jelly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.CHUCHU_JELLY.toString());
	    customItem = nbt.getItem();
	    break;
	case WHITE_CHUCHU_JELLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(69));
	    meta.setDisplayName("White Chuchu Jelly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.WHITE_CHUCHU_JELLY.toString());
	    customItem = nbt.getItem();
	    break;
	case RED_CHUCHU_JELLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(70));
	    meta.setDisplayName("Red Chuchu Jelly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RED_CHUCHU_JELLY.toString());
	    customItem = nbt.getItem();
	    break;
	case YELLOW_CHUCHU_JELLY:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(71));
	    meta.setDisplayName("Yellow Chuchu Jelly");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.YELLOW_CHUCHU_JELLY.toString());
	    customItem = nbt.getItem();
	    break;
	case KEESE_WING:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(72));
	    meta.setDisplayName("Keese Wing");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.KEESE_WING.toString());
	    customItem = nbt.getItem();
	    break;
	case ICE_KEESE_WING:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(73));
	    meta.setDisplayName("Ice Keese Wing");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ICE_KEESE_WING.toString());
	    customItem = nbt.getItem();
	    break;
	case FIRE_KEESE_WING:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(74));
	    meta.setDisplayName("Fire Keese Wing");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FIRE_KEESE_WING.toString());
	    customItem = nbt.getItem();
	    break;
	case ELECTRIC_KEESE_WING:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(75));
	    meta.setDisplayName("Electric Keese Wing");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ELECTRIC_KEESE_WING.toString());
	    customItem = nbt.getItem();
	    break;
	case KEESE_EYEBALL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(76));
	    meta.setDisplayName("Keese Eyeball");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.KEESE_EYEBALL.toString());
	    customItem = nbt.getItem();
	    break;
	case OCTOROK_TENTACLE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(77));
	    meta.setDisplayName("Octorok Tentacle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.OCTOROK_TENTACLE.toString());
	    customItem = nbt.getItem();
	    break;
	case OCTOROK_EYEBALL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(78));
	    meta.setDisplayName("Octorok Eyeball");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.OCTOROK_EYEBALL.toString());
	    customItem = nbt.getItem();
	    break;
	case OCTO_BALLOON:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(79));
	    meta.setDisplayName("Octo Balloon");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.OCTO_BALLOON.toString());
	    customItem = nbt.getItem();
	    break;
	case MOLDUGA_FIN:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(80));
	    meta.setDisplayName("Molduga Fin");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MOLDUGA_FIN.toString());
	    customItem = nbt.getItem();
	    break;
	case MOLDUGA_GUTS:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(81));
	    meta.setDisplayName("Molduga Guts");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MOLDUGA_GUTS.toString());
	    customItem = nbt.getItem();
	    break;
	case HINOX_TOENAIL:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(82));
	    meta.setDisplayName("Hinox Toenail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HINOX_TOENAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case HINOX_TOOTH:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(83));
	    meta.setDisplayName("Hinox Tooth");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HINOX_TOOTH.toString());
	    customItem = nbt.getItem();
	    break;
	case HINOX_GUTS:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(84));
	    meta.setDisplayName("Hinox Guts");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HINOX_GUTS.toString());
	    customItem = nbt.getItem();
	    break;
	case ANCIENT_SCREW:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(85));
	    meta.setDisplayName("Ancient Screw");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ANCIENT_SCREW.toString());
	    customItem = nbt.getItem();
	    break;
	case ANCIENT_SPRING:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(86));
	    meta.setDisplayName("Ancient Spring");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ANCIENT_SPRING.toString());
	    customItem = nbt.getItem();
	    break;
	case ANCIENT_GEAR:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(87));
	    meta.setDisplayName("Ancient Gear");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ANCIENT_GEAR.toString());
	    customItem = nbt.getItem();
	    break;
	case ANCIENT_SHAFT:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(88));
	    meta.setDisplayName("Ancient Shaft");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ANCIENT_SHAFT.toString());
	    customItem = nbt.getItem();
	    break;
	case ANCIENT_CORE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(89));
	    meta.setDisplayName("Ancient Core");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ANCIENT_CORE.toString());
	    customItem = nbt.getItem();
	    break;
	case GIANT_ANCIENT_CORE:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(90));
	    meta.setDisplayName("Giant Ancient Core");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.GIANT_ANCIENT_CORE.toString());
	    customItem = nbt.getItem();
	    break;
	case WOOD:
	    item.setType(Material.CLOCK);
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(91));
	    meta.setDisplayName("Wood");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.WOOD.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_DURIAN:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(1));
	    meta.setDisplayName("Hearty Durian");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_DURIAN.toString());
	    customItem = nbt.getItem();
	    break;
	case PALM_FRUIT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(2));
	    meta.setDisplayName("Palm Fruit");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.PALM_FRUIT.toString());
	    customItem = nbt.getItem();
	    break;
	case APPLE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(3));
	    meta.setDisplayName("Apple");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.APPLE.toString());
	    customItem = nbt.getItem();
	    break;
	case WILDBERRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(4));
	    meta.setDisplayName("Wildberry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.WILDBERRY.toString());
	    customItem = nbt.getItem();
	    break;
	case HYDROMELON:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(5));
	    meta.setDisplayName("Hydromelon");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HYDROMELON.toString());
	    customItem = nbt.getItem();
	    break;
	case SPICY_PEPPER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(6));
	    meta.setDisplayName("Spicy Pepper");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SPICY_PEPPER.toString());
	    customItem = nbt.getItem();
	    break;
	case VOLTFRUIT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(7));
	    meta.setDisplayName("Voltfruit");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.VOLTFRUIT.toString());
	    customItem = nbt.getItem();
	    break;
	case FLEET_LOTUS_SEEDS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(8));
	    meta.setDisplayName("Fleet-Lotus Seeds");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FLEET_LOTUS_SEEDS.toString());
	    customItem = nbt.getItem();
	    break;
	case MIGHTY_BANANAS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(9));
	    meta.setDisplayName("Mighty Bananas");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MIGHTY_BANANAS.toString());
	    customItem = nbt.getItem();
	    break;
	case BIG_HEARTY_TRUFFLE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(10));
	    meta.setDisplayName("Big Hearty Truffle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BIG_HEARTY_TRUFFLE.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_TRUFFLE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(11));
	    meta.setDisplayName("Hearty Truffle");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_TRUFFLE.toString());
	    customItem = nbt.getItem();
	    break;
	case HYLIAN_SHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(12));
	    meta.setDisplayName("Hylian Shroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HYLIAN_SHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case ENDURA_SHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(13));
	    meta.setDisplayName("Endura Shroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ENDURA_SHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case STAMELLA_SHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(14));
	    meta.setDisplayName("Stamella Shroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.STAMELLA_SHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case CHILLSHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(15));
	    meta.setDisplayName("Chillshroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.CHILLSHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case SUNSHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(16));
	    meta.setDisplayName("Sunshroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SUNSHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case ZAPSHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(17));
	    meta.setDisplayName("Zapshroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ZAPSHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case RUSHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(18));
	    meta.setDisplayName("Rushroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RUSHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case RAZORSHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(19));
	    meta.setDisplayName("Razorshroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAZORSHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case IRONSHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(20));
	    meta.setDisplayName("Ironshroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.IRONSHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case SILENT_SHROOM:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(21));
	    meta.setDisplayName("Silent Shroom");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SILENT_SHROOM.toString());
	    customItem = nbt.getItem();
	    break;
	case BIG_HEARTY_RADISH:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(22));
	    meta.setDisplayName("Big Hearty Radish");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BIG_HEARTY_RADISH.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_RADISH:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(23));
	    meta.setDisplayName("Hearty Radish");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_RADISH.toString());
	    customItem = nbt.getItem();
	    break;
	case ENDURA_CARROT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(24));
	    meta.setDisplayName("Endura Carrot");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ENDURA_CARROT.toString());
	    customItem = nbt.getItem();
	    break;
	case HYRULE_HERB:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(25));
	    meta.setDisplayName("Hyrule Herb");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HYRULE_HERB.toString());
	    customItem = nbt.getItem();
	    break;
	case SWIFT_CARROT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(26));
	    meta.setDisplayName("Swift Carrot");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SWIFT_CARROT.toString());
	    customItem = nbt.getItem();
	    break;
	case FORTIFIED_PUMPKIN:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(27));
	    meta.setDisplayName("Fortified Pumpkin");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FORTIFIED_PUMPKIN.toString());
	    customItem = nbt.getItem();
	    break;
	case RAW_GOURMET_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(28));
	    meta.setDisplayName("Raw Gourmet Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAW_GOURMET_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case RAW_WHOLE_BIRD:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(29));
	    meta.setDisplayName("Raw Whole Bird");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAW_WHOLE_BIRD.toString());
	    customItem = nbt.getItem();
	    break;
	case RAW_PRIME_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(30));
	    meta.setDisplayName("Raw Prime Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAW_PRIME_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case RAW_BIRD_THIGH:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(31));
	    meta.setDisplayName("Raw Bird Thigh");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAW_BIRD_THIGH.toString());
	    customItem = nbt.getItem();
	    break;
	case RAW_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(32));
	    meta.setDisplayName("Raw Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAW_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case RAW_BIRD_DRUMSTICK:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(33));
	    meta.setDisplayName("Raw Bird Drumstick");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAW_BIRD_DRUMSTICK.toString());
	    customItem = nbt.getItem();
	    break;
	case COURSER_BEE_HONEY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(34));
	    meta.setDisplayName("Courser Bee Honey");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.COURSER_BEE_HONEY.toString());
	    customItem = nbt.getItem();
	    break;
	case HYLIAN_RICE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(35));
	    meta.setDisplayName("Hylian Rice");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HYLIAN_RICE.toString());
	    customItem = nbt.getItem();
	    break;
	case ACORN:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(36));
	    meta.setDisplayName("Acorn");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ACORN.toString());
	    customItem = nbt.getItem();
	    break;
	case CHICKALOO_TREE_NUT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(37));
	    meta.setDisplayName("Chickaloo Tree Nut");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.CHICKALOO_TREE_NUT.toString());
	    customItem = nbt.getItem();
	    break;
	case BIRD_EGG:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(38));
	    meta.setDisplayName("Bird Egg");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BIRD_EGG.toString());
	    customItem = nbt.getItem();
	    break;
	case TABANTHA_WHEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(39));
	    meta.setDisplayName("Tabantha Wheat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.TABANTHA_WHEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case FRESH_MILK:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(40));
	    meta.setDisplayName("Fresh Milk");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.FRESH_MILK.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_SALMON:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(41));
	    meta.setDisplayName("Hearty Salmon");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_SALMON.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_BASS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(42));
	    meta.setDisplayName("Hearty Bass");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_BASS.toString());
	    customItem = nbt.getItem();
	    break;
	case HYRULE_BASS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(43));
	    meta.setDisplayName("Hyrule Bass");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HYRULE_BASS.toString());
	    customItem = nbt.getItem();
	    break;
	case STAMINOKA_BASS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(44));
	    meta.setDisplayName("Staminoka Bass");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.STAMINOKA_BASS.toString());
	    customItem = nbt.getItem();
	    break;
	case CHILLFIN_TROUT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(45));
	    meta.setDisplayName("Chillfin Trout");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.CHILLFIN_TROUT.toString());
	    customItem = nbt.getItem();
	    break;
	case SIZZLEFIN_TROUT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(46));
	    meta.setDisplayName("Sizzlefin Trout");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SIZZLEFIN_TROUT.toString());
	    customItem = nbt.getItem();
	    break;
	case VOLTFIN_TROUT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(47));
	    meta.setDisplayName("Voltfin Trout");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.VOLTFIN_TROUT.toString());
	    customItem = nbt.getItem();
	    break;
	case STEALTHFIN_TROUT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(48));
	    meta.setDisplayName("Stealthfin Trout");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.STEALTHFIN_TROUT.toString());
	    customItem = nbt.getItem();
	    break;
	case MIGHTY_CARP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(49));
	    meta.setDisplayName("Mighty Carp");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MIGHTY_CARP.toString());
	    customItem = nbt.getItem();
	    break;
	case ARMORED_CARP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(50));
	    meta.setDisplayName("Armored Carp");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ARMORED_CARP.toString());
	    customItem = nbt.getItem();
	    break;
	case SANKE_CARP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(51));
	    meta.setDisplayName("Sanke Carp");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SANKE_CARP.toString());
	    customItem = nbt.getItem();
	    break;
	case MIGHTY_PORGY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(52));
	    meta.setDisplayName("Mighty Porgy");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.MIGHTY_PORGY.toString());
	    customItem = nbt.getItem();
	    break;
	case ARMORED_PORGY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(53));
	    meta.setDisplayName("Armored Porgy");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.ARMORED_PORGY.toString());
	    customItem = nbt.getItem();
	    break;
	case SNEAKY_RIVER_SNAIL:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(54));
	    meta.setDisplayName("Sneaky River Snail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.SNEAKY_RIVER_SNAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_BLUESHELL_SNAIL:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(55));
	    meta.setDisplayName("Hearty Blueshell Snail");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.HEARTY_BLUESHELL_SNAIL.toString());
	    customItem = nbt.getItem();
	    break;
	case RAZORCLAW_CRAB:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(56));
	    meta.setDisplayName("Razorclaw Crab");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.RAZORCLAW_CRAB.toString());
	    customItem = nbt.getItem();
	    break;
	case IRONSHELL_CRAB:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(57));
	    meta.setDisplayName("Ironshell Crab");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.IRONSHELL_CRAB.toString());
	    customItem = nbt.getItem();
	    break;
	case BRIGHT_EYED_CRAB:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(58));
	    meta.setDisplayName("Bright-Eyed Crab");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("IngredientType", Ingredient.BRIGHT_EYED_CRAB.toString());
	    customItem = nbt.getItem();
	    break;
	}
	return customItem;
    }
    
    public static boolean isIngredient(ItemStack item) {
	if (item == null) return false;
	if (item.getType() == Material.AIR) return false;
	return (new NBTItem(item)).getKeys().contains("IngredientType");
    }

}
