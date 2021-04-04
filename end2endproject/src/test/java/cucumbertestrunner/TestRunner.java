package cucumbertestrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
				features = "src/test/java/cucumberfeature",
				glue = "cucumberstepdefinition",
				plugin = {"pretty", "html:target/cucumber-reports"},
				monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
