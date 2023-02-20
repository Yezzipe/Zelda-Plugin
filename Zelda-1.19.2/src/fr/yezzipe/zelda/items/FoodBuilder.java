package fr.yezzipe.zelda.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;
import fr.yezzipe.zelda.items.enums.Food;

public class FoodBuilder {
    public static ItemStack build(Food food) {
	ItemStack item = new ItemStack(Material.BREAD), customItem = null;
	ItemMeta meta;
	NBTItem nbt;
	switch (food) {
	case APPLE_PIE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(27));
	    meta.setDisplayName("Apple Pie");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.APPLE_PIE.toString());
	    customItem = nbt.getItem();
	    break;
	case CARROT_CAKE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(23));
	    meta.setDisplayName("Carrot Cake");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CARROT_CAKE.toString());
	    customItem = nbt.getItem();
	    break;
	case CARROT_STEW:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(45));
	    meta.setDisplayName("Carrot Stew");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CARROT_STEW.toString());
	    customItem = nbt.getItem();
	    break;
	case CHILLY_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(126));
	    meta.setDisplayName("Chilly Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CHILLY_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case CLAM_CHOWDER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(9));
	    meta.setDisplayName("Clam Chowder");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CLAM_CHOWDER.toString());
	    customItem = nbt.getItem();
	    break;
	case COPIOUS_FISH_SKEWERS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(91));
	    meta.setDisplayName("Copious Fish Skewers");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.COPIOUS_FISH_SKEWERS.toString());
	    customItem = nbt.getItem();
	    break;
	case COPIOUS_FRIED_WILD_GREENS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(88));
	    meta.setDisplayName("Copious Fried Wild Greens");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.COPIOUS_FRIED_WILD_GREENS.toString());
	    customItem = nbt.getItem();
	    break;
	case COPIOUS_MEAT_SKEWERS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(87));
	    meta.setDisplayName("Copious Meat Skewers");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.COPIOUS_MEAT_SKEWERS.toString());
	    customItem = nbt.getItem();
	    break;
	case COPIOUS_MUSHROOM_SKEWERS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(90));
	    meta.setDisplayName("Copious Mushroom Skewers");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.COPIOUS_MUSHROOM_SKEWERS.toString());
	    customItem = nbt.getItem();
	    break;
	case COPIOUS_SIMMERED_FRUIT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(89));
	    meta.setDisplayName("Copious Simmered Fruit");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.COPIOUS_SIMMERED_FRUIT.toString());
	    customItem = nbt.getItem();
	    break;
	case CRAB_OMELET_WITH_RICE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(47));
	    meta.setDisplayName("Crab Omelet With Rice");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CRAB_OMELET_WITH_RICE.toString());
	    customItem = nbt.getItem();
	    break;
	case CRAB_RISOTTO:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(37));
	    meta.setDisplayName("Crab Risotto");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CRAB_RISOTTO.toString());
	    customItem = nbt.getItem();
	    break;
	case CRAB_STIR_FRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(81));
	    meta.setDisplayName("Crab Stir-fry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CRAB_STIR_FRY.toString());
	    customItem = nbt.getItem();
	    break;
	case CREAMY_HEART_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(8));
	    meta.setDisplayName("Creamy Heart Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CREAMY_HEART_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case CREAMY_MEAT_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(42));
	    meta.setDisplayName("Creamy Meat Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CREAMY_MEAT_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case CREAMY_SEAFOOD_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(43));
	    meta.setDisplayName("Creamy Seafood Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CREAMY_SEAFOOD_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case CREAM_OF_MUSHROOM_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(40));
	    meta.setDisplayName("Cream Of Mushroom Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CREAM_OF_MUSHROOM_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case CREAM_OF_VEGETABLE_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(44));
	    meta.setDisplayName("Cream Of Vegetable Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CREAM_OF_VEGETABLE_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case CURRY_PILAF:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(55));
	    meta.setDisplayName("Curry Pilaf");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CURRY_PILAF.toString());
	    customItem = nbt.getItem();
	    break;
	case CURRY_RICE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(70));
	    meta.setDisplayName("Curry Rice");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.CURRY_RICE.toString());
	    customItem = nbt.getItem();
	    break;
	case DUBIOUS_FOOD:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(121));
	    meta.setDisplayName("Dubious Food");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.DUBIOUS_FOOD.toString());
	    customItem = nbt.getItem();
	    break;
	case EGG_PUDDING:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(30));
	    meta.setDisplayName("Egg Pudding");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.EGG_PUDDING.toString());
	    customItem = nbt.getItem();
	    break;
	case EGG_TART:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(29));
	    meta.setDisplayName("Egg Tart");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.EGG_TART.toString());
	    customItem = nbt.getItem();
	    break;
	case ELECTRO_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(128));
	    meta.setDisplayName("Electro Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.ELECTRO_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case ENDURING_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(124));
	    meta.setDisplayName("Enduring Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.ENDURING_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case ENERGIZING_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(123));
	    meta.setDisplayName("Energizing Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.ENERGIZING_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case FAIRY_TONIC:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(114));
	    meta.setDisplayName("Fairy Tonic");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FAIRY_TONIC.toString());
	    customItem = nbt.getItem();
	    break;
	case FAIRY_TONIC_2:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(115));
	    meta.setDisplayName("Fairy Tonic");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FAIRY_TONIC_2.toString());
	    customItem = nbt.getItem();
	    break;
	case FAIRY_TONIC_3:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(116));
	    meta.setDisplayName("Fairy Tonic");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FAIRY_TONIC_3.toString());
	    customItem = nbt.getItem();
	    break;
	case FAIRY_TONIC_4:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(117));
	    meta.setDisplayName("Fairy Tonic");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FAIRY_TONIC_4.toString());
	    customItem = nbt.getItem();
	    break;
	case FIREPROOF_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(125));
	    meta.setDisplayName("Fireproof Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FIREPROOF_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case FISH_AND_MUSHROOM_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(102));
	    meta.setDisplayName("Fish And Mushroom Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FISH_AND_MUSHROOM_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case FISH_PIE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(34));
	    meta.setDisplayName("Fish Pie");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FISH_PIE.toString());
	    customItem = nbt.getItem();
	    break;
	case FISH_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(107));
	    meta.setDisplayName("Fish Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FISH_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case FRAGRANT_MUSHROOM_SAUTE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(76));
	    meta.setDisplayName("Fragrant Mushroom Saute");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRAGRANT_MUSHROOM_SAUTE.toString());
	    customItem = nbt.getItem();
	    break;
	case FRIED_BANANAS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(31));
	    meta.setDisplayName("Fried Bananas");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRIED_BANANAS.toString());
	    customItem = nbt.getItem();
	    break;
	case FRIED_EGG_AND_RICE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(59));
	    meta.setDisplayName("Fried Egg And Rice");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRIED_EGG_AND_RICE.toString());
	    customItem = nbt.getItem();
	    break;
	case FRIED_WILD_GREENS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(111));
	    meta.setDisplayName("Fried Wild Greens");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRIED_WILD_GREENS.toString());
	    customItem = nbt.getItem();
	    break;
	case FRIED_WILD_GREENS_2:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(92));
	    meta.setDisplayName("Fried Wild Greens");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRIED_WILD_GREENS_2.toString());
	    customItem = nbt.getItem();
	    break;
	case FRUITCAKE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(1));
	    meta.setDisplayName("Fruitcake");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRUITCAKE.toString());
	    customItem = nbt.getItem();
	    break;
	case FRUIT_AND_MUSHROOM_MIX:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(104));
	    meta.setDisplayName("Fruit And Mushroom Mix");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRUIT_AND_MUSHROOM_MIX.toString());
	    customItem = nbt.getItem();
	    break;
	case FRUIT_PIE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(32));
	    meta.setDisplayName("Fruit Pie");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.FRUIT_PIE.toString());
	    customItem = nbt.getItem();
	    break;
	case GLAZED_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(66));
	    meta.setDisplayName("Glazed Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GLAZED_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case GLAZED_MUSHROOMS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(68));
	    meta.setDisplayName("Glazed Mushrooms");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GLAZED_MUSHROOMS.toString());
	    customItem = nbt.getItem();
	    break;
	case GLAZED_SEAFOOD:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(67));
	    meta.setDisplayName("Glazed Seafood");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GLAZED_SEAFOOD.toString());
	    customItem = nbt.getItem();
	    break;
	case GLAZED_VEGGIES:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(69));
	    meta.setDisplayName("Glazed Veggies");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GLAZED_VEGGIES.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_MEAT_AND_RICE_BOWL:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(56));
	    meta.setDisplayName("Gourmet Meat And Rice Bowl");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_MEAT_AND_RICE_BOWL.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_MEAT_AND_SEAFOOD_FRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(93));
	    meta.setDisplayName("Gourmet Meat And Seafood Fry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_MEAT_AND_SEAFOOD_FRY.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_MEAT_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(14));
	    meta.setDisplayName("Gourmet Meat Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_MEAT_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_MEAT_STEW:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(11));
	    meta.setDisplayName("Gourmet Meat Stew");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_MEAT_STEW.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_POULTRY_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(15));
	    meta.setDisplayName("Gourmet Poultry Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_POULTRY_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_POULTRY_PILAF:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(48));
	    meta.setDisplayName("Gourmet Poultry Pilaf");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_POULTRY_PILAF.toString());
	    customItem = nbt.getItem();
	    break;
	case GOURMET_SPICED_MEAT_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(73));
	    meta.setDisplayName("Gourmet Spiced Meat Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.GOURMET_SPICED_MEAT_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case HASTY_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(129));
	    meta.setDisplayName("Hasty Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HASTY_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case HEARTY_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(122));
	    meta.setDisplayName("Hearty Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HEARTY_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case HERB_SAUTE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(77));
	    meta.setDisplayName("Herb Saute");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HERB_SAUTE.toString());
	    customItem = nbt.getItem();
	    break;
	case HONEYED_APPLE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(71));
	    meta.setDisplayName("Honeyed Apple");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HONEYED_APPLE.toString());
	    customItem = nbt.getItem();
	    break;
	case HONEYED_FRUITS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(72));
	    meta.setDisplayName("Honeyed Fruits");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HONEYED_FRUITS.toString());
	    customItem = nbt.getItem();
	    break;
	case HONEY_CANDY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(119));
	    meta.setDisplayName("Honey Candy");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HONEY_CANDY.toString());
	    customItem = nbt.getItem();
	    break;
	case HONEY_CREPE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(25));
	    meta.setDisplayName("Honey Crepe");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HONEY_CREPE.toString());
	    customItem = nbt.getItem();
	    break;
	case HOT_BUTTERED_APPLE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(64));
	    meta.setDisplayName("Hot Buttered Apple");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.HOT_BUTTERED_APPLE.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_STUFFED_PUMPKINS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(65));
	    meta.setDisplayName("Meat-Stuffed Pumpkins");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_STUFFED_PUMPKINS.toString());
	    customItem = nbt.getItem();
	    break;
	case MEATY_RICE_BALLS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(60));
	    meta.setDisplayName("Meaty Rice Balls");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEATY_RICE_BALLS.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_AND_MUSHROOM_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(103));
	    meta.setDisplayName("Meat And Mushroom Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_AND_MUSHROOM_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_AND_RICE_BOWL:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(58));
	    meta.setDisplayName("Meat And Rice Bowl");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_AND_RICE_BOWL.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_AND_SEAFOOD_FRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(95));
	    meta.setDisplayName("Meat And Seafood Fry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_AND_SEAFOOD_FRY.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(18));
	    meta.setDisplayName("Meat Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_PIE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(33));
	    meta.setDisplayName("Meat Pie");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_PIE.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(105));
	    meta.setDisplayName("Meat Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case MEAT_STEW:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(13));
	    meta.setDisplayName("Meat Stew");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MEAT_STEW.toString());
	    customItem = nbt.getItem();
	    break;
	case MIGHTY_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(131));
	    meta.setDisplayName("Mighty Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MIGHTY_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case MONSTER_CAKE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(5));
	    meta.setDisplayName("Monster Cake");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MONSTER_CAKE.toString());
	    customItem = nbt.getItem();
	    break;
	case MONSTER_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(3));
	    meta.setDisplayName("Monster Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MONSTER_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case MONSTER_RICE_BALLS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(4));
	    meta.setDisplayName("Monster Rice Balls");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MONSTER_RICE_BALLS.toString());
	    customItem = nbt.getItem();
	    break;
	case MONSTER_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(6));
	    meta.setDisplayName("Monster Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MONSTER_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case MONSTER_STEW:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(7));
	    meta.setDisplayName("Monster Stew");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MONSTER_STEW.toString());
	    customItem = nbt.getItem();
	    break;
	case MUSHROOM_OMELET:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(46));
	    meta.setDisplayName("Mushroom Omelet");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MUSHROOM_OMELET.toString());
	    customItem = nbt.getItem();
	    break;
	case MUSHROOM_RICE_BALLS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(62));
	    meta.setDisplayName("Mushroom Rice Balls");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MUSHROOM_RICE_BALLS.toString());
	    customItem = nbt.getItem();
	    break;
	case MUSHROOM_RISOTTO:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(39));
	    meta.setDisplayName("Mushroom Risotto");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MUSHROOM_RISOTTO.toString());
	    customItem = nbt.getItem();
	    break;
	case MUSHROOM_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(110));
	    meta.setDisplayName("Mushroom Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.MUSHROOM_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case NUTCAKE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(28));
	    meta.setDisplayName("Nutcake");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.NUTCAKE.toString());
	    customItem = nbt.getItem();
	    break;
	case OMELET:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(108));
	    meta.setDisplayName("Omelet");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.OMELET.toString());
	    customItem = nbt.getItem();
	    break;
	case PEPPER_STEAK:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(96));
	    meta.setDisplayName("Pepper Steak");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PEPPER_STEAK.toString());
	    customItem = nbt.getItem();
	    break;
	case PLAIN_CREPE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(26));
	    meta.setDisplayName("Plain Crepe");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PLAIN_CREPE.toString());
	    customItem = nbt.getItem();
	    break;
	case PORGY_MEUNIERE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(52));
	    meta.setDisplayName("Porgy Meuniere");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PORGY_MEUNIERE.toString());
	    customItem = nbt.getItem();
	    break;
	case POULTRY_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(19));
	    meta.setDisplayName("Poultry Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.POULTRY_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case POULTRY_PILAF:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(50));
	    meta.setDisplayName("Poultry Pilaf");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.POULTRY_PILAF.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_MEAT_AND_RICE_BOWL:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(57));
	    meta.setDisplayName("Prime Meat And Rice Bowl");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_MEAT_AND_RICE_BOWL.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_MEAT_AND_SEAFOOD_FRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(94));
	    meta.setDisplayName("Prime Meat And Seafood Fry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_MEAT_AND_SEAFOOD_FRY.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_MEAT_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(16));
	    meta.setDisplayName("Prime Meat Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_MEAT_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_MEAT_STEW:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(12));
	    meta.setDisplayName("Prime Meat Stew");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_MEAT_STEW.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_POULTRY_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(17));
	    meta.setDisplayName("Prime Poultry Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_POULTRY_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_POULTRY_PILAF:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(49));
	    meta.setDisplayName("Prime Poultry Pilaf");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_POULTRY_PILAF.toString());
	    customItem = nbt.getItem();
	    break;
	case PRIME_SPICED_MEAT_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(74));
	    meta.setDisplayName("Prime Spiced Meat Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PRIME_SPICED_MEAT_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case PUMPKIN_PIE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(22));
	    meta.setDisplayName("Pumpkin Pie");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PUMPKIN_PIE.toString());
	    customItem = nbt.getItem();
	    break;
	case PUMPKIN_STEW:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(10));
	    meta.setDisplayName("Pumpkin Stew");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.PUMPKIN_STEW.toString());
	    customItem = nbt.getItem();
	    break;
	case ROCK_HARD_FOOD:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(120));
	    meta.setDisplayName("Rock-Hard Food");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.ROCK_HARD_FOOD.toString());
	    customItem = nbt.getItem();
	    break;
	case SALMON_MEUNIERE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(35));
	    meta.setDisplayName("Salmon Meuniere");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALMON_MEUNIERE.toString());
	    customItem = nbt.getItem();
	    break;
	case SALMON_RISOTTO:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(36));
	    meta.setDisplayName("Salmon Risotto");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALMON_RISOTTO.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_CRAB:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(82));
	    meta.setDisplayName("Salt-Grilled Crab");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_CRAB.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_FISH:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(83));
	    meta.setDisplayName("Salt-Grilled Fish");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_FISH.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_GOURMET_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(78));
	    meta.setDisplayName("Salt-Grilled Gourmet Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_GOURMET_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_GREENS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(85));
	    meta.setDisplayName("Salt-Grilled Greens");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_GREENS.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(80));
	    meta.setDisplayName("Salt-Grilled Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_MUSHROOMS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(86));
	    meta.setDisplayName("Salt-Grilled Mushrooms");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_MUSHROOMS.toString());
	    customItem = nbt.getItem();
	    break;
	case SALT_GRILLED_PRIME_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(79));
	    meta.setDisplayName("Salt-Grilled Prime Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SALT_GRILLED_PRIME_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case SAUTEED_NUTS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(113));
	    meta.setDisplayName("Sauteed Nuts");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SAUTEED_NUTS.toString());
	    customItem = nbt.getItem();
	    break;
	case SAUTEED_PEPPERS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(118));
	    meta.setDisplayName("Sauteed Peppers");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SAUTEED_PEPPERS.toString());
	    customItem = nbt.getItem();
	    break;
	case SEAFOOD_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(20));
	    meta.setDisplayName("Seafood Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SEAFOOD_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case SEAFOOD_FRIED_RICE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(54));
	    meta.setDisplayName("Seafood Fried Rice");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SEAFOOD_FRIED_RICE.toString());
	    customItem = nbt.getItem();
	    break;
	case SEAFOOD_MEUNIERE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(53));
	    meta.setDisplayName("Seafood Meuniere");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SEAFOOD_MEUNIERE.toString());
	    customItem = nbt.getItem();
	    break;
	case SEAFOOD_PAELLA:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(2));
	    meta.setDisplayName("Seafood Paella");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SEAFOOD_PAELLA.toString());
	    customItem = nbt.getItem();
	    break;
	case SEAFOOD_RICE_BALLS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(61));
	    meta.setDisplayName("Seafood Rice Balls");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SEAFOOD_RICE_BALLS.toString());
	    customItem = nbt.getItem();
	    break;
	case SEAFOOD_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(106));
	    meta.setDisplayName("Seafood Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SEAFOOD_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case SIMMERED_FRUIT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(112));
	    meta.setDisplayName("Simmered Fruit");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SIMMERED_FRUIT.toString());
	    customItem = nbt.getItem();
	    break;
	case SNEAKY_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(130));
	    meta.setDisplayName("Sneaky Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SNEAKY_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case SPICED_MEAT_SKEWER:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(75));
	    meta.setDisplayName("Spiced Meat Skewer");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SPICED_MEAT_SKEWER.toString());
	    customItem = nbt.getItem();
	    break;
	case SPICY_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(127));
	    meta.setDisplayName("Spicy Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SPICY_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case SPICY_PEPPERED_SEAFOOD:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(97));
	    meta.setDisplayName("Spicy Peppered Seafood");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.SPICY_PEPPERED_SEAFOOD.toString());
	    customItem = nbt.getItem();
	    break;
	case STEAMED_FISH:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(99));
	    meta.setDisplayName("Steamed Fish");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.STEAMED_FISH.toString());
	    customItem = nbt.getItem();
	    break;
	case STEAMED_FRUIT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(101));
	    meta.setDisplayName("Steamed Fruit");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.STEAMED_FRUIT.toString());
	    customItem = nbt.getItem();
	    break;
	case STEAMED_MEAT:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(98));
	    meta.setDisplayName("Steamed Meat");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.STEAMED_MEAT.toString());
	    customItem = nbt.getItem();
	    break;
	case STEAMED_MUSHROOMS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(100));
	    meta.setDisplayName("Steamed Mushrooms");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.STEAMED_MUSHROOMS.toString());
	    customItem = nbt.getItem();
	    break;
	case TOUGH_ELIXIR:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(132));
	    meta.setDisplayName("Tough Elixir");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.TOUGH_ELIXIR.toString());
	    customItem = nbt.getItem();
	    break;
	case VEGETABLE_CURRY:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(21));
	    meta.setDisplayName("Vegetable Curry");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.VEGETABLE_CURRY.toString());
	    customItem = nbt.getItem();
	    break;
	case VEGETABLE_OMELET:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(51));
	    meta.setDisplayName("Vegetable Omelet");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.VEGETABLE_OMELET.toString());
	    customItem = nbt.getItem();
	    break;
	case VEGETABLE_RISOTTO:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(38));
	    meta.setDisplayName("Vegetable Risotto");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.VEGETABLE_RISOTTO.toString());
	    customItem = nbt.getItem();
	    break;
	case VEGGIE_CREAM_SOUP:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(41));
	    meta.setDisplayName("Veggie Cream Soup");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.VEGGIE_CREAM_SOUP.toString());
	    customItem = nbt.getItem();
	    break;
	case VEGGIE_RICE_BALLS:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(63));
	    meta.setDisplayName("Veggie Rice Balls");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.VEGGIE_RICE_BALLS.toString());
	    customItem = nbt.getItem();
	    break;
	case WARM_MILK:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(109));
	    meta.setDisplayName("Warm Milk");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.WARM_MILK.toString());
	    customItem = nbt.getItem();
	    break;
	case WHEAT_BREAD:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(84));
	    meta.setDisplayName("Wheat Bread");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.WHEAT_BREAD.toString());
	    customItem = nbt.getItem();
	    break;
	case WILDBERRY_CREPE:
	    meta = item.getItemMeta();
	    meta.setCustomModelData(Integer.valueOf(24));
	    meta.setDisplayName("Wildberry Crepe");
	    item.setItemMeta(meta);
	    nbt = new NBTItem(item);
	    nbt.setString("FoodType", Food.WILDBERRY_CREPE.toString());
	    customItem = nbt.getItem();
	    break;
	}
	return customItem;
    }
}
