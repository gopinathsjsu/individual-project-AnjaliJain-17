package com.sjsu.billing.model;

import java.util.Objects;

public class Order {

	private String itemName;
	private int quantity;
	private String cardNumber;
	private int itemTotalPrice;
	public int getItemTotalPrice() {
		return itemTotalPrice;
	}
	public void setItemTotalPrice(int itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cardNumber, itemName, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(cardNumber, other.cardNumber) 
				&& Objects.equals(itemName, other.itemName) && quantity == other.quantity;
	}
	@Override
	public String toString() {
		return "Order [itemName=" + itemName + ", quantity=" + quantity + ", cardNumber="
				+ cardNumber + "]";
	}
	public Order(String itemName, int quantity, String cardNumber) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.cardNumber = cardNumber;
	}

	public Order(String itemName, int quantity, String cardNumber, int itemTotalPrice) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.cardNumber = cardNumber;
		this.itemTotalPrice=itemTotalPrice;
	}

	
	
}
