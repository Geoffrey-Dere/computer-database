package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Computer.BuilderComputer;

public abstract class MapperComputer {

    private static final String ID = "Computer.id";
    private static final String NAME = "Computer.name";
    private static final String INTRODUCED = "Computer.introduced";
    private static final String DISCONTINUED = "Computer.discontinued";
    private static final String COMPANY_ID = "Computer.company_id";

    /**
     * @param res the result
     * @param jointureCompany if there is a join
     * @return the new computer
     * @throws SQLException the exception
     */
    public static Computer mapperComputer(ResultSet res, boolean jointureCompany) throws SQLException {

        long id = res.getLong(ID);
        String name = res.getString(NAME);

        BuilderComputer builder = new BuilderComputer(name);
        builder.id(id);

        if (res.getDate("introduced") != null) {
            builder.introduced(res.getDate(INTRODUCED).toLocalDate());
        }

        if (res.getDate("discontinued") != null) {
            builder.introduced(res.getDate(DISCONTINUED).toLocalDate());
        }

        if (jointureCompany && res.getLong(COMPANY_ID) != 0) {
            builder.companyId(MapperCompany.mapperCompany(res));
        }

        return builder.build();
    }

}
