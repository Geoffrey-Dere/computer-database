package com.excilys.computerDatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.persistence.mapper.MapperComputer;

public enum ComputerDAO implements DAO<Computer> {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

    private static final String SQL_FIND_ALL = "select * from computer;";
    private static final String SQL_FIND_ALL_WITH_COMPANY = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id ;";

    private static final String SQL_FIND_BY_ID = "select * from computer where id = ? ;";
    private static final String SQL_DELETE = "delete from computer where id = ? ;";
    private static final String SQL_INSERT = "insert into computer(name, introduced, discontinued, company_id)VALUES(?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_FIND_LIMIT = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id limit ? offset ? ;";
    private static final String SQL_COUNT = "select count(*) from computer";

    private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

    @Override
    public boolean create(Computer obj) {

        LOGGER.debug("inserting new computer {}", obj);

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, obj.getName());

            if (obj.getIntroduced().isPresent()) {
                statement.setTimestamp(2, timeFromDateLocal(obj.getIntroduced().get()));
            } else {
                statement.setNull(2, java.sql.Types.VARCHAR);
            }

            if (obj.getDiscontinued().isPresent()) {
                statement.setTimestamp(3, timeFromDateLocal(obj.getDiscontinued().get()));
            } else {
                statement.setNull(3, java.sql.Types.VARCHAR);
            }

            if (obj.getCompany().isPresent()) {
                statement.setLong(4, obj.getCompany().get().getId());
            } else {
                statement.setNull(4, java.sql.Types.BIGINT);
            }

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                obj.setId(rs.getInt(1));
                LOGGER.debug("computer inserted");
                return true;
            }
            return false;

        } catch (SQLException e) {
            LOGGER.error("sql exception : function create new computer ");
            throw new ExceptionDAO("error create new computer", e);

        }
    }

    @Override
    public boolean delete(Computer obj) {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, obj.getId());
            boolean success = statement.execute();
            return success;

        } catch (SQLException e) {
            LOGGER.error("sql exception : error deleting computer");
            throw new ExceptionDAO("error deleting computer ", e);
        }
    }

    @Override
    public boolean update(Computer obj) {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, obj.getName());

            if (obj.getIntroduced().isPresent()) {
                statement.setTimestamp(2, timeFromDateLocal(obj.getIntroduced().get()));
            }

            if (obj.getDiscontinued().isPresent()) {
                statement.setTimestamp(3, timeFromDateLocal(obj.getDiscontinued().get()));
            }

            if (obj.getCompany().isPresent()) {
                statement.setLong(4, obj.getCompany().get().getId());
            } else {
                statement.setNull(4, java.sql.Types.BIGINT);
            }

            statement.setLong(5, obj.getId());

            boolean success = statement.execute();
            return success;

        } catch (SQLException e) {
            LOGGER.error("error updating computer");
            throw new ExceptionDAO("error updating computer ", e);
        }

    }

    @Override
    public Optional<Computer> find(long id) {

        LOGGER.debug("EUH");

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                Computer computer = MapperComputer.mapperComputer(res, false);
                return Optional.of(computer);
            }

        } catch (SQLException e) {
            LOGGER.error("error to find a computer ");
            throw new ExceptionDAO("error to find a computer ", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Computer> findAll() {

        List<Computer> list = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_WITH_COMPANY);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }

            return list;

        } catch (SQLException e) {
            LOGGER.error("error to find  all computers ");
            throw new ExceptionDAO("error to find  all computers", e);
        }
    }

    /**
     * @param localDate the date to transform
     * @return the date
     */
    private Timestamp timeFromDateLocal(LocalDate localDate) {

        Timestamp t = null;

        if (localDate == null) {
            return null;
        }

        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        t = new Timestamp(date.getTime());
        return t;
    }

    /**
     * @param limit the limit
     * @param offset the offset
     * @return the list of computers
     */
    public List<Computer> findAll(long limit, long offset) {

        List<Computer> list = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_FIND_LIMIT);
            statement.setLong(1, limit);
            statement.setLong(2, offset);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }

            return list;

        } catch (SQLException e) {
            LOGGER.error("error to find  all computers with limit");
            throw new ExceptionDAO("error to find  all computers with limit", e);
        }
    }

    public int count() {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_COUNT);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.error("error");
            throw new ExceptionDAO("error", e);
        }
        return 0;
    }

}
