package com.excilys.cdb.model;

import java.time.LocalDate;
import java.util.Optional;

import com.google.common.annotations.Beta;


public class Computer {

    private long id;

    /**
     * the name of the computer.
     */
    private String name;

    /**
     * the date that the computer was introduced, can be null.
     */
    private LocalDate introduced = null;

    /**
     * the date that the computer was introduced, can be null only if the date.
     * it was introduced is also null.
     */
    private LocalDate discontinued = null;

    private Company company;

    /**
     */
    public Computer() {

    }

    /**
     * @param name name
     */
    public Computer(String name) {
        this.name = name;
    }

    public Optional<LocalDate> getDiscontinued() {
        return Optional.ofNullable(discontinued);
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * @param intro introduced
     * @param discon discontinued
     */
    public void setDate(LocalDate intro, LocalDate discon) {
        this.introduced = intro;
        this.discontinued = discon;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
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

    public Optional<LocalDate> getIntroduced() {
        return Optional.ofNullable(introduced);
    }

    public Optional<Company> getCompany() {
        return Optional.ofNullable(this.company);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * function equals for Computer class <br/>
     * . 2 objects are equal if they have the same name and id
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Computer company = (Computer) obj;

        if (this.name != null ? !this.name.equals(company.name) : company.name != null) {
            return false;
        }

        if (this.introduced != null ? !this.introduced.equals(company.introduced) : company.introduced != null) {
            return false;
        }

        if (this.discontinued != null ? !this.discontinued.equals(company.discontinued)
                : company.discontinued != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        final int prime = 73;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (introduced == null ? 0 : introduced.hashCode());
        result = prime * result + (discontinued == null ? 0 : discontinued.hashCode());
        return result;
    }

    /**
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Computer(" + id + " , " + name + " , " + introduced + " , " + discontinued + "  " + company + " ) ";
    }

    public static class BuilderComputer {

        private Computer computer;

        /**
         * @param name name
         */
        public BuilderComputer(String name) {
            this.computer = new Computer(name);
        }

        /**
         * @param id id
         * @return the builder
         */
        public BuilderComputer id(long id) {
            this.computer.setId(id);
            return this;
        }

        /**
         * @param introduced introduced
         * @return the builder
         */
        public BuilderComputer introduced(LocalDate introduced) {
            this.computer.setIntroduced(introduced);
            return this;
        }

        /**
         * @param discontinued discontinued
         * @return the builder
         */
        public BuilderComputer discontinued(LocalDate discontinued) {
            this.computer.setDiscontinued(discontinued);
            return this;
        }

        /**
         * @param company the company id
         * @return the builder
         */
        public BuilderComputer company(Company company) {
            this.computer.setCompany(company);
            return this;
        }

        /**
         * @return the new computer
         */
        public Computer build() {
            return this.computer;
        }

    }
}
