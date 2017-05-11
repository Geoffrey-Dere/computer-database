package com.excilys.cdb.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;

public class CompanyValidator {

    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyValidator.class);

    /**
     * @param company company
     * @return bool success
     */
    public static boolean isValid(Company company) {

        if (company == null) {
            LOGGER.debug("company is null");
            throw new ValidatorException("no company selected");
        }
        return isNameValid(company.getName());
    }

    /**
     * @param name name
     * @return true if success
     */
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
