package fr.yezzipe.zelda.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.potion.PotionEffect;

public class PotionUtil {

    public static Collection<PotionEffectMemory> merge(Collection<PotionEffectMemory> base,
	    Collection<PotionEffectMemory> fuse) {
	if (fuse == null || fuse.isEmpty())
	    return base;
	for (PotionEffectMemory effect : fuse) {
	    boolean contained = false;
	    PotionEffectMemory eff = null;
	    for (PotionEffectMemory effect2 : base) {
		if (effect2.getType().equals(effect.getType())) {
		    contained = true;
		    eff = effect2;
		}
	    }
	    if (contained) {
		eff.incrementAmplifier(effect.getAmplifier());
	    } else {
		base.add(effect);
	    }
	}
	return base;
    }

    public static Collection<PotionEffectMemory> merge(Collection<PotionEffectMemory> base, PotionEffectMemory effect) {
	if (effect == null)
	    return base;
	boolean contained = false;
	PotionEffectMemory eff = null;
	for (PotionEffectMemory effect2 : base) {
	    if (effect.getType().equals(effect2.getType())) {
		contained = true;
		eff = effect2;
	    }
	}
	if (contained) {
	    eff.incrementAmplifier(effect.getAmplifier());
	} else {
	    base.add(effect);
	}
	return base;
    }

    public static Collection<PotionEffect> getPotionEffects(Collection<PotionEffectMemory> potions) {
	Collection<PotionEffect> effects = new ArrayList<PotionEffect>();
	for (PotionEffectMemory effect : potions) {
	    effects.add(effect.getPotionEffect());
	}
	return effects;
    }

}
