package com.excilys.cdb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceException;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

@Controller
@RequestMapping(value = "/editComputer")
public class EditComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);

    @Autowired
    private ComputerServiceImpl computerService;

    @Autowired
    private CompanyServiceImpl companyService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "id") String id) {

        ModelAndView mv = new ModelAndView("editComputer");
        Optional<Computer> computer = Optional.empty();

        if (isInteger(id)) {
            computer = computerService.get(Integer.parseInt(id));
            if (computer.isPresent()) {
                mv.addObject("computer", ComputerMapper.mapperToDTO(computer.get()));
            }
        }
        List<Company> listCompany = companyService.getAll();
        mv.addObject("listCompany", CompanyMapper.mapperToDTO(listCompany));

        return mv;

    }

    @RequestMapping(method = RequestMethod.POST)
    public String addComputer(@RequestParam(value = "computerName") String name,
            @RequestParam(value = "introduced") String introduced,
            @RequestParam(value = "discontinued") String discontinued,
            @RequestParam(value = "companyID") String companyID) {

        ComputerDTO computerDTO = new ComputerDTO();

        name = name.trim();
        introduced = introduced.trim();
        discontinued = discontinued.trim();
        companyID = companyID.trim();

        if (isInteger(companyID)) {
            computerDTO.setId(Long.parseLong(companyID));
        }

        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);

        try {
            computerDTO.setCompanyId(Long.parseLong(companyID));
        } catch (java.lang.NumberFormatException e) {
            LOGGER.debug("{} isn't a number", companyID);
        }

        LOGGER.trace("updatating new object computerDTO  : {}", computerDTO);

        try {
            computerService.update(ComputerMapper.mapperToModel(computerDTO));
        } catch (ServiceException e) {

        }
        return "redirect:/";
    }

    /**
     * @param s the string
     * @return success if it is a number
     */
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
