package com.excilys.computerDatabase.model;

/**
 * Class company <br/>.
 * Represents a company
 * @author Geoffrey
 */
public class Company {

    /**
     * the id of the company.
     */
    private final long id;

    /**
     * the name of the company.
     */
    private String name;

    /**
     * @param builder the builder
     */
    public Company(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * function equals for Company class <br/>.
     * 2 objects 'Company' are equal if they have the same name and id
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

        return this.id == company.id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Company");
        sb.append("(").append(this.id).append(" , ").append(this.name).append(")");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return name.hashCode() + (int) id;
    }

    public static class Builder {

        private long id;
        private String name;

        /**
         * @param name the name of the company
         */
        public Builder(String name) {
            this.name = name;
        }

        /**
         * @param id the id
         * @return the builder
         */
        public Builder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * @return the new object company
         */
        public Company build() {
            return new Company(this);
        }

    }

}
