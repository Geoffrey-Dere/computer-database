package com.excilys.computerDatabase.service;


import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.DaoFactory;

public class CompanyService implements ICompanyService {

	public CompanyService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Company> getAllCompanies() {
			DaoFactory df = DaoFactory.getDaoFactory();
			return df.getCompanyDAO().findAll();
	}

	@Override
	public Optional<Company> getCompany(long id) {	
		return DaoFactory.getDaoFactory().getCompanyDAO().find(id);
	}

}
