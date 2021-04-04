package com.sumanth.frameworklearning.end2endproject.newuserregistration;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sumanth.frameworklearning.end2endproject.Base;
import com.sumanth.frameworklearning.end2endproject.pageobjects.HomePage;
import com.sumanth.frameworklearning.end2endproject.pageobjects.HomePageSignInWinPopUP;
import com.sumanth.frameworklearning.end2endproject.pageobjects.NewUserRegis;
import com.sumanth.frameworklearning.end2endproject.wrappermethods.WrapperMethods;

public class VerifyIndiaMobileCodeTest extends Base {
	private WebDriver driver = null;
	private Logger log = LogManager.getLogger();
	private Properties propfile = null;

	@BeforeClass
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver Initialized");
	}

	@AfterClass
	public void teardown() {
		driver.quit();
		log.info("Closing the Driver\n");
	}

	@BeforeMethod
	public void launchpage() throws IOException {
		propfile = readPropertiesFile(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		log.info("Launching WebPage");
		WrapperMethods.launchWebPage(driver, propfile.getProperty("url"));
		log.info("Launched WebPage");
	}

	@Test
	public void verifyIndiaMobileCode( ) throws IOException {
		Actions a = new Actions(driver);
		HomePage hp = new HomePage(driver);
		HomePageSignInWinPopUP hpwin = new HomePageSignInWinPopUP(driver);
		NewUserRegis newuser = new NewUserRegis(driver);

		log.debug("Clicking on start here link to register new user");
		a.moveToElement(hp.signIn()).build().perform();
		a.moveToElement(hpwin.startHere()).click().build().perform();

		String text = newuser.createAccount().getText();
		if (!text.contains("Create Account")) {
			log.error("FAIL: New User Registration Page Failed to Load !!!");
			Assert.assertTrue("New User Registration Page Failed to Load", false);
		}
		
		log.debug("Validate India's mobile code number");
		text = newuser.mobIndiaCode().getText();
		if (!text.contains("IN +91")) {
			log.error("FAIL: India Mobile Code is not IN 91, but " + text + " !!!");
			Assert.assertTrue("India Mobile Code is not IN 91, but \" +text+ \" !!!", false);
		}
	}
}
