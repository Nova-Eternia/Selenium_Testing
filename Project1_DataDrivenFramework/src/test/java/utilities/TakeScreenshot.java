package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TakeScreenshot extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException {
		Date date = new Date();//to get the timeStamp
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshotName = date.toString().replace(":", "_").replace(" ", "_")+".jpg";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"//test-output//Screenshots//"+screenshotName));
	}
}
