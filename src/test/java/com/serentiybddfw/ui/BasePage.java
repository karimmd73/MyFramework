package com.serentiybddfw.ui;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import jline.internal.Log;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObjects;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BasePage extends PageObjects {



	public BasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "file-submit")
	WebElementFacade submit;

	@Test
	public void myJunit() {
		Consumer <String> logmsg = logmsg1 -> Log.info("This is a test");

	}

	//Consumer <String> logmsg = logmsg -> Log.info(logmsg);
	Consumer <WebElementFacade> elemen = elemen ->  submit.sendKeys("");;
	
			
			
	HelperMethodInput enterValues = (String logmessage, String inputtext, WebElementFacade element) ->

	{

		Log.info(logmessage);
		element.clear();
		element.sendKeys(inputtext);

	};

	HelperMethodSelect selectValues = (String logmessage, String inputtext, WebElementFacade element) ->

	{
		Select selitem = new Select(element);
		Log.info(logmessage);
		element.selectByValue(inputtext);

	};

	interface HelperMethodInput {
		void enterValues(String logmessage, String inputtext, WebElementFacade element);

	}

	interface HelperMethodSelect {
		void selectValues(String logmessage, String inputtext, WebElementFacade element);

	}

}


