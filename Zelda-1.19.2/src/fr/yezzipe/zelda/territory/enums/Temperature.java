package fr.yezzipe.zelda.territory.enums;

public enum Temperature {
	EXTREME_COLD(-3), VERY_COLD(-2), COLD(-1), NORMAL(0), HOT(1), VERY_HOT(2), EXTREME_HOT(3);
    
    
    private int index;
    
    private Temperature(int i) {
	index = i;
    }
    
    public int getIndex() {
	return index;
    }
    
    public static Temperature getFeeledTemperature(Temperature t, int coldResist, int heatResist) {
	int index;
	if (t.getIndex() > 0) {
	    index = t.getIndex() - heatResist > 3 ? 3 : t.getIndex() - heatResist < 0 ? 0 : t.getIndex() - heatResist;
	} else if (t.getIndex() < 0) {
	    index = t.getIndex() + coldResist > 0 ? 0 : t.getIndex() + coldResist < -3 ? -3 : t.getIndex() + coldResist;
	} else 
	    return Temperature.NORMAL;
	Temperature temp = Temperature.NORMAL;
	for (Temperature t2 : Temperature.values()) {
	    if (t2.getIndex() == index) {
		temp = t2;
		break;
	    }
	}
	return temp;
    }
}
