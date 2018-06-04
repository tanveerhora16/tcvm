package com.yash.tcvm.daoimpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.yash.tcvm.dao.ContainerDao;
import com.yash.tcvm.domain.Container;
import com.yash.tcvm.exception.AlreadyExistException;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Ingrident;
import com.yash.tcvm.literals.JSONFileConstants;
import com.yash.tcvm.util.JSONUtil;

/**
 * This is the implementation class of Container Dao Interface
 * @author tanveer.hora
 *
 */
public class ContainerDaoImpl implements ContainerDao{

	public List<Container> getContainers() throws FileNotFoundException, EmptyException {
		
		List<Container> containers = new ArrayList<>();
		List<?> containerList = JSONUtil.readJSONFromFile(JSONFileConstants.JSON_FILE_PATH,
				JSONFileConstants.CONTAINER_JSON_FILE_NAME);
		for (Object container : containerList) {
			containers.add(JSONUtil.mapToModelObject(Container.class, container));
		}
		return containers;
	}

	public int insertContainer(Container container)
			throws FileNotFoundException, EmptyException, AlreadyExistException {
		int rowsAffected = 0;
		if (container == null) {
			throw new NullPointerException();
		}
		if (getContainer(container.getIngredient()) != null) {
			throw new AlreadyExistException("Container already exist");
		}
		List<Container> containers = null;
		try {
			containers = getContainers();
		} catch (EmptyException e) {
			containers = null;
		}
		if (containers == null) {
			containers = new ArrayList<>();
			containers.add(container);
		} else {
			containers.add(container);
		}
		JSONUtil.writeJSONToFile(containers, JSONFileConstants.JSON_FILE_PATH,
				JSONFileConstants.CONTAINER_JSON_FILE_NAME);
		rowsAffected = 1;
		return rowsAffected;
	}

	public Container getContainer(Ingrident ingrident) throws FileNotFoundException {
		Container selectedContainer = null;
		List<Container> containers = null;
		try {
			containers = getContainers();
		} catch (EmptyException e) {
			containers = null;
		}
		if (containers != null || containers.size() > 0) {
			for (Container container : containers) {
				if (container.getIngredient() == ingrident) {
					selectedContainer = container;
					break;
				}
			}

		}
		return selectedContainer;
	}

	public int updateContainer(Container existingContainer) throws EmptyException, FileNotFoundException {
		int rowsAffected = 0;
		if (existingContainer == null) {
			throw new NullPointerException("Container is null");
		}
		if (getContainer(existingContainer.getIngredient()) == null) {
			throw new NullPointerException("Container doesn't exist");
		}
		List<Container> containers = null;
		try {
			containers = getContainers();
		} catch (EmptyException e) {
			containers = null;
		}
		for (Container container : containers) {
			if (container.getIngredient() == existingContainer.getIngredient()) {
				container.setCurrentAvailability(existingContainer.getCurrentAvailability());
			}
		}
		JSONUtil.writeJSONToFile(containers, JSONFileConstants.JSON_FILE_PATH,
				JSONFileConstants.CONTAINER_JSON_FILE_NAME);
		rowsAffected = 1;
		return rowsAffected;
	}


}
