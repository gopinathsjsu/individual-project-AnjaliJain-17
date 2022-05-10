package com.sjsu.billing.validation;

import java.util.List;
import java.util.stream.Stream;

import com.sjsu.billing.config.ItemCategoryCapConfig;
import com.sjsu.billing.model.Order;
import com.sjsu.billing.model.ValidationResult;
import com.sjsu.billing.repository.Inventory;

public class ItemCategoryCapValidationHandler implements IValidationHandler {

	private IValidationHandler handler;
	
	
	@Override
	public void nextHandler(IValidationHandler next) {
		this.handler=next;
	}

	@Override
	public ValidationResult validate(List<Order> orders) {
		
		Inventory inventorydb = Inventory.getInstance();
	
		Stream<Order> essentialOrders = orders.stream().filter(o->inventorydb.getItem(o.getItemName().toLowerCase()).getCategory().equalsIgnoreCase("Essentials"));
		int essentialsCount=essentialOrders.mapToInt(o->o.getQuantity()).sum();
		System.out.println("Essential items count total"+essentialsCount);
	
		Stream<Order> luxuryorders = orders.stream().filter(o->inventorydb.getItem(o.getItemName().toLowerCase()).getCategory().equalsIgnoreCase("Luxury"));
		int luxItems = luxuryorders.mapToInt(o->o.getQuantity()).sum();
		System.out.println("Luxury items count total"+luxItems);
		
		
		Stream<Order> miscOrders = orders.stream().filter(o->inventorydb.getItem(o.getItemName().toLowerCase()).getCategory().equalsIgnoreCase("Misc"));
		
		int miscCount=miscOrders.mapToInt(o->o.getQuantity()).sum();
		System.out.println("Misc items count total"+miscCount);
		
		StringBuilder errorMsg = new StringBuilder();
		
		if(essentialsCount> ItemCategoryCapConfig.categoryCap.get("Essentials")) {
			errorMsg.append("Please correct quantities for Essentials : Permissible : "+ItemCategoryCapConfig.categoryCap.get("Essentials")+" Actual : "+essentialsCount);
		}
		if(luxItems> ItemCategoryCapConfig.categoryCap.get("Luxury")) {
			errorMsg.append("Please correct quantities for Luxury : Permissible : "+ItemCategoryCapConfig.categoryCap.get("Luxury")+" Actual : "+luxItems);
			
		}
		
		if(	miscCount > ItemCategoryCapConfig.categoryCap.get("Misc")){
			errorMsg.append("Please correct quantities for Misc : Permissible : "+ItemCategoryCapConfig.categoryCap.get("Misc")+" Actual : "+miscCount);
			
		}
		
		if(errorMsg.length()>0) {
			return new ValidationResult(false,errorMsg.toString());
		}
		return this.handler.validate(orders);
	}


}
