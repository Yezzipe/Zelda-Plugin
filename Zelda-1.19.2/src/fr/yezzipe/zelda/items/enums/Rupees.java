package fr.yezzipe.zelda.items.enums;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public enum Rupees {
    GREEN(1, "§aGreen Rupee", 1), BLUE(2, "§9Blue Rupee", 5), YELLOW(3, "§eYellow Rupee", 10),
    RED(4, "§4Red Rupee", 20), PURPLE(5, "§5Purple Rupee", 50), SILVER(6, "§7Silver Rupee", 100),
    GOLD(7, "§6Gold Rupee", 300);

    private int modelData;
    private String displayName;
    private int value;

    private Rupees(int modelData, String displayName, int value) {
	this.modelData = modelData;
	this.displayName = displayName;
	this.value = value;
    }

    public ItemStack getRupee() {
	ItemStack item = new ItemStack(Material.EMERALD);
	ItemMeta meta = item.getItemMeta();
	meta.setCustomModelData(Integer.valueOf(this.modelData));
	meta.setDisplayName(this.displayName);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("RupeeType", this.toString());
	return nbt.getItem();
    }

    public static boolean isRupee(ItemStack item) {
	if (item == null || item.getType() == Material.AIR)
	    return false;
	NBTItem nbt = new NBTItem(item);
	return nbt.getKeys().contains("RupeeType");
    }

    public int getValue() {
	return this.value;
    }

    public static boolean remove(int amount, Player p) {
	int total = 0;
	
	HashMap<Rupees, Integer> currentRupees = new HashMap<Rupees, Integer>();
	for (ItemStack i : p.getInventory().getContents()) {
	    if (isRupee(i)) {
		NBTItem nbt = new NBTItem(i);
		Rupees r = Rupees.valueOf(nbt.getString("RupeeType"));
		if (currentRupees.containsKey(r))
		    currentRupees.put(r, Integer.valueOf(currentRupees.get(r).intValue() + i.getAmount()));
		currentRupees.put(r, Integer.valueOf(i.getAmount()));
		total += i.getAmount() * r.getValue();
	    }
	}
	if (total < amount)
	    return false;
	HashMap<Rupees, Integer> toRemove = new HashMap<Rupees, Integer>();
	Rupees[] r = new Rupees[] { Rupees.GOLD, Rupees.SILVER, Rupees.PURPLE, Rupees.RED, Rupees.YELLOW, Rupees.BLUE,
		Rupees.GREEN };
	for (int i = 0; i < r.length; i++) {
	    int t = amount / r[i].getValue();
	    if (t > 0) {
		if (currentRupees.containsKey(r[i])) {
		    toRemove.put(r[i], t);
		    int x = currentRupees.get(r[i]).intValue() - t;
		    if (x > 0)
			currentRupees.put(r[i], Integer.valueOf(x));
		    else
			currentRupees.remove(r[i]);
		    	amount -= t*r[i].getValue();
		}
	    }
	}
	int ret = 0;
	if (amount > 0) {
	    for (int i = r.length - 2; i >= 0; i--) {
		boolean t = false;
		int x = 0;
		if (i > 0) {
		    Rupees r1 = r[i];
		    if (!currentRupees.containsKey(r1)) continue;
		    int l = currentRupees.get(r1).intValue();
		    Rupees r2 = r[i - 1];
		    while (!t && x * r1.getValue() < r2.getValue() && x <= l) {
			if (amount - x * r1.getValue() < 0) {
			    t = true;
			} else
			    x++;
		    }
		    if (t) {
			amount -= x * r1.getValue();
			ret = -amount;
			if (toRemove.containsKey(r1)) 
			    toRemove.put(r1, Integer.valueOf(toRemove.get(r1).intValue()+x));
			else 
			    toRemove.put(r1, Integer.valueOf(x));
		    }
		} else {
		    Rupees r1 = r[i];
		    if (!currentRupees.containsKey(r1)) continue;
		    int l = currentRupees.get(r1).intValue();
		    while (!t && x <= l) {
			if (amount - x * r1.getValue() < 0) {
			    t = true;
			} else 
			    x++;
			
		    }
		    if (t) {
			amount -= x * r1.getValue();
			ret = -amount;
			if (toRemove.containsKey(r1)) 
			    toRemove.put(r1, Integer.valueOf(toRemove.get(r1).intValue()+x));
			else 
			    toRemove.put(r1, Integer.valueOf(x));
		    }
		}
	    }
	    if (amount > 0 || amount > total) return false;
	}
	if (amount <= 0) {
	    for(Rupees r1 : toRemove.keySet()) {
		int x = toRemove.get(r1).intValue();
		ItemStack item = r1.getRupee();
		item.setAmount(x);
		p.getInventory().removeItem(item);
	    }
	    if (ret > 0) {
		add(ret, p);
	    }
	    return true;
	}
	else return false;
    }

    public static void add(int ret, Player p) {
	Rupees[] r = new Rupees[] { Rupees.GOLD, Rupees.SILVER, Rupees.PURPLE, Rupees.RED, Rupees.YELLOW, Rupees.BLUE,
		Rupees.GREEN };
	HashMap<Rupees, Integer> map = new HashMap<Rupees,Integer>();
	for (Rupees r1 : r) {
	    int x = ret/r1.getValue();
	    if (x > 0) {
		ret -= x*r1.getValue();
		map.put(r1, Integer.valueOf(x));
	    }
	}
	for (Rupees r1 : map.keySet()) {
	    ItemStack item = r1.getRupee();
	    item.setAmount(map.get(r1).intValue());
	    p.getInventory().addItem(item);
	}
    }
}
