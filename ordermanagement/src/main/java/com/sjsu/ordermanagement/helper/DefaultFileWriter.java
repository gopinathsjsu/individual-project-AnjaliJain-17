package com.sjsu.ordermanagement.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.sjsu.ordermanagement.model.Order;

public class DefaultFileWriter implements IOutputFileWriter {


	@Override
	public void writeToFile(String error, List<Order> orders, String path) {
		
		  File file = new File(path+"/output.txt");
		  try {
			FileWriter fw = new FileWriter(file);
			fw.write(error);
			fw.close();
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}		
	}

}
