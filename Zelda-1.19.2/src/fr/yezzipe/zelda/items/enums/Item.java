package fr.yezzipe.zelda.items.enums;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.Main;

public enum Item {
    GRAPPLING_HOOK(1, "Grappling Hook", Material.WARPED_FUNGUS_ON_A_STICK), LIGHT_BOW(1, "§6Light Bow", Material.BOW),
    DARK_BOW(2, "§5Dark Bow", Material.BOW), ICE_BOW(3, "§bIce Bow", Material.BOW),
    ELECTRIC_BOW(4, "§eElectric Bow", Material.BOW), FIRE_BOW(5, "§cFire Bow", Material.BOW),
    BOMB_BOW(6, "§3Bomb Bow", Material.BOW), HEART_PIECE(2, "§cHeart Piece", Material.SLIME_BALL),
    HEART_CONTAINER(3, "§cHeart Container", Material.SLIME_BALL),
    SHADOW_CRYSTAL(1, "§8Shadow Crystal", Material.PHANTOM_MEMBRANE);

    private int modelData;
    private String displayName;
    private Material mat;

    private Item(int modelData, String displayName, Material mat) {
	this.modelData = modelData;
	this.displayName = displayName;
	this.mat = mat;
    }

    public ItemStack getItem() {
	ItemStack item = new ItemStack(mat);
	ItemMeta meta = item.getItemMeta();
	meta.setUnbreakable(true);
	meta.setCustomModelData(Integer.valueOf(modelData));
	meta.setDisplayName(displayName);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("ItemType", this.toString());
	return nbt.getItem();
    }
    
    public static boolean isItem(ItemStack item) {
	if (item == null || item.getType() == Material.AIR) return false;
	NBTItem nbt = new NBTItem(item);
	return nbt.getKeys().contains("ItemType");
    }

    public static void init() {
	ItemStack grappling_item = Item.GRAPPLING_HOOK.getItem();
	ShapedRecipe grappling = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "grappling-hook"),
		grappling_item);
	grappling.shape(new String[] { " IA", "ISI", "FI " });
	grappling.setIngredient('I', Material.IRON_INGOT);
	grappling.setIngredient('A', Material.ARROW);
	grappling.setIngredient('S', Material.SLIME_BALL);
	grappling.setIngredient('F', Material.FISHING_ROD);
	Bukkit.getServer().addRecipe((Recipe) grappling);
	ItemStack light_bow_item = Item.LIGHT_BOW.getItem();
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
	ItemStack dark_bow_item = Item.DARK_BOW.getItem();
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
	ItemStack ice_bow_item = Item.ICE_BOW.getItem();
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
	ItemStack electric_bow_item = Item.ELECTRIC_BOW.getItem();
	ShapedRecipe electric_bow = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "electric-bow"),
		electric_bow_item);
	electric_bow.shape(new String[] { "ENB", "BAN", "IBE" });
	electric_bow.setIngredient('E', Material.LIGHTNING_ROD);
	electric_bow.setIngredient('N', Material.NETHERITE_SCRAP);
	electric_bow.setIngredient('B', Material.RAW_GOLD_BLOCK);
	electric_bow.setIngredient('A', Material.BOW);
	electric_bow.setIngredient('I', Material.IRON_BLOCK);
	Bukkit.getServer().addRecipe((Recipe) electric_bow);
	ItemStack fire_bow_item = Item.FIRE_BOW.getItem();
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
	ItemStack heart_container = Item.HEART_CONTAINER.getItem();
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
		(RecipeChoice) new RecipeChoice.ExactChoice(Item.HEART_PIECE.getItem()));
	heart_container_recipe_2.shape(new String[] { "   ", "PP ", "PP " });
	heart_container_recipe_2.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(Item.HEART_PIECE.getItem()));
	heart_container_recipe_3.shape(new String[] { "PP ", "PP ", "   " });
	heart_container_recipe_3.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(Item.HEART_PIECE.getItem()));
	heart_container_recipe_4.shape(new String[] { " PP", " PP", "   " });
	heart_container_recipe_4.setIngredient('P',
		(RecipeChoice) new RecipeChoice.ExactChoice(Item.HEART_PIECE.getItem()));
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_1);
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_2);
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_3);
	Bukkit.getServer().addRecipe((Recipe) heart_container_recipe_4);
	ItemStack shadow_crystal = Item.SHADOW_CRYSTAL.getItem();
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
