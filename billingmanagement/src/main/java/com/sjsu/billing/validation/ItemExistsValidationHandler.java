package com.sjsu.billing.validation;

import java.util.List;

import com.sjsu.billing.config.ItemCategoryCapConfig;
import com.sjsu.billing.model.Order;
import com.sjsu.billing.model.ValidationResult;
import com.sjsu.billing.repository.Inventory;

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
		
		if(errorMsg.length()>0) {
			return new ValidationResult(false,errorMsg.toString());
		}
		return this.handler.validate(orders);
	}



}
