package com.excilys.cdb.service;


import java.util.Optional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;

public interface ComputerService {

    /**
     * get all computers from the database.
     * @return the list
     */
    Pager<Computer> getAll();

    /**
     * return a computer from the database identified by the id.
     * @param id the id
     * @return the computer or null if no one have this id
     */
    Optional<Computer> get(long id);

    /**
     * Add a computer on the database.
     * @param c the computer
     * @return true if the computer has been added and false otherwise
     */
    boolean add(Computer c);

    /**
     * update a computer on the database.
     * @param c  the computer
     * @return true if the computer has been updated and false otherwise
     */
    boolean update(Computer c);

    /**
     * delete a computer on the database.
     * @param c  the computer
     * @return true if the computer has been updated and false otherwise
     */
    boolean remove(Computer c);

    /**
     * @param limit the limit
     * @param offset the offset
     * @return the pager with the companies
     */
    Pager<Computer> getPage(long limit, long offset, String column, String order);

}
