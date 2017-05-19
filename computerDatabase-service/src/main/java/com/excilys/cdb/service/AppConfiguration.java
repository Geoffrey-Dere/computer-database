package com.excilys.cdb.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={com.excilys.cdb.persistence.AppConfiguration.class})
@ComponentScan(basePackages = "com.excilys.cdb.service")
public class AppConfiguration {

}
