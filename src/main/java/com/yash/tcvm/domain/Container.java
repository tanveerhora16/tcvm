package com.yash.tcvm.domain;

import com.yash.tcvm.literals.Ingrident;

public class Container {

	private Ingrident ingredient;

	private double maxCapacity;

	private double currentAvailability;

	public Container() {

	}

	public Container(Ingrident ingredient, double maxCapacity, double currentAvailability) {
		super();
		this.ingredient = ingredient;
		this.maxCapacity = maxCapacity;
		this.currentAvailability = currentAvailability;
	}

	public Ingrident getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingrident ingredient) {
		this.ingredient = ingredient;
	}

	public double getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(double maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getCurrentAvailability() {
		return currentAvailability;
	}

	public void setCurrentAvailability(double currentAvailability) {
		this.currentAvailability = currentAvailability;
	}

}
