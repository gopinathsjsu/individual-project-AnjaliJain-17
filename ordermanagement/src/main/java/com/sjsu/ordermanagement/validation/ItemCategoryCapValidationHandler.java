package com.sjsu.ordermanagement.validation;

import java.util.List;

import com.sjsu.ordermanagement.config.ItemCategoryCapConfig;
import com.sjsu.ordermanagement.model.Order;
import com.sjsu.ordermanagement.model.ValidationResult;
import com.sjsu.ordermanagement.repository.Inventory;

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
			errorMsg.append(" Exceeds permissible essentials cap limit");
		}
		if(luxuryCount> ItemCategoryCapConfig.categoryCap.get("Luxury")) {
			errorMsg.append(" Exceeds permissible luxury cap limit");
		}
		
		if(	miscCount > ItemCategoryCapConfig.categoryCap.get("Misc")){
			errorMsg.append(" Exceeds permissible misc cap limit");
		}
		
		if(errorMsg.length()>0) {
			return new ValidationResult(false,errorMsg.toString());
		}
		return this.handler.validate(orders);
	}


}
