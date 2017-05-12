package com.excilys.cdb.controller;

import java.io.IOException;
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

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.ServiceException;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

public class AddComputer extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);
    /**
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ComputerServiceImpl computerService;
    @Autowired
    private CompanyServiceImpl companyService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Company> listCompany = companyService.getAll();
        req.setAttribute("listCompany", CompanyMapper.mapperToDTO(listCompany));
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/addComputer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerDTO computerDTO = new ComputerDTO();

        String name = (req.getParameter("computerName") != null) ? req.getParameter("computerName").trim() : "";
        String introduced = (req.getParameter("introduced") != null) ? req.getParameter("introduced").trim() : "";
        String discontinued = (req.getParameter("discontinued") != null) ? req.getParameter("discontinued").trim() : "";
        String companyId = req.getParameter("companyID").trim();

        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);

        long id = 0;
        try {
            id = Long.parseLong(companyId);
        } catch (java.lang.NumberFormatException e) {
            LOGGER.debug("{} isn't a number", companyId);
        }

        try {
            LOGGER.debug("inserting new object computerDTO  : {}", computerDTO);
            computerService.add(ComputerMapper.mapperToModel(computerDTO));
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
        }
        resp.sendRedirect("dashboard");
    }
}
