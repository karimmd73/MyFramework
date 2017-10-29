package com.serentiybddfw.stepdefs;

import com.serentiybddfw.steps.NavigateSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class NavigateStepDefs {
	@Steps
	NavigateSteps navsteps;
	

	@Given("^Mercury Tours Application is launched$")
	public void mercury_Tours_Application_is_launched() throws Throwable {
		navsteps.isLaunched();


	}

	@When("^User clicks on a Page$")
	public void user_clicks_on_a_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		navsteps.navigateToPage("Cruises");
		
	}

	@Then("^Page selected is loaded$")
	public void page_selected_is_loaded() throws Throwable {
		navsteps.pageIsDisplayed("Cruises");
	}

}
