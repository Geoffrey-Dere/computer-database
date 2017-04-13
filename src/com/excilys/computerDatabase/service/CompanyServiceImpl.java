package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.CompanyDAO;

public class CompanyServiceImpl implements CompanyService {

	private CompanyDAO companyDAO = new CompanyDAO();

	public CompanyServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyDAO.findAll();
	}

	@Override
	public Optional<Company> getCompany(long id) {
		return companyDAO.find(id);
	}

}
