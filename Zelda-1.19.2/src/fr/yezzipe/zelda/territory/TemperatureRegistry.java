package fr.yezzipe.zelda.territory;

import org.bukkit.block.Biome;

import fr.yezzipe.zelda.Registry;
import fr.yezzipe.zelda.territory.enums.Temperature;
import net.md_5.bungee.api.ChatColor;

public class TemperatureRegistry<T, Q> extends Registry<T, Q> {

    private static final TemperatureRegistry<Biome, Temperature> instance = new TemperatureRegistry<Biome, Temperature>();
    private static final TemperatureRegistry<Temperature, ChatColor> instance2 = new TemperatureRegistry<Temperature, ChatColor>();

    public TemperatureRegistry() {
    }

    public static void init() {
	instance.bind(Biome.OCEAN, Temperature.NORMAL);
	instance.bind(Biome.PLAINS, Temperature.NORMAL);
	instance.bind(Biome.DESERT, Temperature.VERY_HOT);
	instance.bind(Biome.WINDSWEPT_HILLS, Temperature.COLD);
	instance.bind(Biome.FOREST, Temperature.NORMAL);
	instance.bind(Biome.TAIGA, Temperature.COLD);
	instance.bind(Biome.SWAMP, Temperature.HOT);
	instance.bind(Biome.MANGROVE_SWAMP, Temperature.HOT);
	instance.bind(Biome.RIVER, Temperature.NORMAL);
	instance.bind(Biome.NETHER_WASTES, Temperature.EXTREME_HOT);
	instance.bind(Biome.THE_END, Temperature.EXTREME_COLD);
	instance.bind(Biome.FROZEN_OCEAN, Temperature.VERY_COLD);
	instance.bind(Biome.FROZEN_RIVER, Temperature.VERY_COLD);
	instance.bind(Biome.SNOWY_PLAINS, Temperature.VERY_COLD);
	instance.bind(Biome.MUSHROOM_FIELDS, Temperature.NORMAL);
	instance.bind(Biome.BEACH, Temperature.NORMAL);
	instance.bind(Biome.JUNGLE, Temperature.VERY_HOT);
	instance.bind(Biome.SPARSE_JUNGLE, Temperature.HOT);
	instance.bind(Biome.DEEP_OCEAN, Temperature.NORMAL);
	instance.bind(Biome.STONY_SHORE, Temperature.NORMAL);
	instance.bind(Biome.SNOWY_BEACH, Temperature.VERY_COLD);
	instance.bind(Biome.BIRCH_FOREST, Temperature.NORMAL);
	instance.bind(Biome.DARK_FOREST, Temperature.HOT);
	instance.bind(Biome.SNOWY_TAIGA, Temperature.VERY_COLD);
	instance.bind(Biome.OLD_GROWTH_PINE_TAIGA, Temperature.COLD);
	instance.bind(Biome.WINDSWEPT_FOREST, Temperature.COLD);
	instance.bind(Biome.SAVANNA, Temperature.HOT);
	instance.bind(Biome.SAVANNA_PLATEAU, Temperature.HOT);
	instance.bind(Biome.BADLANDS, Temperature.VERY_HOT);
	instance.bind(Biome.WOODED_BADLANDS, Temperature.VERY_HOT);
	instance.bind(Biome.SMALL_END_ISLANDS, Temperature.EXTREME_COLD);
	instance.bind(Biome.END_MIDLANDS, Temperature.EXTREME_COLD);
	instance.bind(Biome.END_HIGHLANDS, Temperature.EXTREME_COLD);
	instance.bind(Biome.END_BARRENS, Temperature.EXTREME_COLD);
	instance.bind(Biome.WARM_OCEAN, Temperature.HOT);
	instance.bind(Biome.LUKEWARM_OCEAN, Temperature.HOT);
	instance.bind(Biome.COLD_OCEAN, Temperature.COLD);
	instance.bind(Biome.DEEP_LUKEWARM_OCEAN, Temperature.HOT);
	instance.bind(Biome.DEEP_COLD_OCEAN, Temperature.COLD);
	instance.bind(Biome.DEEP_FROZEN_OCEAN, Temperature.VERY_COLD);
	instance.bind(Biome.THE_VOID, Temperature.EXTREME_COLD);
	instance.bind(Biome.SUNFLOWER_PLAINS, Temperature.NORMAL);
	instance.bind(Biome.WINDSWEPT_GRAVELLY_HILLS, Temperature.COLD);
	instance.bind(Biome.FLOWER_FOREST, Temperature.NORMAL);
	instance.bind(Biome.ICE_SPIKES, Temperature.VERY_COLD);
	instance.bind(Biome.OLD_GROWTH_BIRCH_FOREST, Temperature.NORMAL);
	instance.bind(Biome.OLD_GROWTH_SPRUCE_TAIGA, Temperature.COLD);
	instance.bind(Biome.WINDSWEPT_SAVANNA, Temperature.HOT);
	instance.bind(Biome.ERODED_BADLANDS, Temperature.VERY_HOT);
	instance.bind(Biome.BAMBOO_JUNGLE, Temperature.HOT);
	instance.bind(Biome.SOUL_SAND_VALLEY, Temperature.EXTREME_HOT);
	instance.bind(Biome.CRIMSON_FOREST, Temperature.EXTREME_HOT);
	instance.bind(Biome.WARPED_FOREST, Temperature.VERY_HOT);
	instance.bind(Biome.BASALT_DELTAS, Temperature.EXTREME_HOT);
	instance.bind(Biome.DRIPSTONE_CAVES, Temperature.COLD);
	instance.bind(Biome.LUSH_CAVES, Temperature.HOT);
	instance.bind(Biome.DEEP_DARK, Temperature.VERY_COLD);
	instance.bind(Biome.MEADOW, Temperature.COLD);
	instance.bind(Biome.GROVE, Temperature.VERY_COLD);
	instance.bind(Biome.SNOWY_SLOPES, Temperature.VERY_COLD);
	instance.bind(Biome.FROZEN_PEAKS, Temperature.EXTREME_COLD);
	instance.bind(Biome.JAGGED_PEAKS, Temperature.VERY_COLD);
	instance.bind(Biome.STONY_PEAKS, Temperature.VERY_COLD);
	instance2.bind(Temperature.EXTREME_COLD, ChatColor.BLUE);
	instance2.bind(Temperature.VERY_COLD, ChatColor.DARK_AQUA);
	instance2.bind(Temperature.COLD, ChatColor.AQUA);
	instance2.bind(Temperature.NORMAL, ChatColor.WHITE);
	instance2.bind(Temperature.HOT, ChatColor.YELLOW);
	instance2.bind(Temperature.VERY_HOT, ChatColor.GOLD);
	instance2.bind(Temperature.EXTREME_HOT, ChatColor.RED);
    }

    public static Temperature getTemperature(Biome biome) {
	Temperature temp = instance.get(biome);
	if (temp == null)
	    return Temperature.NORMAL;
	return temp;
    }

    public static ChatColor getColor(Temperature temp) {
	ChatColor c = instance2.get(temp);
	if (c == null)
	    return ChatColor.WHITE;
	return c;
    }
}
