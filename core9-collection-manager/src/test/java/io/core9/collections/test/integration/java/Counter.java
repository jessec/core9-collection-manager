package io.core9.collections.test.integration.java;

import java.util.HashMap;
import java.util.Map;

public class Counter {

	private Map<String, Integer> counterMap = new HashMap<String, Integer>();
	private static Counter instance = null;

	protected Counter() {
	}

	public static Counter getInstance() {
		if (instance == null) {
			instance = new Counter();
		}
		return instance;
	}
	
	public void inc(String counter){
		
	    if (counterMap.containsKey(counter)) {
			int nr = counterMap.get(counter);
			nr = nr + 1;
			counterMap.put(counter, nr);		
	     } else {
	    	 counterMap.put(counter, 0);
	     }
	}
	
	public Integer getCount(String counter){
		return counterMap.get(counter);
	}
	
}