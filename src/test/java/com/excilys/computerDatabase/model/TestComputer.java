package com.excilys.computerDatabase.model;

import java.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestComputer {

    public TestComputer() {
        // TODO Auto-generated constructor stub
    }

    // @Test(expected=DateException.class)
    public void testCreateComputerDateGreater() {

        LocalDate introduced = LocalDate.of(2020, 02, 02);
        LocalDate discontinued = LocalDate.of(2010, 02, 02);
        // new Computer(1, "name", introduced, discontinued);
    }

    // @Test(expected=DateException.class)
    public void testCreateComputerIntroducedNull() {
        LocalDate discontinued = LocalDate.of(2010, 02, 02);
        // new Computer(1, "name", null, discontinued);
    }

    @Test
    public void testSetIntroduce() {
        LocalDate introduced = LocalDate.of(2020, 02, 02);
        // Computer computer = new Computer(1, "name");
        // computer.setIntroduced(introduced);
        // assertEquals(computer.getIntroduced(), introduced);
    }

    @Test
    public void testSetDiscontinued() {
        // LocalDate introduced = LocalDate.of(2010, 02, 02);
        // LocalDate discontinued = LocalDate.of(2020, 02, 02);
        // Computer computer = new Computer(1, "name", introduced);
        // computer.setDiscontinued(discontinued);
        // assertEquals(computer.getDiscontinued(), discontinued);
    }

    @Test
    public void testEquals() {

        // Computer c1 = new Computer(1, "name",
        // LocalDate.of(2010, 02, 02),
        // LocalDate.of(2020, 02, 02));
        //
        // Computer c2 = new Computer(1, "name",
        // LocalDate.of(2010, 03, 03),
        // LocalDate.of(2020, 04, 04));
        //
        // assertEquals(c1, c2);
    }

    @Test
    public void testNotEquals() {

        // Computer c1 = new Computer(1, "name",
        // LocalDate.of(2010, 02, 02),
        // LocalDate.of(2020, 02, 02));
        //
        // Computer c2 = new Computer(2, "name2",
        // LocalDate.of(2010, 03, 03),
        // LocalDate.of(2020, 04, 04));
        //
        // assertNotEquals(c1, c2);
    }

}
