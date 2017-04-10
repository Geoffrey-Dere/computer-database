package com.excilys.computerDatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDAOFactory extends DaoFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";

	private static MysqlDAOFactory instance = null ;
	private Connection connection = null;

	static MysqlDAOFactory getInstance(){
		if(instance == null)
			instance = new MysqlDAOFactory();
		return instance ;
	}

	private MysqlDAOFactory(){
		try {  
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch ( ClassNotFoundException e ) {
		}
	}

	@Override
	public DAO getCompanyDAO() {
		return new CompanyDAO(connection);
	}

}
