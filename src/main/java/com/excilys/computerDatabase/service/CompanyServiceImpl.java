package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.CompanyDAO;

public class CompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    /**
     */
    public CompanyServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Company> getCompany(long id) {

        Optional<Company> company = companyDAO.find(id);
        return company;
    }

    public List<Company> getAllCompanies() {

        List<Company> listCompany = companyDAO.findAll();
        return listCompany;
    }

}
