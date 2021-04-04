package com.sumanth.frameworklearning.end2endproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	private Logger log = LogManager.getLogger();

	protected Properties readPropertiesFile(String file) throws IOException {
		Properties propfile = new Properties();
		log.debug("Creating file input stream for file: "+file);
		FileInputStream fis = new FileInputStream(file);
		log.debug("Loading file: "+file);
		propfile.load(fis);
		log.info("Loaded file: "+file);
		return propfile;
	}

	
	protected WebDriver initializeDriver() throws IOException {
		WebDriver driver = null;
		Properties propfile = readPropertiesFile(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		String browserType = propfile.getProperty("browser").toLowerCase();
		
		//String browserType = System.getProperty("browser");
		
		if (browserType.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
	
			if (browserType.contains("headless")) {
				ChromeOptions chromoptn = new ChromeOptions();
				chromoptn.addArguments("headless");
				driver = new ChromeDriver(chromoptn);
			}
			else 
				driver = new ChromeDriver();
			
			log.info("Initialized Chrome Driver");
		} else if (browserType.contains("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
			log.info("Initialized IE Driver");
		} else if (browserType.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Initialized Firefox Driver");
		}
		log.debug("Maximizing Window");
		driver.manage().window().maximize();
		log.debug("Setting Implicit time wait to 10 seconds");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	protected By locatorParser(String locator) {
		By loc = null;
		log.info("Received locator to parse is: "+locator);
		if (locator.contains("By.id")) {
			loc = By.id(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by ID: "+locator);
		}
		else if (locator.contains("By.name")) {
			loc = By.name(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by Name: "+locator);
		}
		else if (locator.contains("By.className")) {
			loc = By.className(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by ClassName: "+locator);
		}
		else if (locator.contains("By.tagName")) {
			loc = By.tagName(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by TagName: "+locator);
		}
		else if (locator.contains("By.partialLinkText")) {
			loc = By.partialLinkText(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by PartialLinkText: "+locator);
		}
		else if (locator.contains("By.linkText")) {
			loc = By.linkText(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by LinkText: "+locator);
		}
		else if (locator.contains("By.cssSelector")) {
			loc = By.cssSelector(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by CssSelector: "+locator);
		}
		else if (locator.contains("By.xpath")) {
			loc = By.xpath(locator.substring(locator.indexOf("\"") + 1, locator.length() - 2));
			log.info("Locating WebElement by Xpath: "+locator);
		}
		return loc;
	}
	
	protected String takeScreenshot(WebDriver driver, String name) throws IOException {
		log.debug("Taking screenshot");
		
		File screenst = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir") + "\\screenshots\\"+name+".png";
		
		File destFile = new File(destpath);
		
		log.debug("Copying screenshot to "+destpath);
		FileUtils.copyFile(screenst, destFile);
		log.info("Screenshot copied");
		
		//This is new path for jenkins
		String newImageString = "http://localhost:8080/job/End2EndProject-RegressionTest/ws/end2endproject/screenshots/"+name+".png";
		
		//return destpath;
		return newImageString;
	}

}
