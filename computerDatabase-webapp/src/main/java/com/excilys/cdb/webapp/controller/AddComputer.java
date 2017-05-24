package com.excilys.cdb.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.webapp.dto.ComputerDTO;
import com.excilys.cdb.webapp.mapper.CompanyMapper;
import com.excilys.cdb.webapp.mapper.ComputerMapper;

@Controller
@RequestMapping("/add")
public class AddComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;
    
   // @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homee() {

        ModelAndView mv = new ModelAndView("addComputer");
        List<Company> listCompany = companyService.getAll();
        mv.addObject("listCompany", CompanyMapper.mapperToDTO(listCompany));
        mv.addObject("computerDTO", new ComputerDTO());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO computerDTO,
            BindingResult bindingResult) {

        LOGGER.info("Controller add computer : {}", computerDTO);

        if (bindingResult.hasErrors()) {
            return "redirect:/add";
        }
        computerService.add(ComputerMapper.mapperToModel(computerDTO));

        return "redirect:/";
    }

}
