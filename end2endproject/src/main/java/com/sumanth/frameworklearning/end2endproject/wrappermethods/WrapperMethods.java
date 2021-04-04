package com.sumanth.frameworklearning.end2endproject.wrappermethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WrapperMethods {
	private static Logger log = LogManager.getLogger();
	
	public static void launchWebPage(WebDriver driver, String url) {
		log.debug("Launch WebPage: "+url);
		driver.get(url);
	}
}
