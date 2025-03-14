package com.ebay.cucumberTestStep;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTestSteps {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	private By signin=By.xpath("//*[@id=\"gh\"]/nav/div[1]/span[1]/span/a");
    private By usernameField = By.id("userid");
    private By passwordField = By.id("pass");
    private By continueButton= By.name("signin-continue-btn");
    private By loginButton = By.id("sgnBt");
    private By skiplnk=By.linkText("Skip for now");
	@Before
	public void init() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
		driver.manage().window().maximize();
		
	}
	
	@After
	public void terminate() {
		driver.quit();
	}

	@Given("User on loginPage")
	public void user_on_login_page() {
		driver.get("https://www.ebay.com/");
		driver.findElement(signin).click();
    	System.out.println("Going to Login Page");
	}

	@When("User types correct username {string}")
	public void user_types_correct_username(String username) {
		driver.findElement(usernameField).sendKeys(username);
        System.out.println("Entered username/E-mail");
	}

	@When("User clicks continue button")
	public void user_clicks_continue_button() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(continueButton)));
        driver.findElement(continueButton).click();
        System.out.println("Continue to password");
	}

	@When("User types correct password {string}")
	public void user_types_correct_password(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(passwordField)));
		driver.findElement(passwordField).sendKeys(password);
        System.out.println("Entered Password");
	}

	@When("User clicks logIn button")
	public void user_clicks_log_in_button() {
		
		driver.findElement(loginButton).click();
        System.out.println("Clicked on Login Button");
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(skiplnk)));
//        driver.findElement(skiplnk).click();
	}

	@Then("User lands on the HomePage")
	public void user_lands_on_the_home_page() {
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"gh-logo\"]")).isDisplayed());
        System.out.println("Successfully LoggedIn");
	}
}
