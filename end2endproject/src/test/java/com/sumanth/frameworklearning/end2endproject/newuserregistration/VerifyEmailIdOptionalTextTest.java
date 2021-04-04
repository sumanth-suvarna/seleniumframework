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

public class VerifyEmailIdOptionalTextTest extends Base {
	public WebDriver driver = null;
	private Logger log = LogManager.getLogger();
	private Properties propfile = null;

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

	@BeforeMethod(groups = {"smoke"})
	public void launchpage() throws IOException {
		propfile = readPropertiesFile(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		log.info("Launching WebPage");
		WrapperMethods.launchWebPage(driver, propfile.getProperty("url"));
		log.info("Launched WebPage");
	}

	@Test(groups = {"smoke"})
	public void verifyemailoptionaltag() throws IOException {
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
		
		log.debug("Validate optional email id text");
		text = newuser.emailIdOptional().getText();
		if (!text.contains("optional")) {
			log.error("FAIL: Email ID is not marked as optional !!!");
			Assert.assertTrue("Email ID is not marked as optional !!!", false);
		}
	}
}
