package com.excilys.cdb.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class ErrorController {

    /**
     * @param httpRequest httpRequest
     * @return the error page 404
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView renderErrorPageNotFound(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("404");
        return errorPage;
    }

    /**
     * @param httpRequest httpRequest
     * @return the error page 403
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView renderErrorAccessDenied(HttpServletRequest httpRequest) {
        System.out.println("testtttttt");

        ModelAndView errorPage = new ModelAndView("403");
        return errorPage;
    }

    /**
     * @param httpRequest httpRequest
     * @return the error page 500
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("500");
        return errorPage;
    }
}
