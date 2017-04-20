package com.excilys.computerDatabase.validator;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.computerDatabase.dto.ComputerDTO;

public class TestComputerValidator {

    ComputerDTO ComputerDTO;

    @Before
    public void before() {
        ComputerDTO = Mockito.mock(ComputerDTO.class);
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidNull() {
        CompanyValidator.isValid(null);
    }

    @Test(expected = ValidatorException.class)
    public void TestIsValidNameError() {
        Mockito.when(ComputerDTO.getName()).thenReturn("name@<false");
        ComputerValidator.isValid(ComputerDTO);
    }

    @Test
    public void TestIsValidName() {
        Mockito.when(ComputerDTO.getName()).thenReturn("name of the computer");
        Mockito.when(ComputerDTO.getIntroduced()).thenReturn("");
        Mockito.when(ComputerDTO.getDiscontinued()).thenReturn("");
        assertTrue(ComputerValidator.isValid(ComputerDTO));
    }

    @Test
    public void TestIsValidIntro() {
        Mockito.when(ComputerDTO.getName()).thenReturn("name of the computer");
        Mockito.when(ComputerDTO.getIntroduced()).thenReturn("1992-02-02");
        Mockito.when(ComputerDTO.getDiscontinued()).thenReturn("");
        assertTrue(ComputerValidator.isValid(ComputerDTO));
    }

    @Test(expected = ValidatorException.class)
    public void TestIsValidInvalidDates() {
        Mockito.when(ComputerDTO.getName()).thenReturn("name of the computer");
        Mockito.when(ComputerDTO.getIntroduced()).thenReturn("1992-02-02");
        Mockito.when(ComputerDTO.getDiscontinued()).thenReturn("1992-02-01");
        ComputerValidator.isValid(ComputerDTO);
    }

    public void TestIsValidDates() {
        Mockito.when(ComputerDTO.getName()).thenReturn("name of the computer");
        Mockito.when(ComputerDTO.getIntroduced()).thenReturn("1992-02-02");
        Mockito.when(ComputerDTO.getDiscontinued()).thenReturn("1992-02-03");
        ComputerValidator.isValid(ComputerDTO);
    }

}