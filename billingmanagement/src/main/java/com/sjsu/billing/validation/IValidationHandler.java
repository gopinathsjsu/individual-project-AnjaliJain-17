package com.sjsu.billing.validation;

import java.util.List;

import com.sjsu.billing.model.Order;
import com.sjsu.billing.model.ValidationResult;

public interface IValidationHandler {
	
	void nextHandler(IValidationHandler next);
	ValidationResult validate(List<Order> orders);

}
