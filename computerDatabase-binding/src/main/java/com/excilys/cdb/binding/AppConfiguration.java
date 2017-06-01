package com.excilys.cdb.binding;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { com.excilys.cdb.binding.validator.PagerValidator.class })
public class AppConfiguration {

}
