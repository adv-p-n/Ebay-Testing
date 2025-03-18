package com.ebay.pom.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ebay.pom.pages.BaseTestPage;
import com.ebay.pom.pages.LoginPage;

public class LoginTest extends BaseTestPage {
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        super.setup(); // Ensure BaseTestPage initializes the driver
        loginPage = new LoginPage(driver);
    }

    public void testLogin() throws Exception {
        driver.get("https://www.ebay.com/");
        loginPage.goToLogin();
        // Ensure the login page is fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));

        // Perform login steps
        loginPage.enterUsername("harikrishna3938@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("pass"))); // Ensure password field is ready
        loginPage.enterPassword("Alan@admin200");
        loginPage.clickLogin();

        // Wait for home page to load and validate login success
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-logo"))); // eBay logo ensures successful login

        Assert.assertTrue(driver.findElement(By.id("gh-logo")).isDisplayed(), "Login failed!");
        System.out.println("✅ Successfully Logged In");
    }


}

