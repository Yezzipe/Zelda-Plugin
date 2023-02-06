package fr.yezzipe.zelda.entity.player;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import fr.yezzipe.zelda.Registry;
import fr.yezzipe.zelda.entity.enums.Race;

public class BiomeRegistry<E,T> extends Registry<E,T> {
	
	private static final BiomeRegistry<Biome, Collection<Race>> instance = new BiomeRegistry<Biome,Collection<Race>>();
	
	public BiomeRegistry() {}
	
	public static void init() {
		Collection<Race> list = Arrays.asList(new Race[] {Race.GERUDO, Race.GORON});
		instance.bind(Biome.BADLANDS, list);
		instance.bind(Biome.ERODED_BADLANDS, list);
		instance.bind(Biome.WOODED_BADLANDS, list);
		list = Arrays.asList(new Race[] {Race.GERUDO});
		instance.bind(Biome.DESERT, list);
		list = Arrays.asList(new Race[] {Race.GORON});
		instance.bind(Biome.CRIMSON_FOREST, list);
		instance.bind(Biome.SOUL_SAND_VALLEY, list);
		instance.bind(Biome.BASALT_DELTAS, list);
		instance.bind(Biome.WARPED_FOREST, list);
		instance.bind(Biome.DRIPSTONE_CAVES, list);
		instance.bind(Biome.NETHER_WASTES, list);
		instance.bind(Biome.WINDSWEPT_HILLS, list);
		instance.bind(Biome.WINDSWEPT_GRAVELLY_HILLS, list);
		list = Arrays.asList(new Race[] {Race.ZORA});
		instance.bind(Biome.BEACH, list);
		instance.bind(Biome.WARM_OCEAN, list);
		instance.bind(Biome.OCEAN, list);
		instance.bind(Biome.LUKEWARM_OCEAN, list);
		instance.bind(Biome.FROZEN_OCEAN, list);
		instance.bind(Biome.DEEP_OCEAN, list);
		instance.bind(Biome.DEEP_LUKEWARM_OCEAN, list);
		instance.bind(Biome.DEEP_FROZEN_OCEAN, list);
		instance.bind(Biome.DEEP_COLD_OCEAN, list);
		instance.bind(Biome.COLD_OCEAN, list);
		list = Arrays.asList(new Race[] {Race.PIAF});
		instance.bind(Biome.GROVE, list);
		instance.bind(Biome.FROZEN_RIVER, list);
		instance.bind(Biome.JAGGED_PEAKS, list);
		instance.bind(Biome.STONY_PEAKS, list);
		instance.bind(Biome.FROZEN_PEAKS, list);
		instance.bind(Biome.SNOWY_TAIGA, list);
		instance.bind(Biome.SNOWY_SLOPES, list);
		instance.bind(Biome.ICE_SPIKES, list);
		instance.bind(Biome.SNOWY_BEACH, list);
		instance.bind(Biome.SNOWY_PLAINS, list);
		list = Arrays.asList(new Race[] {Race.HYLIEN});
		instance.bind(Biome.WINDSWEPT_SAVANNA, list);
		instance.bind(Biome.SAVANNA, list);
		instance.bind(Biome.SAVANNA_PLATEAU, list);
		instance.bind(Biome.PLAINS, list);
		instance.bind(Biome.MEADOW, list);
		list = Arrays.asList(new Race[] {Race.SHEIKAH});
		instance.bind(Biome.MANGROVE_SWAMP, list);
		instance.bind(Biome.TAIGA, list);
		instance.bind(Biome.SWAMP, list);
		list = Arrays.asList(new Race[] {Race.TWILI, Race.SHEIKAH});
		instance.bind(Biome.SMALL_END_ISLANDS, list);
		instance.bind(Biome.END_BARRENS, list);
		instance.bind(Biome.END_HIGHLANDS, list);
		instance.bind(Biome.END_MIDLANDS, list);
		instance.bind(Biome.THE_END, list);
		instance.bind(Biome.THE_VOID, list);
		list = Arrays.asList(new Race[] {Race.KOKIRI});
		instance.bind(Biome.BAMBOO_JUNGLE, list);
		instance.bind(Biome.SPARSE_JUNGLE, list);
		instance.bind(Biome.WINDSWEPT_FOREST, list);
		instance.bind(Biome.DARK_FOREST, list);
		instance.bind(Biome.OLD_GROWTH_BIRCH_FOREST, list);
		instance.bind(Biome.OLD_GROWTH_PINE_TAIGA, list);
		instance.bind(Biome.OLD_GROWTH_SPRUCE_TAIGA, list);
		instance.bind(Biome.JUNGLE, list);
		instance.bind(Biome.FOREST, list);
		instance.bind(Biome.FLOWER_FOREST, list);
		instance.bind(Biome.BIRCH_FOREST, list);
		instance.bind(Biome.SUNFLOWER_PLAINS, list);
	}
	
