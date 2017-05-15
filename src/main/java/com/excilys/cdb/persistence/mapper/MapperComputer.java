package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.BuilderComputer;
import com.excilys.cdb.persistence.ComputerDAO;

public class MapperComputer implements RowMapper<Computer> {

    /**
     * @param res the result
     * @param jointureCompany if there is a join
     * @return the new computer
     * @throws SQLException the exception
     */
    public static Computer mapperComputer(ResultSet res, boolean jointureCompany) throws SQLException {

        long id = res.getLong(ComputerDAO.ID);
        String name = res.getString(ComputerDAO.NAME);

        BuilderComputer builder = new BuilderComputer(name);
        builder.id(id);

        if (res.getDate(ComputerDAO.INTRODUCED) != null) {
            builder.introduced(res.getDate(ComputerDAO.INTRODUCED).toLocalDate());
        }

        if (res.getDate(ComputerDAO.DISCONTINUED) != null) {
            builder.discontinued(res.getDate(ComputerDAO.DISCONTINUED).toLocalDate());
        }

        if (jointureCompany && res.getLong(ComputerDAO.COMPANY_ID) != 0) {
            builder.company(MapperCompany.mapperCompany(res));
        }

        return builder.build();
    }

    @Override
    public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id = rs.getLong(ComputerDAO.ID);
        String name = rs.getString(ComputerDAO.NAME);

        BuilderComputer builder = new BuilderComputer(name);
        builder.id(id);

        if (rs.getDate(ComputerDAO.INTRODUCED) != null) {
            builder.introduced(rs.getDate(ComputerDAO.INTRODUCED).toLocalDate());
        }

        if (rs.getDate(ComputerDAO.DISCONTINUED) != null) {
            builder.discontinued(rs.getDate(ComputerDAO.DISCONTINUED).toLocalDate());
        }

        if (rs.getLong(ComputerDAO.COMPANY_ID) != 0) {
            builder.company(new MapperCompany().mapRow(rs, rowNum));
        }

        return builder.build();
    }

}
