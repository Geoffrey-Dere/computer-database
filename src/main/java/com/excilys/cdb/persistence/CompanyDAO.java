package com.excilys.cdb.persistence;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.mapper.MapperCompany;

@Repository
public class CompanyDAO implements DAO<Company> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    private static final String SQL_FIND_ALL = "select * from company ;";
    private static final String SQL_FIND_BY_ID = "select * from company where id = ? ;";
    private static final String SQL_DELETE = "delete from company where id = ? ";

    public static final String ID = "Company.id";
    public static final String NAME = "Company.name";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Company obj) {
        return false;
    }

    @Override
    public boolean delete(Company obj) {
        LOGGER.debug("delete company {}", obj.toString());
        return this.jdbcTemplate.update(SQL_DELETE, obj.getId()) == 1;
    }

    @Override
    public boolean update(Company obj) {
        return false;
    }

    @Override
    public Optional<Company> find(long id) {

        LOGGER.debug("find company with id = {}", id);
        return Optional
                .ofNullable(this.jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new MapperCompany(), String.valueOf(id)));
    }

    @Override
    public List<Company> findAll() {

        LOGGER.debug("find all companies");
        return this.jdbcTemplate.query(SQL_FIND_ALL, new MapperCompany());
    }
}
