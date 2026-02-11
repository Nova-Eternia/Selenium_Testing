package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestData;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProviderClass = TestData.class, dataProvider="commonData")
	public void addCustomerTest(String firstName, String lastName, String postCode, String runmode) throws InterruptedException {
		
		if(!runmode.equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping this TestCase because the RunMode is NO.....");
		}
		
		driver.findElement(By.cssSelector(OR.getProperty("addCustomerBtn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("sendFirstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("sendLastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("sendPostCode"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addCustomerSubmitBtn"))).click();
		
		//Handling alerts.
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());//output the reference of the alert.
		Assert.assertTrue(alert.getText().contains("Customer added successfully"));
		alert.accept();
//		Assert.fail("Adding customer failed ---");

	}
}
