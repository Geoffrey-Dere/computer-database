package com.excilys.cdb.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;

public class CompanyMapper {

    /**
     * @param c the company we transform
     * @return the object DTO
     */
    public static CompanyDTO mapperToDTO(Company c) {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(c.getId());
        companyDTO.setName(c.getName());

        return companyDTO;
    }

    public static List<CompanyDTO> mapperToDTO(List<Company> list) {

        List<CompanyDTO> listDTO = new ArrayList<>();

        for (Company company : list) {
            listDTO.add(mapperToDTO(company));
        }
        return listDTO;
    }

    /**
     * @param c the company dto 
     * @return the object
     */
    public static Company mapperToModel(CompanyDTO c) {
        return new Company.Builder(c.getName()).id(c.getId()).build();
    }

    public static List<Company> mapperToModel(List<CompanyDTO> listDTO) {

        List<Company> list = new ArrayList<>();

        for (CompanyDTO companyDTO : listDTO) {
            list.add(mapperToModel(companyDTO));
        }
        return list;
    }
}
