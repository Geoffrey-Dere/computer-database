package com.excilys.cdb.validator;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Computer;

public class ComputerValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";

    public static boolean isValid(Computer computer) {

        if (computer == null) {
            LOGGER.debug("computer is null");
            throw new ValidatorException("computer is null");
        }
        return isNameValid(computer.getName()) && isDatesValid(computer.getIntroduced(), computer.getDiscontinued());
    }

    private static boolean isNameValid(String name) {
        if (name.isEmpty()) {
            throw new ValidatorException("the name of the computer is empty");
        }

        if (!name.matches(REGEX_NAME)) {
           // throw new ValidatorException("Name must be alphanumeric");
        	return true;
        }
        return true;
    }

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
}
