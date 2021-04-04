package com.sumanth.frameworklearning.end2endproject.launchpage;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sumanth.frameworklearning.end2endproject.Base;
import com.sumanth.frameworklearning.end2endproject.pageobjects.HomePage;

public class LaunchURLTest extends Base {
	public WebDriver driver = null;
	private Logger log = LogManager.getLogger(LaunchURLTest.class.getName());

	@BeforeClass(groups = {"smoke"})
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver Initialized");
	}
	
	@AfterClass(groups = {"smoke"})
	public void teardown() {
		driver.quit();
		log.info("Closing the Driver\n");
	}
	
	@Test(groups = {"smoke"})
	public void launchURL() throws IOException {
		HomePage hp = new HomePage(driver);
		Properties propfile = readPropertiesFile(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		driver.get(propfile.getProperty("url").toLowerCase());
		Assert.assertTrue(hp.signIn().isEnabled());
		log.info("URL successfully launched");

	}
	
}
