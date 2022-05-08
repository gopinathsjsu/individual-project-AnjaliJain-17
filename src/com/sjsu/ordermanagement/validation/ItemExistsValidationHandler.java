package com.sjsu.ordermanagement.validation;

import java.util.List;

import com.sjsu.ordermanagement.config.ItemCategoryCapConfig;
import com.sjsu.ordermanagement.model.Order;
import com.sjsu.ordermanagement.model.ValidationResult;
import com.sjsu.ordermanagement.repository.Inventory;

public class ItemExistsValidationHandler implements IValidationHandler {

	private IValidationHandler handler;
	
	
	@Override
	public void nextHandler(IValidationHandler next) {
		this.handler=next;
	}
	
	@Override
	public ValidationResult validate(List<Order> orders) {
	   
		Inventory inventorydb = Inventory.getInstance();
		
		StringBuilder errorMsg = new StringBuilder();
		
		for(Order o : orders) {
			if(inventorydb.getItem(o.getItemName().toLowerCase())==null) {
				errorMsg.append(" Item does not exists "+o.getItemName()); 
			}
		}
		
		if(!errorMsg.isEmpty()) {
			return new ValidationResult(false,errorMsg.toString());
		}
		return this.handler.validate(orders);
	}



}
