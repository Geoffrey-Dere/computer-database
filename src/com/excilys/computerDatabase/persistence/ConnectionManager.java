package com.excilys.computerDatabase.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum ConnectionManager {

	INSTANCE;

	private static final String PATH_FILE = "resources/config/bdd.properties";

	private static String url ;
	private static String user ;
	private static String password ;

	private Connection connection = null;

	static{
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(PATH_FILE));
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ConnectionManager(){

	}

	public Connection getConnection(){

		try {
			if(connection == null || connection.isClosed()){
				Class.forName( "com.mysql.jdbc.Driver" );
				connection = DriverManager.getConnection(url,user, password);			
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ExceptionDAO("error connection", e);
		} 
		return connection ;
	}

	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExceptionDAO("error Closure of the connection", e);
		}
	}
}
