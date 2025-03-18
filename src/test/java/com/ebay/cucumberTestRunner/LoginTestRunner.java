package com.ebay.cucumberTestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./MyAppFeatures/LoginFeature.feature"},
		glue = {"com.ebay.cucumberTestStep"}
		)
public class LoginTestRunner {

}
