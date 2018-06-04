package com.yash.tcvm.config;

import java.util.HashMap;
import java.util.Map;

import com.yash.tcvm.config.interfaces.AbstractDrinkConfigurer;
import com.yash.tcvm.config.interfaces.IDrinkConfigurer;
import com.yash.tcvm.literals.Drink;
import com.yash.tcvm.literals.Ingrident;

/**
 * This is the Configuration class for Coffee, in this we defined the
 * material required in making coffee.
 * 
 * @author tanveer.hora
 *
 */

public class CoffeeConfiguration extends AbstractDrinkConfigurer {

	private static IDrinkConfigurer drinkConfigurer;

	public static final double WATER_CONSUMPTION = 20;
	public static final double SUGAR_CONSUMPTION = 15;
	public static final double MILK_CONSUMPTION = 80;
	public static final double COFFEE_CONSUMPTION  = 4;

	public static final double WATER_WASTAGE = 3;
	public static final double SUGAR_WASTAGE = 2;
	public static final double MILK_WASTAGE = 8;
	public static final double COFFEE_WASTAGE = 3;

	public static final double RATE = 15;

	private CoffeeConfiguration() {
		// TODO Auto-generated constructor stub
	}

	static {
		drinkConfigurer = new CoffeeConfiguration();
	}
	
	public static IDrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<Ingrident, Double> ingredientsConsumption = new HashMap<Ingrident, Double>();

		ingredientsConsumption.put(Ingrident.COFFEE, COFFEE_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.MILK, MILK_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.WATER, WATER_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.SUGAR, MILK_CONSUMPTION);

		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<Ingrident, Double> ingredientsWastage = new HashMap<Ingrident, Double>();

		ingredientsWastage.put(Ingrident.COFFEE, COFFEE_WASTAGE);
		ingredientsWastage.put(Ingrident.MILK, MILK_WASTAGE);
		ingredientsWastage.put(Ingrident.WATER, WATER_WASTAGE);
		ingredientsWastage.put(Ingrident.SUGAR, SUGAR_WASTAGE);

		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.TEA);
	}

	public void configDrinkRate() {
		setDrinkRate(RATE);
	}

}
