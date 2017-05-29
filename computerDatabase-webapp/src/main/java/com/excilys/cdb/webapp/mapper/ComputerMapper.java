package com.excilys.cdb.webapp.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Computer.BuilderComputer;
import com.excilys.cdb.core.util.DateFormatter;
import com.excilys.cdb.webapp.dto.CompanyDTO;
import com.excilys.cdb.webapp.dto.ComputerDTO;

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
            computerDTO.setIntroduced(DateFormatter.localDateToString(introduced.get()));
        } else {
            computerDTO.setIntroduced("");
        }

        if (discontinued.isPresent()) {
            computerDTO.setDiscontinued(DateFormatter.localDateToString(discontinued.get()));
        } else {
            computerDTO.setDiscontinued("");
        }

        if (company.isPresent()) {
            computerDTO.setCompanyName(company.get().getName());
            computerDTO.setCompanyId(company.get().getId());
        }

        return computerDTO;
    }

    /**
     * @param list list of computers
     * @return list of computers dto
     */
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

        builder.id(c.getId());

        if (!introduced.isEmpty()) {
            builder.introduced(DateFormatter.stringtoLocalDate(introduced));
        }

        if (!discon.isEmpty()) {
            builder.discontinued(DateFormatter.stringtoLocalDate(discon));
        }
        if (c.getCompanyId() > 0) {
            builder.company(CompanyMapper.mapperToModel(new CompanyDTO(c.getCompanyId(), c.getCompanyName())));
        }

        return builder.build();
    }

}
