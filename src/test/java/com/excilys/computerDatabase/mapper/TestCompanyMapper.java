package com.excilys.computerDatabase.mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.computerDatabase.dto.CompanyDTO;
import com.excilys.computerDatabase.model.Company;

public class TestCompanyMapper {

    private Company company;

    @Before
    public void before() {
        company = Mockito.mock(Company.class);
    }

    @Test
    public void TestMapperToDTO() {

        Mockito.when(company.getName()).thenReturn("my name");
        Mockito.when(company.getId()).thenReturn(1L);

        CompanyDTO companyDTO = CompanyMapper.mapperToDTO(company);
        assertEquals(companyDTO.getName(), "my name");
        assertEquals(companyDTO.getId(), (Long) 1l);
    }
}
