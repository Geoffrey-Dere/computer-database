package com.excilys.cdb.persistence;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    /**
     * Method of creation.
     * @param obj the object to create
     * @return boolean true if the element has been created, otherwise false
     */
    boolean create(T obj);

    /**
     * Removal Method.
     * @param obj the object to delete
     * @return boolean true if the element has been deleted, otherwise false
     */
    boolean delete(T obj);

    /**
     * @param obj the object
     * @return boolean true if the element has been modified, otherwise false
     */
    boolean update(T obj);

    /**
     * Returns an element of the database identified by an id.
     * @param id the id
     * @return T the object that is returned
     */
    Optional<T> find(long id);

    /**
     * Returns all the elements of a table in the database.
     * @return List<T> the collection of theses objects
     */

}
