package com.excilys.cdb.persistence.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.model.Company;

public class TestMapperCompany {

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
    public void TestMapperToCompany() throws SQLException {

        Mockito.when(result.getRow()).thenReturn(1);
        Mockito.when(result.next()).thenReturn(true);

        Mockito.when(result.getLong(MapperCompany.ID)).thenReturn(1L);
        Mockito.when(result.getString(MapperCompany.NAME)).thenReturn("asus");

        Company company = MapperCompany.mapperCompany(result);

        assertNotNull(company);
        assertEquals(company.getId(), 1);
        assertEquals(company.getName(), "asus");
    }

}
