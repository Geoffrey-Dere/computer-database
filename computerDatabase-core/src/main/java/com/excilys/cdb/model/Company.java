package com.excilys.cdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class company <br/>
 * . Represents a company
 */

@Entity
@Table(name = "company")
public class Company implements Serializable {

    /**
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    /**
    */
    public Company() {

    }

    /**
     * @param name name
     */
    public Company(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * function equals for Company class <br/>
     * . 2 objects 'Company' are equal if they have the same name and id
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Company company = (Company) obj;

        if (this.name != null ? !this.name.equals(company.name) : company.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Company");
        sb.append("(").append(this.id).append(" , ").append(this.name).append(")");

        return sb.toString();
    }

    @Override
    public int hashCode() {

        final int prime = 29;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    public static class Builder {

        private Company company;

        /**
         * @param name the name of the company
         */
        public Builder(String name) {
            company = new Company(name);
        }

        /**
         * @param id the id
         * @return the builder
         */
        public Builder id(long id) {
            company.setId(id);
            return this;
        }

        /**
         * @return the new object company
         */
        public Company build() {
            return company;
        }

    }

}
