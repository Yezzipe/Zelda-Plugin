package fr.yezzipe.zelda.items.recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import fr.yezzipe.zelda.items.enums.Food;
import fr.yezzipe.zelda.items.enums.Ingredient;

public class Recipe implements Comparable<Recipe> {

    private static SortedSet<Recipe> recipes = new TreeSet<Recipe>();

    private Collection<RecipeChoice> matrix = new ArrayList<RecipeChoice>();

    private Food output;

    public Recipe(Object... objects) {
	if (objects.length > 5 || objects.length < 1)
	    return;
	for (Object o : objects) {
	    if (o instanceof RecipeChoice) {
		matrix.add((RecipeChoice) o);
	    } else if (o instanceof Ingredient) {
		matrix.add(new RecipeChoice((Ingredient) o));
	    } else {
		throw new Error("Error in Recipe");
	    }
	}
	recipes.add(this);
    }

    public int getSize() {
	return matrix.size();
    }

    public Recipe setOutput(Food food) {
	output = food;
	return this;
    }

    public Food getOutput() {
	return output;
    }

    public boolean match(Collection<Ingredient> ingredients) {
	Collection<RecipeChoice> m = new ArrayList<RecipeChoice>(matrix);
	for (Ingredient i : ingredients) {
	    if (m.size() == 0)
		return true;
	    Iterator<RecipeChoice> iterator = m.iterator();
	    boolean b = false;
	    while (iterator.hasNext() && !b) {
		RecipeChoice c = iterator.next();
		if (c.match(i)) {
		    iterator.remove();
		    b = true;
		}
	    }
	}
	if (m.size() == 0)
	    return true;
	return false;
    }

    public static Recipe getRecipe(Collection<Ingredient> ingredients) {
	if (ingredients.size() > 5)
	    return null;
	for (Recipe r : recipes) {
	    if (r.match(ingredients))
		return r;
	}
	return null;
    }

