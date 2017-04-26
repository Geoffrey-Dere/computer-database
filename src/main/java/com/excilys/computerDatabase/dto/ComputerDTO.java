package com.excilys.computerDatabase.dto;

import java.util.Optional;

public class ComputerDTO {

    private long id;
    private String name;
    private String introduced = "";
    private String discontinued = "";
    private CompanyDTO company;

    public ComputerDTO() {

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

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public Optional<CompanyDTO> getCompany() {
        return Optional.ofNullable(company);
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    @Override
    public String toString() {

        String date1 = (introduced.isEmpty() ? "empty" : introduced);
        String date2 = (discontinued.isEmpty() ? "empty" : introduced);

        return "ComputerDTO(" + id + " , " + name + " , " + date1 + " , " + date2 + "  " + company + " ) ";
    }
}
