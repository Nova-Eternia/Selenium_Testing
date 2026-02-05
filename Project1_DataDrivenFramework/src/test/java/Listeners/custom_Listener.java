package Listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import base.TestBase;
import utilities.TakeScreenshot;

public class custom_Listener extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName().toUpperCase() + " PASSED");
		extent.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getName().toUpperCase() + " FAILED");
		test.log(Status.FAIL, test.addScreenCaptureFromPath(TakeScreenshot.screenshotName));
		extent.flush();
	    System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TakeScreenshot.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log(
			    "<a href='file:///Users/namanpatel/Documents/Selenium/Selenium_Projects/" +
			    "Project1_DataDrivenFramework/test-output/Screenshots/" +
			    TakeScreenshot.screenshotName +
			    "' target='_blank'>View Screenshot</a>",
			    true
			);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
