package com.sjsu.ordermanagement.model;

public class ValidationResult {
	private  boolean isValid;
	private  String errorMsg;

	
	public ValidationResult(boolean isValid, String errorMsg) {
		super();
		this.isValid = isValid;
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String toString() {
		return "ValidationResult [isValid=" + isValid + ", errorMsg=" + errorMsg + "]";
	}

	public boolean isValid() {
		return isValid;
	}
	public String getErrorMsg() {
		return errorMsg;
	}

}
