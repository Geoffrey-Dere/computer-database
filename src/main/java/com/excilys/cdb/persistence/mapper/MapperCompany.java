package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDAO;

public class MapperCompany implements RowMapper<Company> {


    /**
     * @param res the resultset
     * @return the company
     * @throws SQLException the sql exception
     */
    public static Company mapperCompany(ResultSet res) throws SQLException {

        long id = res.getLong(CompanyDAO.ID);
        String name = res.getString(CompanyDAO.NAME);

        return new Company.Builder(name).id(id).build();
    }

    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong(CompanyDAO.ID);
        String name = rs.getString(CompanyDAO.NAME);
        return new Company.Builder(name).id(id).build();
    }

}
