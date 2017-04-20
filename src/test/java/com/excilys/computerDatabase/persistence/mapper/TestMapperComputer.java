package com.excilys.computerDatabase.persistence.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.computerDatabase.model.Computer;
import com.excilys.computerDatabase.util.DateFormatter;

public class TestMapperComputer {

    private ResultSet result;

    @Before
    public void before() {
        result = Mockito.mock(ResultSet.class);
    }

    @After
    public void after() throws SQLException {
        result.close();
    }

    @Test
    public void TestMapperToCompany() throws SQLException, ParseException {

        Mockito.when(result.getRow()).thenReturn(1);
        Mockito.when(result.next()).thenReturn(true);

        Mockito.when(result.getLong(MapperComputer.ID)).thenReturn(1L);
        Mockito.when(result.getString(MapperComputer.NAME)).thenReturn("computer alpha");
        Mockito.when(result.getDate(MapperComputer.INTRODUCED)).thenReturn(DateFormatter.sqlDate("2010-02-22"));
        Mockito.when(result.getDate(MapperComputer.DISCONTINUED)).thenReturn(DateFormatter.sqlDate("2010-02-23"));
        Mockito.when(result.getLong(MapperComputer.COMPANY_ID)).thenReturn(2L);

        Computer computer = MapperComputer.mapperComputer(result, false);

        assertNotNull(computer);
        assertEquals(computer.getId(), 1L);
        assertEquals(computer.getName(), "computer alpha");
        assertEquals(computer.getIntroduced().get(), LocalDate.of(2010, 02, 22));
        assertEquals(computer.getDiscontinued().get(), LocalDate.of(2010, 02, 23));
    }

}
