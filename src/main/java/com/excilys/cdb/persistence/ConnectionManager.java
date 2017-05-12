package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
    public static final String PATH_FILE = "config/hikari.properties";

    @Autowired
    private DataSource dataSource;

    private ThreadLocal<Connection> connection = new ThreadLocal<>();

    /**
     * @return the connection of the database
     */
    public Connection getConnection() {
        LOGGER.debug("get connection {}", dataSource);

        try {
            if (connection.get() == null || connection.get().isClosed()) {
                connection.set(dataSource.getConnection());
            }
        } catch (SQLException e) {
            LOGGER.error("error connection with the database ");
            throw new ExceptionDAO("error connection", e);
        }
        return connection.get();
    }

    /**
     */
    public void close() {
        try {
            connection.get().close();
        } catch (SQLException e) {
            LOGGER.error("error Closure of the connection");
            throw new ExceptionDAO("error Closure of the connection", e);
        }
    }
}
