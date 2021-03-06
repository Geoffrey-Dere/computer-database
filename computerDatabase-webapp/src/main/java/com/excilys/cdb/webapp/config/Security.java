package com.excilys.cdb.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @param auth AuthenticationManagerBuilder
     * @throws Exception exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * @return DigestAuthenticationEntryPoint
     */
    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setKey("key");
        digestAuthenticationEntryPoint.setRealmName("Digest WF Realm");
        return digestAuthenticationEntryPoint;
    }

    /**
     * @param digestAuthenticationEntryPoint digestAuthenticationEntryPoint
     * @return DigestAuthenticationFilte
     */
    @Bean
    public DigestAuthenticationFilter digestAuthenticationFilter(
            DigestAuthenticationEntryPoint digestAuthenticationEntryPoint) {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
        digestAuthenticationFilter.setUserDetailsService(userDetailsService());
        return digestAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/ressources/**", "/i18/**", "/js/**", "/fonts/**", "/css/**").permitAll()
                .antMatchers("/login*").anonymous()
                .antMatchers("/dashboard", "/editComputer", "/deleteComputer", "/add").access("hasAuthority('ADMIN') ")
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login")            .defaultSuccessUrl("/dashboard").and().logout().and().exceptionHandling().accessDeniedPage("/403");
        
        http.csrf().ignoringAntMatchers("/api/**");
    }

}
