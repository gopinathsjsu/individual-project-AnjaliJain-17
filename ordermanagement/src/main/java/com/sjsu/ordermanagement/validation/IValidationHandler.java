package com.sjsu.ordermanagement.validation;

import java.util.List;

import com.sjsu.ordermanagement.model.Order;
import com.sjsu.ordermanagement.model.ValidationResult;

public interface IValidationHandler {
	
	void nextHandler(IValidationHandler next);
	ValidationResult validate(List<Order> orders);

}
