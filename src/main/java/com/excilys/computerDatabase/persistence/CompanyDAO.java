package com.excilys.computerDatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.persistence.mapper.MapperCompany;
import com.excilys.computerDatabase.model.Company;

public enum CompanyDAO implements DAO<Company> {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);

    private static final String SQL_FIND_ALL = "select * from company ;";
    private static final String SQL_FIND_BY_ID = "select * from company where id = ? ;";
    private static final String SQL_GET__ID = "select id from company ;";
    private static final String SQL_DELETE = "delete from company where id = ? ";

    private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

    @Override
    public boolean create(Company obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Company obj) {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

            connection.setAutoCommit(false);

            statement.setLong(1, obj.getId());
            int success = statement.executeUpdate();

            if (success == 1) {
                ComputerDAO computerDAO = ComputerDAO.INSTANCE;

                List<Long> list_computer_id = computerDAO.getIdByCompany(obj.getId());

                for (Long id : list_computer_id) {
                    computerDAO.deleteById(id);
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            LOGGER.error("sql exception : function find ");
        }
        return false;
    }

    @Override
    public boolean update(Company obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Company> find(long id) {

        Company company = null;

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {

            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                company = MapperCompany.mapperCompany(result);
            }

            return Optional.ofNullable(company);

        } catch (SQLException e) {
            LOGGER.error("sql exception : function find ");
            throw new ExceptionDAO("error find one companie", e);
        }
    }

    @Override
    public List<Company> findAll() {

        List<Company> list = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                list.add(MapperCompany.mapperCompany(result));
            }

            return list;

        } catch (SQLException e) {
            LOGGER.error("sql exception : function findAll ");
            throw new ExceptionDAO("error find all companies", e);
        }
    }
}
