package com.ebay.pom.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver driver;
    WebDriverWait wait;

    private By searchBox = By.id("gh-ac");
    private By searchButton = By.id("gh-search-btn");
    private By firstProduct = By.xpath("//*[@id=\"item3da2378a21\"]/div/div[2]/a");
    private By addToCartButton = By.xpath("//*[@id=\"atcBtn_btn_1\"]");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct(String product) throws Exception {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
        Thread.sleep(5000);
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }
    
    //private By cartIcon = By.xpath("//*[@id='gh-cart']");

    public void switchToNewTab() {
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
    }

    public void addToCart() throws Exception {
        switchToNewTab(); // Switch to new tab where product is opened
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        Thread.sleep(5000);
    }

//    public boolean isProductAddedToCart() {
//        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
//        return driver.findElement(cartIcon).isDisplayed();
//    }
}

