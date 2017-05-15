package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    private static final String SQL_FIND_ALL_WITH_COMPANY = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id ";
    private static final String SQL_REGEX = "select * from computer  LEFT OUTER JOIN company on computer.company_id = company.id where computer.name like ? ";
    private static final String SQL_FIND = "select * from computer LEFT OUTER JOIN company on computer.company_id = company.id  ";
    private static final String SQL_FIND_BY_COMPANY = "select id from computer where company_id = ?";

    public static final String ID = "Computer.id";
    public static final String NAME = "Computer.name";
    public static final String INTRODUCED = "Computer.introduced";
    public static final String DISCONTINUED = "Computer.discontinued";
    public static final String COMPANY_ID = "Computer.company_id";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Computer obj) {

        LOGGER.debug("inserting new computer {}", obj);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int row = this.jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
                setStatement(ps, obj);
                return ps;
            }
        }, keyHolder);

        if (row > 0) {
            obj.setId(keyHolder.getKey().longValue()); // line 72
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Computer obj) {

        LOGGER.debug("delete computer {}", obj.toString());
        return this.jdbcTemplate.update(SQL_DELETE, obj.getId()) == 1;
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

        LOGGER.debug("update computer {}", obj.toString());

        Timestamp intro = null, discon = null;
        Long companyId = null;

        if (obj.getIntroduced().isPresent()) {
            intro = timeFromDateLocal(obj.getIntroduced().get());
        }

        if (obj.getDiscontinued().isPresent()) {
            discon = timeFromDateLocal(obj.getDiscontinued().get());
        }

        if (obj.getCompany().isPresent()) {
            companyId = obj.getCompany().get().getId();
        }

        return this.jdbcTemplate.update(SQL_UPDATE, obj.getName(), intro, discon, companyId, obj.getId()) == 1;
    }

    @Override
    public Optional<Computer> find(long id) {

        LOGGER.debug("find computer with id = {}", id);
        return Optional
                .ofNullable(this.jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new MapperComputer(), id));
    }

    @Override
    public List<Computer> findAll() {

        LOGGER.debug("find all computer");
        return this.jdbcTemplate.query(SQL_FIND_ALL_WITH_COMPANY, new MapperComputer());
    }

    /**
     * @param companyId company' id
     * @return list of computer
     */
    public List<Long> getIdByCompany(long companyId) {

        return this.jdbcTemplate.query(SQL_FIND_BY_COMPANY, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(ID);
            }

        }, companyId);
    }

    /**
     * @param limit the limit
     * @param offset the offset
     * @param column column
     * @param order order
     * @return the list of computers
     */
    public List<Computer> findAll(long limit, long offset, String column, String order) {

        LOGGER.debug("find all limit = {}, offset = {}, column = {}, order = {}", limit, offset, column, order);

        return this.jdbcTemplate.query(SQL_FIND + "order by " + column + " " + order + " limit ? offset ?",
                new MapperComputer(), limit, offset);
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

        LOGGER.debug("find all limit = {}, offset = {}, column = {}, order = {}, regex = {}", limit, offset, column,
                order, regex);

        return this.jdbcTemplate.query(SQL_REGEX + "order by " + column + " " + order + " limit ? offset ?",
                new MapperComputer(), regex, limit, offset);
    }

    /**
     * @return count of computer
     */
    public int count() {

        return this.jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
    }

    /**
     * @param name name
     * @return count of computer
     */
    public int count(String name) {

        return this.jdbcTemplate.queryForObject(SQL_COUNT, Integer.class, name + "%");
    }

    /**
     * @param companyId companyiD
     * @return bool success
     */
    public boolean deleteByCompany(long companyId) {

        return this.jdbcTemplate.queryForObject(SQL_DELETE_BY_COMPANY, Integer.class, companyId) >= 1;
    }

    /**
     * @param listId list
     */
    public void remove(List<Integer> listId) {

        String query = "delete from computer where id in (:ids)";

        LOGGER.debug("test = {}", listId);

        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
        Map<String, List> params = Collections.singletonMap("ids", listId);
        namedTemplate.update(query, params);
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
     * @param localDate the date to transform
     * @return the date
     */
    private Timestamp timeFromDateLocal(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

}
