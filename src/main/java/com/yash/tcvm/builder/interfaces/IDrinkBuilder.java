package com.yash.tcvm.builder.interfaces;

import java.io.FileNotFoundException;

import com.yash.tcvm.config.interfaces.IDrinkConfigurer;
import com.yash.tcvm.domain.Order;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.EmptyException;

public interface IDrinkBuilder {
	void setDrinkConfigurer(IDrinkConfigurer drinkConfigurer);

	void prepareDrink(Order order) throws ContainerUnderflowException, FileNotFoundException, EmptyException;

	double getDrinkRate();
}
