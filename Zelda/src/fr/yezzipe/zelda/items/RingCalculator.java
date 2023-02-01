package fr.yezzipe.zelda.items;

import java.util.List;

import fr.yezzipe.zelda.items.enums.Ring;

public class RingCalculator {

    public static double getPhysicalDamageModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.STRENGTH_RING_3)) {
	    mod = 1.15D;
	} else if (rings.contains(Ring.STRENGTH_RING_2)) {
	    mod = 1.1D;
	} else if (rings.contains(Ring.STRENGTH_RING_1)) {
	    mod = 1.05D;
	}
	return mod;
    }

    public static double getPhysicalArmorModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.ARMOR_RING_3)) {
	    mod = 0.85D;
	} else if (rings.contains(Ring.ARMOR_RING_2)) {
	    mod = 0.9D;
	} else if (rings.contains(Ring.ARMOR_RING_1)) {
	    mod = 0.95D;
	}
	return mod;
    }

    public static double getFireDamageModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.FLAME_RING_2)) {
	    mod = 1.3D;
	} else if (rings.contains(Ring.FLAME_RING_1)) {
	    mod = 1.15D;
	}
	return mod;
    }

    public static double getFireArmorModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.BURNING_RING_2)) {
	    mod = 0.7D;
	} else if (rings.contains(Ring.BURNING_RING_1)) {
	    mod = 0.85D;
	}
	return mod;
    }

    public static double getDarkDamageModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.EVIL_RING_2)) {
	    mod = 1.3D;
	} else if (rings.contains(Ring.EVIL_RING_1)) {
	    mod = 1.15D;
	}
	return mod;
    }

    public static double getDarkArmorModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.OBSCURE_RING_2)) {
	    mod = 0.7D;
	} else if (rings.contains(Ring.OBSCURE_RING_1)) {
	    mod = 0.85D;
	}
	return mod;
    }

    public static double getElectricDamageModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.LIGHTNING_RING_2)) {
	    mod = 1.3D;
	} else if (rings.contains(Ring.LIGHTNING_RING_1)) {
	    mod = 1.15D;
	}
	return mod;
    }

    public static double getElectricArmorModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.STATIC_RING_2)) {
	    mod = 0.7D;
	} else if (rings.contains(Ring.STATIC_RING_1)) {
	    mod = 0.85D;
	}
	return mod;
    }

    public static double getIceDamageModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.FREEZING_RING_2)) {
	    mod = 1.3D;
	} else if (rings.contains(Ring.FREEZING_RING_1)) {
	    mod = 1.15D;
	}
	return mod;
    }

    public static double getIceArmorModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.FROST_RING_2)) {
	    mod = 0.85D;
	} else if (rings.contains(Ring.FROST_RING_1)) {
	    mod = 0.7D;
	}
	return mod;
    }

    public static double getLightDamageModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.LIGHT_RING_2)) {
	    mod = 1.3D;
	} else if (rings.contains(Ring.LIGHT_RING_1)) {
	    mod = 1.15D;
	}
	return mod;
    }

    public static double getLightArmorModifier(List<Ring> rings) {
	double mod = 1.0D;
	if (rings.contains(Ring.RADIANT_RING_2)) {
	    mod = 0.85D;
	} else if (rings.contains(Ring.RADIANT_RING_1)) {
	    mod = 0.7D;
	}
	return mod;
    }
}
