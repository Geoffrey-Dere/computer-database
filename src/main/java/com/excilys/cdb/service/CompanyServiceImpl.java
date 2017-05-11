package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;
import com.excilys.cdb.persistence.ConnectionManager;
import com.excilys.cdb.persistence.ExceptionDAO;

public class CompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO = CompanyDAO.INSTANCE;

    /**
     */
    public CompanyServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<Company> get(long id) {

        Optional<Company> company = companyDAO.find(id);
        return company;
    }

    public List<Company> getAll() {

        List<Company> listCompany = companyDAO.findAll();
        return listCompany;
    }

    public boolean removeCascade(Company company) throws SQLException {

        Connection connection = ConnectionManager.INSTANCE.getConnection();

        try {
            connection.setAutoCommit(false);
            new ComputerServiceImpl().removeByCompanyId(company.getId());
            companyDAO.delete(company);
            connection.commit();
            return true ;
        } catch (SQLException | ExceptionDAO e) {
            e.printStackTrace();
            connection.rollback();
        } finally{
        	connection.close();
        }
        return false;
    }
}
