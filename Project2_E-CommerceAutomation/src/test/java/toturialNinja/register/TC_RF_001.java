package toturialNinja.register;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_RF_001 {
	WebDriver driver;

	@BeforeClass
	public void startTest() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void registerTest() throws InterruptedException {
		
		driver.get("https://tutorialsninja.com/demo/");

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("Kartik");
		driver.findElement(By.id("input-lastname")).sendKeys("Sharma");
		String email = generateEmail();
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys("9812763454");

		driver.findElement(By.id("input-password")).sendKeys("Kartik@12");
		driver.findElement(By.id("input-confirm")).sendKeys("Kartik@12");

		driver.findElement(By.cssSelector("input[name='newsletter'][value='1']")).click();
		driver.findElement(By.cssSelector("input[type='checkbox'][value='1']")).click();

		driver.findElement(By.cssSelector("input[type='submit']")).click();
	}

	@Test(dependsOnMethods = "registerTest")
	public void verifyRegisterTest() {
		String ExpectedText = "Your Account Has Been Created!";
		AssertJUnit.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		String verifyText = driver.findElement(By.cssSelector("div[id='content'] h1")).getText();
		AssertJUnit.assertEquals(verifyText, ExpectedText);
	}

	@Test(priority = 2)
	public void accountPageTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[class='pull-right'] a")).click();
	}

	@Test(dependsOnMethods = "accountPageTest")
	public void verifyAccountPageTest() {
		String expectedText1 = "My Account";
		String expectedText2 = "My Orders";
		String expectedText3 = "My Affiliate Account";
		String expectedText4 = "Newsletter";

		String verifyText1 = driver.findElement(By.xpath("//div[@id='content']//h2[1]")).getText();
		String verifyText2 = driver.findElement(By.xpath("//div[@id='content']//h2[2]")).getText();
		String verifyText3 = driver.findElement(By.xpath("//div[@id='content']//h2[3]")).getText();
		String verifyText4 = driver.findElement(By.xpath("//div[@id='content']//h2[4]")).getText();

		AssertJUnit.assertEquals(expectedText1, verifyText1);
		AssertJUnit.assertEquals(expectedText2, verifyText2);
		AssertJUnit.assertEquals(expectedText3, verifyText3);
		AssertJUnit.assertEquals(expectedText4, verifyText4);
	}

	@AfterClass
	public void endTest() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	public String generateEmail() {
		Date date = new Date();
		String email = date.toString().replaceAll(" ", "").replaceAll(":", "") + "@gmail.com";
		return email;
	}
}
