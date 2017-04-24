package com.excilys.computerDatabase.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dto.CompanyDTO;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.ConnectionManager;

public class CompanyValidator {

    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    public static boolean isValid(Company company) {

        if (company == null) {
            LOGGER.debug("company is null");
            throw new ValidatorException("no company selected");
        }
        return isNameValid(company.getName());
    }

    private static boolean isNameValid(String name) {
        if (name.isEmpty()) {
            throw new ValidatorException("the name of the company is empty");
        }
        if (!name.matches(REGEX_NAME)) {
            throw new ValidatorException("Company's name must be alphanumeric");
        }
        return true;
    }
}
