package com.ebay.pom.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ebay.pom.pages.LoginPage;
import com.ebay.utils.DriverManager;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://www.ebay.com/");
        loginPage.goToLogin();
    }

    @Test(groups = "login", dependsOnGroups = "registration")
    public void testLogin() throws Exception {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
        loginPage.enterUsername("harikrishna3938@gmail.com");
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("pass"))));
        loginPage.enterPassword("Alan@admin200");
        loginPage.clickLogin();
        By skiplnk=(By.linkText("Skip for now"));
        if(driver.findElement(skiplnk).isDisplayed()) {driver.findElement(skiplnk).click();}
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"gh-logo\"]")).isDisplayed());
        System.out.println("Successfully LoggedIn");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}