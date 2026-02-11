package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;

import utilities.ExcelReader;
import utilities.ExtentManager;

public class TestBase {
	
	/**
	 * intitilizing 
	 * 	WebDriver  - done
	 * Properties  - done
	 * logs
	 * extentReport,ReportNG
	 * DB
	 * excel
	 * mail
	 * Jenkins
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream file;
	public static Logger log = LogManager.getLogger(TestBase.class);
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src/test//resources//excel//TextData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	
	
	@BeforeSuite
	public void setUp() throws IOException {
		if(driver == null) {
			file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//config.properties");
			config.load(file);
			log.info("Config file loaded !");
			file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
			OR.load(file);
			log.info("OR file loaded !");

		}
		String browser = config.getProperty("browser");
		log.info("Browser selected: " + browser);

		if ("safari".equalsIgnoreCase(browser)) {
		    driver = new SafariDriver();
		} else if ("chrome".equalsIgnoreCase(browser)) {
		    driver = new ChromeDriver();
		}
		
		driver.get(config.getProperty("testSiteURL"));
		log.info("Navigated to "+ config.getProperty("testSiteURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit_wait"))));
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		
		log.debug("Test Execution Completed !!!");
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
//	public void clickon(String locator) {
//		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
//		test.log(Status.INFO, "Clicking on : " + locator);
//	}
//	public void sendkey(String locator, String value) {
//		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);;
//		test.log(Status.INFO, "Typing in  "+ locator +" : " +  value);
//	}


	
}
