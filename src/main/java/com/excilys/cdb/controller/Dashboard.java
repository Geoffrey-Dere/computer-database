package com.excilys.cdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

public class Dashboard extends HttpServlet {

    private static final int LIMIT_DEFAULT = 10;
    private static final String URI_PAGE = "page";
    private static final String URI_LIMIT = "limit";
    private static final String URI_SEARCH = "search";
    private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);

    private static final String URI_ORDER = "order";
    private static final String ORDER_DEFAULT = "ASC";

    private static final String URI_COLUMN = "column";
    private static final String COLUMN_ORDER = "computer.name";

    private static final long serialVersionUID = 1L;

    @Autowired
    private ComputerServiceImpl computerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int limit = LIMIT_DEFAULT;
        String column = COLUMN_ORDER;
        String order = ORDER_DEFAULT;

        int currentPage = 1;
        int size = 0;
        Pager<Computer> pager;

        // get the current page by (url parameter)
        if (isInteger(req.getParameter(URI_PAGE)) && Integer.parseInt(req.getParameter(URI_PAGE)) >= 1) {
            currentPage = Integer.parseInt(req.getParameter(URI_PAGE));
        }

        // get the limit
        if (isInteger(req.getParameter(URI_LIMIT))) {
            limit = Integer.parseInt(req.getParameter(URI_LIMIT));
        }

        if (req.getParameter(URI_ORDER) != null) {
            order = req.getParameter(URI_ORDER);
        }

        if (req.getParameter(URI_COLUMN) != null) {
            column = req.getParameter(URI_COLUMN);
        }

        // search
        if (req.getParameter(URI_SEARCH) != null) {
            String regex = req.getParameter(URI_SEARCH);
            req.setAttribute("search", regex);
            pager = computerService.getPage(limit, (currentPage - 1) * limit, regex, column, order);
            size = computerService.count(regex);
        } else {
            pager = computerService.getPage(limit, (currentPage - 1) * limit, column, order);
            size = computerService.count();
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

        String[] selection = req.getParameter("selection").split(",");

        List<Integer> listId = new ArrayList<>(selection.length);

        for (int i = 0; i < selection.length; i++) {

            if (isInteger(selection[i])) {
                listId.add(Integer.parseInt(selection[i]));
            } else {
                LOGGER.debug("{} not a number", selection[i]);
            }
        }
        computerService.remove(listId);
        doGet(req, resp);
    }

    /**
     * @param s the string
     * @return boolean if he is a number
     */
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
