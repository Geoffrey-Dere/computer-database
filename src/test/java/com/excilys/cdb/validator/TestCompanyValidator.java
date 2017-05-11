package com.excilys.cdb.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.model.Company;

public class TestCompanyValidator {

    Company company;

    @Before
    public void before() {
        company = Mockito.mock(Company.class);
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidNull() {
        CompanyValidator.isValid(null);
    }

    @Test(expected = ValidatorException.class)
    public void TestIsValidNameError() {
        Mockito.when(company.getName()).thenReturn("name@<false");
        CompanyValidator.isValid(company);
    }

    @Test
    public void TestIsValid() {
        Mockito.when(company.getName()).thenReturn("name of the computer");
        assertTrue(CompanyValidator.isValid(company));

    }

}
