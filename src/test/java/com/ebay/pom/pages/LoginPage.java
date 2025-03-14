package com.ebay.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    // Locators
    private By signin=By.xpath("//*[@id=\"gh\"]/nav/div[1]/span[1]/span/a");
    private By usernameField = By.id("userid");
    private By passwordField = By.id("pass");
    private By loginButton = By.id("sgnBt");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void goToLogin() {
    	driver.findElement(signin).click();
    	System.out.println("Going to Login Page");
    }
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(By.name("signin-continue-btn")).click();
        System.out.println("Entered username/E-mail");
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        System.out.println("Entered Password");
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
        System.out.println("Clicked on Login Button");
    }
}