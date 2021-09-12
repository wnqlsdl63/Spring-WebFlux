package com.jubin.springboot.reactive.entity;

public class CartItem {

	private Item item;
	private int quantity;

	private CartItem() {
	}

	public CartItem(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
