package com.excilys.cdb.dto;

import javax.validation.constraints.Size;

import com.excilys.cdb.validator.Alphanumeric;
import com.excilys.cdb.validator.Computer;

@Computer
public class ComputerDTO {

    private long id;
    
    @Size(min=6, max=15, message ="{size.name}")
    @Alphanumeric
    private String name;
    
    private String introduced = "";
    private String discontinued = "";
    private long companyId;
    private String companyName = "";

    /**
     */
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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {

        String date1 = (introduced.isEmpty() ? "empty" : introduced);
        String date2 = (discontinued.isEmpty() ? "empty" : introduced);

        return "ComputerDTO(" + id + " , " + name + " , " + date1 + " , " + date2 + "  " + companyId + " , "
                + companyName + " ) ";
    }
}
