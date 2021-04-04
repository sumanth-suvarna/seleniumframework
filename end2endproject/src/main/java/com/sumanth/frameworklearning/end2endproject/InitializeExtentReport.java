package com.sumanth.frameworklearning.end2endproject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class InitializeExtentReport {
	public static ExtentReports initializeReport() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter =  new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Automation Test Report");
		reporter.config().setReportName("Amazon Test Reports");
		ExtentReports reports =  new ExtentReports();
		reports.attachReporter(reporter);
		return reports;
	}
}
