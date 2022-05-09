package com.sjsu.billing.validation;

import java.util.List;

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
	
		int essentialsCount=(int) orders.stream().filter(o->inventorydb.getItem(o.getItemName().toLowerCase()).getCategory().equalsIgnoreCase("Essentials")).count();
		int luxuryCount=(int) orders.stream().filter(o->inventorydb.getItem(o.getItemName().toLowerCase()).getCategory().equalsIgnoreCase("Luxury")).count();
		int miscCount=(int) orders.stream().filter(o->inventorydb.getItem(o.getItemName().toLowerCase()).getCategory().equalsIgnoreCase("Misc")).count();
		 
		StringBuilder errorMsg = new StringBuilder();
		
		if(essentialsCount> ItemCategoryCapConfig.categoryCap.get("Essentials")) {
			errorMsg.append("Please correct quantities : Permissible : "+ItemCategoryCapConfig.categoryCap.get("Essentials")+" Actual : "+essentialsCount);
		}
		if(luxuryCount> ItemCategoryCapConfig.categoryCap.get("Luxury")) {
			errorMsg.append("Please correct quantities : Permissible : "+ItemCategoryCapConfig.categoryCap.get("Luxury")+" Actual : "+luxuryCount);
			
		}
		
		if(	miscCount > ItemCategoryCapConfig.categoryCap.get("Misc")){
			errorMsg.append("Please correct quantities : Permissible : "+ItemCategoryCapConfig.categoryCap.get("Misc")+" Actual : "+miscCount);
			
		}
		
		if(errorMsg.length()>0) {
			return new ValidationResult(false,errorMsg.toString());
		}
		return this.handler.validate(orders);
	}


}
