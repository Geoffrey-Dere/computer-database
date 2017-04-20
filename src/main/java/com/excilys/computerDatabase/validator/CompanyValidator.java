package com.excilys.computerDatabase.validator;

import com.excilys.computerDatabase.dto.CompanyDTO;

public class CompanyValidator {

    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";
    
    public static boolean isValid(CompanyDTO company) {
        return isNameValid(company.getName());

    }

    private static boolean isNameValid(String name) {
        if (name.isEmpty()) {
            throw new ValidatorException("the name of the company is empty");
        }
        if(!name.matches(REGEX_NAME)){
            throw new ValidatorException("Company's name must be alphanumeric");
        }
        return true;
    }

}
