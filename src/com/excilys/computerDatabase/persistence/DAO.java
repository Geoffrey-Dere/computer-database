package com.excilys.computerDatabase.persistence;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

	protected Connection connection = null;

	public DAO(Connection connection) {
		this.connection = connection ;		
	}

	/**
	 * Method of creation
	 * @param obj
	 * @return boolean, , true if the element has been created, otherwise false
	 */
	public abstract boolean create(T obj);

	/**
	 * Removal Method
	 * @param obj
	 * @return boolean, true if the element has been deleted, otherwise false
	 */
	public abstract boolean delete(T obj);

	/**
	 * @param obj
	 * @return boolean, true if the element has been modified, otherwise false
	 */
	public abstract boolean update(T obj);

	/**
	 * Returns an element of the database identified by an id
	 * @param id, the id 
	 * @return T, the object that is returned
	 */
	public abstract T find(int id);


	/**
	 * Returns all the elements of a table in the database
	 * @return List<T>, the collection of theses objects
	 */
	public abstract List<T> findAll();

}
