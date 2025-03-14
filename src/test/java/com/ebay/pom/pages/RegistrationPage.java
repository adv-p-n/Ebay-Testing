package com.ebay.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    // Locators
    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By email = By.id("Email");
    private By password = By.id("password");
    private By registerButton = By.xpath("//*[@id=\"EMAIL_REG_FORM_SUBMIT\"]");

    // Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterFirstName(String fname) {
        driver.findElement(firstName).sendKeys(fname);
        System.out.println("Entering Details");
    }

    public void enterLastName(String lname) {
        driver.findElement(lastName).sendKeys(lname);
    }

    public void enterEmail(String emailAddr) {
        driver.findElement(email).sendKeys(emailAddr);
    }

    public void enterPassword(String pwd) {
        driver.findElement(password).sendKeys(pwd);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
        System.out.println("Clicked Register Button");
    }
}