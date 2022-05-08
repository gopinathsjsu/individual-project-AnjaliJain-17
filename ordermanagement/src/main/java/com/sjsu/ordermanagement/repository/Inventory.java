package com.sjsu.ordermanagement.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sjsu.ordermanagement.model.Item;

public class Inventory {
	
	private volatile static Inventory instance=null;
	private Map<String,Item> stockmap = new HashMap<>();
	private List<String> cardNumbers = new ArrayList<>();
	private Inventory() {}
	
	//double checked locking singleton implementation
	public static Inventory getInstance() {
		if(instance==null) {
			synchronized(Inventory.class) {
				if(instance==null) {
					instance=new Inventory();
				}
			}
		}
		
		return instance;
	}
	//add item in hashmap
	 public void addItem(String name, Item item) {
		 stockmap.put(name,item);       
	 }

	 public void addCardNumberIfNotExists(String cardnumber) {
		if(!cardNumbers.contains(cardnumber)) {
			cardNumbers.add(cardnumber);
		}
	 }
	 
	 public Item getItem(String itemName) {
		return stockmap.get(itemName);
		 
	 }
}
