package com.excilys.cdb.validator;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDAO;

public class ComputerValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";

    private final Validator companyValidator;

    public ComputerValidator(Validator companyValidator) {
        if (companyValidator == null) {
            throw new IllegalArgumentException("The supplied [Validator] is " + "required and must not be null.");
        }
        if (!companyValidator.supports(CompanyDTO.class)) {
            throw new IllegalArgumentException(
                    "The supplied [Validator] must " + "support the validation of [Address] instances.");
        }
        this.companyValidator = companyValidator;
    }

    /**
     * @param name name
     * @return true if the name is valid
     */
    private static String isNameValid(String name) {
        if (name.isEmpty()) {
            return "the name of the computer is empty";
        }

        if (!name.matches(REGEX_NAME)) {
            return "Name must be alphanumeric";
        }
        return "";
    }

    /**
     * @param introduced introduced
     * @param discontinued discontinued
     * @return true if valid
     */
    private static boolean isDatesValid(Optional<LocalDate> introduced, Optional<LocalDate> discontinued) {

        if (!introduced.isPresent() && !discontinued.isPresent()) {
            return true;
        }

        if (!introduced.isPresent() && discontinued.isPresent()) {
            LOGGER.debug("introduced empty ({}} but not discontinued ({})", introduced, discontinued);
            throw new ValidatorException("introduced empty but not discontinued");
        }

        if (discontinued.isPresent()) {
            if (introduced.get().isAfter(discontinued.get())) {
                LOGGER.debug("introduced ({}} is after that the date it was discontinued ({})", introduced,
                        discontinued);
                throw new ValidatorException("introduced after discontinued");
            }
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return ComputerDTO.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object arg, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");

        ComputerDTO computerDTO = (ComputerDTO) arg;

        try {
            e.pushNestedPath("address");
            CompanyDTO companyDTO = new CompanyDTO(0, computerDTO.getCompanyName());
            ValidationUtils.invokeValidator(this.companyValidator, companyDTO, e);
        } finally {
            e.popNestedPath();
        }

        String checkName = isNameValid(computerDTO.getName());
        if (checkName != "") {
            e.reject("name", checkName);
        }
        //TODO
        // custom validator
    }
}
