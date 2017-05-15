package com.excilys.cdb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.model.Pager.BuilderPage;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ServiceException;
import com.excilys.cdb.validator.ComputerValidator;
import com.excilys.cdb.validator.ValidatorException;

@Service
public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

    /**
     */
    @Autowired
    private ComputerDAO computerDAO;

    /**
     */
    public ComputerServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    /**
     */
    @Override
    public Pager<Computer> getAll() {
        return getPage(Long.MAX_VALUE, 0, "", " ");
    }

    /**
     */
    @Override
    public Pager<Computer> getPage(long limit, long offset, String column, String order) {
        List<Computer> listComputer = computerDAO.findAll(limit, offset, column, order);
        BuilderPage<Computer> builder = new BuilderPage<>(listComputer);
        return builder.build();
    }

    /**
     * @param limit limit
     * @param offset offset
     * @param regex regex
     * @param column column
     * @param order order
     * @return page of computers
     */
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

    /**
     * @param id id
     * @return boolean success
     */
    public boolean remove(int id) {
        Computer computer = new Computer();
        computer.setId(id);
        return computerDAO.delete(computer);

    }

    /**
     * @return count
     */
    public int count() {
        return computerDAO.count();
    }

    /**
     * @param name name
     * @return count of computer
     */
    public int count(String name) {
        return computerDAO.count(name);
    }

    /**
     * @param id id
     */
    public void removeByCompanyId(long id) {
        computerDAO.deleteByCompany(id);
    }

    /**
     * @param listId list of id
     */
    public void remove(List<Integer> listId) {
        computerDAO.remove(listId);
    }
}
