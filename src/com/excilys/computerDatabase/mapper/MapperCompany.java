package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Company.BuilderCompany;


public abstract class MapperCompany {

	private final static String ID = "id";
	private final static String NAME = "name" ;


	public static Company mapToCompany(ResultSet res) throws SQLException {

		long id = res.getLong(ID);
		String name = res.getString(NAME);

		return new BuilderCompany(name).id(id).build();
	}

	public static List<Company >  mapListCompany (ResultSet res) throws SQLException{

		List<Company > list = new ArrayList<>();

		while(res.next()){
			list.add(mapToCompany(res));
		}
		return list ;
	}
}
