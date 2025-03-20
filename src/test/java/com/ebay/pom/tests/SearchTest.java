package com.ebay.pom.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ebay.pom.pages.BaseTestPage;
import com.ebay.pom.pages.SearchPage;
import com.ebay.utils.ExcelReader;

public class SearchTest extends BaseTestPage {

    SearchPage searchPage;

    @BeforeClass
    public void setup() {
        super.setup();
        searchPage = new SearchPage(driver);
    }

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException {
        return ExcelReader.readExcelData("src/test/resources/TestingData.xlsx", "Price");
    }

    @Test(priority=3, dataProvider = "searchData", groups = "search")
    public void testSearchProduct(String productName, String expectedPrice) throws Exception {
        driver.get("https://www.ebay.com/");
        searchPage.searchProduct(productName);
        
        String formattedPrice = expectedPrice.replaceAll("[^0-9.]", "");
        double expectedProductPrice = Double.parseDouble(formattedPrice);
        double actualPrice = Double.parseDouble(searchPage.getFirstProductPrice());

        if (actualPrice <= expectedProductPrice) {
            System.out.println("✅ Verified price for " + productName + ": " + actualPrice);
            
            // Add product to cart
            searchPage.clickFirstProduct(expectedProductPrice);
            searchPage.addToCart();
            Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/h1")).isDisplayed(), 
                "Product not added to cart!");
            System.out.println("🛒 Product added to cart: " + productName);
            searchPage.switchBackToMainTab();
        } else {
            Assert.fail("❌ Price mismatch! Expected ≤ $" + expectedProductPrice + " but found $" + actualPrice);
        }
    }
}
