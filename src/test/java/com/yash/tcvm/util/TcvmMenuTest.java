package com.yash.tcvm.util;

import org.junit.Test;

import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.exception.FilePathIsNullException;

public class TcvmMenuTest {

	public class OperatorMenuOptionsTest {

		@Test(expected = FilePathIsNullException.class)
		public void getOperatorMenuOptions_shouldThrowFileNameIsNullException_when_filePathIsNull() {
			String filepath = null;
			TcvmMenu.getTcvmMenu(filepath);

		}

		@Test(expected = EmptyException.class)
		public void getMenu_shouldThrowEmptyException_whenFileLocationIsEmpty() {
			TcvmMenu.getTcvmMenu(" ");
		}

		@Test
		public void getOperatorMenuOptions_shouldThrowEmptyException_whenFileIsEmpty() {
			String filepath = "/src/main/resources/file/TcvmMenu.txt";
			TcvmMenu.getTcvmMenu(filepath);

		}
	}

}
