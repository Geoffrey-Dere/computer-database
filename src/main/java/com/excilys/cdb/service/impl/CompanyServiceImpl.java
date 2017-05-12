package com.excilys.cdb.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private ComputerDAO computerDAO;

    @Override
    public Optional<Company> get(long id) {

        Optional<Company> company = companyDAO.find(id);
        return company;
    }

    /**
     * @return list of companies
     */
    public List<Company> getAll() {

        List<Company> listCompany = companyDAO.findAll();
        return listCompany;
    }

    /**
     * @param company company
     * @return success
     * @throws SQLException exception
     */
    @Transactional
    public boolean removeCascade(Company company) throws SQLException {

        if (companyDAO.delete(company)) {
            computerDAO.deleteByCompany(company.getId());
        }
        return true;
    }
}
