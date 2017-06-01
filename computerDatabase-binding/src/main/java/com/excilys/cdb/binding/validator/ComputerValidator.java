package com.excilys.cdb.binding.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.binding.dto.ComputerDTO;
import com.excilys.cdb.core.util.DateFormatter;

public class ComputerValidator implements ConstraintValidator<Computer, ComputerDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);

    @Override
    public void initialize(final Computer date) {

    }

    @Override
    public boolean isValid(ComputerDTO obj, ConstraintValidatorContext context) {

        String introduced = obj.getIntroduced();
        String discontinued = obj.getDiscontinued();
        //
        if (discontinued.isEmpty()) {
            return true;
        }

        if (!introduced.matches(DateValidator.REGEX) && !introduced.isEmpty()) {
            return false;
        }
        if (!discontinued.matches(DateValidator.REGEX) && !discontinued.isEmpty()) {
            return false;
        }

        if (introduced.isEmpty() && !discontinued.isEmpty()) {
            context.buildConstraintViolationWithTemplate("{addComputer.computer.intro.isNull}")
                    .addConstraintViolation();
            return false;
        }

        LocalDate intro = DateFormatter.stringtoLocalDate(introduced);
        LocalDate discon = DateFormatter.stringtoLocalDate(discontinued);
        LOGGER.debug("TEST");

        if (intro.isAfter(discon)) {
            context.buildConstraintViolationWithTemplate("{addComputer.computer.intro.isAfter}")
                    .addConstraintViolation();
        }

        return true;
    }
}