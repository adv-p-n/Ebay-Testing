Feature: Login Functionality
	Here we are testing the Login Functionality of the website

Scenario Outline: Login Validation for different combination of username and password

Given User on loginPage
When User types correct username "<username>" 
And User clicks continue button
And User types correct password "<password>"
And User clicks logIn button
Then User lands on the HomePage

Examples:
|  username  								  |  password  					|
|	 user1@fmail.com					  |  Pass1@admin001			|
|	 harikrishna3938@gmail.com  | Alan@admin200 			|

