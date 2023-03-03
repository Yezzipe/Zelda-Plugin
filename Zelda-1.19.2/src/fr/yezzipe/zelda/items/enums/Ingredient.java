package fr.yezzipe.zelda.items.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;

public enum Ingredient {
    ACORN(36, "Acorn", Material.BEEF, 2, FoodBonus.NONE, new int[] {}, new int[] { 50, 30 }),
    AMBER(46, "Amber", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    ANCIENT_CORE(89, "Ancient Core", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    ANCIENT_GEAR(87, "Ancient Gear", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    ANCIENT_SCREW(85, "Ancient Screw", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    ANCIENT_SHAFT(88, "Ancient Shaft", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    ANCIENT_SPRING(86, "Ancient Spring", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    APPLE(3, "Apple", Material.BEEF, 2, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    ARMORANTH(6, "Armoranth", Material.CLOCK, 0, FoodBonus.TOUGH, new int[] { 1, 1, 1, 1, 2 }, new int[] { 50 }),
    ARMORED_CARP(50, "Armored Carp", Material.BEEF, 4, FoodBonus.TOUGH, new int[] { 1, 1, 2, 3, 3 }, new int[] { 50 }),
    ARMORED_PORGY(53, "Armored Porgy", Material.BEEF, 4, FoodBonus.TOUGH, new int[] { 1, 2, 3, 3, 3 },
	    new int[] { 50 }),
    BIG_HEARTY_RADISH(22, "Big Hearty Radish", Material.BEEF, 16, FoodBonus.HEARTY, new int[] { 5 }, new int[] {}),
    BIG_HEARTY_TRUFFLE(10, "Big Hearty Truffle", Material.BEEF, 12, FoodBonus.HEARTY, new int[] { 4 }, new int[] {}),
    BIRD_EGG(38, "Bird Egg", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 90, 30 }),
    BLADED_RHINO_BEETLE(36, "Bladed Rhino Beetle", Material.CLOCK, 0, FoodBonus.MIGHTY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 50 }),
    BLUE_NIGHTSHADE(7, "Blue Nightshade", Material.CLOCK, 0, FoodBonus.SNEAKY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 120 }),
    BOKOBLIN_FANG(54, "Bokoblin Fang", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    BOKOBLIN_GUTS(55, "Bokoblin Guts", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    BOKOBLIN_HORN(53, "Bokoblin Horn", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    BRIGHT_EYED_CRAB(58, "Bright-Eyed Crab", Material.BEEF, 4, FoodBonus.ENERGIZING, new int[] { 2, 5, 8, 11, 14 },
	    new int[] {}),
    CANE_SUGAR(9, "Cane Sugar", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 80, 30 }),
    CHICKALOO_TREE_NUT(37, "Chickaloo Tree Nut", Material.BEEF, 2, FoodBonus.NONE, new int[] {}, new int[] { 40, 30 }),
    CHILLFIN_TROUT(45, "Chillfin Trout", Material.BEEF, 4, FoodBonus.CHILLY, new int[] { 1, 2, 2, 2, 2 },
	    new int[] { 150 }),
    CHILLSHROOM(15, "Chillshroom", Material.BEEF, 2, FoodBonus.CHILLY, new int[] { 1, 1, 2, 2, 2 }, new int[] { 150 }),
    CHUCHU_JELLY(68, "Chuchu Jelly", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    COLD_DARNER(32, "Cold Darner", Material.CLOCK, 0, FoodBonus.CHILLY, new int[] { 1, 1, 2, 2, 2 }, new int[] { 150 }),
    COOL_SAFFLINA(1, "Cool Safflina", Material.CLOCK, 0, FoodBonus.CHILLY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 150 }),
    COURSER_BEE_HONEY(34, "Courser Bee Honey", Material.BEEF, 8, FoodBonus.ENERGIZING, new int[] { 2, 5, 8, 11, 14 },
	    new int[] {}),
    DIAMOND(52, "Diamond", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    DINRAALS_CLAW(16, "Dinraals Claw", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 210, 30 }),
    DINRAALS_SCALE(15, "Dinraals Scale", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 90, 30 }),
    ELECTRIC_DARNER(34, "Electric Darner", Material.CLOCK, 0, FoodBonus.ELECTRO, new int[] { 1, 2, 3, 3, 3 },
	    new int[] { 150 }),
    ELECTRIC_KEESE_WING(75, "Electric Keese Wing", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    ELECTRIC_SAFFLINA(3, "Electric Safflina", Material.CLOCK, 0, FoodBonus.ELECTRO, new int[] { 1, 1, 1, 2, 2 },
	    new int[] { 150 }),
    ENDURA_CARROT(24, "Endura Carrot", Material.BEEF, 8, FoodBonus.ENDURING, new int[] { 2, 4, 6, 8, 10 },
	    new int[] {}),
    ENDURA_SHROOM(13, "Endura Shroom", Material.BEEF, 4, FoodBonus.ENDURING, new int[] { 1, 1, 1, 2, 2 }, new int[] {}),
    ENERGETIC_RHINO_BEETLE(38, "Energetic Rhino Beetle", Material.CLOCK, 0, FoodBonus.ENERGIZING,
	    new int[] { 8, 15, 15, 15, 15 }, new int[] {}),
    FAIRY(27, "Fairy", Material.CLOCK, 20, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    FAROSHS_CLAW(24, "Faroshs Claw", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 210, 30 }),
    FAROSHS_SCALE(23, "Faroshs Scale", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 90, 30 }),
    FIREPROOF_LIZARD(44, "Fireproof Lizard", Material.CLOCK, 0, FoodBonus.FIREPROOF, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 150 }),
    FIRE_KEESE_WING(74, "Fire Keese Wing", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    FLEET_LOTUS_SEEDS(
	    8, "Fleet-Lotus Seeds", Material.BEEF, 2, FoodBonus.HASTY, new int[] { 1, 1, 2, 3, 3 }, new int[] { 60 }),
    FLINT(45, "Flint", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    FORTIFIED_PUMPKIN(27, "Fortified Pumpkin", Material.BEEF, 2, FoodBonus.TOUGH, new int[] { 1, 1, 2, 3, 3 },
	    new int[] { 50 }),
    FRESH_MILK(40, "Fresh Milk", Material.BEEF, 2, FoodBonus.NONE, new int[] {}, new int[] { 80, 30 }),
    GIANT_ANCIENT_CORE(90, "Giant Ancient Core", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    GOAT_BUTTER(10, "Goat Butter", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 80, 30 }),
    GORON_SPICE(11, "Goron Spice", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 90, 30 }),
    HEARTY_BASS(42, "Hearty Bass", Material.BEEF, 8, FoodBonus.HEARTY, new int[] { 2 }, new int[] {}),
    HEARTY_BLUESHELL_SNAIL(55, "Hearty Blueshell Snail", Material.BEEF, 12, FoodBonus.HEARTY, new int[] { 3 },
	    new int[] {}),
    HEARTY_DURIAN(1, "Hearty Durian", Material.BEEF, 12, FoodBonus.HEARTY, new int[] { 4 }, new int[] {}),
    HEARTY_LIZARD(43, "Hearty Lizard", Material.CLOCK, 0, FoodBonus.HEARTY, new int[] { 4 }, new int[] {}),
    HEARTY_RADISH(23, "Hearty Radish", Material.BEEF, 10, FoodBonus.HEARTY, new int[] { 3 }, new int[] {}),
    HEARTY_SALMON(41, "Hearty Salmon", Material.BEEF, 16, FoodBonus.HEARTY, new int[] { 4 }, new int[] {}),
    HEARTY_TRUFFLE(11, "Hearty Truffle", Material.BEEF, 8, FoodBonus.HEARTY, new int[] { 1 }, new int[] {}),
    HIGHTAIL_LIZARD(42, "Hightail Lizard", Material.CLOCK, 0, FoodBonus.HASTY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 60 }),
    HINOX_GUTS(84, "Hinox Guts", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    HINOX_TOENAIL(82, "Hinox Toenail", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    HINOX_TOOTH(83, "Hinox Tooth", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    HOT_FOOTED_FROG(40, "Hot-Footed Frog", Material.CLOCK, 0, FoodBonus.HASTY, new int[] { 1, 1, 2, 3, 3 },
	    new int[] { 60 }),
    HYDROMELON(5, "Hydromelon", Material.BEEF, 2, FoodBonus.CHILLY, new int[] { 1, 1, 1, 1, 1 }, new int[] { 150 }),
    HYLIAN_RICE(29, "Hylian Rice", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 60, 30 }),
    HYLIAN_SHROOM(12, "Hylian Shroom", Material.BEEF, 2, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    HYRULE_BASS(43, "Hyrule Bass", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    HYRULE_HERB(25, "Hyrule Herb", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    ICE_KEESE_WING(73, "Ice Keese Wing", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    ICY_LIZALFOS_TAIL(62, "Icy Lizalfos Tail", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    IRONSHELL_CRAB(57, "Ironshell Crab", Material.BEEF, 4, FoodBonus.TOUGH, new int[] { 1, 1, 2, 3, 3 },
	    new int[] { 50 }),
    IRONSHROOM(20, "Ironshroom", Material.BEEF, 2, FoodBonus.TOUGH, new int[] { 1, 1, 2, 3, 3 }, new int[] { 50 }),
    KEESE_EYEBALL(76, "Keese Eyeball", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    KEESE_WING(72, "Keese Wing", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    LIZALFOS_HORN(59, "Lizalfos Horn", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    LIZALFOS_TAIL(61, "Lizalfos Tail", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    LIZALFOS_TALON(60, "Lizalfos Talon", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    LUMINOUS_STONE(48, "Luminous Stone", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    LYNEL_GUTS(67, "Lynel Guts", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    LYNEL_HOOF(66, "Lynel Hoof", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    LYNEL_HORN(65, "Lynel Horn", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    MIGHTY_BANANAS(9, "Mighty Bananas", Material.BEEF, 2, FoodBonus.MIGHTY, new int[] { 1, 1, 2, 3, 3 },
	    new int[] { 50 }),
    MIGHTY_CARP(49, "Mighty Carp", Material.BEEF, 4, FoodBonus.MIGHTY, new int[] { 1, 1, 2, 3, 3 }, new int[] { 50 }),
    MIGHTY_PORGY(52, "Mighty Porgy", Material.BEEF, 4, FoodBonus.MIGHTY, new int[] { 1, 2, 3, 3, 3 }, new int[] { 50 }),
    MIGHTY_THISTLE(5, "Mighty Thistle", Material.CLOCK, 0, FoodBonus.MIGHTY, new int[] { 1, 1, 1, 1, 2 },
	    new int[] { 50 }),
    MOBLIN_FANG(57, "Moblin Fang", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    MOBLIN_GUTS(58, "Moblin Guts", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    MOBLIN_HORN(56, "Moblin Horn", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    MOLDUGA_FIN(80, "Molduga Fin", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    MOLDUGA_GUTS(81, "Molduga Guts", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    MONSTER_EXTRACT(13, "Monster Extract", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] {}),
    NAYDRAS_CLAW(20, "Naydras Claw", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 210, 30 }),
    NAYDRAS_SCALE(19, "Naydras Scale", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 90, 30 }),
    OCTOROK_EYEBALL(78, "Octorok Eyeball", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    OCTOROK_TENTACLE(77, "Octorok Tentacle", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    OCTO_BALLOON(79, "Octo Balloon", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 70 }),
    OPAL(47, "Opal", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    PALM_FRUIT(2, "Palm Fruit", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAW_BIRD_DRUMSTICK(33, "Raw Bird Drumstick", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAW_BIRD_THIGH(31, "Raw Bird Thigh", Material.BEEF, 6, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAW_GOURMET_MEAT(28, "Raw Gourmet Meat", Material.BEEF, 12, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAW_MEAT(32, "Raw Meat", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAW_PRIME_MEAT(30, "Raw Prime Meat", Material.BEEF, 6, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAW_WHOLE_BIRD(29, "Raw Whole Bird", Material.BEEF, 12, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    RAZORCLAW_CRAB(56, "Razorclaw Crab", Material.BEEF, 4, FoodBonus.MIGHTY, new int[] { 1, 1, 2, 3, 3 },
	    new int[] { 50 }),
    RAZORSHROOM(19, "Razorshroom", Material.BEEF, 2, FoodBonus.MIGHTY, new int[] { 1, 1, 2, 3, 3 }, new int[] { 50 }),
    RED_CHUCHU_JELLY(70, "Red Chuchu Jelly", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    RED_LIZALFOS_TAIL(63, "Red Lizalfos Tail", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 190 }),
    RESTLESS_CRICKET(35, "Restless Cricket", Material.CLOCK, 0, FoodBonus.ENERGIZING, new int[] { 1, 2, 4, 5, 5 },
	    new int[] {}),
    ROCK_SALT(12, "Rock Salt", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 60, 30 }),
    RUBY(50, "Ruby", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    RUGGED_RHINO_BEETLE(37, "Rugged Rhino Beetle", Material.CLOCK, 0, FoodBonus.TOUGH, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 50 }),
    RUSHROOM(18, "Rushroom", Material.BEEF, 2, FoodBonus.HASTY, new int[] { 1, 1, 1, 1, 2 }, new int[] { 60 }),
    SANKE_CARP(51, "Sanke Carp", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    SAPPHIRE(51, "Sapphire", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    SHARD_OF_DINRAALS_FANG(17, "Shard Of Dinraals Fang", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {},
	    new int[] { 630, 30 }),
    SHARD_OF_DINRAALS_HORN(18, "Shard Of Dinraals Horn", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {},
	    new int[] { 1800 }),
    SHARD_OF_FAROSHS_FANG(25, "Shard Of Faroshs Fang", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {},
	    new int[] { 630, 30 }),
    SHARD_OF_FAROSHS_HORN(26, "Shard Of Faroshs Horn", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {},
	    new int[] { 1800 }),
    SHARD_OF_NAYDRAS_FANG(21, "Shard Of Naydras Fang", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {},
	    new int[] { 630, 30 }),
    SHARD_OF_NAYDRAS_HORN(22, "Shard Of Naydras Horn", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {},
	    new int[] { 1800 }),
    SILENT_PRINCESS(8, "Silent Princess", Material.CLOCK, 4, FoodBonus.SNEAKY, new int[] { 1, 2, 3, 3, 3 },
	    new int[] { 120 }),
    SILENT_SHROOM(21, "Silent Shroom", Material.BEEF, 2, FoodBonus.SNEAKY, new int[] { 1, 1, 2, 2, 3 },
	    new int[] { 120 }),
    SIZZLEFIN_TROUT(46, "Sizzlefin Trout", Material.BEEF, 4, FoodBonus.SPICY, new int[] { 1, 2, 2, 2, 2 },
	    new int[] { 150 }),
    SMOTHERWING_BUTTERFLY(31, "Smotherwing Butterfly", Material.CLOCK, 0, FoodBonus.FIREPROOF,
	    new int[] { 1, 1, 1, 2, 2 }, new int[] { 150 }),
    SNEAKY_RIVER_SNAIL(54, "Sneaky River Snail", Material.BEEF, 4, FoodBonus.SNEAKY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 120 }),
    SPICY_PEPPER(6, "Spicy Pepper", Material.BEEF, 2, FoodBonus.SPICY, new int[] { 1, 1, 1, 1, 1 }, new int[] { 150 }),
    STAMELLA_SHROOM(14, "Stamella Shroom", Material.BEEF, 2, FoodBonus.ENERGIZING, new int[] { 1, 2, 4, 5, 7 },
	    new int[] {}),
    STAMINOKA_BASS(44, "Staminoka Bass", Material.BEEF, 4, FoodBonus.ENERGIZING, new int[] { 5, 11, 15, 15, 15 },
	    new int[] {}),
    STAR_FRAGMENT(14, "Star Fragment", Material.CLOCK, 0, FoodBonus.RANDOM, new int[] {}, new int[] { 30 }),
    STEALTHFIN_TROUT(48, "Stealthfin Trout", Material.BEEF, 4, FoodBonus.SNEAKY, new int[] { 1, 1, 2, 2, 3 },
	    new int[] { 120 }),
    SUMMERWING_BUTTERFLY(29, "Summerwing Butterfly", Material.CLOCK, 0, FoodBonus.SPICY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 150 }),
    SUNSET_FIREFLY(39, "Sunset Firefly", Material.CLOCK, 0, FoodBonus.SNEAKY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 120 }),
    SUNSHROOM(16, "Sunshroom", Material.BEEF, 2, FoodBonus.SPICY, new int[] { 1, 1, 2, 2, 2 }, new int[] { 150 }),
    SWIFT_CARROT(26, "Swift Carrot", Material.BEEF, 2, FoodBonus.HASTY, new int[] { 1, 1, 1, 1, 2 }, new int[] { 60 }),
    SWIFT_VIOLET(4, "Swift Violet", Material.CLOCK, 0, FoodBonus.HASTY, new int[] { 1, 1, 2, 3, 3 }, new int[] { 60 }),
    TABANTHA_WHEAT(39, "Tabantha Wheat", Material.BEEF, 4, FoodBonus.NONE, new int[] {}, new int[] { 60, 30 }),
    THUNDERWING_BUTTERFLY(30, "Thunderwing Butterfly", Material.CLOCK, 0, FoodBonus.ELECTRO,
	    new int[] { 1, 1, 1, 2, 2 }, new int[] { 150 }),
    TIRELESS_FROG(
	    41, "Tireless Frog", Material.CLOCK, 8, FoodBonus.ENDURING, new int[] { 1, 2, 3, 4, 4 }, new int[] {}),
    TOPAZ(49, "Topaz", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    VOLTFIN_TROUT(47, "Voltfin Trout", Material.BEEF, 4, FoodBonus.ELECTRO, new int[] { 1, 3, 3, 3, 3 },
	    new int[] { 150 }),
    VOLTFRUIT(7, "Voltfruit", Material.BEEF, 2, FoodBonus.ELECTRO, new int[] { 1, 1, 1, 2, 2 }, new int[] { 150 }),
    WARM_DARNER(33, "Warm Darner", Material.CLOCK, 0, FoodBonus.SPICY, new int[] { 1, 1, 2, 2, 2 }, new int[] { 150 }),
    WARM_SAFFLINA(2, "Warm Safflina", Material.CLOCK, 0, FoodBonus.SPICY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 150 }),
    WHITE_CHUCHU_JELLY(69, "White Chuchu Jelly", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    WILDBERRY(4, "Wildberry", Material.BEEF, 2, FoodBonus.NONE, new int[] {}, new int[] { 30 }),
    WINTERWING_BUTTERFLY(28, "Winterwing Butterfly", Material.CLOCK, 0, FoodBonus.CHILLY, new int[] { 1, 1, 1, 1, 1 },
	    new int[] { 150 }),
    WOOD(91, "Wood", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] {}),
    YELLOW_CHUCHU_JELLY(71, "Yellow Chuchu Jelly", Material.CLOCK, 0, FoodBonus.NONE, new int[] {}, new int[] { 110 }),
    YELLOW_LIZALFOS_TAIL(64, "Yellow Lizalfos Tail", Material.CLOCK, 0, FoodBonus.NONE, new int[] {},
	    new int[] { 190 }),
    ZAPSHROOM(17, "Zapshroom", Material.BEEF, 2, FoodBonus.ELECTRO, new int[] { 1, 2, 3, 3, 3 }, new int[] { 150 });

    private final int modelData;
    private final String displayName;
    private final Material mat;
    private final int health;
    private final FoodBonus bonus;
    private final int[] potency;
    private final int[] duration;

    private Ingredient(int modelData, String displayName, Material mat, int health, FoodBonus bonus, int[] potency,
	    int[] duration) {
	this.modelData = modelData;
	this.displayName = displayName;
	this.mat = mat;
	this.health = health;
	this.bonus = bonus;
	this.potency = potency;
	this.duration = duration;
    }

    public ItemStack getIngredient() {
	ItemStack item = new ItemStack(mat);
	ItemMeta meta = item.getItemMeta();
	meta.setCustomModelData(Integer.valueOf(modelData));
	meta.setDisplayName(displayName);
	item.setItemMeta(meta);
	NBTItem nbt = new NBTItem(item);
	nbt.setString("IngredientType", this.toString());
	return nbt.getItem();
    }
    
    public static boolean isIngredient(ItemStack item) {
	if (item == null || item.getType() == Material.AIR) return false;
	return (new NBTItem(item)).getKeys().contains("IngredientType");
    }

    public int getHealth() {
	return health;
    }

    public FoodBonus getBonus() {
	return bonus;
    }

    public int[] getPotency() {
	return potency;
    }

    public int[] getDuration() {
	return duration;
    }
}
