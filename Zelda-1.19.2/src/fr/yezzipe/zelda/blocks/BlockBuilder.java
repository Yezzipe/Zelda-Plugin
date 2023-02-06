package fr.yezzipe.zelda.blocks;

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

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.Main;
import fr.yezzipe.zelda.blocks.enums.BlockEnum;

public class BlockBuilder {
    public static ItemStack build(BlockEnum block) {
	ItemMeta meta;
	NBTItem nbt;
	ItemStack camp, custom_item = null;
	switch (block) {
	case CAMP_UNLIT:
	    camp = new ItemStack(Material.IRON_NUGGET);
	    meta = camp.getItemMeta();
	    meta.setCustomModelData(40);
	    meta.setDisplayName("Campfire");
	    camp.setItemMeta(meta);
	    nbt = new NBTItem(camp);
	    nbt.setString("BlockType", "Campfire_Unlit");
	    nbt.setString("AntiStack", UUID.randomUUID().toString());
	    custom_item = nbt.getItem();
	    break;
	case CAMP_LIT:
	    camp = new ItemStack(Material.IRON_NUGGET);
	    meta = camp.getItemMeta();
	    meta.setCustomModelData(39);
	    meta.setDisplayName("Campfire");
	    camp.setItemMeta(meta);
	    nbt = new NBTItem(camp);
	    nbt.setString("BlockType", "Campfire_Lit");
	    nbt.setString("AntiStack", UUID.randomUUID().toString());
	    custom_item = nbt.getItem();
	    break;
	}
	return custom_item;
    }
    
    public static void init() {
	ItemStack camp_unlit_item = build(BlockEnum.CAMP_UNLIT);
	ShapedRecipe camp_unlit = new ShapedRecipe(new NamespacedKey((Plugin) Main.getInstance(), "camp-unlit"),
		camp_unlit_item);
	camp_unlit.shape(new String[] { " C ", "III", "WHW" });
	camp_unlit.setIngredient('C', Material.CAULDRON);
	camp_unlit.setIngredient('I', Material.IRON_INGOT);
	RecipeChoice choice1 = new RecipeChoice.MaterialChoice(Material.OAK_LOG, Material.ACACIA_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.MANGROVE_LOG, Material.SPRUCE_LOG);
	camp_unlit.setIngredient('W', choice1);
	RecipeChoice choice2 = new RecipeChoice.MaterialChoice(Material.COAL, Material.CHARCOAL);
	camp_unlit.setIngredient('H', choice2);
	Bukkit.getServer().addRecipe((Recipe) camp_unlit);
    }
}
