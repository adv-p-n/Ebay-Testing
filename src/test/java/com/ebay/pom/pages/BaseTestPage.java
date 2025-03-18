package com.ebay.pom.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ebay.utils.DriverManager;

public class BaseTestPage {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized!");
        }
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}