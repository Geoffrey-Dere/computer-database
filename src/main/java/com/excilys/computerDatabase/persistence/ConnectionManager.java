package com.excilys.computerDatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ConnectionManager {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    private static final String PATH_FILE = "config/bdd.properties";
    private static final String DRIVER = "driver";
    private static final String BASE = "base";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static String driver;
    private static String base;
    private static String user;
    private static String password;

    private Connection connection = null;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try {
            prop.load(classLoader.getResourceAsStream(PATH_FILE));
            driver = prop.getProperty(DRIVER);
            base = prop.getProperty(BASE);
            user = prop.getProperty(USER);
            password = prop.getProperty(PASSWORD);
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
            if (connection == null || connection.isClosed()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(base, user, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("error connection with the database ");
            throw new ExceptionDAO("error connection", e);
        }
        return connection;
    }

    /**
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("error Closure of the connection");
            throw new ExceptionDAO("error Closure of the connection", e);
        }
    }
}
