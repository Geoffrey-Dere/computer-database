package com.excilys.cdb.webapp.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.excilys.cdb.core.util.DateFormatter;

public class LanguageInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, javax.servlet.http.HttpServletResponse response,
            Object handler) throws ServletException {
        super.preHandle(request, response, handler);

        String language = request.getParameter(getParamName());
        if (language != null) {
            DateFormatter.setFormatter(language);
        }

        return true;
    }
}
