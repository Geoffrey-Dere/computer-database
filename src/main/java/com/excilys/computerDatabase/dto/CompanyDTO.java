package com.excilys.computerDatabase.dto;

public class CompanyDTO {

    private long id;
    private String name;

    /**
     * @param builder the builder
     */
    public CompanyDTO() {

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

    @Override
    public String toString() {
        return "ComputerDTO("+this.id+" , " + this.name + ")";    
    }}
