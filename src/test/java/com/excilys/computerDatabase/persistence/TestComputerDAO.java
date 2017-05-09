package com.excilys.computerDatabase.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDatabase.model.Computer;

public class TestComputerDAO {

	
    private static final Logger LOGGER = LoggerFactory.getLogger(TestComputerDAO.class);
	private static IDatabaseConnection dbUnitConnection;
	private static IDataSet dataSet;

	private ComputerDAO computerDAO = ComputerDAO.INSTANCE;

	private static final String PATH_FILE = "config/hikari/bdd.properties";
	private static final String BASE = "base";
	private static final String USER = "username";
	private static final String PASSWORD = "password";

	private static String base;
	private static String user;
	private static String password;

	@BeforeClass
	public static void beforeClass() throws SQLException, DatabaseUnitException, IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		Connection jdbcConnection = null;

		prop.load(classLoader.getResourceAsStream(PATH_FILE));
		base = prop.getProperty(BASE);
		user = prop.getProperty(USER);
		password = prop.getProperty(PASSWORD);
		
		LOGGER.debug("initialisation : {}", base);


		jdbcConnection = DriverManager.getConnection(base, user, password);

		dbUnitConnection = new DatabaseConnection(jdbcConnection);

		FlatXmlDataSetBuilder xmlDSBuilder = new FlatXmlDataSetBuilder();
		xmlDSBuilder.setCaseSensitiveTableNames(false);
		dataSet = xmlDSBuilder.build(classLoader.getResourceAsStream("dbunit.xml"));
	}

	@Before
	public void before() throws DatabaseUnitException, SQLException {

		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataSet);

	}

	@Test
	public void testCountShouldBeTrue() {
		assertEquals(computerDAO.count(), 2);
	}

	@Test
	public void testFindByIdShouldByNull() {
		long id = -9999;
		Optional<Computer> computer = computerDAO.find(id);
		assertFalse(computer.isPresent());
	}

	@Test
	public void testFIndByIdShouldNotNull() {
		long id = 1;
		Optional<Computer> computer = computerDAO.find(id);
		assertTrue(computer.isPresent());
	}

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

	@Test
	public void testFindByName() {
		assertEquals(computerDAO.findByName(2, 0, "teur", "computer.name", "ASC").size(), 0);
		assertEquals(computerDAO.findByName(2, 0, "ordi", "computer.name", "ASC").size(), 2);
		assertEquals(computerDAO.findByName(2, 0, "sdsds", "computer.name", "ASC").size(), 0);

	}

	@AfterClass
	public static void afterClass() throws SQLException {
		dbUnitConnection.close();
	}

}
