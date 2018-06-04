package com.yash.tcvm.serviceimpl;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.dao.ContainerDao;
import com.yash.tcvm.domain.Container;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Ingrident;
import com.yash.tcvm.service.ContainerService;

/**
 * This is the implementation class of Container Service Interface, this class
 * contains all the methods related to containers of vending machine.
 * 
 * @author tanveer.hora
 *
 */
public class ContainerServiceImpl implements ContainerService {

	private ContainerDao containerDao;

	public ContainerServiceImpl(ContainerDao containerDao) {
		this.containerDao = containerDao;
	}

	/**
	 * This method return the list of available containers.
	 */

	@Override
	public List<Container> getContainers() throws FileNotFoundException, EmptyException {
		List<Container> containers = containerDao.getContainers();
		if (containers == null) {
			throw new NullPointerException("Container's list is null");
		}

		if (containers.isEmpty()) {
			throw new EmptyException("Container's list is empty");
		}
		return containers;
	}

	/**
	 * This method return the container based on the Ingrident selected.
	 */

	@Override
	public Container getContainer(Ingrident ingrident) throws FileNotFoundException {

		if (ingrident == null) {
			throw new NullPointerException("Ingredient is null");
		}
		Container container = containerDao.getContainer(ingrident);
		return container;
	}

	/**
	 * This method is used to update the container object.
	 */
	@Override
	public int updateContainer(Container container) throws FileNotFoundException, EmptyException {
		int rowsAffected = 0;
		if (container == null) {
			throw new NullPointerException("Container cannot be null");
		}
		rowsAffected = containerDao.updateContainer(container);
		return rowsAffected;
	}

}
