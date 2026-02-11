package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestData;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestData.class, dataProvider = "commonData")
	public void openAccountTest(String customer, String currency, String runmode) throws InterruptedException {
		
		if(!runmode.equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping this TestCase because the RunMode is NO.....");
		}
		
		driver.findElement(By.cssSelector(OR.getProperty("openAccBtn"))).click();	
		
		WebElement username = driver.findElement(By.id(OR.getProperty("openAccUserSelect")));
		WebElement money = driver.findElement(By.id(OR.getProperty("openAccCurrency")));
		
		Select selectUser = new Select(username);
		selectUser.selectByVisibleText(customer);
		
		Select selectCurrency= new Select(money);
		selectCurrency.selectByVisibleText(currency);
		
		driver.findElement(By.cssSelector(OR.getProperty("openAccProcessBtn"))).click();	

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		

	}
}
