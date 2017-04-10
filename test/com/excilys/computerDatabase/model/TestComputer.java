package com.excilys.computerDatabase.model;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.computerDatabase.exception.DateException;

public class TestComputer {

	public TestComputer() {
		// TODO Auto-generated constructor stub
	}


	@Test(expected=DateException.class)
	public void createComputerDateGreater() throws DateException{

		LocalDate introduced = LocalDate.of(2020, 02, 02);
		LocalDate discontinued = LocalDate.of(2010, 02, 02);

		Computer computer = new Computer(1, "name", introduced, discontinued);				
	}


	@Test(expected=DateException.class)
	public void createComputerIntroducedNull() throws DateException{
		
		LocalDate discontinued = LocalDate.of(2010, 02, 02);
		
		Computer computer = new Computer(1, "name", null, discontinued);
		
	}
	
	

}
