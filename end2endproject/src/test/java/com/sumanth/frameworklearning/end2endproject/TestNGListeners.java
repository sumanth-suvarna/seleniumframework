package com.sumanth.frameworklearning.end2endproject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestNGListeners extends Base implements ITestListener {
	private Logger log = LogManager.getLogger();
	private ExtentReports reports = InitializeExtentReport.initializeReport();
	private ExtentTest test = null;
	private ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		log.info("\n");
		log.info("Starting Test Case: "+result.getInstanceName());
		test = reports.createTest(result.getMethod().getMethodName());
		thread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("Test Case "+result.getInstanceName()+" PASSED");
		log.info("\n");
		//test.pass("Test Passed");
		thread.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("Test Case "+result.getInstanceName()+" FAILED");
		//test.fail(result.getThrowable());
		thread.get().fail(result.getThrowable());
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			//test.addScreenCaptureFromPath(takeScreenshot(driver, result.getMethod().getMethodName()));
			//thread.get().addScreenCaptureFromPath(takeScreenshot(driver, result.getMethod().getMethodName()));
			//String screenshotName = "." + takeScreenshot(driver, result.getMethod().getMethodName());
			String screenshotName = takeScreenshot(driver, result.getMethod().getMethodName());
			thread.get().addScreenCaptureFromPath(screenshotName);
		} catch (IOException e) {
			e.printStackTrace();
			log.debug(e.getStackTrace());
		}
		log.info("\n");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		log.info("Test Case "+result.getInstanceName()+" FAILED WITH TIMEOUT");
		log.info("\n");
		thread.get().fail(result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		log.info("Starting Test Suite");
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("Ending Test Suite");
		reports.flush();
	}
	
}
