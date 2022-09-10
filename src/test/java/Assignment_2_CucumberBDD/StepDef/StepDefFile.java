package Assignment_2_CucumberBDD.StepDef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import Assignment_2_CucumberBDD.Core.WebDriverFactory;
import Assignment_2_CucumberBDD.PageObjects.CommonPageObjects;
import Assignment_2_CucumberBDD.PageObjects.FooterSectionObject;
import Assignment_2_CucumberBDD.PageObjects.SignInPageObject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefFile {
	private static final Logger logger = LogManager.getLogger(StepDefFile.class);
	WebDriver driver;
	Scenario scn;
	String base_url = "http://automationpractice.com/";

//============== Declare object reference of pageObjects classes =============================//
	
	CommonPageObjects cmnPageObject;
	FooterSectionObject footerObject;
	SignInPageObject signInObject;
	
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn=scn;

		//Get the desired browser to be invoked
		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");

		//Initialize object of page objects classes 
		 cmnPageObject= new CommonPageObjects(driver, scn);
		 footerObject= new FooterSectionObject(driver, scn);
		 signInObject= new SignInPageObject(driver, scn);
	}

//====================== After Hook =========================================================//
	
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	
//====================== Background ================================================//
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
	    }

//===================== Scenarios =================================================//

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Validate current URL of landing page with expected URL")
	public void validate_current_url_of_landing_page_with_expected_url() {
		 cmnPageObject.validatePageURL();
		 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
	}
	
	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
		cmnPageObject.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
		
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		cmnPageObject.fetchLogoHeight();
	}

	@Then("Height of logo should be {string}")
	public void height_of_logo_should_be(String height) {
		cmnPageObject.logoHeightValid(height);
	}
		
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		cmnPageObject.fetchLogoWidth(); 
	}

	@Then("Width of logo should be {string}")
	public void width_of_logo_should_be(String width) {
		cmnPageObject.logoWidthValid(width);
	}
	
	@Given("User see SignIn button")
	public void user_see_sign_in_button() {
		signInObject.signInBtnValidation();
		scn.log("Display the signIn Button");
	}

	@When("User click on SignIn button")
	public void user_click_on_sign_in_button() {
		signInObject.clickOnSignInBtn();
	}
	@Then("User is on signIn page which have expected page title as {string}")
	public void user_is_on_sign_in_page_which_have_expected_page_title_as(String SignInPageTitle) {
		signInObject.validateSignInPage(SignInPageTitle);
	}

	@Given("User click on twitter link")
	public void user_click_on_twitter_link() {
		footerObject.setTwitterLink();
		footerObject.clickOnTwitterLink();
	}

	@When("navigate to twitter account page")
	public void navigate_to_twitter_account_page() {
		footerObject.twitterAcPage();
	}
	@Then("User see the twitter account name {string}")
	public void user_see_the_twitter_account_name(String AcName) {
		footerObject.twitterAcNameValidation(AcName);
	}	
}
