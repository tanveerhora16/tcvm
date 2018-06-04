package com.yash.tcvm.dao;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.domain.Container;
import com.yash.tcvm.exception.AlreadyExistException;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Ingrident;

public interface ContainerDao {

	List<Container> getContainers() throws FileNotFoundException, EmptyException;

	int insertContainer(Container container) throws EmptyException, FileNotFoundException, AlreadyExistException;

	Container getContainer(Ingrident ingrident) throws FileNotFoundException;

	int updateContainer(Container container)  throws EmptyException, FileNotFoundException;
}
