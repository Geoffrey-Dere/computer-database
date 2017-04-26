package com.excilys.computerDatabase.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

    public void testRedirectAddComputeur() {
        WebElement addComputer = driver.findElement(By.id("addComputer"));
        addComputer.click();
        assertEquals(driver.getCurrentUrl(), this.baseUrl + "/addComputer");
    }

}
