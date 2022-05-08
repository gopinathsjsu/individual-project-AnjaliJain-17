package com.sjsu.ordermanagement.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.sjsu.ordermanagement.model.Order;

public class InputFileReader {
	
	public static List<Order> readFile(String filepath) throws IOException {
		
		List<Order> orders = new ArrayList<>();
		File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            String itemName = fields[0];
            String quantity = fields[1];
            String cardNumber = fields[2];
            Order order = new Order(itemName, Integer.valueOf(quantity), cardNumber);
            orders.add(order);
        }
        br.close();
        return orders;
	}

}
