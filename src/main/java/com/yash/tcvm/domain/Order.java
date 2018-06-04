package com.yash.tcvm.domain;

import java.util.Date;

import com.yash.tcvm.literals.Drink;



public class Order {

	private int quantity;

	private Drink drink;


	private Boolean status;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(int quantity, Drink drink,  Boolean status) {
		super();
		this.quantity = quantity;
		this.drink = drink;
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
