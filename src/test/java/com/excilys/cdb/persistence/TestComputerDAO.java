package com.excilys.cdb.persistence;

import java.sql.SQLException;
import java.time.LocalDate;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class TestComputerDAO extends DBTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestComputerDAO.class);
   
    @Autowired
    private ComputerDAO computerDAO;
        
    //
    // @Test
    // public void testCountShouldBeTrue() {
    // assertEquals(computerDAO.count(), 2);
    // }
    //
    // @Test
    // public void testFindByIdShouldByNull() {
    // long id = -9999;
    // Optional<Computer> computer = computerDAO.find(id);
    // assertFalse(computer.isPresent());
    // }
    //
    // @Test
    // public void testFIndByIdShouldNotNull() {
    // long id = 1;
    // Optional<Computer> computer = computerDAO.find(id);
    // assertTrue(computer.isPresent());
    // }

    @Test
    public void testAddComputer() throws SQLException {

        Computer computer = new Computer();
        computer.setName("ordinateur");
        computer.setIntroduced(LocalDate.of(2012, 2, 12));
        computer.setDiscontinued(LocalDate.of(2012, 2, 11));

        int count = computerDAO.count();
        boolean res = computerDAO.create(computer);
        assertTrue(res);
        assertEquals(count + 1, computerDAO.count());

    }

    //
    // @Test
    // public void testFindByName() {
    // assertEquals(computerDAO.findByName(2, 0, "teur", "computer.name",
    // "ASC").size(), 0);
    // assertEquals(computerDAO.findByName(2, 0, "ordi", "computer.name",
    // "ASC").size(), 2);
    // assertEquals(computerDAO.findByName(2, 0, "sdsds", "computer.name",
    // "ASC").size(), 0);
    //
    // }
    //
    // @AfterClass
    // public static void afterClass() throws SQLException {
    // dbUnitConnection.close();
    // }
    //
    @Override
    protected IDataSet getDataSet() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new FlatXmlDataSetBuilder().build(classLoader.getResourceAsStream("dbunit.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

}
