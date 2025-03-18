package com.ebay.pom.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ebay.pom.pages.BaseTestPage;
import com.ebay.pom.pages.RegistrationPage;

public class RegistrationTest extends BaseTestPage {
    
    private RegistrationPage registrationPage;  // Declare without initialization
    
    @BeforeClass
    public void setup() {
        super.setup(); // Call parent setup() to initialize driver
        registrationPage = new RegistrationPage(driver);  // Now driver is initialized
    }

    @Test(groups = "registration")
    public void testUserRegistration() throws Exception {
        driver.get("https://www.ebay.com/");
        driver.findElement(By.linkText("register")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='personalaccount-radio']")));
        driver.findElement(By.xpath("//*[@id='personalaccount-radio']")).click();

        // Use registrationPage after driver is initialized
        registrationPage.enterFirstName("Test");
        registrationPage.enterLastName("User01");
        registrationPage.enterEmail("TesTUser001@test.com");
        registrationPage.enterPassword("Alan@admin001");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='EMAIL_REG_FORM_SUBMIT']")));
        registrationPage.clickRegister();
        
        Thread.sleep(3000);
        boolean exists2 = driver.findElement(By.linkText("Register with a different email address")).isDisplayed();
        if (exists2) {
            Assert.assertTrue(true, "Already Registered with This Email");
            System.out.println("Already Registered with This Email - Test Passed");
        } else {
            driver.findElement(By.linkText("Skip for now")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[@id='gh']/nav/div[1]/span[1]/div/button/span")).getText().contains("Hi "));
        }
    }

}
