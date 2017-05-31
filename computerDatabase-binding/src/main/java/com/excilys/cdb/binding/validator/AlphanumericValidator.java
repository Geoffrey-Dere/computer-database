package com.excilys.cdb.binding.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {

    private static final String REGEX = "^[a-zA-Z0-9 ]*$";

    @Override
    public void initialize(Alphanumeric constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(REGEX);
    }

}
