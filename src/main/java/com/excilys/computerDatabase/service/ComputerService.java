package com.excilys.computerDatabase.service;

import java.util.List;
import java.util.Optional;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.model.Pager;

public interface ComputerService {

    /**
     * get all computers from the database.
     * @return the list
     */
    List<Computer> getAllComputers();

    /**
     * return a computer from the database identified by the id.
     * @param id the id
     * @return the computer or null if no one have this id
     */
    Optional<Computer> getComputer(long id);

    /**
     * Add a computer on the database.
     * @param c the computer
     * @return true if the computer has been added and false otherwise
     */
    boolean addComputer(Computer c);

    /**
     * update a computer on the database.
     * @param c  the computer
     * @return true if the computer has been updated and false otherwise
     */
    boolean updateComputer(Computer c);

    /**
     * delete a computer on the database.
     * @param c  the computer
     * @return true if the computer has been updated and false otherwise
     */
    boolean removeComputer(Computer c);

    /**
     * @param limit the limit
     * @param offset the offset
     * @return the pager with the companies
     */
    Pager<Computer> getPageComputer(int limit, int offset);

}