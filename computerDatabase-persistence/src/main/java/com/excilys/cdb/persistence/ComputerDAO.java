package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Pager;

public interface ComputerDAO extends DAO<Computer>{

    public void findAll(Pager<Computer> page);
    public boolean deleteByCompany(long companyId);
    public void remove(List<Long> listId);
    public boolean deleteById(long id);
    public List<Long> getIdByCompany(long companyId);
    public int count() ;
    public int count(String name) ;
    
}
