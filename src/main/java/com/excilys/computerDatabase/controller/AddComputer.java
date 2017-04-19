package com.excilys.computerDatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDatabase.dto.ComputerDTO;
import com.excilys.computerDatabase.service.ComputerServiceImpl;

public class AddComputer extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/addComputer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("computerName");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);
        
        ComputerServiceImpl service = new ComputerServiceImpl();
        service.addComputer(computerDTO) ;
   
        
        
        
    }

    
    
    
}
