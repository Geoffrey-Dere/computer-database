package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.model.Pager.BuilderPage;
import com.excilys.computerDatabase.persistence.ComputerDAO;

public class ComputerServiceImpl implements ComputerService {

    /**
     */
    private ComputerDAO computerDAO = new ComputerDAO();

    /**
     */
    public ComputerServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Computer> getAllComputers() {
        return computerDAO.findAll();
    }

    @Override
    public Pager<Computer> getPageComputer(int limit, int offset) {
        BuilderPage<Computer> builder = new BuilderPage<>(computerDAO.findAll(limit, offset));
        return builder.build();
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
