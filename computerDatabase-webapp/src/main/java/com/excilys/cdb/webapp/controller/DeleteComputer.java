package com.excilys.cdb.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputer.class);
    private static final String PARAM_DELETE = "selection";

    @Autowired
    private ComputerService computerService;

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@RequestParam(value = PARAM_DELETE) String selection) {

        LOGGER.info("Controller delete computer with param {}", selection);

        String[] tabSelection = selection.split(",");
        List<Long> listId = new ArrayList<>(tabSelection.length);

        for (int i = 0; i < tabSelection.length; i++) {
            if (isInteger(tabSelection[i]) > 0) {
                listId.add(Long.parseLong(tabSelection[i]));
            }
        }
        computerService.remove(listId);
        return "redirect:/";
    }

    /**
     * @param s the string
     * @return boolean if he is a number
     */
    private int isInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        } catch (NullPointerException e) {
            return -1;
        }
    }
}
