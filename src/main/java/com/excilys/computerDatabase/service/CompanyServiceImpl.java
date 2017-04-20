package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.dto.CompanyDTO;
import com.excilys.computerDatabase.mapper.CompanyMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.persistence.CompanyDAO;
import com.excilys.computerDatabase.persistence.mapper.MapperCompany;

public class CompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO = new CompanyDAO();

    /**
     */
    public CompanyServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Optional<CompanyDTO> getCompany(long id) {
        
        Optional<Company> company = companyDAO.find(id);   
        if(company.isPresent()){
            return Optional.of(CompanyMapper.mapperToDTO(company.get()));
        }
        return Optional.empty();
    }

   
    public List<CompanyDTO> getAllCompanies() {
        
        List<Company> listCompany = companyDAO.findAll();
        return CompanyMapper.mapperToDTO(listCompany);
    }

}
