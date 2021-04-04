package cucumberstepdefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.sumanth.frameworklearning.end2endproject.Base;
import com.sumanth.frameworklearning.end2endproject.pageobjects.HomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LaunchWebPage extends Base {
	public WebDriver driver = null;
	private Logger log = LogManager.getLogger(LaunchWebPage.class.getName());

	@Given("^The web drivers are initialized$")
	public void the_web_drivers_are_initialized() throws Throwable {
		driver = initializeDriver();
		log.info("Driver Initialized");

	}

	@When("^The user enters Amazon URL \"([^\"]*)\" in the browser$")
	public void the_user_enters_Amazon_URL_in_the_browser(String arg1) throws Throwable {
		log.info("Launching WebPage: " +arg1);
		driver.get(arg1);
	}

	@Then("^Verify Amazon webpage is launched successfully$")
	public void the_Amazon_webpage_is_launched_successfully1() throws Throwable {
		HomePage hp = new HomePage(driver);
		Boolean value = hp.signIn().isEnabled();
		driver.close();
		if (value) {
			log.info("URL successfully Amazon Webpage");
			Assert.assertTrue(value);
		}
		else {
			log.info("Failed to launch Amazon Webpage");
			Assert.assertTrue(value);
		}
		

	}

}
