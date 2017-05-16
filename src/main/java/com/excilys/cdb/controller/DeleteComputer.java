package com.excilys.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.impl.ComputerServiceImpl;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputer.class);
    private static final String PARAM_DELETE = "selection";

    @Autowired
    private ComputerServiceImpl computerService;

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@RequestParam(value = PARAM_DELETE) String selection) {

        String[] tabSelection = selection.split(",");
        List<Integer> listId = new ArrayList<>(tabSelection.length);

        for (int i = 0; i < tabSelection.length; i++) {
            if (isInteger(tabSelection[i])) {
                listId.add(Integer.parseInt(tabSelection[i]));
            } else {
                LOGGER.debug("{} not a number", tabSelection[i]);
            }
        }
        computerService.remove(listId);
        return "redirect:/";
    }

    /**
     * @param s the string
     * @return boolean if he is a number
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
