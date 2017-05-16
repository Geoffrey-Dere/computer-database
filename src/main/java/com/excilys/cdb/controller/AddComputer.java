package com.excilys.cdb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ServiceException;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

@Controller
@RequestMapping("/add")
public class AddComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

    @Autowired
    private ComputerServiceImpl computerService;
    @Autowired
    private CompanyServiceImpl companyService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homee() {

        ModelAndView mv = new ModelAndView("addComputer");
        List<Company> listCompany = companyService.getAll();
        mv.addObject("listCompany", CompanyMapper.mapperToDTO(listCompany));

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addComputer(@RequestParam(value = "computerName") String name,
            @RequestParam(value = "introduced") String introduced,
            @RequestParam(value = "discontinued") String discontinued,
            @RequestParam(value = "companyID") String companyID) {

        name = name.trim();
        introduced = introduced.trim();
        discontinued = discontinued.trim();
        companyID = companyID.trim();

        ComputerDTO computerDTO = new ComputerDTO();

        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);

        try {
            long id = Long.parseLong(companyID);
            computerDTO.setCompanyId(id);
        } catch (java.lang.NumberFormatException e) {
            LOGGER.debug("{} isn't a number", companyID);
        }

        try {
            LOGGER.debug("inserting new object computerDTO  : {}", computerDTO);
            computerService.add(ComputerMapper.mapperToModel(computerDTO));
        } catch (ServiceException e) {
            LOGGER.debug("erreur");
        }

        return "redirect:/";
    }

}
