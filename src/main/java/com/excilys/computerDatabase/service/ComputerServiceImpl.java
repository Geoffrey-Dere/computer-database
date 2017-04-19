package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.mapper.CompanyMapper;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.model.Pager.BuilderPage;
import com.excilys.computerDatabase.persistence.ComputerDAO;
import com.excilys.computerDatabase.persistence.ConnectionManager;
import com.excilys.computerDatabase.persistence.mapper.MapperComputer;

public class ComputerServiceImpl implements ComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
    
    /**
     */
    private ComputerDAO computerDAO = new ComputerDAO();

    /**
     */
    public ComputerServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Pager<ComputerDTO> getAllComputers() {
        return getPageComputer(Long.MAX_VALUE, 0);
    }

    @Override
    public Pager<ComputerDTO> getPageComputer(long limit, long offset) {
        List<Computer> listComputer = computerDAO.findAll(limit, offset);
        BuilderPage<ComputerDTO> builder = new BuilderPage<>(ComputerMapper.mapperToDTO(listComputer));
        return builder.build();
    }

    @Override
    public ComputerDTO getComputer(long id) {
        Optional<Computer> computer = computerDAO.find(id);
        if (computer.isPresent()) {
            return ComputerMapper.mapperToDTO(computer.get());
        }
        LOGGER.debug("no computer with id {} ",  id);
        throw new ServiceException("no computer with id " + id);
    }

    @Override
    public boolean addComputer(ComputerDTO c) {
        return computerDAO.create(ComputerMapper.mapperToModel(c));
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
