package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProvider="addCustomerData")
	public void addCustomer(String firstName, String lastName, String postCode) throws InterruptedException {
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
	
	@DataProvider(name = "addCustomerData")
	public Object[][] getData() {

	    String sheetName = "AddCustomerTest";

	    int rows = excel.getRowCount(sheetName);
	    int columns = excel.getColumnCount(sheetName);

	    // Exclude header row
	    Object[][] data = new Object[rows - 1][columns];

	    for (int i = 2; i <= rows; i++) {          // rows
	        for (int j = 0; j < columns; j++) {    // columns
	            data[i - 2][j] = excel.getCellData(sheetName, j, i);
	        }
	    }
	    return data;
	}
}
