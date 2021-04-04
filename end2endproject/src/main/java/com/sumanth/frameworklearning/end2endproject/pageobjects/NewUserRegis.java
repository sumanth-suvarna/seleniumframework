package com.sumanth.frameworklearning.end2endproject.pageobjects;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sumanth.frameworklearning.end2endproject.Base;

public class NewUserRegis extends Base {
	private WebDriver driver = null;
	private Properties propfile = null;
	private Logger log = LogManager.getLogger();

	// constructor
	public NewUserRegis(WebDriver driver) throws IOException {
		this.driver = driver;
		this.propfile = readPropertiesFile(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\locators-newuserregis.properties");
	}

	// return Create Account WebElement
	public WebElement createAccount() {
		log.debug("Locating createAccount WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("createaccount")));
	}

	// return your name WebElement
	public WebElement yourName() {
		log.debug("Locating yourName WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("yourName")));
	}

	// return India mobile code WebElement
	public WebElement mobIndiaCode() {
		log.debug("Locating India's mobile code WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("mobindiacode")));
	}

	// return mobile number WebElement
	public WebElement mobNo() {
		log.debug("Locating mobile number WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("mobno")));
	}

	// return Email ID Optional text WebElement
	public WebElement emailIdOptional() {
		log.debug("Locating Email ID Optional Text WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("emailidoptional")));
	}

	// return Email ID WebElement
	public WebElement emailId() {
		log.debug("Locating Email ID WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("emailid")));
	}

	// return password WebElement
	public WebElement password() {
		log.debug("Locating password WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("password")));
	}

	// return continue button WebElement
	public WebElement continueButton() {
		log.debug("Locating continue button WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("continue")));
	}

	// return there was a problem text WebElement
	public WebElement problem() {
		log.debug("Locating 'There was a problem' WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("problem")));
	}

	// return email already used text WebElement
	public WebElement emailIdExists() {
		log.debug("Locating 'Email ID already used' WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("emailused")));
	}
}
