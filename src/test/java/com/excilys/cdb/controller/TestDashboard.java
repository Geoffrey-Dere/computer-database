package com.excilys.cdb.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class TestDashboard {

    private WebDriver driver;

    private String baseUrl;

    @BeforeClass
    public static void beforeClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/computerDatabase";
    }

    @After
    public void after() throws IOException {
        driver.quit();
    }

    @Test
    public void testUrl() {
        driver.get(baseUrl + "/dashboard");
        assertEquals(driver.getTitle(), "dashboard");
    }

    @Test
    public void testRedirectAddComputeur() {
        driver.get(baseUrl + "/dashboard");
        WebElement addComputer = driver.findElement(By.id("addComputer"));
        addComputer.click();
        assertEquals(driver.getCurrentUrl(), this.baseUrl + "/addComputer");
    }

}
