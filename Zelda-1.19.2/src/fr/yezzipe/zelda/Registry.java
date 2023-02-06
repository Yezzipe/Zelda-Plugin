package fr.yezzipe.zelda;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registry<T,Q> {
	
	private Map<T,Q> map;
	
	public Registry() {
		map = new HashMap<T,Q>();
	}
	
	public Q get(T obj) {
		return map.get(obj);
	}
	
	public void bind(T obj, Q obj2) {
		map.put(obj, obj2);
	}
	
	public void unbind(T obj) {
		map.remove(obj);
	}
	
	public Set<T> keys() {
	    return map.keySet();
	}
}
