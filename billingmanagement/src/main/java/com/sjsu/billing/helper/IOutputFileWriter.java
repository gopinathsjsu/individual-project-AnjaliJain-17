package com.sjsu.billing.helper;

import java.util.List;

import com.sjsu.billing.model.Order;

public interface IOutputFileWriter {
	
	public void writeToFile(String error, List<Order> orders, String path);

}
