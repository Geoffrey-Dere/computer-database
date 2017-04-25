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

    private static final String SQL_FIND_BY_ID = "select * from computer  LEFT OUTER JOIN company on computer.company_id = company.id where computer.id = ? ";

    private static final String SQL_DELETE = "delete from computer where id = ? ";

    private static final String SQL_INSERT = "insert into computer(name, introduced, discontinued, company_id)VALUES(?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

    private static final String SQL_COUNT = "select count(*) from computer";

    private static final String SQL_FIND_ALL_WITH_COMPANY = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id ";
    private static final String SQL_REGEX = "select * from computer  LEFT OUTER JOIN company on computer.company_id = company.id where computer.name like ? limit ? offset ? ";
    private static final String SQL_FIND_LIMIT = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id limit ? offset ? ";
    private static final String SQL_FIND_BY_COMPANY = "select id from computer where company_id = ?";

    private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

    @Override
    public boolean create(Computer obj) {

        LOGGER.debug("inserting new computer {}", obj);

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                        Statement.RETURN_GENERATED_KEYS)) {

            this.setStatement(statement, obj);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                obj.setId(rs.getInt(1));
                LOGGER.debug("computer inserted");
                return true;
            } else {
                throw new ExceptionDAO("hasn't been inserted");
            }

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
            int success = statement.executeUpdate();

            if (success >= 1) {
                LOGGER.debug("The computer with id {} has been deleted", obj.getId());
            } else {
                LOGGER.debug("The computer with id {} hasn't been deleted", obj.getId());
            }
            return success == 1;

        } catch (SQLException e) {
            LOGGER.error("sql exception : error deleting computer");
            throw new ExceptionDAO("error deleting computer ", e);
        }
    }

    public boolean deleteById(long id) {
        Computer computer = new Computer();
        computer.setId(id);
        return delete(computer);
    }

    @Override
    public boolean update(Computer obj) {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {

            LOGGER.trace("update new computer {}", obj);

            this.setStatement(statement, obj);
            statement.setLong(5, obj.getId());
            int success = statement.executeUpdate();
            return success == 1;

        } catch (SQLException e) {
            LOGGER.error("error updating computer");
            throw new ExceptionDAO("error updating computer ", e);
        }
    }

    @Override
    public Optional<Computer> find(long id) {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {

            statement.setString(1, String.valueOf(id));
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                Computer computer = MapperComputer.mapperComputer(res, true);
                res.close();
                return Optional.of(computer);
            } else {
                res.close();
                return Optional.empty();
            }

        } catch (SQLException e) {
            LOGGER.error("error to find a computer ");
            throw new ExceptionDAO("error to find a computer ", e);
        }

    }

    @Override
    public List<Computer> findAll() {

        List<Computer> list = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_WITH_COMPANY);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }
            return list;

        } catch (SQLException e) {
            LOGGER.error("error to find  all computers ");
            throw new ExceptionDAO("error to find  all computers", e);
        }
    }

    public List<Long> getIdByCompany(long company_id) {

        List<Long> list = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_COMPANY)) {

            statement.setLong(1, company_id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(result.getLong("id"));
            }
        } catch (SQLException e) {
            LOGGER.error("error function");
        }
        return list;
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

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_LIMIT)) {

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

    public List<Computer> findByName(long limit, long offset, String regex) {

        List<Computer> list = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_REGEX)) {

            statement.setString(1, "%" + regex + "%");
            statement.setLong(2, limit);
            statement.setLong(3, offset);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error("error to find  computer with regex");
            throw new ExceptionDAO("error to find  computer with regex", e);
        }

    }

    public int count() {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_COUNT);
                ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.error("error");
            throw new ExceptionDAO("error", e);
        }
        return 0;
    }

    private PreparedStatement setStatement(PreparedStatement statement, Computer obj) throws SQLException {

        statement.setString(1, obj.getName());

        if (obj.getIntroduced().isPresent()) {
            statement.setTimestamp(2, timeFromDateLocal(obj.getIntroduced().get()));
        } else {
            statement.setNull(2, java.sql.Types.TIMESTAMP);
        }

        if (obj.getDiscontinued().isPresent()) {
            statement.setTimestamp(3, timeFromDateLocal(obj.getDiscontinued().get()));
        } else {
            statement.setNull(3, java.sql.Types.TIMESTAMP);
        }

        if (obj.getCompany().isPresent()) {
            statement.setLong(4, obj.getCompany().get().getId());
        } else {
            statement.setNull(4, java.sql.Types.BIGINT);
        }
        return statement;
    }
}