    public static void init() {
	new Recipe(new RecipeChoice(Ingredient.APPLE, Ingredient.WILDBERRY),
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.HYDROMELON,
			Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS,
			Ingredient.MIGHTY_BANANAS),
		Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR).setOutput(Food.FRUITCAKE);
	new Recipe(new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY),
		Ingredient.HEARTY_BLUESHELL_SNAIL, Ingredient.HYLIAN_RICE, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT)
		.setOutput(Food.SEAFOOD_PAELLA);
	new Recipe(Ingredient.MONSTER_EXTRACT, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE)
		.setOutput(Food.MONSTER_CURRY);
	new Recipe(Ingredient.MONSTER_EXTRACT, Ingredient.HYLIAN_RICE, Ingredient.ROCK_SALT)
		.setOutput(Food.MONSTER_RICE_BALLS);
	new Recipe(Ingredient.MONSTER_EXTRACT, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR, Ingredient.GOAT_BUTTER)
		.setOutput(Food.MONSTER_CAKE);
	new Recipe(Ingredient.MONSTER_EXTRACT, Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER)
		.setOutput(Food.MONSTER_SOUP);
	new Recipe(Ingredient.MONSTER_EXTRACT,
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.MONSTER_STEW);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.HYDROMELON,
		Ingredient.VOLTFRUIT, Ingredient.FRESH_MILK).setOutput(Food.CREAMY_HEART_SOUP);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		Ingredient.HEARTY_BLUESHELL_SNAIL).setOutput(Food.CLAM_CHOWDER);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		Ingredient.FORTIFIED_PUMPKIN).setOutput(Food.PUMPKIN_STEW);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		new RecipeChoice(Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_GOURMET_MEAT))
		.setOutput(Food.GOURMET_MEAT_STEW);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		new RecipeChoice(Ingredient.RAW_PRIME_MEAT, Ingredient.RAW_BIRD_THIGH)).setOutput(Food.PRIME_MEAT_STEW);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK)).setOutput(Food.MEAT_STEW);
	new Recipe(Ingredient.RAW_GOURMET_MEAT, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE)
		.setOutput(Food.GOURMET_MEAT_CURRY);
	new Recipe(Ingredient.RAW_WHOLE_BIRD, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE)
		.setOutput(Food.GOURMET_POULTRY_CURRY);
	new Recipe(Ingredient.RAW_PRIME_MEAT, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE)
		.setOutput(Food.PRIME_MEAT_CURRY);
	new Recipe(Ingredient.RAW_BIRD_THIGH, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE)
		.setOutput(Food.PRIME_POULTRY_CURRY);
	new Recipe(Ingredient.RAW_MEAT, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE).setOutput(Food.MEAT_CURRY);
	new Recipe(Ingredient.RAW_BIRD_DRUMSTICK, Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE)
		.setOutput(Food.POULTRY_CURRY);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_BLUESHELL_SNAIL, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY),
		Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE).setOutput(Food.SEAFOOD_CURRY);
	new Recipe(new RecipeChoice(Ingredient.ENDURA_CARROT, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE).setOutput(Food.VEGETABLE_CURRY);
	new Recipe(Ingredient.FORTIFIED_PUMPKIN, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR,
		Ingredient.GOAT_BUTTER).setOutput(Food.PUMPKIN_PIE);
	new Recipe(new RecipeChoice(Ingredient.ENDURA_CARROT, Ingredient.SWIFT_CARROT), Ingredient.TABANTHA_WHEAT,
		Ingredient.CANE_SUGAR, Ingredient.GOAT_BUTTER).setOutput(Food.CARROT_CAKE);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.BIRD_EGG, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR,
		Ingredient.WILDBERRY).setOutput(Food.WILDBERRY_CREPE);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.BIRD_EGG, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR,
		Ingredient.COURSER_BEE_HONEY).setOutput(Food.HONEY_CREPE);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.BIRD_EGG, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR)
		.setOutput(Food.PLAIN_CREPE);
	new Recipe(Ingredient.APPLE, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR, Ingredient.GOAT_BUTTER)
		.setOutput(Food.APPLE_PIE);
	new Recipe(new RecipeChoice(Ingredient.ACORN, Ingredient.CHICKALOO_TREE_NUT), Ingredient.TABANTHA_WHEAT,
		Ingredient.CANE_SUGAR, Ingredient.GOAT_BUTTER).setOutput(Food.NUTCAKE);
	new Recipe(Ingredient.BIRD_EGG, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR, Ingredient.GOAT_BUTTER)
		.setOutput(Food.EGG_TART);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.BIRD_EGG, Ingredient.CANE_SUGAR).setOutput(Food.EGG_PUDDING);
	new Recipe(Ingredient.MIGHTY_BANANAS, Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR)
		.setOutput(Food.FRIED_BANANAS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.WILDBERRY,
			Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
			Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS),
		Ingredient.TABANTHA_WHEAT, Ingredient.CANE_SUGAR, Ingredient.GOAT_BUTTER).setOutput(Food.FRUIT_PIE);
	new Recipe(Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.MEAT_PIE);
	new Recipe(Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.FISH_PIE);
	new Recipe(Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER, Ingredient.HEARTY_SALMON)
		.setOutput(Food.SALMON_MEUNIERE);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT, Ingredient.HEARTY_SALMON)
		.setOutput(Food.SALMON_RISOTTO);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.CRAB_RISOTTO);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.ENDURA_CARROT, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN))
		.setOutput(Food.VEGETABLE_RISOTTO);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.MUSHROOM_RISOTTO);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.CREAM_OF_MUSHROOM_SOUP);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.ENDURA_CARROT, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN))
		.setOutput(Food.VEGGIE_CREAM_SOUP);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.CREAMY_MEAT_SOUP);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.CREAMY_SEAFOOD_SOUP);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.CREAM_OF_VEGETABLE_SOUP);
	new Recipe(Ingredient.FRESH_MILK, Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		new RecipeChoice(Ingredient.ENDURA_CARROT, Ingredient.SWIFT_CARROT)).setOutput(Food.CARROT_STEW);
	new Recipe(Ingredient.BIRD_EGG, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.MUSHROOM_OMELET);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.BIRD_EGG, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.CRAB_OMELET_WITH_RICE);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.BIRD_EGG, Ingredient.GOAT_BUTTER, Ingredient.RAW_WHOLE_BIRD)
		.setOutput(Food.GOURMET_POULTRY_PILAF);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.BIRD_EGG, Ingredient.GOAT_BUTTER, Ingredient.RAW_BIRD_THIGH)
		.setOutput(Food.PRIME_POULTRY_PILAF);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.BIRD_EGG, Ingredient.GOAT_BUTTER, Ingredient.RAW_BIRD_DRUMSTICK)
		.setOutput(Food.POULTRY_PILAF);
	new Recipe(Ingredient.BIRD_EGG, Ingredient.GOAT_BUTTER, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.VEGETABLE_OMELET);
	new Recipe(Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)).setOutput(Food.PORGY_MEUNIERE);
	new Recipe(Ingredient.TABANTHA_WHEAT, Ingredient.GOAT_BUTTER,
		new RecipeChoice(Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS, Ingredient.STAMINOKA_BASS,
			Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.SEAFOOD_MEUNIERE);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY, Ingredient.HEARTY_BLUESHELL_SNAIL))
		.setOutput(Food.SEAFOOD_FRIED_RICE);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.GOAT_BUTTER, Ingredient.GORON_SPICE).setOutput(Food.CURRY_PILAF);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD))
		.setOutput(Food.GOURMET_MEAT_AND_RICE_BOWL);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAW_PRIME_MEAT, Ingredient.RAW_BIRD_THIGH))
		.setOutput(Food.PRIME_MEAT_AND_RICE_BOWL);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.ROCK_SALT,
		new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.MEAT_AND_RICE_BOWL);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.BIRD_EGG).setOutput(Food.FRIED_EGG_AND_RICE);
	new Recipe(Ingredient.HYLIAN_RICE,
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.MEATY_RICE_BALLS);
	new Recipe(Ingredient.HYLIAN_RICE,
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.SEAFOOD_RICE_BALLS);
	new Recipe(Ingredient.HYLIAN_RICE,
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.MUSHROOM_RICE_BALLS);
	new Recipe(Ingredient.HYLIAN_RICE,
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.VEGGIE_RICE_BALLS);
	new Recipe(Ingredient.APPLE, Ingredient.GOAT_BUTTER).setOutput(Food.HOT_BUTTERED_APPLE);
	new Recipe(Ingredient.FORTIFIED_PUMPKIN,
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.MEAT_STUFFED_PUMPKINS);
	new Recipe(
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		Ingredient.COURSER_BEE_HONEY).setOutput(Food.GLAZED_MEAT);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB),
		Ingredient.COURSER_BEE_HONEY).setOutput(Food.GLAZED_SEAFOOD);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
		Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
		Ingredient.SILENT_SHROOM), Ingredient.COURSER_BEE_HONEY).setOutput(Food.GLAZED_MUSHROOMS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN), Ingredient.COURSER_BEE_HONEY)
		.setOutput(Food.GLAZED_VEGGIES);
	new Recipe(Ingredient.HYLIAN_RICE, Ingredient.GORON_SPICE).setOutput(Food.CURRY_RICE);
	new Recipe(Ingredient.APPLE, Ingredient.COURSER_BEE_HONEY).setOutput(Food.HONEYED_APPLE);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.WILDBERRY,
		Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS,
		Ingredient.MIGHTY_BANANAS), Ingredient.COURSER_BEE_HONEY).setOutput(Food.HONEYED_FRUITS);
	new Recipe(Ingredient.RAW_GOURMET_MEAT, Ingredient.GORON_SPICE).setOutput(Food.GOURMET_SPICED_MEAT_SKEWER);
	new Recipe(Ingredient.RAW_PRIME_MEAT, Ingredient.GORON_SPICE).setOutput(Food.PRIME_SPICED_MEAT_SKEWER);
	new Recipe(Ingredient.RAW_MEAT, Ingredient.GORON_SPICE).setOutput(Food.SPICED_MEAT_SKEWER);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
		Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
		Ingredient.SILENT_SHROOM), Ingredient.GORON_SPICE);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
		Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
		Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
		Ingredient.SILENT_PRINCESS), Ingredient.GORON_SPICE).setOutput(Food.HERB_SAUTE);
	new Recipe(new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD), Ingredient.ROCK_SALT)
		.setOutput(Food.SALT_GRILLED_GOURMET_MEAT);
	new Recipe(new RecipeChoice(Ingredient.RAW_PRIME_MEAT, Ingredient.RAW_BIRD_THIGH), Ingredient.ROCK_SALT)
		.setOutput(Food.SALT_GRILLED_PRIME_MEAT);
	new Recipe(new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK), Ingredient.ROCK_SALT)
		.setOutput(Food.SALT_GRILLED_MEAT);
	new Recipe(new RecipeChoice(Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB),
		Ingredient.GORON_SPICE).setOutput(Food.CRAB_STIR_FRY);
	new Recipe(new RecipeChoice(Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB),
		Ingredient.ROCK_SALT).setOutput(Food.SALT_GRILLED_CRAB);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
		Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
		Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
		Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY), Ingredient.ROCK_SALT)
		.setOutput(Food.SALT_GRILLED_FISH);
	new Recipe(Ingredient.TABANTHA_WHEAT, Ingredient.ROCK_SALT).setOutput(Food.WHEAT_BREAD);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
		Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
		Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
		Ingredient.SILENT_PRINCESS), Ingredient.ROCK_SALT).setOutput(Food.SALT_GRILLED_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
		Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
		Ingredient.SILENT_SHROOM), Ingredient.ROCK_SALT).setOutput(Food.SALT_GRILLED_MUSHROOMS);
	new Recipe(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
		new RecipeChoice(Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_BIRD_THIGH,
		new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_PRIME_MEAT, Ingredient.RAW_BIRD_THIGH,
		new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT, Ingredient.RAW_BIRD_THIGH,
		new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD), Ingredient.RAW_PRIME_MEAT,
		Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK).setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT),
		Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK)
		.setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_MEAT,
		Ingredient.RAW_BIRD_DRUMSTICK).setOutput(Food.COPIOUS_MEAT_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS).setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.COOL_SAFFLINA, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA),
		Ingredient.WARM_SAFFLINA, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA),
		Ingredient.ELECTRIC_SAFFLINA, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA),
		Ingredient.SWIFT_VIOLET, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
		Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET),
		Ingredient.MIGHTY_THISTLE, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS)
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		new RecipeChoice(Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.HYRULE_HERB,
		new RecipeChoice(Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
			Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.SWIFT_CARROT,
		new RecipeChoice(Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA,
			Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.FORTIFIED_PUMPKIN,
		new RecipeChoice(Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.COOL_SAFFLINA,
		new RecipeChoice(Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.WARM_SAFFLINA,
		new RecipeChoice(Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.SWIFT_VIOLET,
		new RecipeChoice(Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.HYRULE_HERB,
		new RecipeChoice(Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
			Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.SWIFT_CARROT,
		new RecipeChoice(Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA,
			Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.FORTIFIED_PUMPKIN,
		new RecipeChoice(Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.COOL_SAFFLINA,
		new RecipeChoice(Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.WARM_SAFFLINA,
		new RecipeChoice(Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH), Ingredient.ENDURA_CARROT,
		Ingredient.ARMORANTH, new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT,
		new RecipeChoice(Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA,
			Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.FORTIFIED_PUMPKIN,
		new RecipeChoice(Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.COOL_SAFFLINA,
		new RecipeChoice(Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.WARM_SAFFLINA,
		new RecipeChoice(Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT),
		Ingredient.HYRULE_HERB, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
		new RecipeChoice(Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.COOL_SAFFLINA,
		new RecipeChoice(Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.WARM_SAFFLINA,
		new RecipeChoice(Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB),
		Ingredient.SWIFT_CARROT, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
		new RecipeChoice(Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
			Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
			Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.WARM_SAFFLINA,
		new RecipeChoice(Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT),
		Ingredient.FORTIFIED_PUMPKIN, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA,
		new RecipeChoice(Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.COOL_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.COOL_SAFFLINA, Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.COOL_SAFFLINA, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN),
		Ingredient.COOL_SAFFLINA, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA),
		Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
		new RecipeChoice(Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA),
		Ingredient.WARM_SAFFLINA, Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA),
		Ingredient.WARM_SAFFLINA, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA),
		Ingredient.WARM_SAFFLINA, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA),
		Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET, new RecipeChoice(Ingredient.MIGHTY_THISTLE,
			Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA),
		Ingredient.ELECTRIC_SAFFLINA, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA),
		Ingredient.ELECTRIC_SAFFLINA, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA),
		Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE,
		new RecipeChoice(Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA),
		Ingredient.SWIFT_VIOLET, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
		Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET),
		Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
		new RecipeChoice(Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.BLUE_NIGHTSHADE,
		Ingredient.SILENT_PRINCESS).setOutput(Food.COPIOUS_FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT), Ingredient.APPLE,
		Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS).setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE),
		Ingredient.WILDBERRY, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS)
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY),
		Ingredient.HYDROMELON, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS)
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY, Ingredient.HYDROMELON),
		Ingredient.SPICY_PEPPER, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS)
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
		new RecipeChoice(Ingredient.WILDBERRY, Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER,
			Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.WILDBERRY,
		new RecipeChoice(Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
			Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.HYDROMELON, new RecipeChoice(
		Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.SPICY_PEPPER,
		new RecipeChoice(Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.VOLTFRUIT,
		new RecipeChoice(Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT), Ingredient.APPLE,
		Ingredient.WILDBERRY,
		new RecipeChoice(Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
			Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT), Ingredient.APPLE,
		Ingredient.HYDROMELON, new RecipeChoice(Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
			Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT), Ingredient.APPLE,
		Ingredient.SPICY_PEPPER,
		new RecipeChoice(Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT), Ingredient.APPLE,
		Ingredient.VOLTFRUIT, new RecipeChoice(Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE),
		Ingredient.WILDBERRY, Ingredient.HYDROMELON, new RecipeChoice(Ingredient.SPICY_PEPPER,
			Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE),
		Ingredient.WILDBERRY, Ingredient.SPICY_PEPPER,
		new RecipeChoice(Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE),
		Ingredient.WILDBERRY, Ingredient.VOLTFRUIT,
		new RecipeChoice(Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY),
		Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER,
		new RecipeChoice(Ingredient.VOLTFRUIT, Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY),
		Ingredient.HYDROMELON, Ingredient.VOLTFRUIT,
		new RecipeChoice(Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY, Ingredient.HYDROMELON),
		Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
		new RecipeChoice(Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS))
		.setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.FLEET_LOTUS_SEEDS,
		Ingredient.MIGHTY_BANANAS).setOutput(Food.COPIOUS_SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM).setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM)
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM),
		Ingredient.STAMELLA_SHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM)
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM),
		Ingredient.CHILLSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM)
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM),
		Ingredient.SUNSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM)
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM),
		Ingredient.ZAPSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM)
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM),
		Ingredient.RUSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM)
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		new RecipeChoice(Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.ENDURA_SHROOM,
		new RecipeChoice(Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
			Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
			Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.STAMELLA_SHROOM,
		new RecipeChoice(Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM,
			Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.CHILLSHROOM,
		new RecipeChoice(Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.SUNSHROOM,
		new RecipeChoice(Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.ZAPSHROOM, new RecipeChoice(
		Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM,
		new RecipeChoice(Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
			Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
			Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.STAMELLA_SHROOM,
		new RecipeChoice(Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM,
			Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.CHILLSHROOM,
		new RecipeChoice(Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.SUNSHROOM, new RecipeChoice(Ingredient.ZAPSHROOM, Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.ZAPSHROOM, new RecipeChoice(Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE), Ingredient.HYLIAN_SHROOM,
		Ingredient.RAZORSHROOM, new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM,
		new RecipeChoice(Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM,
			Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.CHILLSHROOM,
		new RecipeChoice(Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.SUNSHROOM, new RecipeChoice(Ingredient.ZAPSHROOM,
			Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.ZAPSHROOM, new RecipeChoice(Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM),
		Ingredient.ENDURA_SHROOM, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM),
		Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
		new RecipeChoice(Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM),
		Ingredient.STAMELLA_SHROOM, Ingredient.SUNSHROOM, new RecipeChoice(Ingredient.ZAPSHROOM,
			Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM),
		Ingredient.STAMELLA_SHROOM, Ingredient.ZAPSHROOM, new RecipeChoice(Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM),
		Ingredient.STAMELLA_SHROOM, Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM),
		Ingredient.STAMELLA_SHROOM, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM),
		Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM, new RecipeChoice(Ingredient.ZAPSHROOM,
			Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM),
		Ingredient.CHILLSHROOM, Ingredient.ZAPSHROOM, new RecipeChoice(Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM),
		Ingredient.CHILLSHROOM, Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM),
		Ingredient.CHILLSHROOM, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM),
		Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, new RecipeChoice(Ingredient.RUSHROOM,
			Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM),
		Ingredient.SUNSHROOM, Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM),
		Ingredient.SUNSHROOM, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM),
		Ingredient.ZAPSHROOM, Ingredient.RUSHROOM,
		new RecipeChoice(Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM),
		Ingredient.ZAPSHROOM, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM),
		Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
		new RecipeChoice(Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.IRONSHROOM,
		Ingredient.SILENT_SHROOM).setOutput(Food.COPIOUS_MUSHROOM_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY).setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT),
		Ingredient.SIZZLEFIN_TROUT, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT),
		Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT),
		Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT),
		Ingredient.MIGHTY_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP),
		Ingredient.ARMORED_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
		new RecipeChoice(Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.STAMINOKA_BASS,
		new RecipeChoice(Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT, Ingredient.VOLTFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.CHILLFIN_TROUT,
		new RecipeChoice(Ingredient.SIZZLEFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
			Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.SIZZLEFIN_TROUT,
		new RecipeChoice(Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.VOLTFIN_TROUT,
		new RecipeChoice(Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.STEALTHFIN_TROUT,
		new RecipeChoice(Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
			Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.MIGHTY_CARP, new RecipeChoice(
		Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.STAMINOKA_BASS,
		new RecipeChoice(Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT, Ingredient.VOLTFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.CHILLFIN_TROUT,
		new RecipeChoice(Ingredient.SIZZLEFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
			Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.SIZZLEFIN_TROUT,
		new RecipeChoice(Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.VOLTFIN_TROUT,
		new RecipeChoice(Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.STEALTHFIN_TROUT, new RecipeChoice(Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.MIGHTY_CARP, new RecipeChoice(Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
			Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS), Ingredient.HYRULE_BASS,
		Ingredient.SANKE_CARP, new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT,
		new RecipeChoice(Ingredient.SIZZLEFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
			Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.SIZZLEFIN_TROUT,
		new RecipeChoice(Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.VOLTFIN_TROUT,
		new RecipeChoice(Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.STEALTHFIN_TROUT,
		new RecipeChoice(Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
			Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.MIGHTY_CARP, new RecipeChoice(Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS),
		Ingredient.STAMINOKA_BASS, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
		new RecipeChoice(Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.VOLTFIN_TROUT,
		new RecipeChoice(Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
		new RecipeChoice(Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
			Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.MIGHTY_CARP, new RecipeChoice(Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS),
		Ingredient.CHILLFIN_TROUT, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT),
		Ingredient.SIZZLEFIN_TROUT, Ingredient.VOLTFIN_TROUT,
		new RecipeChoice(Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT),
		Ingredient.SIZZLEFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
		new RecipeChoice(Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
			Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT),
		Ingredient.SIZZLEFIN_TROUT, Ingredient.MIGHTY_CARP, new RecipeChoice(Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT),
		Ingredient.SIZZLEFIN_TROUT, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT),
		Ingredient.SIZZLEFIN_TROUT, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT),
		Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT,
		new RecipeChoice(Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
			Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT),
		Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP, new RecipeChoice(Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT),
		Ingredient.VOLTFIN_TROUT, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT),
		Ingredient.VOLTFIN_TROUT, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT),
		Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP, new RecipeChoice(Ingredient.ARMORED_CARP,
			Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT),
		Ingredient.STEALTHFIN_TROUT, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT),
		Ingredient.STEALTHFIN_TROUT, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT),
		Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
		new RecipeChoice(Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT),
		Ingredient.MIGHTY_CARP, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.VOLTFIN_TROUT, Ingredient.STEALTHFIN_TROUT, Ingredient.MIGHTY_CARP),
		Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP,
		new RecipeChoice(Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY))
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)
		.setOutput(Food.COPIOUS_FISH_SKEWERS);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.FRIED_WILD_GREENS_2);
	new Recipe(new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD),
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.GOURMET_MEAT_AND_SEAFOOD_FRY);
	new Recipe(new RecipeChoice(Ingredient.RAW_PRIME_MEAT, Ingredient.RAW_BIRD_THIGH),
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.PRIME_MEAT_AND_SEAFOOD_FRY);
	new Recipe(new RecipeChoice(Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.MEAT_AND_SEAFOOD_FRY);
	new Recipe(
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		Ingredient.SPICY_PEPPER).setOutput(Food.PEPPER_STEAK);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
			Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
			Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP,
			Ingredient.ARMORED_CARP, Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY,
			Ingredient.ARMORED_PORGY, Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
			Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB),
		Ingredient.SPICY_PEPPER).setOutput(Food.SPICY_PEPPERED_SEAFOOD);
	new Recipe(
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.STEAMED_MEAT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
		Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
		Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
		Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.STEAMED_FISH);
	new Recipe(
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.STEAMED_MUSHROOMS);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY, Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
			Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS),
		new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
			Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN,
			Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA,
			Ingredient.SWIFT_VIOLET, Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH,
			Ingredient.BLUE_NIGHTSHADE, Ingredient.SILENT_PRINCESS))
		.setOutput(Food.STEAMED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
		Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
		Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
		Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY),
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.FISH_AND_MUSHROOM_SKEWER);
	new Recipe(
		new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
			Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK),
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.MEAT_AND_MUSHROOM_SKEWER);
	new Recipe(
		new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
			Ingredient.WILDBERRY, Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
			Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS),
		new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
			Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM,
			Ingredient.SUNSHROOM, Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM,
			Ingredient.IRONSHROOM, Ingredient.SILENT_SHROOM))
		.setOutput(Food.FRUIT_AND_MUSHROOM_MIX);
	new Recipe(new RecipeChoice(Ingredient.RAW_GOURMET_MEAT, Ingredient.RAW_WHOLE_BIRD, Ingredient.RAW_PRIME_MEAT,
		Ingredient.RAW_BIRD_THIGH, Ingredient.RAW_MEAT, Ingredient.RAW_BIRD_DRUMSTICK))
		.setOutput(Food.MEAT_SKEWER);
	new Recipe(new RecipeChoice(Ingredient.SNEAKY_RIVER_SNAIL, Ingredient.HEARTY_BLUESHELL_SNAIL,
		Ingredient.RAZORCLAW_CRAB, Ingredient.IRONSHELL_CRAB, Ingredient.BRIGHT_EYED_CRAB))
		.setOutput(Food.SEAFOOD_SKEWER);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_SALMON, Ingredient.HEARTY_BASS, Ingredient.HYRULE_BASS,
		Ingredient.STAMINOKA_BASS, Ingredient.CHILLFIN_TROUT, Ingredient.SIZZLEFIN_TROUT,
		Ingredient.STEALTHFIN_TROUT, Ingredient.VOLTFIN_TROUT, Ingredient.MIGHTY_CARP, Ingredient.ARMORED_CARP,
		Ingredient.SANKE_CARP, Ingredient.MIGHTY_PORGY, Ingredient.ARMORED_PORGY)).setOutput(Food.FISH_SKEWER);
	new Recipe(Ingredient.BIRD_EGG).setOutput(Food.OMELET);
	new Recipe(Ingredient.FRESH_MILK).setOutput(Food.WARM_MILK);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
		Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
		Ingredient.SILENT_SHROOM)).setOutput(Food.MUSHROOM_SKEWER);
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_RADISH, Ingredient.HEARTY_RADISH, Ingredient.ENDURA_CARROT,
		Ingredient.HYRULE_HERB, Ingredient.SWIFT_CARROT, Ingredient.FORTIFIED_PUMPKIN, Ingredient.COOL_SAFFLINA,
		Ingredient.WARM_SAFFLINA, Ingredient.ELECTRIC_SAFFLINA, Ingredient.SWIFT_VIOLET,
		Ingredient.MIGHTY_THISTLE, Ingredient.ARMORANTH, Ingredient.BLUE_NIGHTSHADE,
		Ingredient.SILENT_PRINCESS)).setOutput(Food.FRIED_WILD_GREENS);
	new Recipe(new RecipeChoice(Ingredient.HEARTY_DURIAN, Ingredient.PALM_FRUIT, Ingredient.APPLE,
		Ingredient.WILDBERRY, Ingredient.HYDROMELON, Ingredient.SPICY_PEPPER, Ingredient.VOLTFRUIT,
		Ingredient.FLEET_LOTUS_SEEDS, Ingredient.MIGHTY_BANANAS)).setOutput(Food.SIMMERED_FRUIT);
	new Recipe(new RecipeChoice(Ingredient.CHICKALOO_TREE_NUT, Ingredient.ACORN)).setOutput(Food.SAUTEED_NUTS);
	new Recipe(Ingredient.FAIRY).setOutput(Food.FAIRY_TONIC);
	new Recipe(Ingredient.FAIRY, Ingredient.FAIRY).setOutput(Food.FAIRY_TONIC_2);
	new Recipe(Ingredient.FAIRY, Ingredient.FAIRY, Ingredient.FAIRY).setOutput(Food.FAIRY_TONIC_3);
	new Recipe(Ingredient.FAIRY, Ingredient.FAIRY, Ingredient.FAIRY, Ingredient.FAIRY)
		.setOutput(Food.FAIRY_TONIC_4);
	new Recipe(Ingredient.SPICY_PEPPER).setOutput(Food.SAUTEED_PEPPERS);
	new Recipe(Ingredient.COURSER_BEE_HONEY).setOutput(Food.HONEY_CANDY);
	new Recipe(new RecipeChoice(Ingredient.FLINT, Ingredient.AMBER, Ingredient.OPAL, Ingredient.LUMINOUS_STONE,
		Ingredient.TOPAZ, Ingredient.RUBY, Ingredient.SAPPHIRE, Ingredient.DIAMOND, Ingredient.WOOD))
		.setOutput(Food.ROCK_HARD_FOOD);
	new Recipe(new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
		Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS, Ingredient.LIZALFOS_HORN,
		Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL, Ingredient.ICY_LIZALFOS_TAIL,
		Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL, Ingredient.LYNEL_HORN,
		Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY, Ingredient.WHITE_CHUCHU_JELLY,
		Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY, Ingredient.KEESE_WING,
		Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING, Ingredient.ELECTRIC_KEESE_WING,
		Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE, Ingredient.OCTOROK_EYEBALL,
		Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN, Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL,
		Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS, Ingredient.WINTERWING_BUTTERFLY,
		Ingredient.SUMMERWING_BUTTERFLY, Ingredient.THUNDERWING_BUTTERFLY, Ingredient.SMOTHERWING_BUTTERFLY,
		Ingredient.COLD_DARNER, Ingredient.WARM_DARNER, Ingredient.ELECTRIC_DARNER, Ingredient.RESTLESS_CRICKET,
		Ingredient.BLADED_RHINO_BEETLE, Ingredient.RUGGED_RHINO_BEETLE, Ingredient.ENERGETIC_RHINO_BEETLE,
		Ingredient.SUNSET_FIREFLY, Ingredient.HOT_FOOTED_FROG, Ingredient.TIRELESS_FROG,
		Ingredient.HIGHTAIL_LIZARD, Ingredient.HEARTY_LIZARD, Ingredient.FIREPROOF_LIZARD))
		.setOutput(Food.DUBIOUS_FOOD);
	new Recipe(Ingredient.HEARTY_LIZARD, new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG,
		Ingredient.BOKOBLIN_GUTS, Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
		Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
		Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
		Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
		Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
		Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
		Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
		Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN, Ingredient.MOLDUGA_GUTS,
		Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS)).setOutput(Food.HEARTY_ELIXIR);
	new Recipe(new RecipeChoice(Ingredient.RESTLESS_CRICKET, Ingredient.ENERGETIC_RHINO_BEETLE),
		new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
			Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
			Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
			Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
			Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
			Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
			Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
			Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
			Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
			Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH,
			Ingredient.HINOX_GUTS))
		.setOutput(Food.ENERGIZING_ELIXIR);
	new Recipe(Ingredient.TIRELESS_FROG, new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG,
		Ingredient.BOKOBLIN_GUTS, Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
		Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
		Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
		Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
		Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
		Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
		Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
		Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN, Ingredient.MOLDUGA_GUTS,
		Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS))
		.setOutput(Food.ENDURING_ELIXIR);
	new Recipe(new RecipeChoice(Ingredient.SMOTHERWING_BUTTERFLY, Ingredient.FIREPROOF_LIZARD),
		new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
			Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
			Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
			Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
			Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
			Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
			Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
			Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
			Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
			Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH,
			Ingredient.HINOX_GUTS))
		.setOutput(Food.FIREPROOF_ELIXIR);
	new Recipe(new RecipeChoice(Ingredient.WINTERWING_BUTTERFLY, Ingredient.COLD_DARNER),
		new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
			Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
			Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
			Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
			Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
			Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
			Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
			Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
			Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
			Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH,
			Ingredient.HINOX_GUTS))
		.setOutput(Food.CHILLY_ELIXIR);
	new Recipe(new RecipeChoice(Ingredient.SUMMERWING_BUTTERFLY, Ingredient.WARM_DARNER),
		new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
			Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
			Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
			Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
			Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
			Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
			Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
			Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
			Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
			Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH,
			Ingredient.HINOX_GUTS))
		.setOutput(Food.SPICY_ELIXIR);
	new Recipe(new RecipeChoice(Ingredient.THUNDERWING_BUTTERFLY, Ingredient.ELECTRIC_DARNER),
		new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
			Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
			Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
			Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
			Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
			Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
			Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
			Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
			Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
			Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH,
			Ingredient.HINOX_GUTS))
		.setOutput(Food.ELECTRO_ELIXIR);
	new Recipe(new RecipeChoice(Ingredient.HOT_FOOTED_FROG, Ingredient.HIGHTAIL_LIZARD),
		new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG, Ingredient.BOKOBLIN_GUTS,
			Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
			Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
			Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
			Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
			Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
			Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
			Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
			Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
			Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH,
			Ingredient.HINOX_GUTS))
		.setOutput(Food.HASTY_ELIXIR);
	new Recipe(Ingredient.SUNSET_FIREFLY, new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG,
		Ingredient.BOKOBLIN_GUTS, Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
		Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
		Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
		Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
		Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
		Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
		Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
		Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN, Ingredient.MOLDUGA_GUTS,
		Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS)).setOutput(Food.SNEAKY_ELIXIR);
	new Recipe(Ingredient.BLADED_RHINO_BEETLE, new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG,
		Ingredient.BOKOBLIN_GUTS, Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
		Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
		Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
		Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
		Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
		Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
		Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
		Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN, Ingredient.MOLDUGA_GUTS,
		Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS)).setOutput(Food.MIGHTY_ELIXIR);
	new Recipe(Ingredient.RUGGED_RHINO_BEETLE, new RecipeChoice(Ingredient.BOKOBLIN_HORN, Ingredient.BOKOBLIN_FANG,
		Ingredient.BOKOBLIN_GUTS, Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS,
		Ingredient.LIZALFOS_HORN, Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL,
		Ingredient.ICY_LIZALFOS_TAIL, Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL,
		Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF, Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY,
		Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY, Ingredient.YELLOW_CHUCHU_JELLY,
		Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING, Ingredient.FIRE_KEESE_WING,
		Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL, Ingredient.OCTOROK_TENTACLE,
		Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN, Ingredient.MOLDUGA_GUTS,
		Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS)).setOutput(Food.TOUGH_ELIXIR);
	System.out.println("Recipes : " + recipes.size());
    }

    @Override
    public int compareTo(Recipe o) {
	if (matrix.size() > o.getSize())
	    return -1;
	else 
	    return 1;
    }

}
