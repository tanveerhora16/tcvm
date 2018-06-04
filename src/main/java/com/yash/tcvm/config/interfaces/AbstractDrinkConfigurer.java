package com.yash.tcvm.config.interfaces;

import java.util.Map;


import com.yash.tcvm.literals.Drink;
import com.yash.tcvm.literals.Ingrident;

public abstract class AbstractDrinkConfigurer implements IDrinkConfigurer {

	private Map<Ingrident, Double> ingredientConsumption;

	private Map<Ingrident, Double> ingredientWastage;

	private double drinkRate;

	private Drink drinkType;

	public AbstractDrinkConfigurer() {
		initDrinkConfig();
	}

	private void initDrinkConfig() {

		configIngredientConsumption();

		configIngredientWastage();

		configDrinkType();

		configDrinkRate();

	}

	public Map<Ingrident, Double> getIngredientConsumption() {
		return ingredientConsumption;
	}

	public void setIngredientConsumption(Map<Ingrident, Double> ingredientConsumption) {
		this.ingredientConsumption = ingredientConsumption;
	}

	public Map<Ingrident, Double> getIngredientWastage() {
		return ingredientWastage;
	}

	public void setIngredientWastage(Map<Ingrident, Double> ingredientWastage) {
		this.ingredientWastage = ingredientWastage;
	}

	public double getDrinkRate() {
		return drinkRate;
	}

	public void setDrinkRate(double drinkRate) {
		this.drinkRate = drinkRate;
	}

	public Drink getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(Drink drinkType) {
		this.drinkType = drinkType;
	}
	
	public static IDrinkConfigurer getDrinkConfigurer(){
		// to override according to implementation
		return null;
	}

}
