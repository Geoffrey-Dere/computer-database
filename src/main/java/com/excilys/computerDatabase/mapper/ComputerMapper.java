package com.excilys.computerDatabase.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Computer.BuilderComputer;
import com.excilys.computerDatabase.util.DateFormatter;


public class ComputerMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);
    
    /**
     * @param c the computer we transform
     * @return the object DTO
     */
    public static ComputerDTO mapperToDTO(Computer c) {

        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setId(c.getId());
        computerDTO.setName(c.getName());
        Optional<LocalDate> introduced = c.getIntroduced();
        Optional<LocalDate> discontinued = c.getDiscontinued();
        Optional<Company> company = c.getCompany();

        if (introduced.isPresent()) {
            computerDTO.setIntroduced(DateFormatter.LocalDateToString(introduced.get()));
        } else {
            computerDTO.setIntroduced("");
        }

        if (discontinued.isPresent()) {
            computerDTO.setDiscontinued(DateFormatter.LocalDateToString(discontinued.get()));
        } else {
            computerDTO.setDiscontinued("");
        }

        if (company.isPresent()) {
            computerDTO.setCompany(CompanyMapper.mapperToDTO(company.get()));
        }

        return computerDTO;
    }

    public static List<ComputerDTO> mapperToDTO(List<Computer> list) {

        List<ComputerDTO> listDTO = new ArrayList<>();

        for (Computer computer : list) {
            listDTO.add(mapperToDTO(computer));
        }
        return listDTO;
    }

    /**
     * @param c the company dto 
     * @return the object
     */
    public static Computer mapperToModel(ComputerDTO c) {

        String introduced = c.getIntroduced();
        String discon = c.getDiscontinued();

        Computer.BuilderComputer builder = new BuilderComputer(c.getName());

        LOGGER.debug("id = {}", c.getId());
        builder.id(c.getId());

        if (!introduced.isEmpty()) {
            builder.introduced(DateFormatter.stringtoLocalDate(introduced));
        }

        if (!discon.isEmpty()) {
            builder.discontinued(DateFormatter.stringtoLocalDate(discon));
        }
        if (c.getCompany().isPresent()) {
            builder.company(CompanyMapper.mapperToModel(c.getCompany().get()));
        }

        return builder.build();
    }

}
