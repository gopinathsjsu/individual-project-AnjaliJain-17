package com.sjsu.billing.validation;

public class ValidationHandlerDriver {

	public IValidationHandler v1;
	
	public ValidationHandlerDriver() {
		this.v1= new ItemExistsValidationHandler();
		IValidationHandler v2 = new ItemCategoryCapValidationHandler();
		IValidationHandler v3 = new ItemQuantityValidationHandler();
		v1.nextHandler(v2);
		v2.nextHandler(v3);
	}
}
