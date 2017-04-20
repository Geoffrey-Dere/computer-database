package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.dto.CompanyDTO;

public interface CompanyService {

    /**
     * get all companies from the database.
     * @return the pager of company
     */
    List<CompanyDTO> getAllCompanies();

    /**
     * return a company from the database identified by the id.
     * @param id the id of the company
     * @return the company or null if no one have this id
     */
    Optional<CompanyDTO> getCompany(long id);

}
