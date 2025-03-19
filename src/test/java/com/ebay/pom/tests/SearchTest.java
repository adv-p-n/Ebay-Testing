package com.ebay.pom.tests;

import org.testng.annotations.Test;

import com.ebay.pom.pages.BaseTestPage;
import com.ebay.pom.pages.SearchPage;
import com.ebay.utils.DriverManager;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchTest extends BaseTestPage{

    SearchPage searchPage;

    @BeforeClass
    public void setup() {
        super.setup();
        searchPage = new SearchPage(driver);
    }

    @Test(priority = 1, groups = "search")
    public void testSearchProduct() throws Exception {
        driver.get("https://www.ebay.com/");
        searchPage.searchProduct("Laptop");
        Assert.assertTrue(driver.getTitle().contains("Laptop"), "Search results page did not load properly!");
        System.out.println("Searching for Laptop");
    }

    @Test(priority = 2, dependsOnMethods = "testSearchProduct", groups = "cart")
    public void testAddToCart() throws Exception {
        searchPage.clickFirstProduct();
        searchPage.addToCart();
        System.out.println("Adding First Product to Cart");
        // Validate that the product was added (Assume a confirmation message exists)
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/h1")).isDisplayed(), "Product not added to cart!");
        System.out.println("Product Added Successfully");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
