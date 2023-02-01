package fr.yezzipe.zelda.entity;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectMemory {
    
    private int duration;
    
    private int amplifier;
    
    private String type;
    
    private boolean isAmbient;
    
    private boolean hasParticles;
    
    private boolean hasIcon;
    
    public PotionEffectMemory(PotionEffect effect) {
	duration = effect.getDuration();
	amplifier = effect.getAmplifier();
	type = effect.getType().getName();
	isAmbient = effect.isAmbient();
	hasParticles = effect.hasParticles();
	hasIcon = effect.hasIcon();
    }
    
    public PotionEffectMemory(PotionEffectType type, int duration) {
	this(type, duration, 0, false, false, false);
    }
    
    public PotionEffectMemory(PotionEffectType type, int duration, int amplifier) {
	this(type, duration, amplifier, false, false, false);
    }
    
    public PotionEffectMemory(PotionEffectType type, int duration, int amplifier, boolean ambient) {
	this(type, duration, amplifier, ambient, false, false);
    }
    
    public PotionEffectMemory(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particle) {
	this(type, duration, amplifier, ambient, particle, false);
    }
    
    public PotionEffectMemory(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particle, boolean icon) {
	this.type = type.getName();
	this.duration = duration;
	this.amplifier = amplifier;
	this.isAmbient = ambient;
	this.hasParticles = particle;
	this.hasIcon = icon;
    }

    public void TickDuration() {
	duration -= 20;
    }

    public int getDuration() {
	return duration;
    }

    public PotionEffectType getType() {
	return PotionEffectType.getByName(type);
    }

    public int getAmplifier() {
	return amplifier;
    }

    public PotionEffect getPotionEffect() {
	return new PotionEffect(getType(), duration, amplifier, isAmbient, hasParticles, hasIcon);
    }

	public void incrementAmplifier(int amplifier2) {
		amplifier += 1+amplifier2;
	}
    
}
