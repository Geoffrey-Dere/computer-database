package com.excilys.cdb.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class TestComputer {

    @Test
    public void TestEgalComputer() {

        LocalDate introduced = LocalDate.now();
        LocalDate discon = introduced.plusDays(1);
        String name = "test";

        Computer c1 = new Computer(name);
        c1.setIntroduced(introduced);
        c1.setDiscontinued(discon);

        Computer c2 = new Computer(name);
        c2.setIntroduced(introduced);
        c2.setDiscontinued(discon);

        assertEquals(c1, c2);
    }
}
