package com.excilys.cdb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceException;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

public class EditComputer extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);

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

        String id = req.getParameter("id");
        Optional<Computer> computer = Optional.empty();

        if (isInteger(id)) {
            computer = computerService.get(Integer.parseInt(id));
            if (computer.isPresent()) {
                req.setAttribute("computer", ComputerMapper.mapperToDTO(computer.get()));
            }
        }

        List<Company> listCompany = companyService.getAll();
        req.setAttribute("listCompany", CompanyMapper.mapperToDTO(listCompany));

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/editComputer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerDTO computerDTO = new ComputerDTO();

        String computerId = req.getParameter("id").trim();
        String name = req.getParameter("computerName").trim();
        String introduced = req.getParameter("introduced").trim();
        String discontinued = req.getParameter("discontinued").trim();
        String companyId = req.getParameter("companyID").trim();

        if (isInteger(computerId)) {
            computerDTO.setId(Long.parseLong(computerId));
        }

        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);

        try {
            computerDTO.setCompanyId(Long.parseLong(companyId));
        } catch (java.lang.NumberFormatException e) {
            LOGGER.debug("{} isn't a number", companyId);
        }

        LOGGER.trace("updatating new object computerDTO  : {}", computerDTO);

        try {
            computerService.update(ComputerMapper.mapperToModel(computerDTO));
        } catch (ServiceException e) {
            req.setAttribute("error", e.getMessage());
        }
        resp.sendRedirect("dashboard");
    }

    /**
     * @param s the string
     * @return success if it is a number
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
