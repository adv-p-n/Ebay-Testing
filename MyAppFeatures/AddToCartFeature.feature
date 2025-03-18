Feature: Add Product to Cart
	here we testing the Add to Cart functionality

  Scenario: User adds a product to the cart
    Given I am on the eBay homepage
    When I search for "Laptop"
    And I select the first product
    Then I add the product to the cart