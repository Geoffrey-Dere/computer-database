package com.excilys.computerDatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.persistence.ConnectionManager;
import com.excilys.computerDatabase.service.ComputerServiceImpl;

public class Dashboard extends HttpServlet {

    private static final int LIMIT_DEFAULT = 10;
    private static final String URI_PAGE = "page";
    private static final String URI_LIMIT = "limit";
    private static final String URI_SEARCH = "search";
    private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);

    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerServiceImpl computerService = new ComputerServiceImpl();
        int limit = LIMIT_DEFAULT;
        int currentPage = 1;
        int size = 0 ;
        Pager<Computer> pager;

        // get the current page by (url parameter)
        if (isInteger(req.getParameter(URI_PAGE)) && Integer.parseInt(req.getParameter(URI_PAGE)) >= 1) {
            currentPage = Integer.parseInt(req.getParameter(URI_PAGE));
        }

        // get the limit
        if (isInteger(req.getParameter(URI_LIMIT))) {
            limit = Integer.parseInt(req.getParameter(URI_LIMIT));
        }

        // search
        if (req.getParameter(URI_SEARCH) != null) {
            String regex = req.getParameter(URI_SEARCH);
            req.setAttribute("search", regex);
            pager = computerService.getPageComputer(limit, (currentPage - 1) * limit, regex);
            size = computerService.count(regex) ;
        } else {
            pager = computerService.getPageComputer(limit, (currentPage - 1) * limit);
            size = computerService.count() ;
        }

        req.setAttribute("listComputer", ComputerMapper.mapperToDTO(pager.getListEntity()));
        req.setAttribute("limit", limit);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("maxPages", Math.ceil((double) size / limit));
        req.setAttribute("uriPage", URI_PAGE);
        req.setAttribute("uriLimit", URI_LIMIT);
        req.setAttribute("uriSearch", URI_SEARCH);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerServiceImpl computerService = new ComputerServiceImpl();

        String[] selection = req.getParameter("selection").split(",");

        for (int i = 0; i < selection.length; i++) {

            if (isInteger(selection[i])) {
                computerService.removeComputer(Integer.parseInt(selection[i]));
            } else {
                LOGGER.debug("{} not a number", selection[i]);
            }
        }
        
        doGet(req, resp);
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
