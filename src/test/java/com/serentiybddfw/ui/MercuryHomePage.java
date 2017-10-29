package com.serentiybddfw.ui;
import static org.assertj.core.api.StrictAssertions.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject ;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://newtours.demoaut.com/mercurywelcome.php")
public class MercuryHomePage extends PageObject{
	


	@FindBy(linkText="Cruises")
	WebElementFacade  cruiselink;
	
	@FindBy(id="file-upload")
	WebElementFacade uploadfile;
	
	@FindBy(id="file-submit")
	WebElementFacade submit;
	
	@FindBy(xpath="/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/th/table/tbody/tr[1]/td/span")
	private WebElementFacade cruiseItnry;

	public void opened() {
		
		System.out.println("Home Page is open");

	}

	public void navigateToPage(String page) throws Exception {
        Thread.sleep(2000);
		cruiselink.waitUntilVisible().click();
	
	}

	public void verifyNavigation() throws Exception {
		assertThat(cruiseItnry.getText().contains("Cruise Itinerary" ));
		getDriver().navigate().to("http://the-internet.herokuapp.com/upload");
		Thread.sleep(2000);
		System.out.println("File Upload Started");
		upload("src/test/resources/sample.txt").to(uploadfile);
		Thread.sleep(2000);
		submit.waitUntilVisible().click();
		System.out.println("File Uploaded");
		
	}

}
