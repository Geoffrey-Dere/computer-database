package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Computer.BuilderComputer;

public abstract class MapperComputer {

	private final static String ID = "Computer.id";
	private final static String NAME = "Computer.name";
	private final static String INTRODUCED = "Computer.introduced";
	private final static String DISCONTINUED = "Computer.discontinued";
	private final static String COMPANY_ID = "Computer.company_id";

	public static Computer mapperComputer(ResultSet res, boolean jointureCompany) throws SQLException {

		long id = res.getLong(ID);
		String name = res.getString(NAME);

		BuilderComputer builder = new BuilderComputer(name);
		builder.id(id);

		if (res.getDate("introduced") != null) {
			builder.introduced(res.getDate(INTRODUCED).toLocalDate());
		}

		if (res.getDate("discontinued") != null) {
			builder.introduced(res.getDate(DISCONTINUED).toLocalDate());
		}

		if (jointureCompany && res.getLong(COMPANY_ID) != 0) {
			builder.company_id(MapperCompany.mapperCompany(res));
		}

		return builder.build();
	}

}
