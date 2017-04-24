package com.excilys.computerDatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Pager;
import com.excilys.computerDatabase.service.ComputerServiceImpl;

public class Dashboard extends HttpServlet {

    private static final int LIMIT_DEFAULT = 10;
    private static final String URI_PAGE = "page";
    private static final String URI_LIMIT = "limit";

    /**
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerServiceImpl computerService = new ComputerServiceImpl();

        int size = computerService.count();

        // get the current page by (url parameter)
        if (isInteger(req.getParameter(URI_PAGE))) {
            req.setAttribute("currentPage", req.getParameter("page"));
        } else {
            req.setAttribute("currentPage", "1");
        }

        int limit = LIMIT_DEFAULT;
        // get the limit
        if (isInteger(req.getParameter(URI_LIMIT))) {
            limit = Integer.parseInt(req.getParameter(URI_LIMIT));
            req.setAttribute("limit", req.getParameter(URI_LIMIT));
        } else {
            req.setAttribute("limit", LIMIT_DEFAULT);
        }

        int currentPage = Integer.parseInt((String) req.getAttribute("currentPage"));

        Pager<Computer> pager = computerService.getPageComputer(limit, (currentPage - 1) * limit);
        
        req.setAttribute("listComputer", ComputerMapper.mapperToDTO(pager.getListEntity()));
        req.setAttribute("limit", limit);
        req.setAttribute("maxPages", Math.ceil((double) size / limit));
        req.setAttribute("uriPage", URI_PAGE);
        req.setAttribute("uriLimit", URI_LIMIT);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(req, resp);

        System.out.println();
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
