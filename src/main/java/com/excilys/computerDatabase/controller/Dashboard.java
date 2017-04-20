package com.excilys.computerDatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.service.ComputerServiceImpl;

public class Dashboard extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerServiceImpl computerService = new ComputerServiceImpl();
        req.setAttribute("page", computerService.getAllComputers());

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(req, resp);
    }

}
