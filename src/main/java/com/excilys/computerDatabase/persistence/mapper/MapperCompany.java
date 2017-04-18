package com.excilys.computerDatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Company.BuilderCompany;

public abstract class MapperCompany {

    public static final String ID = "Company.id";
    public static final String NAME = "Company.name";

    /**
     * @param res the resultset
     * @return the company
     * @throws SQLException the sql exception
     */
    public static Company mapperCompany(ResultSet res) throws SQLException {

        long id = res.getLong(ID);
        String name = res.getString(NAME);

        return new BuilderCompany(name).id(id).build();
    }

}
