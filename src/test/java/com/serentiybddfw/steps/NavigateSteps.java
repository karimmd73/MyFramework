package com.serentiybddfw.steps;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.serentiybddfw.ui.MercuryHomePage;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@RunWith(CucumberWithSerenity.class)
public class NavigateSteps extends ScenarioSteps {
	WebDriver driver;
	MercuryHomePage homePage;

	@Step("Launch the application under test")
	
	public void isLaunched() {		
		homePage.open();
		homePage.opened();
	}

	@Step
	public void navigateToPage(String page) throws Exception {
		homePage.navigateToPage(page);
		homePage.wait(5000);

	}

	@Step
	public void pageIsDisplayed(String string) throws Exception {
		homePage.verifyNavigation();

	}

}
