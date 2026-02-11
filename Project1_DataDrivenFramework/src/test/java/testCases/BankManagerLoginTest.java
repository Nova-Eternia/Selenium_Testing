package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import base.TestBase;

public class BankManagerLoginTest extends TestBase {
   
	@Test
	public void bankManagerLoginTest() throws InterruptedException {
		log.debug("Inside Login Test ----");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerBtn"))),"Login not Successfull ----");
		log.debug("Successfully Executed ----");
		Reporter.log("Login Successfully Executed ----");//configer report
//		Assert.fail("Login Failed ---");
	
	}
}
