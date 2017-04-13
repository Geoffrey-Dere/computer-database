package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Company.BuilderCompany;

public abstract class MapperCompany {

	private final static String ID = "Company.id";
	private final static String NAME = "Company.name";

	public static Company mapperCompany(ResultSet res) throws SQLException {

		long id = res.getLong(ID);
		String name = res.getString(NAME);

		return new BuilderCompany(name).id(id).build();
	}

}
