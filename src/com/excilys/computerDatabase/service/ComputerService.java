package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.persistence.CompanyDAO;
import com.excilys.computerDatabase.persistence.ComputerDAO;

public class ComputerService implements IComputerService {

	private ComputerDAO computerDAO = new ComputerDAO();
	
	public ComputerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Computer> getAllComputers() {
		return computerDAO.findAll();
	}

	@Override
	public Optional<Computer> getComputer(long id) {
		return computerDAO.find(id);
	}

	@Override
	public boolean addComputer(Computer c) {
		return computerDAO.create(c);
	}

	@Override
	public boolean updateComputer(Computer c) {
		return false;
	}

	@Override
	public boolean removeComputer(Computer c) {
		return computerDAO.delete(c);
	}

}
