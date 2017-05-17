package com.excilys.cdb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dto.ComputerDTO;

public class ComputerValidator implements ConstraintValidator<Computer, ComputerDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);
    private static final String REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    @Override
    public void initialize(final Computer date) {

    }

    @Override
    public boolean isValid(ComputerDTO obj, ConstraintValidatorContext context) {

        String introduced = obj.getIntroduced();
        String discontinued = obj.getDiscontinued();

        if (introduced.isEmpty() && discontinued.isEmpty()) {
            return true;
        }

        if (introduced.isEmpty() && !discontinued.isEmpty()) {
            context.buildConstraintViolationWithTemplate("{computer.intro.null}").addConstraintViolation();
            return false;
        }

        boolean format = true;
        if (!introduced.matches(REGEX)) {
            context.buildConstraintViolationWithTemplate("{date introduced format incorrect}").addConstraintViolation();
            format = false;

        }

        if (!discontinued.matches(REGEX)) {
            context.buildConstraintViolationWithTemplate("{date discontinued format incorrect}")
                    .addConstraintViolation();
            format = false;
        }

        if (!format) {
            return false;
        }
        
        return true;
    }

}
