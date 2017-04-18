package com.excilys.computerDatabase.service;

import java.util.Optional;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.persistence.CompanyDAO;

public class CompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO = new CompanyDAO();

    /**
     */
    public CompanyServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Pager<Company> getAllCompanies() {
        return new Pager.BuilderPage<Company>(companyDAO.findAll()).build();
    }

    @Override
    public Optional<Company> getCompany(long id) {
        return companyDAO.find(id);
    }

}
