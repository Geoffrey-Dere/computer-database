package com.excilys.cdb.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.excilys.cdb")
public class AppConfiguration extends WebMvcConfigurerAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);
    private static final String PATH_FILE = "config/hikari.properties";

    /**
     * @return the dataSource
     */
    private DataSource dataSource() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();
        try (InputStream resourceStream = classLoader.getResourceAsStream(PATH_FILE)) {
            prop.load(resourceStream);
            HikariConfig config = new HikariConfig(prop);
            HikariDataSource hikari = new HikariDataSource(config);
            return hikari;
        } catch (IOException e) {
            LOGGER.error("can't read the file {} ", PATH_FILE);
            throw new ExceptionDAO("error to read the file " + PATH_FILE, e);
        }
    }

    /**
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//          .addResourceHandler("/js/**", "/css/**", "/fonts/**")
//          .addResourceLocations("/js/", "/css/", "/fonts/")
//          .setCachePeriod(3600)
//          .resourceChain(true)
//          .addResolver(new PathResourceResolver());
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
   
 
}