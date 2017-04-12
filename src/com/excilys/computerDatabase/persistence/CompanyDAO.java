package com.excilys.computerDatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.mapper.MapperCompany;
import com.excilys.computerDatabase.mapper.MapperComputer;
import com.excilys.computerDatabase.model.Company;

public class CompanyDAO implements DAO<Company> {

	private static final String SQL_FIND_ALL = "select * from company ;" ;
	private static final String SQL_FIND_BY_ID = "select * from company where id = ? ;";

	private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Company> find(long id) {

		try {
			Connection connection = connectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setString(1, String.valueOf(id));
			ResultSet result = statement.executeQuery();
			connection.close();

			if (result.next()){
				return Optional.of(MapperCompany.mapToCompany(result));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExceptionDAO("error find one companie", e);
		} finally {
			connectionManager.close();
		}

		return Optional.empty();
	}

	@Override
	public List<Company> findAll() {

		try{
			Connection connection = connectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
			ResultSet result = statement.executeQuery();
			connection.close();

			return MapperCompany.mapListCompany(result);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExceptionDAO("error find all companies", e);
		} finally {
			connectionManager.close();
		}
	}

}
