package com.excilys.computerDatabase.dto;

public class ComputerDTO {

    private Long id ;
    private String name ;
    private String introduced ;
    private String discontinued ;
    private CompanyDTO company ;
    
    
    public ComputerDTO(){
        
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
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


    public CompanyDTO getCompany() {
        return company;
    }


    public void setCompany(CompanyDTO company) {
        this.company = company;
    }
    
    
    
    
}
