package com.excilys.computerDatabase.service;

import java.util.List;

import com.excilys.computerDatabase.model.Company;

public interface ICompanyService {

	/**
	 * get all companies from the database
	 * @return List<Company>, the list
	 */
	
	public List<Company> getAllCompanies() ;
	
	/**
	 * return a company from the database identified by the id
	 * @param id, the id of the company
	 * @return the company or null if no one have this id  
	 */
	public Company getCompany (long id);
		
}
