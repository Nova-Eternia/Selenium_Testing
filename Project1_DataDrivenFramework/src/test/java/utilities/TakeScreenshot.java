//package utilities;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import base.TestBase;
//
//public class TakeScreenshot extends TestBase {
//	public static String screenshotPath;
//	public static String screenshotName;
//	
//	public static void captureScreenshot() throws IOException {
//		Date date = new Date();//to get the timeStamp
//		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		screenshotName = date.toString().replace(":", "_").replace(" ", "_")+".jpg";
//		screenshotPath = System.getProperty("user.dir")+"//test-output//Screenshots//"+screenshotName;
//		FileUtils.copyFile(srcFile, new File(screenshotPath));
//	}
//}
package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TakeScreenshot extends TestBase {

    public static String captureScreenshot() throws IOException {

        // Timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = "FAIL_" + timestamp + ".jpg";

        // Directory
        String screenshotDir =
                System.getProperty("user.dir") + "/test-output/Screenshots/";

        // Ensure directory exists
        new File(screenshotDir).mkdirs();

        String screenshotPath = screenshotDir + screenshotName;

        // Capture
        File srcFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile, new File(screenshotPath));

        return screenshotPath; // 🔑 return path instead of static field
    }
}

