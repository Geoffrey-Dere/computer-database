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

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.mapper.CompanyMapper;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.service.CompanyServiceImpl;
import com.excilys.computerDatabase.service.ComputerServiceImpl;
import com.excilys.computerDatabase.service.ServiceException;

public class EditComputer extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerServiceImpl service = new ComputerServiceImpl();

        String id = req.getParameter("id");
        Optional<Computer> computer = Optional.empty();

        if (isInteger(id)) {
            computer = service.getComputer(Integer.parseInt(id));
            if (computer.isPresent()) {
                req.setAttribute("computer", ComputerMapper.mapperToDTO(computer.get()));
            }
        }

        CompanyServiceImpl Companyservice = new CompanyServiceImpl();
        List<Company> listCompany = Companyservice.getAllCompanies();
        req.setAttribute("listCompany", CompanyMapper.mapperToDTO(listCompany));

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/editComputer.jsp").forward(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CompanyServiceImpl companyService = new CompanyServiceImpl();
        ComputerServiceImpl service = new ComputerServiceImpl();
        ComputerDTO computerDTO = new ComputerDTO();

        String computer_id = req.getParameter("id").trim();
        String name = req.getParameter("computerName").trim();
        String introduced = req.getParameter("introduced").trim();
        String discontinued = req.getParameter("discontinued").trim();
        String companyId = req.getParameter("companyID").trim();

       
        if (isInteger(computer_id)) {
            computerDTO.setId(Long.parseLong(computer_id));
        }

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
            Optional<Company> company = companyService.getCompany(id);
            if (company.isPresent()) {
                computerDTO.setCompany(CompanyMapper.mapperToDTO(company.get()));
            } else {
                LOGGER.debug("no company found with id", id);
            }
        }

        LOGGER.trace("updatating new object computerDTO  : {}", computerDTO);

        try {
            service.updateComputer(ComputerMapper.mapperToModel(computerDTO));
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
        }
        resp.sendRedirect("dashboard");
    }
}
