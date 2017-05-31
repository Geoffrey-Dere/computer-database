package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyDAO extends DAO<Company> {

    List<Company> findAll();

}
