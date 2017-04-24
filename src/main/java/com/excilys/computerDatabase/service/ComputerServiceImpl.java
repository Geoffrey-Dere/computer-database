package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.model.Pager.BuilderPage;
import com.excilys.computerDatabase.persistence.ComputerDAO;
import com.excilys.computerDatabase.validator.ComputerValidator;
import com.excilys.computerDatabase.validator.ValidatorException;

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
    public Pager<Computer> getAllComputers() {
        return getPageComputer(Long.MAX_VALUE, 0);
    }

    @Override
    public Pager<Computer> getPageComputer(long limit, long offset) {
        List<Computer> listComputer = computerDAO.findAll(limit, offset);
        BuilderPage<Computer> builder = new BuilderPage<>(listComputer);
        return builder.build();
    }
    
    public Pager<Computer> getPageComputer(long limit, long offset, String regex) {
        List<Computer> listComputer = computerDAO.findAllWithRegex(limit, offset);
        BuilderPage<Computer> builder = new BuilderPage<>(listComputer);
        return builder.build();
    }

    @Override
    public Optional<Computer> getComputer(long id) {
        Optional<Computer> computer = computerDAO.find(id);
        return computer;
    }

    @Override
    public boolean addComputer(Computer c) {
        ComputerValidator.isValid(c);
        return computerDAO.create(c);
    }

    @Override
    public boolean updateComputer(Computer c) {
        ComputerValidator.isValid(c);
        return computerDAO.update(c);
    }

    @Override
    public boolean removeComputer(Computer c) {
        return computerDAO.delete(c);
    }
    
    public boolean removeComputer(int id){
       Computer computer = new Computer(); 
       computer.setId(id);
       return computerDAO.delete(computer);
        
    }

    public int count() {
        return computerDAO.count();
    }
}
