package com.excilys.computerDatabase.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestCompany {

	private Company company ;
	
	@Before
	public void setUp(){
	//	company = new Company(2, "name");
	}
	
	@Test
	public void testEqualWrongName() {
	//	assertNotEquals(company, new Company(2, "name2"));
	}
	
	@Test
	public void testEqualWrongId(){
	//	assertNotEquals(company, new Company(1, "name2"));
		
	}
	
	@Test
	public void testEqual(){
	//	assertEquals(company, new Company(2, "name"));
	}

}
