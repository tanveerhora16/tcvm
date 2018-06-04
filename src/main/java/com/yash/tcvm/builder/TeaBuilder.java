package com.yash.tcvm.builder;

import java.io.FileNotFoundException;

import com.yash.tcvm.builder.interfaces.AbstractDrinkBuilder;
import com.yash.tcvm.builder.interfaces.IDrinkBuilder;
import com.yash.tcvm.config.TeaConfiguration;
import com.yash.tcvm.domain.Order;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Drink;

public class TeaBuilder extends AbstractDrinkBuilder{

	public TeaBuilder() {
		setDrinkConfigurer(TeaConfiguration.getDrinkConfigurer());
	}

	@Override
	public void prepareDrink(Order order) throws ContainerUnderflowException, FileNotFoundException, EmptyException {
		if (order.getDrink() == Drink.TEA) {
			super.prepareDrink(order);
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.TEA + " and found " + order.getDrink());
		}
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new TeaBuilder();
	}

	@Override
	public double getDrinkRate() {
		return super.getDrinkRate();
	}
}
