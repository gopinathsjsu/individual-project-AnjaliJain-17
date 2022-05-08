package com.sjsu.billing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.sjsu.billing.helper.GenericFileWriter;
import com.sjsu.billing.helper.InputFileReader;
import com.sjsu.billing.model.Item;
import com.sjsu.billing.model.Order;
import com.sjsu.billing.model.ValidationResult;
import com.sjsu.billing.repository.Inventory;
import com.sjsu.billing.validation.ValidationHandlerDriver;

public class Billing {

	public  static String inputFilePath = "";
	public  static String outputFilePath ="";
	public static void main(String[] args) {
		
	///Users/anjalijain/Desktop/SJSU/Programming/files/input.csv
		///Users/anjalijain/Desktop/SJSU/Programming/files
		List<Order> finalOrders = new ArrayList<>();
		captureFilePath();
		System.out.println("printing input file path"+inputFilePath);
		System.out.println("printing output file path"+outputFilePath);
		initializeInventoryDB();
		List<Order> orders = readInputFile(inputFilePath);
		ValidationHandlerDriver validator = new ValidationHandlerDriver();
		ValidationResult result = validator.v1.validate(orders);
		if(result.isValid()) {
			finalOrders = calculateOrderAmount(orders);
		}
		GenericFileWriter fw = new GenericFileWriter();
		System.out.println("Writing to output file");
		fw.write(result).writeToFile(result.getErrorMsg(),finalOrders,outputFilePath);
		System.out.println("Done");
			
	}
	
	private static void captureFilePath() {
		Scanner inScanner = new Scanner(System.in);
		System.out.print("Enter input file path and name");
		try {
		 inputFilePath = inScanner.nextLine();
		System.out.println("You entered: " + inputFilePath);           
		System.out.print("Enter output file path");
		 outputFilePath = inScanner.nextLine();
		System.out.println("You entered: " + outputFilePath);
		
		}catch (InputMismatchException e) {
            System.out.println("\tInvalid input entered. Please enter the specified input");
        }

		inScanner.close();
	}
	
	
	private static List<Order> calculateOrderAmount(List<Order> orders) {
		System.out.println("Calculating order amount");
		Inventory inventoryDb = Inventory.getInstance();
		for(Order o : orders) {
			inventoryDb.addCardNumberIfNotExists(o.getCardNumber());
			int qty = inventoryDb.getItem(o.getItemName().toLowerCase()).getQuantity();
			int priceperunit = (int) inventoryDb.getItem(o.getItemName().toLowerCase()).getPrice();
			int itemTotalPrice = priceperunit * o.getQuantity();
			o.setItemTotalPrice(itemTotalPrice);
			inventoryDb.getItem(o.getItemName().toLowerCase()).setQuantity(qty-o.getQuantity());
		}
		
		return orders;
	}
	
	private static List<Order> readInputFile(String filepath) {
		List<Order> orders = new ArrayList<>();
		try {
			orders= InputFileReader.readFile(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orders;
		
	}
	
	private static void initializeInventoryDB() {
				//Preloading inventory data
				System.out.println("Initializing inventory DB");
				Inventory inventory=Inventory.getInstance();
				inventory.addItem("clothes", new Item("Essentials","clothes",100,20));
				inventory.addItem("soap", new Item("Essentials","soap",200,5));
				inventory.addItem("shampoo", new Item("Essentials","shampoo",200,10));
				inventory.addItem("milk", new Item("Essentials","milk",100,5));
				inventory.addItem("perfume", new Item("Luxury","perfume",50,50));
				inventory.addItem("chocolates", new Item("Luxury","chocolates",300,3));
				inventory.addItem("handbag", new Item("Luxury","handbag",75,150));
				inventory.addItem("wallet", new Item("Luxury","wallet",100,100));
				inventory.addItem("bedsheet", new Item("Misc","bedsheet",150,75));
				inventory.addItem("footware", new Item("Misc","footware",200,25));
				inventory.addItem("homedecorpiece", new Item("Misc","homedecorpiece",100,40));
				inventory.addItem("pen", new Item("Misc","pen",400,3));
				inventory.addItem("pencil", new Item("Misc","pencil",400,3));
				//Preloading cardnumber data
				inventory.addCardNumberIfNotExists("5410000000000000");
				inventory.addCardNumberIfNotExists("4120000000000");
				inventory.addCardNumberIfNotExists("341000000000000");
				inventory.addCardNumberIfNotExists("6010000000000000");
				System.out.println("Initializing inventory DB - complete");
	}

}
