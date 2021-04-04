package com.sumanth.frameworklearning.end2endproject.pageobjects;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sumanth.frameworklearning.end2endproject.Base;

public class HomePageSignInWinPopUP extends Base {
	private WebDriver driver = null;
	private Properties propfile = null;
	private Logger log = LogManager.getLogger();
	
	//constructor
	public HomePageSignInWinPopUP(WebDriver driver) throws IOException {
		this.driver = driver;
		this.propfile = readPropertiesFile(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\locators-homepage-SignIn-WinPopUp.properties");
	}
	
	// return New Customer Start Here WebElement
	public WebElement startHere() {
		log.debug("Locating start here WebElement");
		return driver.findElement(locatorParser(propfile.getProperty("starthere")));
	}
}
