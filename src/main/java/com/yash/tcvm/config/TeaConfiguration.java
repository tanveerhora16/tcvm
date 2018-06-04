package com.yash.tcvm.config;

import java.util.HashMap;
import java.util.Map;

import com.yash.tcvm.config.interfaces.AbstractDrinkConfigurer;
import com.yash.tcvm.config.interfaces.IDrinkConfigurer;
import com.yash.tcvm.literals.Drink;
import com.yash.tcvm.literals.Ingrident;

/**
 * This is the Configuration class for Tea, in this we defined the
 * material required in making tea.
 * 
 * @author tanveer.hora
 *
 */

public class TeaConfiguration extends AbstractDrinkConfigurer {

	private static IDrinkConfigurer drinkConfigurer;

	public static final double WATER_CONSUMPTION = 60;
	public static final double SUGAR_CONSUMPTION = 15;
	public static final double MILK_CONSUMPTION = 40;
	public static final double TEA_CONSUMPTION = 5;

	public static final double WATER_WASTAGE = 5;
	public static final double SUGAR_WASTAGE = 2;
	public static final double MILK_WASTAGE = 4;
	public static final double TEA_WASTAGE = 1;

	public static final double RATE = 10;

	private TeaConfiguration() {
		// TODO Auto-generated constructor stub
	}

	static {
		drinkConfigurer = new TeaConfiguration();
	}

	public static IDrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	@Override
	public void configIngredientConsumption() {
		Map<Ingrident, Double> ingredientsConsumption = new HashMap<Ingrident, Double>();
		ingredientsConsumption.put(Ingrident.TEA, TEA_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.MILK, MILK_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.WATER, WATER_CONSUMPTION);
		ingredientsConsumption.put(Ingrident.SUGAR, MILK_CONSUMPTION);

		setIngredientConsumption(ingredientsConsumption);
	}

	@Override
	public void configIngredientWastage() {
		Map<Ingrident, Double> ingredientsWastage = new HashMap<Ingrident, Double>();
		ingredientsWastage.put(Ingrident.TEA, TEA_WASTAGE);
		ingredientsWastage.put(Ingrident.MILK, MILK_WASTAGE);
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
