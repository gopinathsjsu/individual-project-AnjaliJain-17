package com.sjsu.ordermanagement.helper;

import java.util.List;

import com.sjsu.ordermanagement.model.Order;

public interface IOutputFileWriter {
	
	public void writeToFile(String error, List<Order> orders, String path);

}
