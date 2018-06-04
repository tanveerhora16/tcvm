package com.yash.tcvm.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yash.tcvm.builder.BlackCoffeeBuilder;
import com.yash.tcvm.builder.BlackTeaBuilder;
import com.yash.tcvm.builder.CoffeeBuilder;
import com.yash.tcvm.builder.TeaBuilder;
import com.yash.tcvm.builder.interfaces.IDrinkBuilder;
import com.yash.tcvm.dao.ContainerDao;
import com.yash.tcvm.dao.OrderDao;
import com.yash.tcvm.daoimpl.ContainerDaoImpl;
import com.yash.tcvm.daoimpl.OrderDaoImpl;
import com.yash.tcvm.domain.Container;
import com.yash.tcvm.domain.Order;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Drink;
import com.yash.tcvm.literals.ExcelSheetConstants;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.service.OrderService;
import com.yash.tcvm.serviceimpl.ContainerServiceImpl;
import com.yash.tcvm.serviceimpl.OrderServiceImpl;
import com.yash.tcvm.util.ExcelUtil;
import com.yash.tcvm.util.TcvmMenu;

/**
 * This class contains all the menu which displays to the user.
 * 
 * @author tanveer.hora
 *
 */

public class TCVM {

	private static TCVM tcvm;
	private ContainerDao containerDao;
	private ContainerService containerService;
	private OrderService orderService;
	private OrderDao orderDao;

	private TCVM() {

		containerDao = new ContainerDaoImpl();
		containerService = new ContainerServiceImpl(containerDao);

		orderDao = new OrderDaoImpl();
		orderService = new OrderServiceImpl(orderDao);
	}

	public static TCVM getTCVM() {
		tcvm = new TCVM();
		return tcvm;
	}

	public void showMenu() throws EmptyException, IOException {
		String filepath = "src/main/resources/file/TcvmMenu.txt";
		TcvmMenu tcvmMenu = new TcvmMenu();
		Scanner inputScanner = new Scanner(System.in);
		String continueChoice;
		do {
			tcvmMenu.getTcvmMenu(filepath);
			System.out.println("Enter your choice ");
			int choice = inputScanner.nextInt();
			switch (choice) {
			case 1:
				makeCoffee();
				break;
			case 2:
				makeTea();
				break;
			case 3:
				makeBlackCoffee();
				break;
			case 4:
				makeBlackTea();
				break;
			case 5:
				refillcontainer();
				break;
			case 6:
				checkTotalSale();
				break;
			case 7:
				containerStatus();
			case 8:
				showReports();
				break;
			case 9:
				inputScanner.nextLine();
				System.out.println("exit");
				break;
			}
			System.out.println("Do you want to Continue (Y/N)");
			continueChoice = inputScanner.next();
		} while (continueChoice.equalsIgnoreCase("y"));

	}

	private void showReports() throws EmptyException, IOException {
		System.out.println("Container Status Report");
		generateContainerStatusReport();
	}

	private void generateContainerStatusReport() throws EmptyException, IOException {

		List<String> columns = new ArrayList<String>();
		columns.add("Ingredient");
		columns.add("Maximum capacity");
		columns.add("Current Availability");

		List<Container> containers = containerService.getContainers();
		List<Object[]> data = new ArrayList<>();
		for (Container container : containers) {
			Object[] datum = new Object[3];
			datum[0] = container.getIngredient();
			datum[1] = container.getMaxCapacity();
			datum[2] = container.getCurrentAvailability();
			data.add(datum);
		}

		ExcelUtil.createExcelSheet(columns, "Container's status", ExcelSheetConstants.CONTAINER_STATUS_REPORT, data);
		System.out.println(ExcelSheetConstants.CONTAINER_STATUS_REPORT + " generated successfully!");
	}

	private void makeCoffee() {
		System.out.println("");
		IDrinkBuilder drinkBuilder = CoffeeBuilder.getDrinkBuilder();
		makeDrink(drinkBuilder, Drink.COFFEE);
		System.out.println(" Your Order Is Ready! ");
	}

	private void makeTea() {
		IDrinkBuilder drinkBuilder = TeaBuilder.getDrinkBuilder();
		makeDrink(drinkBuilder, Drink.TEA);
		System.out.println(" Your Order Is Ready! ");
	}

	private void makeDrink(IDrinkBuilder drinkBuilder, Drink drink) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter no of cups: ");
		int qtyOrdered = scanner.nextInt();

		Order order = new Order();
		order.setDrink(drink);
		order.setQuantity(qtyOrdered);

		try {
			drinkBuilder.prepareDrink(order);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ContainerUnderflowException e) {
			System.out.println("Not enough ingredients");
			e.printStackTrace();
		} catch (EmptyException e) {
			e.printStackTrace();
			
		}
	}

	private void makeBlackCoffee() {
		IDrinkBuilder drinkBuilder = BlackCoffeeBuilder.getDrinkBuilder();
		makeDrink(drinkBuilder, Drink.BLACK_COFFEE);
		System.out.println(" Your Order Is Ready! ");
	}

	private void makeBlackTea() {
		IDrinkBuilder drinkBuilder = BlackTeaBuilder.getDrinkBuilder();
		makeDrink(drinkBuilder, Drink.BLACK_TEA);
		System.out.println(" Your Order Is Ready! ");
	}

	private void refillcontainer() {
		System.out.println("Your Containers Are Refilled");
		List<Container> containers = null;
		try {
			containers = containerService.getContainers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EmptyException e) {
			e.printStackTrace();
		}
		for (Container container : containers) {
			container.setCurrentAvailability(container.getMaxCapacity());
			try {
				containerService.updateContainer(container);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (EmptyException e) {
				e.printStackTrace();
			}
		}
		containerStatus();
	}

	private void checkTotalSale() {
		System.out.println(" TOTAL SALE ");
		calculateSaleOfDrink(TeaBuilder.getDrinkBuilder(), Drink.TEA);
		calculateSaleOfDrink(CoffeeBuilder.getDrinkBuilder(), Drink.COFFEE);
		calculateSaleOfDrink(BlackTeaBuilder.getDrinkBuilder(), Drink.BLACK_TEA);
		calculateSaleOfDrink(BlackCoffeeBuilder.getDrinkBuilder(), Drink.BLACK_COFFEE);
	}

	private void calculateSaleOfDrink(IDrinkBuilder drinkBuilder, Drink drink) {

		double price = drinkBuilder.getDrinkRate();
		double totalSale = 0;
		List<Order> orders = null;

		try {
			orders = orderService.getOrdersByDrink(drink);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EmptyException e) {
			e.printStackTrace();
		}
		for (Order order : orders) {
			totalSale = totalSale + (order.getQuantity() * price);
		}
		System.out.println("Drink: " + drink + " --- Total sale: Rs." + totalSale);
	}

	private void containerStatus() {
		System.out.println(" CONTAINERS STATUS ");
		List<Container> containers = null;
		try {
			containers = containerService.getContainers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EmptyException e) {
			e.printStackTrace();
		}
		for (Container container : containers) {
			System.out.println(container.getIngredient() + " CONTAINER");
			System.out.println("Max capacity: " + container.getMaxCapacity());
			System.out.println("Current availability: " + container.getCurrentAvailability() + "\n");
		}
	}

}
