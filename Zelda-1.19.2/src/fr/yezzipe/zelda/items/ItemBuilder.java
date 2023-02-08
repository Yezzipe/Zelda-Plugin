package fr.yezzipe.zelda.items;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.items.enums.Item;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ItemBuilder {
    public static ItemStack build(Item item) {
	ItemStack grappling_item;
	ItemMeta meta;
	NBTItem nbt;
	ItemStack dark_bow, electric_bow, fire_bow, ice_bow, light_bow, bomb_bow, heart_piece, heart_container, crystal,
		customItem = null;
	switch (item) {
	case GRAPPLING_HOOK:
	    grappling_item = new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK);
	    meta = grappling_item.getItemMeta();
	    meta.setUnbreakable(true);
	    meta.setCustomModelData(Integer.valueOf(1));
	    meta.setDisplayName("Grappling Hook");
	    grappling_item.setItemMeta(meta);
	    nbt = new NBTItem(grappling_item);
	    nbt.setString("ItemType", Item.GRAPPLING_HOOK.toString());
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
	    nbt.setString("ItemType", Item.DARK_BOW.toString());
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
	    nbt.setString("ItemType", Item.ELECTRIC_BOW.toString());
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
	    nbt.setString("ItemType", Item.FIRE_BOW.toString());
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
	    nbt.setString("ItemType", Item.ICE_BOW.toString());
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
	    nbt.setString("ItemType", Item.LIGHT_BOW.toString());
	    customItem = nbt.getItem();
	    break;
	case BOMB_BOW:
	    bomb_bow = new ItemStack(Material.BOW);
	    meta = bomb_bow.getItemMeta();
	    meta.setUnbreakable(true);
	    meta.setCustomModelData(Integer.valueOf(6));
	    meta.setDisplayName("§3Bomb Bow");
	    bomb_bow.setItemMeta(meta);
	    nbt = new NBTItem(bomb_bow);
	    nbt.setString("ItemType", Item.BOMB_BOW.toString());
	    customItem = nbt.getItem();
	    break;
	case HEART_PIECE:
	    heart_piece = new ItemStack(Material.SLIME_BALL);
	    meta = heart_piece.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(2));
	    meta.setDisplayName("§cHeart Piece");
	    heart_piece.setItemMeta(meta);
	    nbt = new NBTItem(heart_piece);
	    nbt.setString("ItemType", Item.HEART_PIECE.toString());
	    customItem = nbt.getItem();
	    break;
	case HEART_CONTAINER:
	    heart_container = new ItemStack(Material.SLIME_BALL);
	    meta = heart_container.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(3));
	    meta.setDisplayName("§cHeart Container");
	    heart_container.setItemMeta(meta);
	    nbt = new NBTItem(heart_container);
	    nbt.setString("ItemType", Item.HEART_CONTAINER.toString());
	    customItem = nbt.getItem();
	    break;
	case SHADOW_CRYSTAL:
	    crystal = new ItemStack(Material.PHANTOM_MEMBRANE);
	    meta = crystal.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(1));
	    meta.setDisplayName("§8Shadow Crystal");
	    crystal.setItemMeta(meta);
	    nbt = new NBTItem(crystal);
	    nbt.setString("ItemType", Item.SHADOW_CRYSTAL.toString());
	    nbt.setString("AntiStack", UUID.randomUUID().toString());
	    customItem = nbt.getItem();
	    break;
	}
	return customItem;
    }

    public static void init() {
	ItemStack grappling_item = ItemBuilder.build(Item.GRAPPLING_HOOK);
	ShapedRecipe grappling = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "grappling-hook"),
		grappling_item);
	grappling.shape(new String[] { " IA", "ISI", "FI " });
	grappling.setIngredient('I', Material.IRON_INGOT);
	grappling.setIngredient('A', Material.ARROW);
	grappling.setIngredient('S', Material.SLIME_BALL);
	grappling.setIngredient('F', Material.FISHING_ROD);
	Bukkit.getServer().addRecipe((Recipe) grappling);
	ItemStack light_bow_item = ItemBuilder.build(Item.LIGHT_BOW);
	ShapedRecipe light_bow = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "light-bow"),
		light_bow_item);
	light_bow.shape(new String[] { "ENG", "BAN", "IBE" });
	light_bow.setIngredient('E', Material.END_ROD);
	light_bow.setIngredient('N', Material.NETHERITE_SCRAP);
	light_bow.setIngredient('G', Material.GLOWSTONE);
	light_bow.setIngredient('B', Material.GLOW_BERRIES);
	light_bow.setIngredient('A', Material.BOW);
	light_bow.setIngredient('I', Material.IRON_BLOCK);
	Bukkit.getServer().addRecipe((Recipe) light_bow);
	ItemStack dark_bow_item = ItemBuilder.build(Item.DARK_BOW);
	ShapedRecipe dark_bow = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "dark-bow"),
		dark_bow_item);
	dark_bow.shape(new String[] { "ENG", "BAN", "IBE" });
	dark_bow.setIngredient('E', Material.SHULKER_SHELL);
	dark_bow.setIngredient('N', Material.NETHERITE_SCRAP);
	dark_bow.setIngredient('G', Material.WITHER_SKELETON_SKULL);
	dark_bow.setIngredient('B', Material.CHORUS_FRUIT);
	dark_bow.setIngredient('A', Material.BOW);
	dark_bow.setIngredient('I', Material.IRON_BLOCK);
	Bukkit.getServer().addRecipe((Recipe) dark_bow);
	ItemStack ice_bow_item = ItemBuilder.build(Item.ICE_BOW);
	ShapedRecipe ice_bow = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "ice-bow"),
		ice_bow_item);
	ice_bow.shape(new String[] { "ENG", "BAN", "IBE" });
	ice_bow.setIngredient('E', Material.SNOWBALL);
	ice_bow.setIngredient('N', Material.NETHERITE_SCRAP);
	ice_bow.setIngredient('G', Material.PACKED_ICE);
	ice_bow.setIngredient('B', Material.SNOW_BLOCK);
	ice_bow.setIngredient('A', Material.BOW);
	ice_bow.setIngredient('I', Material.IRON_BLOCK);
	Bukkit.getServer().addRecipe((Recipe) ice_bow);
	ItemStack electric_bow_item = ItemBuilder.build(Item.ELECTRIC_BOW);
	ShapedRecipe electric_bow = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "electric-bow"),
		electric_bow_item);
	electric_bow.shape(new String[] { "ENB", "BAN", "IBE" });
	electric_bow.setIngredient('E', Material.LIGHTNING_ROD);
	electric_bow.setIngredient('N', Material.NETHERITE_SCRAP);
	electric_bow.setIngredient('B', Material.RAW_GOLD_BLOCK);
	electric_bow.setIngredient('A', Material.BOW);
	electric_bow.setIngredient('I', Material.IRON_BLOCK);
	Bukkit.getServer().addRecipe((Recipe) electric_bow);
	ItemStack fire_bow_item = ItemBuilder.build(Item.FIRE_BOW);
	ShapedRecipe fire_bow = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "fire-bow"),
		fire_bow_item);
	fire_bow.shape(new String[] { "ENG", "BAN", "IBE" });
	fire_bow.setIngredient('E', Material.BLAZE_ROD);
	fire_bow.setIngredient('N', Material.NETHERITE_SCRAP);
	fire_bow.setIngredient('G', Material.CRYING_OBSIDIAN);
	fire_bow.setIngredient('B', Material.FIRE_CHARGE);
	fire_bow.setIngredient('A', Material.BOW);
	fire_bow.setIngredient('I', Material.IRON_BLOCK);
	Bukkit.getServer().addRecipe((Recipe) fire_bow);
	ItemStack heart_container = ItemBuilder.build(Item.HEART_CONTAINER);
	ShapedRecipe heart_container_recipe_1 = new ShapedRecipe(
		new NamespacedKey((Plugin) Main.getInstance(), "heart-container-1"), heart_container);
	ShapedRecipe heart_container_recipe_2 = new ShapedRecipe(
		new NamespacedKey((Plugin) Main.getInstance(), "heart-container-2"), heart_container);
	ShapedRecipe heart_container_recipe_3 = new ShapedRecipe(
		new NamespacedKey((Plugin) Main.getInstance(), "heart-container-3"), heart_container);
	ShapedRecipe heart_container_recipe_4 = new ShapedRecipe(
		new NamespacedKey((Plugin) Main.getInstance(), "heart-container-4"), heart_container);
	heart_container_recipe_1.shape(new String[] { "   ", " PP", " PP" });
	heart_container_recipe_1.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
	heart_container_recipe_2.shape(new String[] { "   ", "PP ", "PP " });
	heart_container_recipe_2.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
	heart_container_recipe_3.shape(new String[] { "PP ", "PP ", "   " });
	heart_container_recipe_3.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
	heart_container_recipe_4.shape(new String[] { " PP", " PP", "   " });
	heart_container_recipe_4.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(ItemBuilder.build(Item.HEART_PIECE)));
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_1);
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_2);
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_3);
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_4);
	ItemStack shadow_crystal = ItemBuilder.build(Item.SHADOW_CRYSTAL);
	ShapedRecipe shadow_crystal_recipe = new ShapedRecipe(
		new NamespacedKey((Plugin) Main.getInstance(), "shadow-crystal"), shadow_crystal);
	shadow_crystal_recipe.shape(new String[] { "DOD", "CSC", "POP" });
	shadow_crystal_recipe.setIngredient('D', Material.ORANGE_DYE);
	shadow_crystal_recipe.setIngredient('O', Material.CRYING_OBSIDIAN);
	shadow_crystal_recipe.setIngredient('C', Material.END_CRYSTAL);
	shadow_crystal_recipe.setIngredient('S', Material.NETHER_STAR);
	shadow_crystal_recipe.setIngredient('P', Material.ENDER_PEARL);
	Bukkit.getServer().addRecipe((Recipe) shadow_crystal_recipe);
    }
}
