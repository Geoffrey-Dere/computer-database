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

public class Test extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        if (Login.isUserLogged()) {
            LOGGER.debug("ICIIIII...");
            Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (object instanceof UserAuthenticated) {
                LOGGER.debug("ICIIIII");
                modelAndView.addObject("user", object);

            }
        }

        modelAndView.addObject("isUserLogged", Login.isUserLogged());
    }

}
