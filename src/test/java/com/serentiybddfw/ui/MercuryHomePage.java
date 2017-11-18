package com.serentiybddfw.ui;
import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject ;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import static org.junit.Assert.assertEquals;
//@DefaultUrl("http://newtours.demoaut.com/mercurywelcome.php")
@DefaultUrl("https://sites.google.com/a/whps.org/google-sites/sample-sites")
public class MercuryHomePage extends PageObject{
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MercuryHomePage.class);
String parentHandle, childHandle = null;
BasePage bp = new BasePage(getDriver());

	@FindBy(linkText="Cruises")
	WebElementFacade  cruiselink;
	
	@FindBy(id="file-upload")
	WebElementFacade uploadfile;
	
	@FindBy(id="file-submit")
	WebElementFacade submit;
	
	@FindBy(xpath="/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/th/table/tbody/tr[1]/td/span")
	private WebElementFacade cruiseItnry;
	
	//@FindBy(linkText="on a state")
	@FindBy(xpath="//*[@id='sites-canvas-main-content']/table/tbody/tr/td[1]/div/div[1]/div[2]/ul/li[4]/b/font/a")
	private WebElementFacade onaState;

	public void opened() {
		
		System.out.println("Home Page is open");
		
		System.out.println("Maximized page");
	}

	public void navigateToPage(String page) throws Exception {
		 
		 parentHandle =  getDriver().getWindowHandle() ;
		Thread.sleep(2000);
		System.out.println("It is there");
		// cruiselink.waitUntilVisible().click();
		onaState.isDisplayed();
		System.out.println("Is Displayed");
		onaState.click();
		System.out.println("Is Clicked");
		Thread.sleep(2000);
		 Set<String> handles = getDriver().getWindowHandles() ;
		 System.out.println("Handles:" + handles);

		for (String whandle : handles) {
			if (!whandle.equals(parentHandle)) {
				childHandle = whandle;
				getDriver().switchTo().window(childHandle);
				System.out.println("Child Handle:" + childHandle + " Title:" + getDriver().getTitle());
				
			}

		}
		
	
		

	}

	public void verifyNavigation() throws Exception {
		/*assertThat(cruiseItnry.getText().contains("Cruise Itinerary" ));
		getDriver().navigate().to("http://the-internet.herokuapp.com/upload");
		Thread.sleep(2000);
		System.out.println("File Upload Started");
		upload("src/test/resources/sample.txt").to(uploadfile);
		Thread.sleep(2000);
		submit.waitUntilVisible().click();
		System.out.println("File Uploaded");*/
		System.out.println("Verify Navigation" +  childHandle);
		 getDriver().switchTo().window(childHandle);
		 System.out.println("Switched");
		assertEquals( "Google Forms1",getDriver().getTitle().trim());
		
		getDriver().close();

		getDriver().switchTo().window(parentHandle);
		System.out.println("Parent Handle:" + parentHandle + " Title:" + getDriver().getTitle());
		System.out.println("**********************Ended*********************************");
		
	}

}
