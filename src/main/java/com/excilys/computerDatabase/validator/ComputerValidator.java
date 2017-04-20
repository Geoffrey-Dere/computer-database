package com.excilys.computerDatabase.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.util.DateFormatter;

public class ComputerValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
    private static final String REGEX_NAME = "^[a-zA-Z0-9 ]*$";

    public static boolean isValid(ComputerDTO computer) {

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
            throw new ValidatorException("Name must be alphanumericr");
        }

        return true;
    }

    private static boolean isDatesValid(String introduced, String discontinued) {

        if (introduced.isEmpty() && discontinued.isEmpty()) {
            return true;
        }

        if (introduced.isEmpty() && !discontinued.isEmpty()) {
            LOGGER.debug("introduced empty ({}} but not discontinued ({})", introduced, discontinued);
            throw new ValidatorException("introduced empty but not discontinued");
        }

        LocalDate LDIntroduced;
        LocalDate LDDiscontinued;

        try {
            LDIntroduced = DateFormatter.stringtoLocalDate(introduced);
        } catch (DateTimeParseException e) {
            LOGGER.debug("The date introduced {} can not be formatted (pattern {})", introduced,
                    DateFormatter.getCurrentPattern());
            throw new ValidatorException("The date introduced can not be formatted");
        }

        if (!discontinued.isEmpty()) {
            try {
                LDDiscontinued = DateFormatter.stringtoLocalDate(discontinued);
            } catch (DateTimeParseException e) {
                LOGGER.debug("The date discontinued {} can not be formatted (pattern {})", discontinued,
                        DateFormatter.getCurrentPattern());
                throw new ValidatorException("The date discontinued can not be formatted");
            }

            if (LDIntroduced.isAfter(LDDiscontinued)) {
                LOGGER.debug("introduced ({}} is after that the date it was discontinued ({})", introduced,
                        discontinued);
                throw new ValidatorException("introduced after discontinued");
            }
        }
        return true;
    }
}
