package fr.yezzipe.zelda.items.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public enum Food {
    APPLE_PIE(27, "Apple Pie"), CARROT_CAKE(23, "Carrot Cake"), CARROT_STEW(45, "Carrot Stew"),
    CHILLY_ELIXIR(126, "Chilly Elixir"), CLAM_CHOWDER(9, "Clam Chowder"),
    COPIOUS_FISH_SKEWERS(91, "Copious Fish Skewers"), COPIOUS_FRIED_WILD_GREENS(88, "Copious Fried Wild Greens"),
    COPIOUS_MEAT_SKEWERS(87, "Copious Meat Skewers"), COPIOUS_MUSHROOM_SKEWERS(90, "Copious Mushroom Skewers"),
    COPIOUS_SIMMERED_FRUIT(89, "Copious Simmered Fruit"), CRAB_OMELET_WITH_RICE(47, "Crab Omelet With Rice"),
    CRAB_RISOTTO(37, "Crab Risotto"), CRAB_STIR_FRY(81, "Crab Stir-fry"), CREAMY_HEART_SOUP(8, "Creamy Heart Soup"),
    CREAMY_MEAT_SOUP(42, "Creamy Meat Soup"), CREAMY_SEAFOOD_SOUP(43, "Creamy Seafood Soup"),
    CREAM_OF_MUSHROOM_SOUP(40, "Cream Of Mushroom Soup"), CREAM_OF_VEGETABLE_SOUP(44, "Cream Of Vegetable Soup"),
    CURRY_PILAF(55, "Curry Pilaf"), CURRY_RICE(70, "Curry Rice"), DUBIOUS_FOOD(121, "Dubious Food"),
    EGG_PUDDING(30, "Egg Pudding"), EGG_TART(29, "Egg Tart"), ELECTRO_ELIXIR(128, "Electro Elixir"),
    ENDURING_ELIXIR(124, "Enduring Elixir"), ENERGIZING_ELIXIR(123, "Energizing Elixir"),
    FAIRY_TONIC(114, "Fairy Tonic"), FAIRY_TONIC_2(115, "Fairy Tonic"), FAIRY_TONIC_3(116, "Fairy Tonic"),
    FAIRY_TONIC_4(117, "Fairy Tonic"), FIREPROOF_ELIXIR(125, "Fireproof Elixir"),
    FISH_AND_MUSHROOM_SKEWER(102, "Fish And Mushroom Skewer"), FISH_PIE(34, "Fish Pie"),
    FISH_SKEWER(107, "Fish Skewer"), FRAGRANT_MUSHROOM_SAUTE(76, "Fragrant Mushroom Saute"),
    FRIED_BANANAS(31, "Fried Bananas"), FRIED_EGG_AND_RICE(59, "Fried Egg And Rice"),
    FRIED_WILD_GREENS(111, "Fried Wild Greens"), FRIED_WILD_GREENS_2(92, "Fried Wild Greens"),
    FRUITCAKE(1, "Fruitcake"), FRUIT_AND_MUSHROOM_MIX(104, "Fruit And Mushroom Mix"), FRUIT_PIE(32, "Fruit Pie"),
    GLAZED_MEAT(66, "Glazed Meat"), GLAZED_MUSHROOMS(68, "Glazed Mushrooms"), GLAZED_SEAFOOD(67, "Glazed Seafood"),
    GLAZED_VEGGIES(69, "Glazed Veggies"), GOURMET_MEAT_AND_RICE_BOWL(56, "Gourmet Meat And Rice Bowl"),
    GOURMET_MEAT_AND_SEAFOOD_FRY(93, "Gourmet Meat And Seafood Fry"), GOURMET_MEAT_CURRY(14, "Gourmet Meat Curry"),
    GOURMET_MEAT_STEW(11, "Gourmet Meat Stew"), GOURMET_POULTRY_CURRY(15, "Gourmet Poultry Curry"),
    GOURMET_POULTRY_PILAF(48, "Gourmet Poultry Pilaf"), GOURMET_SPICED_MEAT_SKEWER(73, "Gourmet Spiced Meat Skewer"),
    HASTY_ELIXIR(129, "Hasty Elixir"), HEARTY_ELIXIR(122, "Hearty Elixir"), HERB_SAUTE(77, "Herb Saute"),
    HONEYED_APPLE(71, "Honeyed Apple"), HONEYED_FRUITS(72, "Honeyed Fruits"), HONEY_CANDY(119, "Honey Candy"),
    HONEY_CREPE(25, "Honey Crepe"), HOT_BUTTERED_APPLE(64, "Hot Buttered Apple"),
    MEAT_STUFFED_PUMPKINS(65, "Meat-Stuffed Pumpkins"), MEATY_RICE_BALLS(60, "Meaty Rice Balls"),
    MEAT_AND_MUSHROOM_SKEWER(103, "Meat And Mushroom Skewer"), MEAT_AND_RICE_BOWL(58, "Meat And Rice Bowl"),
    MEAT_AND_SEAFOOD_FRY(95, "Meat And Seafood Fry"), MEAT_CURRY(18, "Meat Curry"), MEAT_PIE(33, "Meat Pie"),
    MEAT_SKEWER(105, "Meat Skewer"), MEAT_STEW(13, "Meat Stew"), MIGHTY_ELIXIR(131, "Mighty Elixir"),
    MONSTER_CAKE(5, "Monster Cake"), MONSTER_CURRY(3, "Monster Curry"), MONSTER_RICE_BALLS(4, "Monster Rice Balls"),
    MONSTER_SOUP(6, "Monster Soup"), MONSTER_STEW(7, "Monster Stew"), MUSHROOM_OMELET(46, "Mushroom Omelet"),
    MUSHROOM_RICE_BALLS(62, "Mushroom Rice Balls"), MUSHROOM_RISOTTO(39, "Mushroom Risotto"),
    MUSHROOM_SKEWER(110, "Mushroom Skewer"), NUTCAKE(28, "Nutcake"), OMELET(108, "Omelet"),
    PEPPER_STEAK(96, "Pepper Steak"), PLAIN_CREPE(26, "Plain Crepe"), PORGY_MEUNIERE(52, "Porgy Meuniere"),
    POULTRY_CURRY(19, "Poultry Curry"), POULTRY_PILAF(50, "Poultry Pilaf"),
    PRIME_MEAT_AND_RICE_BOWL(57, "Prime Meat And Rice Bowl"),
    PRIME_MEAT_AND_SEAFOOD_FRY(94, "Prime Meat And Seafood Fry"), PRIME_MEAT_CURRY(16, "Prime Meat Curry"),
    PRIME_MEAT_STEW(12, "Prime Meat Stew"), PRIME_POULTRY_CURRY(17, "Prime Poultry Curry"),
    PRIME_POULTRY_PILAF(49, "Prime Poultry Pilaf"), PRIME_SPICED_MEAT_SKEWER(74, "Prime Spiced Meat Skewer"),
    PUMPKIN_PIE(22, "Pumpkin Pie"), PUMPKIN_STEW(10, "Pumpkin Stew"), ROCK_HARD_FOOD(120, "Rock-Hard Food"),
    SALMON_MEUNIERE(35, "Salmon Meuniere"), SALMON_RISOTTO(36, "Salmon Risotto"),
    SALT_GRILLED_CRAB(82, "Salt-Grilled Crab"), SALT_GRILLED_FISH(83, "Salt-Grilled Fish"),
    SALT_GRILLED_GOURMET_MEAT(78, "Salt-Grilled Gourmet Meat"), SALT_GRILLED_GREENS(85, "Salt-Grilled Greens"),
    SALT_GRILLED_MEAT(80, "Salt-Grilled Meat"), SALT_GRILLED_MUSHROOMS(86, "Salt-Grilled Mushrooms"),
    SALT_GRILLED_PRIME_MEAT(79, "Salt-Grilled Prime Meat"), SAUTEED_NUTS(113, "Sauteed Nuts"),
    SAUTEED_PEPPERS(118, "Sauteed Peppers"), SEAFOOD_CURRY(20, "Seafood Curry"),
    SEAFOOD_FRIED_RICE(54, "Seafood Fried Rice"), SEAFOOD_MEUNIERE(53, "Seafood Meuniere"),
    SEAFOOD_PAELLA(2, "Seafood Paella"), SEAFOOD_RICE_BALLS(61, "Seafood Rice Balls"),
    SEAFOOD_SKEWER(106, "Seafood Skewer"), SIMMERED_FRUIT(112, "Simmered Fruit"), SNEAKY_ELIXIR(130, "Sneaky Elixir"),
    SPICED_MEAT_SKEWER(75, "Spiced Meat Skewer"), SPICY_ELIXIR(127, "Spicy Elixir"),
    SPICY_PEPPERED_SEAFOOD(97, "Spicy Peppered Seafood"), STEAMED_FISH(99, "Steamed Fish"),
    STEAMED_FRUIT(101, "Steamed Fruit"), STEAMED_MEAT(98, "Steamed Meat"), STEAMED_MUSHROOMS(100, "Steamed Mushrooms"),
    TOUGH_ELIXIR(132, "Tough Elixir"), VEGETABLE_CURRY(21, "Vegetable Curry"), VEGETABLE_OMELET(51, "Vegetable Omelet"),
    VEGETABLE_RISOTTO(38, "Vegetable Risotto"), VEGGIE_CREAM_SOUP(41, "Veggie Cream Soup"),
    VEGGIE_RICE_BALLS(63, "Veggie Rice Balls"), WARM_MILK(109, "Warm Milk"), WHEAT_BREAD(84, "Wheat Bread"),
    WILDBERRY_CREPE(24, "Wildberry Crepe");

    private int modelData;
    private String displayName;

    private Food(int modelData, String displayName) {
	this.modelData = modelData;
	this.displayName = displayName;
    }

    public ItemStack getFood() {
	ItemStack item = new ItemStack(Material.BREAD);
	ItemMeta meta = item.getItemMeta();
	meta.setCustomModelData(Integer.valueOf(modelData));
	meta.setDisplayName("§f"+displayName);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("FoodType", this.toString());
	return nbt.getItem();
    }

    public static boolean isFood(ItemStack item) {
	if (item == null || item.getType() == Material.AIR)
	    return false;
	NBTItem nbt = new NBTItem(item);
	return nbt.getKeys().contains("FoodType");
    }
}
