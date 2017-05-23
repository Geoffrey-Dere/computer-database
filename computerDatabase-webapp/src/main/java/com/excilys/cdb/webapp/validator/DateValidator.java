package com.excilys.cdb.webapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {

    public static final String REGEX = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    @Override
    public void initialize(Date constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.isEmpty() || value.matches(REGEX);
    }

}
