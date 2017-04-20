package com.excilys.computerDatabase.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.computerDatabase.dto.CompanyDTO;

public class TestCompanyValidator {

    CompanyDTO companyDTO;

    @Before
    public void before() {
        companyDTO = Mockito.mock(CompanyDTO.class);
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidNull() {
        CompanyValidator.isValid(null);
    }

    @Test(expected = ValidatorException.class)
    public void TestIsValidNameError() {
        Mockito.when(companyDTO.getName()).thenReturn("name@<false");
        CompanyValidator.isValid(companyDTO);
    }

    @Test
    public void TestIsValid() {
        Mockito.when(companyDTO.getName()).thenReturn("name of the computer");
        assertTrue(CompanyValidator.isValid(companyDTO));

    }

}
