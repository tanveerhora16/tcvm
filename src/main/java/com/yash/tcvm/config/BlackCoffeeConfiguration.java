package com.yash.tcvm.config;

import java.util.HashMap;
import java.util.Map;

import com.yash.tcvm.config.interfaces.AbstractDrinkConfigurer;
import com.yash.tcvm.config.interfaces.IDrinkConfigurer;
import com.yash.tcvm.literals.Drink;
import com.yash.tcvm.literals.Ingrident;

/**
 * This is the Configuration class for Black Coffee, in this we defined the
 * material required in making black coffee.
 * 
 * @author tanveer.hora
 *
 */

public class BlackCoffeeConfiguration extends AbstractDrinkConfigurer {

	private static IDrinkConfigurer drinkConfigurer;

	public static final double WATER_CONSUMPTION = 100;
	public static final double SUGAR_CONSUMPTION = 15;
	public static final double COFFEE_CONSUMPTION = 3;

	public static final double WATER_WASTAGE = 12;
	public static final double SUGAR_WASTAGE = 2;
	public static final double COFFEE_WASTAGE = 0;

	public static final double RATE = 10;

	private BlackCoffeeConfiguration() {
		// TODO Auto-generated constructor stub
	}

	static {
		drinkConfigurer = new BlackCoffeeConfiguration();
	}

	public static IDrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	@Override
	public void configIngredientConsumption() {
		Map<Ingrident, Double> ingredientsConsumption = new HashMap<Ingrident, Double>();
		ingredientsConsumption.put(Ingrident.COFFEE, COFFEE_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.WATER, WATER_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.SUGAR, SUGAR_CONSUMPTION);

		setIngredientConsumption(ingredientsConsumption);
	}

	@Override
	public void configIngredientWastage() {
		Map<Ingrident, Double> ingredientsWastage = new HashMap<Ingrident, Double>();
		ingredientsWastage.put(Ingrident.COFFEE, COFFEE_WASTAGE);
		ingredientsWastage.put(Ingrident.WATER, WATER_WASTAGE);
		ingredientsWastage.put(Ingrident.SUGAR, SUGAR_WASTAGE);

		setIngredientWastage(ingredientsWastage);

	}

	@Override
	public void configDrinkType() {
		setDrinkType(Drink.TEA);

	}

	@Override
	public void configDrinkRate() {
		setDrinkRate(RATE);

	}

}
