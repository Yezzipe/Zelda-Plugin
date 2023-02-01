package fr.yezzipe.zelda.entity.player;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.block.Biome;

import fr.yezzipe.zelda.entity.enums.Race;

public class BiomeRegistry {

    public static Collection<Race> getRaceFromBiome(Biome biome) {
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
	// case MANGROVE:
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
	default:
	    list.add(Race.HYLIA);

	}
	return list;
    }
}
