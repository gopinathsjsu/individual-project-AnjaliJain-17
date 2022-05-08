package com.sjsu.ordermanagement.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.sjsu.ordermanagement.model.Order;

public class CsvFileWriter implements IOutputFileWriter {

	@Override
	public void writeToFile(String error, List<Order> orders, String path) {
		// TODO Auto-generated method stub
		File csvFile = new File(path);
		try {
			FileWriter csvWriter = new FileWriter(csvFile+"/output.csv");
			
			csvWriter.append("Item").append(",").append("Quantity").append(",").append("Price").append(",").append("TotalPrice").append("\n");
			int totalPrice = 0;
			boolean istotalPriceWritten = false;
			for(Order o : orders) {
				totalPrice+=o.getItemTotalPrice();
			}
			for(Order o : orders) {
				csvWriter.append(o.getItemName()).append(",");
				csvWriter.append(String.valueOf(o.getQuantity())).append(",");
				csvWriter.append(String.valueOf(o.getItemTotalPrice()));	
				if(!istotalPriceWritten) {
					csvWriter.append(",").append(String.valueOf(totalPrice));
					istotalPriceWritten=true;
				}
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
