package com.sjsu.ordermanagement.config;

import java.util.HashMap;
import java.util.Map;

public class ItemCategoryCapConfig {
	
	public static Map<String, Integer> categoryCap;
	  
    // Instantiating the static map
    static
    {
    	categoryCap = new HashMap<>();
    	categoryCap.put("Essentials", 3);
    	categoryCap.put("Luxury", 4);
    	categoryCap.put("Misc", 6);
    }
  

}