	public static boolean isMainBiome(Player p) {
		PlayerData PData = PlayerData.getData(p);
		return isMainBiome(PData);
	}
	
	public static boolean isMainBiome(PlayerData PData) {
	    	Collection<Race> c = instance.get(PData.getCurrentBiome());
	    	if (c == null) return false;
		return c.contains(PData.getCurrentRace());
	}

    /*public static Collection<Race> getRaceFromBiome(Biome biome) {
	Collection<Race> list = new ArrayList<Race>();
	switch (biome) {
	case BADLANDS:
	case ERODED_BADLANDS:
	case WOODED_BADLANDS:
	    list.add(Race.GERUDO);
	    list.add(Race.GORON);
	    break;
	case DESERT:
	    list.add(Race.GERUDO);
	    break;
	case CRIMSON_FOREST:
	case SOUL_SAND_VALLEY:
	case BASALT_DELTAS:
	case WARPED_FOREST:
	case DRIPSTONE_CAVES:
	case NETHER_WASTES:
	case WINDSWEPT_HILLS:
	case WINDSWEPT_GRAVELLY_HILLS:
	    list.add(Race.GORON);
	    break;
	case BEACH:
	case WARM_OCEAN:
	case OCEAN:
	case LUKEWARM_OCEAN:
	case FROZEN_OCEAN:
	case DEEP_OCEAN:
	case DEEP_LUKEWARM_OCEAN:
	case DEEP_FROZEN_OCEAN:
	case DEEP_COLD_OCEAN:
	case COLD_OCEAN:
	    list.add(Race.ZORA);
	    break;
	case GROVE:
	case FROZEN_RIVER:
	case JAGGED_PEAKS:
	case STONY_PEAKS:
	case FROZEN_PEAKS:
	case SNOWY_TAIGA:
	case SNOWY_SLOPES:
	case ICE_SPIKES:
	case SNOWY_BEACH:
	case SNOWY_PLAINS:
	    list.add(Race.PIAF);
	    break;
	case WINDSWEPT_SAVANNA:
	case SAVANNA:
	case SAVANNA_PLATEAU:
	case SUNFLOWER_PLAINS:
	case PLAINS:
	case MEADOW:
	    list.add(Race.HYLIEN);
	    break;
	case MANGROVE_SWAMP:
	case TAIGA:
	case SWAMP:
	    list.add(Race.SHEIKAH);
	    break;
	case SMALL_END_ISLANDS:
	case END_BARRENS:
	case END_HIGHLANDS:
	case END_MIDLANDS:
	case THE_VOID:
	case THE_END:
	    list.add(Race.TWILI);
	    list.add(Race.SHEIKAH);
	    break;
	case BAMBOO_JUNGLE:
	case SPARSE_JUNGLE:
	case WINDSWEPT_FOREST:
	case DARK_FOREST:
	case OLD_GROWTH_SPRUCE_TAIGA:
	case OLD_GROWTH_PINE_TAIGA:
	case OLD_GROWTH_BIRCH_FOREST:
	case JUNGLE:
	case FOREST:
	case FLOWER_FOREST:
	case BIRCH_FOREST:
	    list.add(Race.KOKIRI);
	    break;
	case CUSTOM:
	case RIVER:
	case STONY_SHORE:
	case MUSHROOM_FIELDS:
	case LUSH_CAVES:	
	case DEEP_DARK:
	default:
	    list.add(Race.HYLIA);

	}
	return list;
    }*/
}
