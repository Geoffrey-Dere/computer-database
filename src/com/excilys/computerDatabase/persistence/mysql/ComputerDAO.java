package com.excilys.computerDatabase.persistence.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.exception.DateException;
import com.excilys.computerDatabase.persistence.DAO;
import com.mysql.fabric.xmlrpc.base.Array;

public class ComputerDAO extends DAO<Computer>{

	private static final String SQL_FIND_ALL = "select * from computer;" ;
	private static final String SQL_FIND_BY_ID = "select * from computer where id = ? ;";

	public ComputerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Optional<Computer> find(long id) {

		try {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
			statement.setString(1, String.valueOf(id));

			ResultSet result = statement.executeQuery();

			if (result.next()){

				Optional<Timestamp> introduced = Optional.ofNullable(result.getTimestamp("introduced"));
				Optional<Timestamp> discontinued = Optional.ofNullable(result.getTimestamp("discontinued"));

				return CreateComputer(result.getLong("id"), result.getString("name"), 
						introduced, discontinued);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<Computer> findAll() {
		
		List<Computer> res= new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
			ResultSet result = statement.executeQuery();

			while(result.next()){

				Optional<Timestamp> introduced = Optional.ofNullable(result.getTimestamp("introduced"));
				Optional<Timestamp> discontinued = Optional.ofNullable(result.getTimestamp("discontinued"));

				Optional<Computer> tmp = CreateComputer(result.getLong("id"), result.getString("name"), 
						introduced, discontinued);
				
				if(tmp.isPresent())
					res.add(tmp.get());			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Optional<Computer> CreateComputer(long id, String name,
			Optional<Timestamp> introduced, Optional <Timestamp> disc){

		try {
			
			if(disc.isPresent()){
				LocalDate discon = disc.get().toLocalDateTime().toLocalDate();
				
				LocalDate intro ;
				intro = introduced.isPresent() ?  introduced.get().toLocalDateTime().toLocalDate()
						: discon.minusDays(1);
			
				if(intro.equals(discon))
					intro = discon.minusDays(1);
				
				return Optional.of(new Computer(id, name, intro, discon));	
			}

			if(introduced.isPresent()){
				LocalDate intro = introduced.get().toLocalDateTime().toLocalDate();
				return Optional.of(new Computer(id, name, intro));
			}

			return Optional.of(new Computer(id, name));

		} catch(DateException e){
			e.printStackTrace();

		}
		return Optional.empty();
	}

}
