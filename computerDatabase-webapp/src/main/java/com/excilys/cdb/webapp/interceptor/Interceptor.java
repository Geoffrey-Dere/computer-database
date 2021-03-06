package com.excilys.cdb.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.excilys.cdb.service.UserAuthenticated;
import com.excilys.cdb.webapp.controller.Login;

public class Interceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (Login.isUserLogged()) {
            Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (object instanceof UserAuthenticated) {
                request.setAttribute("user", object);
            }
        }
        request.setAttribute("isUserLogged", Login.isUserLogged());

        return true;
    }
}
