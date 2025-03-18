package com.ebay.cucumberTestStep;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.ebay.utils.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class AddToCartSteps {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;

    private By searchBox = By.id("gh-ac");
    private By searchButton = By.id("gh-search-btn");
    private By firstProduct = By.xpath("//*[@id=\"item3da2378a21\"]/div/div[2]/a");
    private By addToCartButton = By.xpath("//*[@id=\"atcBtn_btn_1\"]");
    
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


    @Given("I am on the eBay homepage")
    public void i_am_on_the_ebay_homepage() {
        //driver = DriverManager.getDriver();
        driver.get("https://www.ebay.com/");
  
    }

    @When("I search for {string}")
    public void i_search_for(String productName) {
    	driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    @When("I select the first product")
    public void i_select_the_first_product() {
    	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(firstProduct))).click();
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
    	// Get current window handle
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        // Switch to the new tab
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToCartButton))).click();
    }

    @Then("I should see the product in my cart")
    public void i_should_see_the_product_in_my_cart() {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/h1")).isDisplayed(), "Product not added to cart!");
        System.out.println("Product successfully added to the cart!");
    }
}