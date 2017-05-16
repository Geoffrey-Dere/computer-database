package com.excilys.cdb.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.persistence.CompanyDAO;

public class CompanyValidator implements Validator {

    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";

    /**
     * @param name name
     * @return true if success
     */
    private static String isNameValid(String name) {
        if (name.isEmpty()) {
            return "name is empty";
        }
        if (!name.matches(REGEX_NAME)) {
            return "Company's name must be alphanumeric";
        }
        return "";
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompanyDAO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");

        CompanyDTO companyDTO = (CompanyDTO) obj;
        String checkName = isNameValid(companyDTO.getName());
        if (checkName != "") {
            e.reject("name", checkName);
        }
    }
}
