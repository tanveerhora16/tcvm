package com.yash.tcvm.dao;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.domain.Order;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Drink;

public interface OrderDao {

    List<Order> getOrders() throws FileNotFoundException, EmptyException;
	
	int insertOrder(Order order) throws EmptyException, FileNotFoundException;

	List<Order> getOrdersByDrink(Drink drink) throws FileNotFoundException;
}
