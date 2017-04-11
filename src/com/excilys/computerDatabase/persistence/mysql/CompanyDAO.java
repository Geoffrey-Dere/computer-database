package com.excilys.computerDatabase.persistence.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.DAO;

public class CompanyDAO extends DAO<Company> {

	private static final String SQL_FIND_ALL = "select * from company ;" ;
	private static final String SQL_FIND_BY_ID = "select * from company where id = ? ;";

	public CompanyDAO(Connection connection) {
		super(connection);
	}

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
	public Company find(long id) {

		try {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setString(1, String.valueOf(id));

			ResultSet result = statement.executeQuery();

			if (result.next())
				return new Company(result.getLong("id"), result.getString("name"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Company> findAll() {

		List<Company> list = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)){

			ResultSet result = statement.executeQuery();

			while(result.next())
				list.add(new Company(result.getLong("id"), result.getString("name")));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
