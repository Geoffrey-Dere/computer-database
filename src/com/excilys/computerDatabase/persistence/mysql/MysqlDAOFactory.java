package com.excilys.computerDatabase.persistence.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.persistence.DAO;
import com.excilys.computerDatabase.persistence.DaoFactory;

public class MysqlDAOFactory extends DaoFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";

	private static MysqlDAOFactory instance = null ;
	private Connection connection = null;

	public static MysqlDAOFactory getInstance(){
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
	public DAO<Company> getCompanyDAO() {
		return new CompanyDAO(connection);
	}

	@Override
	public DAO<Computer> getComputerDAO() {
		return new ComputerDAO(connection);
	}

}
