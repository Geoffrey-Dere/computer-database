package com.excilys.computerDatabase.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.dto.CompanyDTO;
import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.service.CompanyServiceImpl;
import com.excilys.computerDatabase.service.ComputerServiceImpl;

public class AddComputer extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);
    /**
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CompanyServiceImpl service = new CompanyServiceImpl();
        List<CompanyDTO> listCompany = service.getAllCompanies();
        req.setAttribute("listCompany", listCompany);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/addComputer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CompanyServiceImpl companyService = new CompanyServiceImpl();
        ComputerServiceImpl service = new ComputerServiceImpl();
        ComputerDTO computerDTO = new ComputerDTO();

        String name = req.getParameter("computerName").trim();
        String introduced = req.getParameter("introduced").trim();
        String discontinued = req.getParameter("discontinued").trim();
        String companyId = req.getParameter("companyID").trim();

        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);

        Long id = 0L;

        try {
            id = Long.parseLong(companyId);
        } catch (java.lang.NumberFormatException e) {
            LOGGER.debug("{} isn't a number", companyId);
        }

        if (id > 0) {
            Optional<CompanyDTO> company = companyService.getCompany(id);
            if (company.isPresent()) {
                computerDTO.setCompany(company.get());
            } else {
                LOGGER.debug("no company found with id", id);
            }
        }

        LOGGER.debug("inserting new object computerDTO  : {}", computerDTO);
        service.addComputer(computerDTO);
    }
}
