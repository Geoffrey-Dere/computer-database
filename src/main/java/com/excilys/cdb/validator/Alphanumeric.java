package com.excilys.cdb.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = AlphanumericValidator.class)
@Target( { ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Alphanumeric {

    String message() default "{alphanumeric.error}";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
