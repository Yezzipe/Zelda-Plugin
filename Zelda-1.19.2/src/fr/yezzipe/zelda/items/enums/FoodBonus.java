package fr.yezzipe.zelda.items.enums;

import java.util.Random;

public enum FoodBonus {
    CHILLY, ELECTRO, ENDURING, ENERGIZING, FIREPROOF, HASTY, HEARTY, MIGHTY, SNEAKY, SPICY, TOUGH, NONE, RANDOM;

    public static FoodBonus random() {
	Random rand = new Random();
	int index = Math.abs(rand.nextInt()%FoodBonus.values().length);
	while (FoodBonus.values()[index] == FoodBonus.NONE || FoodBonus.values()[index] == FoodBonus.RANDOM) {
	    index = Math.abs(rand.nextInt()%FoodBonus.values().length);
	}
	return FoodBonus.values()[index];
    }
}
