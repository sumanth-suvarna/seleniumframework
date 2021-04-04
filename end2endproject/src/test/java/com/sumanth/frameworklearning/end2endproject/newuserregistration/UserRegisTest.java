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

public class UserRegisTest extends Base {
	public WebDriver driver = null;
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
	public void existingUserRegistration() throws IOException {

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
		log.debug("Enter new user name");
		newuser.yourName().sendKeys(propfile.getProperty("existinguser"));
		log.info("Entered new user name");

		log.debug("Enter new user mobile number");
		newuser.mobNo().sendKeys(propfile.getProperty("existingusermobno"));
		log.info("Entered new user mobile number");

		log.debug("Enter new user email id");
		newuser.emailId().sendKeys(propfile.getProperty("existinguseremail"));
		log.info("Entered new user email id");

		log.debug("Enter new user password");
		newuser.password().sendKeys(propfile.getProperty("existinguserpwd"));
		log.info("Entered new user password");

		log.debug("Click on continue button");
		newuser.continueButton().click();

		log.debug("Validate Email Id Already Registered Message");
		text = newuser.emailIdExists().getText();
		//if (!text.contains(propfile.getProperty("existinguseremail"))) {
		if (!text.contains("whitehorse")) {
			log.error("FAIL: Existing user registration successful even though email id sumanth.suvarna@gmail.com already exists !!!");
			Assert.assertTrue("Existing user registration successful even though email id sumanth.suvarna@gmail.com already exists !!!", false);
		}
	}
}
