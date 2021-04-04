package cucumberstepdefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.sumanth.frameworklearning.end2endproject.Base;
import com.sumanth.frameworklearning.end2endproject.pageobjects.HomePage;
import com.sumanth.frameworklearning.end2endproject.pageobjects.HomePageSignInWinPopUP;
import com.sumanth.frameworklearning.end2endproject.pageobjects.NewUserRegis;
import com.sumanth.frameworklearning.end2endproject.wrappermethods.WrapperMethods;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NewUserRegistration extends Base {
	public WebDriver driver = null;
	private Logger log = LogManager.getLogger(LaunchWebPage.class.getName());
	
	@Given("^The Amazon webpage \"([^\"]*)\" is launched$")
	public void the_Amazon_webpage_is_launched(String arg1) throws Throwable {
		driver = initializeDriver();
		log.info("Driver Initialized");
		log.info("Launching WebPage");
		WrapperMethods.launchWebPage(driver, arg1);
		log.info("Launched WebPage");
	}

	@Given("^Navigate to new user signin page$")
	public void navigate_to_new_user_signin_page() throws Throwable {
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
	}

	@When("^The user tries to register already existing user \"([^\"]*)\" with mobno \"([^\"]*)\" email id \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void the_user_tries_to_register_already_existing_user_with_mobno_email_id_and_password(String username, String mobno, String email, String pwd) throws Throwable {
		NewUserRegis newuser = new NewUserRegis(driver);
		
		log.debug("Enter new user name");
		newuser.yourName().sendKeys(username);
		log.info("Entered new user name");

		log.debug("Enter new user mobile number");
		newuser.mobNo().sendKeys(mobno);
		log.info("Entered new user mobile number");

		log.debug("Enter new user email id");
		newuser.emailId().sendKeys(email);
		log.info("Entered new user email id");

		log.debug("Enter new user password");
		newuser.password().sendKeys(pwd);
		log.info("Entered new user password");

		log.debug("Click on continue button");
		newuser.continueButton().click();
		
	}

	@Then("^Validate Email Id \"([^\"]*)\" is already registered message in the browser$")
	public void validate_Email_Id_is_already_registered_message_in_the_browser(String email) throws Throwable {
		log.debug("Validate Email Id Already Registered Message");
		
		NewUserRegis newuser = new NewUserRegis(driver);
		String text = newuser.emailIdExists().getText();
		driver.close();
		if (!text.contains(email)) {
		//if (!text.contains("whitehorse")) {
			log.error("FAIL: Existing user registration failed !!!");
			Assert.assertTrue("Existing user registration failed !!!", false);
		}
	}
}
