package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		if(extent == null) {
			String path = System.getProperty("user.dir"+"//test-output//extentreports//extent.html");
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			extent = new ExtentReports();
			extent.attachReporter(reporter)
		}
		return extent;
	}

}
