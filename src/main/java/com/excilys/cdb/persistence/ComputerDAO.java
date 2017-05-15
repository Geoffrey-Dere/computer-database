package com.excilys.cdb.persistence;

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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.mapper.MapperComputer;

@Repository
public class ComputerDAO implements DAO<Computer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);
    private static final String SQL_FIND_BY_ID = "select * from computer  LEFT OUTER JOIN company on computer.company_id = company.id where computer.id = ? ";
    private static final String SQL_DELETE = "delete from computer where id = ? ";
    private static final String SQL_DELETE_BY_COMPANY = "delete from computer where company.id = ?";
    private static final String SQL_INSERT = "insert into computer(name, introduced, discontinued, company_id)VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String SQL_COUNT = "select count(*) from computer";
    private static final String SQL_COUNT_REGEX = "select count(*) from computer where computer.name LIKE ?";
    private static final String SQL_FIND_ALL_WITH_COMPANY = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id ";
    private static final String SQL_REGEX = "select * from computer  LEFT OUTER JOIN company on computer.company_id = company.id where computer.name like ? ";
    private static final String SQL_FIND = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id  ";
    private static final String SQL_FIND_BY_COMPANY = "select id from computer where company_id = ?";

    @Autowired
    private ConnectionManager connectionManager;

    @Autowired
    private DataSource datasource;

    @Override
    public boolean create(Computer obj) {

        boolean success = false;
        LOGGER.debug("inserting new computer {}", obj);

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT,
                        Statement.RETURN_GENERATED_KEYS)) {

            connection.setReadOnly(false);
            this.setStatement(statement, obj);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                obj.setId(rs.getInt(1));
                LOGGER.debug("computer inserted");
                success = true;
            }
            rs.close();
            return success;

        } catch (SQLException e) {
            LOGGER.error("sql exception : function create new computer ");
            throw new ExceptionDAO("error create new computer", e);
        }
    }

    @Override
    public boolean delete(Computer obj) {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {

            connection.setReadOnly(false);
            statement.setLong(1, obj.getId());
            int success = statement.executeUpdate();

            return success == 1;

        } catch (SQLException e) {
            LOGGER.error("sql exception : error deleting computer");
            throw new ExceptionDAO("error deleting computer ", e);
        }
    }

    /**
     * @param id id of the computer
     * @return true if success
     */
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
            connection.setReadOnly(false);
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

            connection.setReadOnly(true);
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
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_WITH_COMPANY)) {

            connection.setReadOnly(true);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }

            result.close();
            return list;

        } catch (SQLException e) {
            LOGGER.error("error to find  all computers ");
            throw new ExceptionDAO("error to find  all computers", e);
        }
    }

    /**
     * @param companyId company' id
     * @return list of computer
     */
    public List<Long> getIdByCompany(long companyId) {

        List<Long> list = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_COMPANY)) {

            connection.setReadOnly(true);
            statement.setLong(1, companyId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(result.getLong("id"));
            }
            result.close();
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
     * @param column column
     * @param order order
     * @return the list of computers
     */
    public List<Computer> findAll(long limit, long offset, String column, String order) {

        List<Computer> list = new ArrayList<>();
        LOGGER.debug(SQL_FIND + "order by " + column + " " + order + "limit ? offset ?");
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(SQL_FIND + "order by " + column + " " + order + " limit ? offset ?")) {

            LOGGER.debug("COUCOU TOI {}", this.datasource);

            connection.setReadOnly(true);
            statement.setLong(1, limit);
            statement.setLong(2, offset);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }
            result.close();
        } catch (SQLException e) {
            LOGGER.error("error to find  all computers with limit");
            throw new ExceptionDAO("error to find  all computers with limit", e);
        }
        return list;
    }

    /**
     * @param limit limit
     * @param offset offset
     * @param regex regex
     * @param column column
     * @param order order
     * @return list of computer
     */
    public List<Computer> findByName(long limit, long offset, String regex, String column, String order) {

        List<Computer> list = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(SQL_REGEX + "order by " + column + " " + order + " limit ? offset ?")) {

            connection.setReadOnly(true);
            statement.setString(1, regex + "%");
            statement.setLong(2, limit);
            statement.setLong(3, offset);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }
            result.close();
            return list;
        } catch (SQLException e) {
            LOGGER.error("error to find  computer with regex");
            throw new ExceptionDAO("error to find  computer with regex", e);
        }
    }

    /**
     * @return count of computer
     */
    public int count() {

        int count = 0;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_COUNT)) {

            connection.setReadOnly(true);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

            result.close();
            return count;
        } catch (SQLException e) {
            LOGGER.error("error count");
        }
        return 0;
    }

    /**
     * @param name name
     * @return count of computer
     */
    public int count(String name) {

        int res = 0;
        ResultSet result;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_COUNT_REGEX)) {

            connection.setReadOnly(true);
            statement.setString(1, name + "%");
            result = statement.executeQuery();
            if (result.next()) {
                res = result.getInt(1);
            }
            result.close();
        } catch (SQLException e) {
            LOGGER.error("error count with regex : {}", e.getMessage());
        }
        LOGGER.debug("count =  {}", res);
        return res;
    }

    /**
     * @param statement statement
     * @param obj computer
     * @return the statement
     * @throws SQLException sqlexception
     */
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

    /**
     * @param companyId companyiD
     * @return bool success
     */
    public boolean deleteByCompany(long companyId) {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_COMPANY)) {

            connection.setReadOnly(false);
            statement.setLong(1, companyId);
            return statement.executeUpdate() >= 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param listId list
     */
    public void remove(List<Integer> listId) {

        String query = "delete from computer where id in (";

        for (int id : listId) {
            query += id + ",";
        }

        query += "0)";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            connection.setReadOnly(false);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
