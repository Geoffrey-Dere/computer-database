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

public class TestAddComputer {

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
    public void testAddShouldNotWork() {
        driver.get(baseUrl + "/addComputer");
        WebElement inputName = driver.findElement(By.id("computerName"));
        WebElement buttonAdd = driver.findElement(By.id("submit"));
        inputName.sendKeys("dfd&é'(-@");
        buttonAdd.click();
        assertEquals(driver.getCurrentUrl(), baseUrl + "/addComputer");
    }

    @Test
    public void testAddShouldWork() {
        driver.get(baseUrl + "/addComputer");
        // assertEquals(driver.getTitle(), "dashboard");
        WebElement inputName = driver.findElement(By.id("computerName"));
        WebElement buttonAdd = driver.findElement(By.id("submit"));
        inputName.sendKeys("name is valid");
        buttonAdd.click();
        assertEquals(driver.getCurrentUrl(), baseUrl + "/dashboard");
    }
}
