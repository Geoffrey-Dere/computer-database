package com.excilys.computerDatabase.model;

import java.time.LocalDate;
import java.util.Optional;

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
     * @param builder company builder
     */
    private Computer(BuilderComputer builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.company = builder.company;
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

    /**
     * function equals for Computer class <br/>.
     * 2 objects are equal if they have the same name and id
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

        return this.id == company.id;
    }

    /**
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Computer(" + id + " , " + name + " , " + introduced + " , " + discontinued + " ,\n\t " + company
                + " ) ";
    }

    @Override
    public int hashCode() {
        return introduced.hashCode() + this.discontinued.hashCode() + name.hashCode();
    }

    public static class BuilderComputer {

        private long id;
        private String name;
        private LocalDate introduced = null;
        private LocalDate discontinued = null;
        private Company company = null;

        /**
         * @param name name
         */
        public BuilderComputer(String name) {
            this.name = name;
        }

        /**
         * @param id id
         * @return the builder
         */
        public BuilderComputer id(long id) {
            this.id = id;
            return this;
        }

        /**
         * @param introduced introduced
         * @return the builder
         */
        public BuilderComputer introduced(LocalDate introduced) {
            this.introduced = introduced;
            return this;
        }

        /**
         * @param discontinued discontinued
         * @return the builder
         */
        public BuilderComputer discontinued(LocalDate discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        /**
         * @param company the company id
         * @return the builder
         */
        public BuilderComputer company(Company company) {
            this.company = company;
            return this;
        }

        /**
         * @return the new computer
         */
        public Computer build() {
            return new Computer(this);
        }

    }
}
