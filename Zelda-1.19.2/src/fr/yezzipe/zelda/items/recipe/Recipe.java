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
	if (objects.length > 5 || objects.length < 1) return;
	for (Object o : objects) {
	    if (o instanceof RecipeChoice) {
		matrix.add((RecipeChoice) o);
	    } else if (o instanceof Ingredient) {
		matrix.add(new RecipeChoice((Ingredient) o));
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
	new Recipe(new RecipeChoice(Ingredient.BIG_HEARTY_TRUFFLE, Ingredient.HEARTY_TRUFFLE, Ingredient.HYLIAN_SHROOM,
		Ingredient.ENDURA_SHROOM, Ingredient.STAMELLA_SHROOM, Ingredient.CHILLSHROOM, Ingredient.SUNSHROOM,
		Ingredient.ZAPSHROOM, Ingredient.RUSHROOM, Ingredient.RAZORSHROOM, Ingredient.IRONSHROOM,
		Ingredient.SILENT_SHROOM)).setOutput(Food.MUSHROOM_SKEWER);
    }

    @Override
    public int compareTo(Recipe o) {
	if (matrix.size() > o.getSize()) return 1;
	else if (matrix.size() == o.getSize()) return 0;
	return -1;
    }

}
