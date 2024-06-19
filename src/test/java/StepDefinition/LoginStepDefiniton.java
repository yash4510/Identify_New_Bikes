package StepDefinition;

import static org.junit.Assert.*;

import java.io.IOException;

import BaseClass.BaseClass;
import Hooks.Hooks;
import PageObject.LoginPage;
import UitilltyFiles.ExcelUtils;
import io.cucumber.java.en.*;

public class LoginStepDefiniton {
	static LoginPage login;
	static int i=1;
	static String fileEmail = System.getProperty("user.dir")+"//IOfile//emails.xlsx";
	String fileIO = System.getProperty("user.dir")+"//IOfile//IOfiles.xlsx";
	
	@Given("The user should on the accountsgoogle page")
	public void the_user_should_on_the_accountsgoogle_page() {
		login =  new LoginPage(BaseClass.getDriver());
		BaseClass.getLogger().info("***********************Login Page***********************");
		assertTrue(login.getCurrentTitle().contains("Sign in"));
		
	}
	
	@When("the enters {string} into the email field")
	public void the_enters_into_the_email_field(String emailIndex) throws Exception{
		String emailString = ExcelUtils.getCellData(fileEmail, "sheet1", Integer.parseInt(emailIndex), 0);
		boolean result = login.EmailValidation(emailString);
		ExcelUtils.setCellData(fileIO, "email", i, 0,emailString);
		System.out.println("Email: "+emailString);
		 if(!result) {
				BaseClass.getLogger().error("Unable to Enter Email Entered");
			}else{
				BaseClass.getLogger().info("Email got Entered");
			}
		assertEquals(result, true);  
	}
	
	@When("clicks on the next button")
	public void clicks_on_the_next_button() {
		boolean result = login.clickOnNextBtn();
		 if(!result) {
				BaseClass.getLogger().error("Unable to Clicke on Next Button");
			}else{
				BaseClass.getLogger().info("Clicked on Next Button");
			}
		assertEquals(result, true);
	    
	}
	
	@Then("capture the error message")
	public void capture_the_error_message() throws IOException {
		
		String result = login.captureErrorMessage();
		 if(result==null) {
				BaseClass.getLogger().error("Unable to capture Error message");
			}else{
				System.out.println("Error message: "+result);
				ExcelUtils.setCellData(fileIO, "email", i, 2,result);
				ExcelUtils.setCellData(fileIO, "email", i,3,(result.equals(ExcelUtils.getCellData(fileIO, "email", i, 1)))?"PASS":"FAIL") ;
				BaseClass.getLogger().info("Error message capture was successful");
				i++;
				assertEquals(result,ExcelUtils.getCellData(fileIO, "email", i-1, 2) );
			}
	    
	}
	
	@Then("close the google window")
	public void close_the_google_window() {
		
	    login.closeSignIn();
	    Hooks.isQuit = true;
	}

}
