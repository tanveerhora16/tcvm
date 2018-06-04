package com.yash.tcvm.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.tcvm.domain.Container;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Ingrident;

public interface ContainerService {

	List<Container> getContainers() throws FileNotFoundException, EmptyException;

	Container getContainer(Ingrident ingrident) throws FileNotFoundException;

	int updateContainer(Container container) throws FileNotFoundException, EmptyException;
}
