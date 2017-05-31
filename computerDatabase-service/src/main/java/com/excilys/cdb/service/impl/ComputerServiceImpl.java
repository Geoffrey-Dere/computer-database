package com.excilys.cdb.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.persistence.ComputerDAO;
import com.excilys.cdb.persistence.ExceptionDAO;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ServiceException;

@Service
@Transactional
public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

    /**
     */
    @Autowired
    private ComputerDAO computerDAO;

    @Override
    public Optional<Computer> get(long id) {
        Optional<Computer> computer = computerDAO.find(id);
        return computer;
    }

    @Override
    public boolean add(Computer c) {
        return computerDAO.create(c);
    }

    @Override
    public boolean update(Computer c) {
        return computerDAO.update(c);
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
    public void remove(List<Long> listId) {
        try {
            computerDAO.remove(listId);
        } catch (ExceptionDAO e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void find(Pager<Computer> page) {
        computerDAO.findAll(page);

    }
}
