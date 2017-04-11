package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.persistence.DaoFactory;

public class ComputerService implements IComputerService {

	public ComputerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Computer> getAllComputers() {
		return DaoFactory.getDaoFactory().getComputerDAO().findAll();
	}

	@Override
	public Optional<Computer> getComputer(long id) {
		return DaoFactory.getDaoFactory().getComputerDAO().find(id);
	}

	@Override
	public boolean addComputer(Computer c) {
		return DaoFactory.getDaoFactory().getComputerDAO().create(c);
	}

	@Override
	public boolean updateComputer(Computer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeComputer(Computer c) {
		return DaoFactory.getDaoFactory().getComputerDAO().delete(c);
	}

	@Override
	public boolean removeComputer(int id) {
		return DaoFactory.getDaoFactory().getComputerDAO().delete(new Computer(id,""));
	}

}
