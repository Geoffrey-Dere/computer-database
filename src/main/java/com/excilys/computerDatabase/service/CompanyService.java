package com.excilys.computerDatabase.service;

import java.util.Optional;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Pager;

public interface CompanyService {

    /**
     * get all companies from the database.
     * @return the pager of company
     */
    Pager<Company> getAllCompanies();

    /**
     * return a company from the database identified by the id.
     * @param id the id of the company
     * @return the company or null if no one have this id
     */
    Optional<Company> getCompany(long id);

}
