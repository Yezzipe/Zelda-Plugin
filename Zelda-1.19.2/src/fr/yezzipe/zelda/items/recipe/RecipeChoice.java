package fr.yezzipe.zelda.items.recipe;

import java.util.HashSet;
import java.util.Set;

import fr.yezzipe.zelda.items.enums.Ingredient;

public class RecipeChoice {
    
    private Set<Ingredient> choice = new HashSet<Ingredient>();

    public RecipeChoice(Ingredient...ingredients) {
	for (Ingredient i : ingredients) {
	    choice.add(i);
	}
    }
    
    public Set<Ingredient> getIngredients() {
	return choice;
    }
    
    public boolean match(Ingredient ingredient) {
	return choice.contains(ingredient);
    }

}
