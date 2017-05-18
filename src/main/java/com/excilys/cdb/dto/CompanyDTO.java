package com.excilys.cdb.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.excilys.cdb.validator.Alphanumeric;

public class CompanyDTO {

    @Min(0)
    private long id;

    @Size(min = 6, max = 15, message = "{size.name}")
    @Alphanumeric
    private String name;

    /**
     */
    public CompanyDTO() {

    }

    /**
     * @param id the id
     * @param name name
     */
    public CompanyDTO(long id, String name) {
        this.id = id;
        this.name = name;
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
        return "ComputerDTO(" + this.id + " , " + this.name + ")";
    }
}
