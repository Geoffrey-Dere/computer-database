package com.excilys.computerDatabase.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.excilys.computerDatabase.dto.CompanyDTO;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.util.DateFormatter;

public class ComputerMapper {

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

    public static List<ComputerDTO> mapperToDTO(List<Computer> list){
        
        List<ComputerDTO> listDTO = new ArrayList<>();
        
        for(Computer computer : list){
            listDTO.add(mapperToDTO(computer));
        }
        return listDTO ;
    }
    
    
    
    /**
     * @param c the company dto 
     * @return the object
     */
    public static Computer mapperToModel(ComputerDTO c) {

        return null;
    }

}
