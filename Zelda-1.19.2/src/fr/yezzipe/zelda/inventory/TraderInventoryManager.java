package fr.yezzipe.zelda.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Ingredient;
import fr.yezzipe.zelda.items.enums.Rupees;

public class TraderInventoryManager extends InventoryManager {

    private static HashMap<WanderingTrader, TraderInventoryManager> map = new HashMap<WanderingTrader, TraderInventoryManager>();

    private Collection<Ingredient> shop = new ArrayList<Ingredient>();

    private HashMap<Ingredient, Integer> buyMap = new HashMap<Ingredient, Integer>();

    private HashMap<Ingredient, Integer> sellMap = new HashMap<Ingredient, Integer>();

    private static Collection<Ingredient> ingredients = Arrays.asList(new Ingredient[] { Ingredient.BOKOBLIN_GUTS,
	    Ingredient.MOBLIN_HORN, Ingredient.MOBLIN_FANG, Ingredient.MOBLIN_GUTS, Ingredient.LIZALFOS_HORN,
	    Ingredient.LIZALFOS_TALON, Ingredient.LIZALFOS_TAIL, Ingredient.ICY_LIZALFOS_TAIL,
	    Ingredient.RED_LIZALFOS_TAIL, Ingredient.YELLOW_LIZALFOS_TAIL, Ingredient.LYNEL_HORN, Ingredient.LYNEL_HOOF,
	    Ingredient.LYNEL_GUTS, Ingredient.CHUCHU_JELLY, Ingredient.WHITE_CHUCHU_JELLY, Ingredient.RED_CHUCHU_JELLY,
	    Ingredient.YELLOW_CHUCHU_JELLY, Ingredient.KEESE_WING, Ingredient.ICE_KEESE_WING,
	    Ingredient.FIRE_KEESE_WING, Ingredient.ELECTRIC_KEESE_WING, Ingredient.KEESE_EYEBALL,
	    Ingredient.OCTOROK_TENTACLE, Ingredient.OCTOROK_EYEBALL, Ingredient.OCTO_BALLOON, Ingredient.MOLDUGA_FIN,
	    Ingredient.MOLDUGA_GUTS, Ingredient.HINOX_TOENAIL, Ingredient.HINOX_TOOTH, Ingredient.HINOX_GUTS,
	    Ingredient.ANCIENT_SCREW, Ingredient.ANCIENT_SPRING, Ingredient.ANCIENT_GEAR, Ingredient.ANCIENT_SHAFT,
	    Ingredient.ANCIENT_CORE, Ingredient.GIANT_ANCIENT_CORE, Ingredient.COURSER_BEE_HONEY,
	    Ingredient.HYLIAN_RICE, Ingredient.ACORN, Ingredient.CHICKALOO_TREE_NUT, Ingredient.BIRD_EGG,
	    Ingredient.TABANTHA_WHEAT, Ingredient.FRESH_MILK, Ingredient.COOL_SAFFLINA, Ingredient.WARM_SAFFLINA,
	    Ingredient.GORON_SPICE, Ingredient.GOAT_BUTTER, Ingredient.CANE_SUGAR, Ingredient.SILENT_PRINCESS,
	    Ingredient.BLUE_NIGHTSHADE, Ingredient.ARMORANTH, Ingredient.MIGHTY_THISTLE, Ingredient.SWIFT_VIOLET,
	    Ingredient.ELECTRIC_SAFFLINA, Ingredient.ROCK_SALT, Ingredient.MONSTER_EXTRACT, Ingredient.BOKOBLIN_FANG,
	    Ingredient.BOKOBLIN_HORN, Ingredient.HYRULE_HERB });

    public TraderInventoryManager(WanderingTrader trader) {
	map.put(trader, this);
	createInventory(null, 9, "Trading", CustomInventoryType.WANDERING_TRADER);
	genShop();
	populateInventory();
    }

    @Override
    protected void populateInventory() {
	Random rand = new Random();
	for (int i = 0; i < shop.size(); i++) {
	    Ingredient ing = (Ingredient) shop.toArray()[i];
	    ItemStack item = ing.getIngredient();
	    ItemMeta meta = item.getItemMeta();
	    List<String> l = meta.getLore() == null ? new ArrayList<String>() : meta.getLore();
	    l.add("");
	    int buy = Math.abs(rand.nextInt()) % 31 + 30;
	    int sell = buy / 3;
	    buyMap.put(ing, Integer.valueOf(buy));
	    sellMap.put(ing, Integer.valueOf(sell));
	    l.add("§fBuy : " + buy);
	    l.add("§fSell : " + sell);
	    meta.setLore(l);
	    item.setItemMeta(meta);
	    NBTItem nbt = new NBTItem(item);
	    inv.setItem(i, nbt.getItem());
	}
    }

    @Override
    public void handleClick(InventoryClickEvent e) {
	Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1),
		Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6),
		Integer.valueOf(7), Integer.valueOf(8) });
	ItemStack currItem = (e.getCurrentItem() == null) ? new ItemStack(Material.AIR) : e.getCurrentItem();
	if (blockedSlots.contains(Integer.valueOf(e.getRawSlot()))) {
	    e.setCancelled(true);
	    ItemStack i = inv.getItem(e.getRawSlot());
	    NBTItem nbt = new NBTItem(i);
	    Ingredient ing = Ingredient.valueOf(nbt.getString("IngredientType"));
	    Player p = (Player) e.getWhoClicked();
	    if (e.getClick() == ClickType.LEFT) {
		boolean b = Rupees.remove(buyMap.get(ing).intValue(), p);
		if (b) {
		    p.getInventory().addItem(ing.getIngredient());
		}
	    } else if (e.getClick() == ClickType.RIGHT) {
		boolean b = p.getInventory().containsAtLeast(ing.getIngredient(), 1);
		if (b) {
		    p.getInventory().removeItem(ing.getIngredient());
		    Rupees.add(sellMap.get(ing), p);
		}
	    }
	} else if (e.getClick() == ClickType.DOUBLE_CLICK && currItem.getType() == Material.AIR) {
	    e.setCancelled(true);
	}
    }

    @Override
    public void handleDrag(InventoryDragEvent e) {
	Set<Integer> slots = e.getRawSlots();
	Collection<Integer> blockedSlots = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1),
		Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6),
		Integer.valueOf(7), Integer.valueOf(8) });
	for (Iterator<Integer> iterator = slots.iterator(); iterator.hasNext();) {
	    int slot = ((Integer) iterator.next()).intValue();
	    if (blockedSlots.contains(Integer.valueOf(slot))) {
		e.setCancelled(true);
		break;
	    }
	}
    }

    @Override
    public void handleClose(InventoryCloseEvent e) {
	// TODO Auto-generated method stub
    }

    public void genShop() {
	Random rand = new Random();
	while (shop.size() < 9) {
	    int r = Math.abs(rand.nextInt()) % ingredients.size();
	    if (!shop.contains(ingredients.toArray()[r])) {
		shop.add((Ingredient) ingredients.toArray()[r]);
	    }
	}
    }

    public static TraderInventoryManager getManager(WanderingTrader trader) {
	if (map.containsKey(trader))
	    return map.get(trader);
	return new TraderInventoryManager(trader);
    }

}
