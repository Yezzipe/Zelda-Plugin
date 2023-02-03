package fr.yezzipe.zelda.territory;

import org.bukkit.block.Biome;

import fr.yezzipe.zelda.Registry;
import fr.yezzipe.zelda.territory.enums.Temperature;

public class TemperatureRegistry<T,Q> extends Registry<T,Q> {
	
	private static final TemperatureRegistry<Biome,Temperature> instance = new TemperatureRegistry<>();
	
	public TemperatureRegistry() {}
	
	public static void init() {
		
	}
	
	public Temperature getTemperature(Biome biome) {
		return instance.get(biome);
	}
}
