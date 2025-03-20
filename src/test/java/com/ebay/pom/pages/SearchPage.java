package com.ebay.pom.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> prices;

    private By searchBox = By.id("gh-ac");
    private By searchButton = By.id("gh-search-btn");
    private By firstProduct = By.xpath("(//div[contains(@class,'s-item')])[1]//a");
    private By addToCartButton = By.id("atcBtn_btn_1");
    private By cartPrice = By.xpath("//span[@class='item-price']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct(String product) throws Exception {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
    }

    public String getFirstProductPrice() {
    	prices = driver.findElements(By.className("s-item__price"));
    	System.out.println("Prices found: " + prices.size());
//    	for (WebElement price : prices) {
//    	    System.out.println("Price: " + price.getText());
//    	}

        String price = prices.get(2).getText().trim();
        String formattedPrice = price.replaceAll("[^0-9.]", "");
        double productPrice = Double.parseDouble(formattedPrice);
        System.out.println("Product price fetched: " + productPrice);
        return price.isEmpty() ? "0" : formattedPrice;
    	
    }
    public void clickFirstProduct(double expectedPrice) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("s-item__price")));
        prices = driver.findElements(By.className("s-item__price"));
        List<WebElement> productLinks = driver.findElements(By.className("s-item__link"));

        if (prices.isEmpty() || productLinks.isEmpty()) {
            throw new RuntimeException("❌ No products found to click.");
        }

        for (int i = 0; i < prices.size(); i++) {
            String formattedPrice = prices.get(i).getText().replaceAll("[^0-9.]", "");

            if (!formattedPrice.isEmpty()) {
                double actualPrice = Double.parseDouble(formattedPrice);

                if (actualPrice <= expectedPrice) {
                    System.out.println("✅ Clicking on product with price: " + actualPrice);
                    productLinks.get(i).click();
                    return;
                }
            }
        }

        throw new RuntimeException("❌ No matching product found for the expected price.");
    }


    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void addToCart() throws Exception {
        switchToNewTab();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        Thread.sleep(5000);
    }

    public void switchBackToMainTab() {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            driver.switchTo().window(window);
            break;  // Switches to the first available window
        }
    }

//    public String getCartPrice() {
//        return driver.findElement(cartPrice).getText().trim();
//    }
}
