package com.excilys.cdb.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.model.Pager.BuilderPage;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.validator.ComputerValidator;
import com.excilys.cdb.validator.ValidatorException;

public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

    /**
     */
    private ComputerDAO computerDAO = ComputerDAO.INSTANCE;

    /**
     */
    public ComputerServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Pager<Computer> getAll() {
        return getPage(Long.MAX_VALUE, 0, "", " ");
    }

    @Override
    public Pager<Computer> getPage(long limit, long offset, String column, String order) {
        List<Computer> listComputer = computerDAO.findAll(limit, offset, column, order);
        BuilderPage<Computer> builder = new BuilderPage<>(listComputer);
        return builder.build();
    }

    public Pager<Computer> getPage(long limit, long offset, String regex, String column, String order) {
        List<Computer> listComputer = computerDAO.findByName(limit, offset, regex, column, order);
        BuilderPage<Computer> builder = new BuilderPage<>(listComputer);
        return builder.build();
    }

    @Override
    public Optional<Computer> get(long id) {
        Optional<Computer> computer = computerDAO.find(id);
        return computer;
    }

    @Override
    public boolean add(Computer c) {
        try {
            ComputerValidator.isValid(c);
            return computerDAO.create(c);
        } catch (ValidatorException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Computer c) {
        try {
            ComputerValidator.isValid(c);
            return computerDAO.update(c);
        } catch (ValidatorException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean remove(Computer c) {
        return computerDAO.delete(c);
    }

    public boolean remove(int id) {
        Computer computer = new Computer();
        computer.setId(id);
        return computerDAO.delete(computer);

    }

    public int count() {
        return computerDAO.count();
    }

    public int count(String name) {
        return computerDAO.count(name);
    }

    public void removeByCompanyId(long id) {
        computerDAO.deleteByCompany(id);

    }

	public void remove(List<Integer> list_id) {
computerDAO.remove(list_id);		
	}
}
