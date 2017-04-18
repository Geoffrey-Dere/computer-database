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

import com.excilys.computerDatabase.persistence.mapper.MapperComputer;
import com.excilys.computerDatabase.model.Computer;

public class ComputerDAO implements DAO<Computer> {

    private static final String SQL_FIND_ALL = "select * from computer;";
    private static final String SQL_FIND_ALL_WITH_COMPANY = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id ;";

    private static final String SQL_FIND_BY_ID = "select * from computer where id = ? ;";
    private static final String SQL_DELETE = "delete from computer where id = ? ;";
    private static final String SQL_INSERT = "insert into computer(name, introduced, discontinued, company_id)VALUES(?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

    private static final String SQL_FIND_LIMIT = "select * from computer limit ? offset ? ;";

    private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

    @Override
    public boolean create(Computer obj) {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, obj.getName());
            statement.setTimestamp(2, timeFromDateLocal(obj.getIntroduced()));
            statement.setTimestamp(3, timeFromDateLocal(obj.getDiscontinued()));

            if (obj.getCompany() != null) {
                statement.setLong(4, obj.getCompany().getId());
            } else {
                statement.setNull(4, java.sql.Types.BIGINT);
            }

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                obj.setId(rs.getInt(1));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(Computer obj) {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setLong(1, obj.getId());
            boolean success = statement.execute();
            return success;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("error deleting computer ", e);
        }
    }

    @Override
    public boolean update(Computer obj) {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, obj.getName());
            statement.setTimestamp(2, timeFromDateLocal(obj.getIntroduced()));
            statement.setTimestamp(3, timeFromDateLocal(obj.getDiscontinued()));

            if (obj.getCompany() != null) {
                statement.setLong(4, obj.getCompany().getId());
            } else {
                statement.setNull(4, java.sql.Types.BIGINT);
            }

            statement.setLong(5, obj.getId());

            boolean success = statement.execute();
            return success;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("error updating computer ", e);
        }

    }

    @Override
    public Optional<Computer> find(long id) {

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                Computer computer = MapperComputer.mapperComputer(res, false);
                return Optional.of(computer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("error to find computer ", e);
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
            e.printStackTrace();
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
    public List<Computer> findAll(int limit, int offset) {

        List<Computer> list = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SQL_FIND_LIMIT);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                list.add(MapperComputer.mapperComputer(result, true));
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("error to find  all computers", e);
        }
    }

}
