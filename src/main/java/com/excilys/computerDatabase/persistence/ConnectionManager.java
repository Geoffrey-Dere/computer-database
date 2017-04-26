package com.excilys.computerDatabase.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionManager {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    private static HikariDataSource hikari;
    private static HikariConfig config;

    private static final String PATH_FILE = "config/bdd.properties";

    private ThreadLocal<Connection> connection = new ThreadLocal<>();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try (InputStream resourceStream = classLoader.getResourceAsStream("config/hikari.properties")) {
            prop.load(resourceStream);
            config = new HikariConfig(prop);
            hikari = new HikariDataSource(config);
        } catch (IOException e) {
            LOGGER.error("can't read the file {} ", PATH_FILE);
            throw new ExceptionDAO("error to read the file " + PATH_FILE, e);
        }
    }

    /**
     */
    ConnectionManager() {
    }

    /**
     * @return the connection of the database
     */
    public Connection getConnection() {

        try {
            if (connection.get() == null || connection.get().isClosed()) {
                // Class.forName(driver);
                connection.set(hikari.getConnection());
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
