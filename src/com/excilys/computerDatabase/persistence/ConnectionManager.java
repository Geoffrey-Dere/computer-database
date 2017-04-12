package com.excilys.computerDatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionManager {

	INSTANCE;

	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";

	private Connection connection = null;


	private ConnectionManager(){
	}


	public Connection getConnection(){

		try {
			if(connection == null || connection.isClosed()){
				Class.forName( "com.mysql.jdbc.Driver" );
				connection = DriverManager.getConnection(URL, USER, PASSWORD);			
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
