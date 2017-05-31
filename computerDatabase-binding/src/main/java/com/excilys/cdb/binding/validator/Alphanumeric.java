package com.excilys.cdb.binding.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AlphanumericValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Alphanumeric {

    /**
     * @return the message
     */
    String message() default "{alphanumeric.error}";

    /**
     * @return table of class
     */
    Class<?>[] groups() default {};

    /**
     * @return class
     */
    Class<? extends Payload>[] payload() default {};
}
