package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Computer;

public interface ComputerService {

	/**
	 * get all computers from the database
	 * @return the list
	 */	
	public List<Computer> getAllComputers() ;
	
	/**
	 * return a computer from the database identified by the id
	 * @param id, the id
	 * @return the computer or null if no one have this id  
	 */
	public Optional<Computer> getComputer (long id);
	
	
	/**
	 * Add a computer on the database 
	 * @param c, the computer
	 * @return true if the computer has been added and false otherwise
	 */
	public boolean addComputer(Computer c);
	
	
	
	/**
	 * update a computer on the database 
	 * @param c, the computer
	 * @return true if the computer has been updated and false otherwise
	 */
	public boolean updateComputer(Computer c);
	
	
	/**
	 * delete a computer on the database 
	 * @param c, the computer
	 * @return true if the computer has been updated and false otherwise
	 */
	public boolean removeComputer(Computer c);
	
}
