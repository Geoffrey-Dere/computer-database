package com.excilys.cdb.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCompany {

    @Test
    public void TestEgalCompany() {

        Company c1 = new Company("test");
        Company c2 = new Company("test");

        assertEquals(c1, c2);
    }

}
