package fr.yezzipe.zelda.events;

import fr.yezzipe.zelda.entity.enums.Race;
import fr.yezzipe.zelda.events.enums.DamageType;

public class ModifierCalculator {
  public static double getResitanceModifier(DamageType damageType, Race race) {
    double mod = 1.0D;
    if (damageType == DamageType.DARK) {
      if (race == Race.KOKIRI)
        mod -= 0.15D; 
      if (race == Race.HYLIEN)
        mod += 0.05D; 
      if (race == Race.PIAF)
        mod += 0.1D; 
    } else if (damageType == DamageType.LIGHT) {
      if (race == Race.SHEIKA)
        mod -= 0.1D; 
      if (race == Race.TWILI)
        mod += 0.1D; 
    } else if (damageType == DamageType.FIRE) {
      if (race == Race.GORON)
        mod -= 0.25D; 
      if (race == Race.ZORA)
        mod += 0.1D; 
      if (race == Race.KOKIRI)
        mod += 0.15D; 
    } else if (damageType == DamageType.ICE) {
      if (race == Race.ZORA)
        mod -= 0.15D; 
      if (race == Race.TWILI)
        mod -= 0.1D; 
      if (race == Race.GORON)
        mod += 0.1D; 
      if (race == Race.GERUDO)
        mod += 0.15D; 
    } else if (damageType == DamageType.ELECTRIC) {
      if (race == Race.SHEIKA)
        mod -= 0.15D; 
      if (race == Race.GORON)
        mod -= 0.1D; 
      if (race == Race.PIAF)
        mod += 0.1D; 
      if (race == Race.KOKIRI)
        mod += 0.15D; 
    } 
    return mod;
  }
  
  public static double getStrengthModifier(DamageType damageType, Race race) {
    double mod = 1.0D;
    if (damageType == DamageType.DARK) {
      if (race == Race.TWILI)
        mod += 0.25D; 
      if (race == Race.HYLIEN)
        mod -= 0.1D; 
    } else if (damageType == DamageType.LIGHT) {
      if (race == Race.KOKIRI)
        mod += 0.05D; 
      if (race == Race.HYLIEN)
        mod += 0.1D; 
      if (race == Race.TWILI)
        mod -= 0.15D; 
    } else if (damageType == DamageType.FIRE) {
      if (race == Race.GERUDO)
        mod += 0.1D; 
      if (race == Race.SHEIKA)
        mod -= 0.1D; 
    } else if (damageType == DamageType.ICE) {
      if (race == Race.ZORA)
        mod += 0.1D; 
      if (race == Race.SHEIKA)
        mod -= 0.1D; 
    } else if (damageType == DamageType.ELECTRIC) {
      if (race == Race.GERUDO)
        mod += 0.1D; 
      if (race == Race.ZORA)
        mod -= 0.1D; 
    } 
    return mod;
  }
}

