package com.excilys.cdb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
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
            } else {
                return new ModelAndView("dashboard");
            }
        }
        List<Company> listCompany = companyService.getAll();
        mv.addObject("listCompany", CompanyMapper.mapperToDTO(listCompany));

        return mv;

    }

    @RequestMapping(method = RequestMethod.POST)
    public String addComputer(@Valid @ModelAttribute("computer") ComputerDTO computerDTO, BindingResult bindingResult) {

        LOGGER.debug("updatating object computerDTO  : {}", computerDTO);

        if (bindingResult.hasErrors()) {
            return "editComputer";
        }

        computerService.update(ComputerMapper.mapperToModel(computerDTO));

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
