package com.sjsu.billing.model;

import java.util.Objects;

public class Item {
	
	private String category;
	private String item;
	private int quantity;
	private double price;
	
	public Item(String category, String item, int quantity, double price) {
		super();
		this.category = category;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, item, price, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(category, other.category) && Objects.equals(item, other.item)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && quantity == other.quantity;
	}


	@Override
	public String toString() {
		return "Item [category=" + category + ", item=" + item + ", quantity=" + quantity + ", price=" + price + "]";
	}

	

}
