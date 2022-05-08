package com.sjsu.billing.validation;

import java.util.List;

import com.sjsu.billing.model.Order;
import com.sjsu.billing.model.ValidationResult;
import com.sjsu.billing.repository.Inventory;

public class ItemQuantityValidationHandler implements IValidationHandler {

	private IValidationHandler handler;
	
	
	@Override
	public void nextHandler(IValidationHandler next) {
		this.handler=next;
	}

	
	@Override
	public ValidationResult validate(List<Order> orders) {
	
		StringBuilder errorMsg = new StringBuilder();
		for(Order o : orders) {
			int allowed = Inventory.getInstance().getItem(o.getItemName().toLowerCase()).getQuantity();
			if(allowed - o.getQuantity()<0) {
				errorMsg.append(" Insufficient quantity for item "+o.getItemName()+" allowed :"+allowed +"expected :"+o.getQuantity());
			}
		}
		
		if(errorMsg.length()>0) {
			return new ValidationResult(false,errorMsg.toString());
		}
		
		return this.handler==null? new ValidationResult(true,null):this.handler.validate(orders);
	}

	

}
