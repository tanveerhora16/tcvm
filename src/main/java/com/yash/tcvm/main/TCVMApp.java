package com.yash.tcvm.main;

import java.io.IOException;

import com.yash.tcvm.exception.EmptyException;

public class TCVMApp {
	/**
	 * This is the main class from where application starts.
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws EmptyException 
	 */

	public static void main(String[] args) throws EmptyException, IOException {

		TCVM.getTCVM().showMenu();
	}

}
