package fr.yezzipe.zelda.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	potency = 0;
	int random = 0;
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
	    if (b == FoodBonus.RANDOM)
		random++;
	    ing.add(i);
	}
	if (bonuses.size() > 1)
	    bonus = FoodBonus.NONE;
	else if (bonuses.size() == 1)
	    bonus = bonuses.toArray(new FoodBonus[1])[0];
	else
	    bonus = FoodBonus.NONE;
	if (bonus == FoodBonus.NONE && random > 0) {
	    bonus = FoodBonus.random();
	}
	potency += random;
	HashMap<Ingredient, Integer> map = new HashMap<Ingredient, Integer>();
	for (Ingredient i : ingredients) {
	    if (i.getBonus() != bonus)
		continue;
	    int t = 1;
	    if (map.containsKey(i)) {
		t += map.get(i).intValue();
	    }
	    map.put(i, Integer.valueOf(t));
	}
	for (Ingredient i : map.keySet()) {
	    int index = Math.min(map.get(i).intValue() - 1, i.getPotency().length - 1);
	    if (i.getPotency().length > 0)
		potency += i.getPotency()[index];
	}
	if (bonus == FoodBonus.HEARTY || bonus == FoodBonus.ENERGIZING)
	    potency = Math.min(potency, 20);
	health = Math.min(health, 50);
    }

    public ItemStack apply(ItemStack item) {
	ItemMeta meta = item.getItemMeta();
	List<String> lore = new ArrayList<String>();
	String name1 = meta.getDisplayName();
	String name = "";
	if (health > 0) {
	    lore.add("§cHealth : " + health);
	}
	boolean d = false;
	if (potency > 0)
	    switch (bonus) {
	    case CHILLY:
		lore.add("§bChilly : " + potency);
		name += " (Chilly)";
		d = true;
		break;
	    case ELECTRO:
		lore.add("§eElectro : " + potency);
		name += " (Electro)";
		d = true;
		break;
	    case ENDURING:
		lore.add("§eEnduring : " + potency);
		name += " (Enduring)";
		break;
	    case ENERGIZING:
		lore.add("§aEnergizing : " + potency);
		name += " (Energizing)";
		break;
	    case FIREPROOF:
		lore.add("§4Fireproof : " + potency);
		name += " (Fireproof)";
		d = true;
		break;
	    case HASTY:
		lore.add("§9Hasty : " + potency);
		name += " (Hasty)";
		d = true;
		break;
	    case HEARTY:
		lore.add("§eAbsorbtion : " + potency);
		name += " (Hearty)";
		break;
	    case MIGHTY:
		lore.add("§fMighty : " + potency);
		name += " (Mighty)";
		d = true;
		break;
	    case SNEAKY:
		lore.add("§dSneaky : " + potency);
		name += " (Sneaky)";
		d = true;
		break;
	    case SPICY:
		lore.add("§6Spicy : " + potency);
		name += " (Spicy)";
		d = true;
		break;
	    case TOUGH:
		lore.add("§fTough : " + potency);
		name += " (Tough)";
		d = true;
		break;
	    case NONE:
	    case RANDOM:
	    default:
		break;
	    }
	if (duration > 0 && d) {
	    int m = duration/60;
	    int s = duration%60;
	    lore.add("§fDuration : " + m + ":" + (s > 9 ? s : "0" + s) );
	}
	final String regex = "Elixir";     
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(name1);
        if (!matcher.find()) name1 += name;
	if (lore.size() > 0)
	    meta.setLore(lore);
	meta.setDisplayName(name1);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("Bonus", bonus.toString());
	if (potency > 0)
	    nbt.setInteger("Potency", potency);
	if (duration > 0)
	    nbt.setInteger("Duration", duration);
	if (health > 0)
	    nbt.setInteger("Health", health);
	return nbt.getItem();
    }

}
