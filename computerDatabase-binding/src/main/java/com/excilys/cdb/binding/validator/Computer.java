package com.excilys.cdb.binding.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ComputerValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Computer {

    /**
     * @return the message
     */
    String message() default "";

    /**
     * @return table of class
     */
    Class<?>[] groups() default {};

    /**
     * @return class
     */
    Class<? extends Payload>[] payload() default {};
}
