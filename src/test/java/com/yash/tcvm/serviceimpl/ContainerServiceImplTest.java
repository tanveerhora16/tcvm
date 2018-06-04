package com.yash.tcvm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.yash.tcvm.dao.ContainerDao;
import com.yash.tcvm.domain.Container;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.literals.Ingrident;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImplTest {

	@Mock
	private ContainerDao containerDao;
	private ContainerService containerService;
	private Container container;

	@Before
	public void init() {
		containerService = new ContainerServiceImpl(containerDao);
	}

	@Test
	public void getContainers_ShouldReturnContainerListSize() throws FileNotFoundException, EmptyException {
		List<Container> containers = new ArrayList<>();
		containers.add(new Container(Ingrident.MILK, 12000, 12500));
		when(containerDao.getContainers()).thenReturn(containers);
		assertEquals(1, containerService.getContainers().size());
	}

	@Test(expected = NullPointerException.class)
	public void getContainers_ShouldThrowNullPointerException_WhenContainerListIsNull()
			throws FileNotFoundException, EmptyException {
		List<Container> containers = null;
		when(containerDao.getContainers()).thenReturn(containers);
		containerService.getContainers();
	}

	@Test(expected = EmptyException.class)
	public void getContainers_ShouldThrowEmptyException_WhenContainerListIsEmpty()
			throws FileNotFoundException, EmptyException {
		List<Container> containers = new ArrayList<>();
		when(containerDao.getContainers()).thenReturn(containers);
		containerService.getContainers();
	}

	@Test
	public void getContainer_ShouldReturnContainerObject_WhenIngridentIsGivenAndContainerObjectIsNotNull()
			throws FileNotFoundException {
		container = new Container(Ingrident.MILK, 10000, 15000);
		when(containerDao.getContainer(Ingrident.MILK)).thenReturn(container);
		assertTrue(containerService.getContainer(Ingrident.MILK) != null);
	}

	@Test(expected = NullPointerException.class)
	public void getContainer_ShouldThrowException_WhenIngridentIsNull() throws FileNotFoundException {
		containerService.getContainer(null);
	}

	@Test
	public void updateContainer_ShouldReturnOne_WhenContainerObjectIsGivenAndContainerIsPresent()
			throws FileNotFoundException, EmptyException {
		container = new Container(Ingrident.MILK, 10000, 15000);
		when(containerDao.updateContainer(container)).thenReturn(1);
		assertEquals(1, containerService.updateContainer(container));
	}

	@Test(expected = NullPointerException.class)
	public void updateContainer_ShouldThrowException_WhenContainerObjectIsNull()
			throws FileNotFoundException, EmptyException {
		container = null;
		when(containerDao.updateContainer(container)).thenReturn(1);
		containerService.updateContainer(container);
	}

}
