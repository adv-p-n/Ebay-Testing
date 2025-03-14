package com.ebay.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        System.out.println("Opening Browser For Testing");
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Closing Browser");
            driver = null;
        }
    }
}
