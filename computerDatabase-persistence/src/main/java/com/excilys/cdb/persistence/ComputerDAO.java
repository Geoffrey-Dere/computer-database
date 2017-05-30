package com.excilys.cdb.persistence;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface ComputerDAO extends DAO<Computer>{

    public boolean deleteById(long id);
    public List<Long> getIdByCompany(long companyId);
    public List<Computer> findAll(long limit, long offset, String column, String order);
    public List<Computer> findByName(long limit, long offset, String regex, String column, String order);
    public int count() ;
    public int count(String name) ;
    public boolean deleteByCompany(long companyId);
    public void remove(List<Integer> listId);
    
}
