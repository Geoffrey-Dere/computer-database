package com.excilys.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Computer.BuilderComputer;

public abstract class MapperComputer {

	private final static String ID = "id";
	private final static String NAME = "name" ;
	private final static String INTRODUCED = "introduced" ;
	private final static String DISCONTINUED = "discontinued" ;

	public static Computer mapToComputer(ResultSet res) throws SQLException {

		long id = res.getLong(ID);
		String name = res.getString(NAME);

		BuilderComputer builder = new BuilderComputer(name);
		builder.id(id);

		if(res.getDate("introduced") != null){
			builder.introduced(res.getDate(INTRODUCED).toLocalDate());
		}

		if(res.getDate("discontinued") != null){
			builder.introduced(res.getDate(DISCONTINUED).toLocalDate());
		}

		return builder.build();
	}

	public static List<Computer>  mapListComputer (ResultSet res) throws SQLException{

		List<Computer> list = new ArrayList<>();

		while(res.next()){
			list.add(mapToComputer(res));
		}
		return list ;
	}
}

