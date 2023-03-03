package fr.yezzipe.zelda.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Food;
import fr.yezzipe.zelda.items.enums.FoodBonus;
import fr.yezzipe.zelda.items.enums.Ingredient;

public class FoodStatCalculator {

    private FoodBonus bonus;

    private int health;
    
    private int potency;

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
	    int durInd = Math.max(0, durations.length - 1);
	    if (ing.contains(i) && durations.length > 0)
		this.duration += durations[durInd];
	    else if (durations.length > 0)
		this.duration += durations[0];
	    if (b != FoodBonus.RANDOM && b != FoodBonus.NONE)
		bonuses.add(b);
	    ing.add(i);
	}
	if (bonuses.size() > 1)
	    bonus = FoodBonus.NONE;
	else if (bonuses.size() == 1)
	    bonus = bonuses.toArray(new FoodBonus[1])[0];
	else
	    bonus = FoodBonus.NONE;
	HashMap<Ingredient, Integer> map = new HashMap<Ingredient, Integer>();
	for (Ingredient i : ingredients) {
	    if (i.getBonus() != bonus) continue;
	    int t = 1; 
	    if (map.containsKey(i)) {
		t += map.get(i).intValue();
	    } 
	    int index = Math.min(t, i.getPotency().length-1);
	    potency += i.getPotency()[index];
	    map.put(i, Integer.valueOf(t));
	}
	if (bonus == FoodBonus.HEARTY || bonus == FoodBonus.ENERGIZING) 
	    potency = Math.min(potency, 20);
	else if (bonus != FoodBonus.RANDOM && bonus != FoodBonus.NONE)
	    potency = Math.min(potency, 3);
	health = Math.min(health, 50);
	System.out.println(health);
	System.out.println(duration);
	System.out.println(bonus);
    }

    public ItemStack apply(ItemStack item) {
	ItemMeta meta = item.getItemMeta();
	List<String> lore = new ArrayList<String>();
	String name = meta.getDisplayName();
	NBTItem nbt = new NBTItem(item);
	if (health > 0) {
	    lore.add("§cHealth : " + health);
	    nbt.setInteger("Health", health);
	}
	nbt.setString("Bonus", bonus.name());
	if (potency > 0)
	    nbt.setInteger("Potency", potency);
	if (bonus == FoodBonus.HEARTY && potency > 0) {
	    lore.add("§eAbsorbtion : " + potency);
	    name += " (Hearty)";
	}
	else if (bonus == FoodBonus.CHILLY && potency > 0) {
	    lore.add("§6Chilly : " + potency);
	    name += " (Chilly)";
	}
	else if (bonus == FoodBonus.ELECTRO && potency > 0) {
	    lore.add("§eElectro : " + potency);
	    name += " (Electro)";
	}
	else if (bonus == FoodBonus.ENDURING && potency > 0) {
	    lore.add("§eEnduring : " + potency);
	    name += " (Enduring)";
	}
	else if (bonus == FoodBonus.ENERGIZING && potency > 0) {
	    lore.add("§aEnergizing : " + potency);
	    name += " (Energizing)";
	}
	else if (bonus == FoodBonus.FIREPROOF && potency > 0) {
	    lore.add("§4Fireproof : " + potency);
	    name += " (Fireproof)";
	}
	else if (bonus == FoodBonus.HASTY && potency > 0) {
	    lore.add("§9Hasty : " + potency);
	    name += " (Hasty)";
	}
	else if (bonus == FoodBonus.MIGHTY && potency > 0) {
	    lore.add("§fMighty : " + potency);
	    name += " (Mighty)";
	}
	else if (bonus == FoodBonus.SNEAKY && potency > 0) {
	    lore.add("§dSneaky : " + potency);
	    name += " (Sneaky)";
	}
	else if (bonus == FoodBonus.SPICY && potency > 0) {
	    lore.add("§bSpicy : " + potency);
	    name += " (Spicy)";
	}
	else if (bonus == FoodBonus.TOUGH && potency > 0) {
	    lore.add("§fTough : " + potency);
	    name += " (Tough)";
	}
	if (lore.size() > 0)
	    meta.setLore(lore);
	meta.setDisplayName(name);
	ItemStack item2 = nbt.getItem();
	item2.setItemMeta(meta);
	return item2;
    }

}
