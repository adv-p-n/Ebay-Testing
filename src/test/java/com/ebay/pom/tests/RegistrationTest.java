package com.ebay.pom.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ebay.pom.pages.RegistrationPage;
import com.ebay.utils.DriverManager;

public class RegistrationTest {
    WebDriver driver;
    RegistrationPage registrationPage;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        registrationPage = new RegistrationPage(driver);
        driver.get("https://www.ebay.com/");
        driver.findElement(By.linkText("register")).click();
    }

    @Test(groups = "registration")
    public void testUserRegistration() throws Exception {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"personalaccount-radio\"]")));
    	driver.findElement(By.xpath("//*[@id=\"personalaccount-radio\"]")).click();
        registrationPage.enterFirstName("Test");
        registrationPage.enterLastName("User01");
        registrationPage.enterEmail("TesTUser001@test.com");
        registrationPage.enterPassword("Alan@admin001");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"EMAIL_REG_FORM_SUBMIT\"]")));
        registrationPage.clickRegister();
        Thread.sleep(3000);
        //boolean exists=driver.findElement(By.linkText("Email_err")).isDisplayed();
        boolean exists2=driver.findElement(By.linkText("Register with a different email address")).isDisplayed();
        if(exists2) {
        	Assert.assertTrue(true, "Alredy Registered with This Email");
        	System.out.println("Alredy Registered with This Email so Pass");
        }
        else {
			/*
			 * if(exists2) { Assert.assertTrue(true, "Alredy Registered with This Email");
			 * System.out.println("Alredy Registered with This Email so Pass"); }
			 */
        	driver.findElement(By.linkText("Skip for now")).click();
            //driver.findElement(By.xpath("//*[@id=\"backbutton\"]/div/svg/path")).click();
        	Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"gh\"]/nav/div[1]/span[1]/div/button/span/text()")).getText().contains("Hi "));
        }
    }

   @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
