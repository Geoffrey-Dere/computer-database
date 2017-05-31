package com.excilys.cdb.webapp.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.binding.dto.CompanyDTO;
import com.excilys.cdb.binding.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

@RestController
@RequestMapping("/api")
public class RestCompany {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestCompany.class);

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> getComputer(@PathVariable("id") long id) {

        Optional<Company> company = companyService.get(id);
        if (company.isPresent()) {
            return new ResponseEntity<>(CompanyMapper.mapperToDTO(company.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
