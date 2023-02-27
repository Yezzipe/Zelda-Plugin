package fr.yezzipe.zelda.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import fr.yezzipe.zelda.items.enums.Food;
import fr.yezzipe.zelda.items.enums.FoodBonus;
import fr.yezzipe.zelda.items.enums.Ingredient;

public class FoodStatCalculator {
    
    private FoodBonus bonus;
    
    private int health;
    
    private int duration;
    
    public FoodStatCalculator(Collection<Ingredient> ingredients, Food food) {
	Set<FoodBonus> bonuses = new HashSet<FoodBonus>();
	Set<Ingredient> ing = new HashSet<Ingredient>();
	duration = 0;
	health = 0;
	for (Ingredient i : ingredients) {
	    FoodBonus b = i.getBonus();
	    health += i.getHealth();
	    int[] durations = i.getDuration();
	    int durInd = Math.max(0, durations.length-1);
	    if (ing.contains(i) && durations.length > 0) this.duration += durations[durInd];
	    else if (durations.length > 0) this.duration += durations[0];
	    if (b != FoodBonus.RANDOM && b != FoodBonus.NONE) bonuses.add(b);
	    ing.add(i);
	}
	if (bonuses.size() > 1) bonus = FoodBonus.NONE;
	else if (bonuses.size() == 1) bonus = bonuses.toArray(new FoodBonus[1])[0];
	else bonus = FoodBonus.NONE;
	System.out.println(health);
	System.out.println(duration);
	System.out.println(bonus);
    }
    
}
