package StepDefinition;

import static org.junit.Assert.*;


import java.io.IOException;

import BaseClass.BaseClass;
import PageObject.UsedCarsPage;
import UitilltyFiles.ExcelUtils;
import io.cucumber.java.en.*;

public class UsedCarsStepDefinition {
	
	static UsedCarsPage cars;
	String fileIO = System.getProperty("user.dir")+"//IOfile//IOfiles.xlsx";
	
	@Given("the user is on the Used Cars Page")
	public void the_user_is_on_the_used_cars_page() {
		cars = new UsedCarsPage(BaseClass.getDriver());
		BaseClass.getLogger().info("**********************USED CAR PAGE*********************");
		assertTrue(cars.getCurrentTitle().contains("Used Cars"));
	}

	@When("the user clicks on {string}")
	public void the_user_clicks_on(String string) {
		boolean result  = cars.clickOnReadMore();
	    if(!result) {
			BaseClass.getLogger().error("Unable to click on Load-More");
		}else{
			BaseClass.getLogger().info("Load-More got Clicked");
		}
	    assertEquals(result, true);
	}

	@Then("the popular cars should be displayed")
	public void the_popular_cars_should_be_displayed() throws IOException {
		int length = cars.getCarSize();
		int j=1;
		System.out.println("Fetching Popular Cars");
		for(int i = 0;i<length;i++) {
			String carsName = cars.fetchPopularCars(i);
			if(!carsName.equals("")) {
				System.out.println(carsName);
				ExcelUtils.setCellData(fileIO, "cars", j++, 0, carsName);
				BaseClass.getLogger().info(carsName +" ---> Entry Sucess");
			}else {
				BaseClass.getLogger().error("Entry Failed");
			}
		}
	   
	}

	@When("the user clicks on the user icon to log in")
	public void the_user_clicks_on_the_user_icon_to_log_in() {
		boolean result  = cars.loginUser();
	    if(!result) {
			BaseClass.getLogger().error("unable to click on user");
		}else{
			BaseClass.getLogger().info("user got clicked");
		}
		assertEquals(result , true);
	   
	   
	}

	@Then("the sign-in pop-up option will appear")
	public void the_sign_in_pop_up_option_will_appear() {
		boolean result  = cars.waitingForPopup();
	    if(!result) {
			BaseClass.getLogger().error("sigin in pop not displayed");
		}else{
			BaseClass.getLogger().info("sigin in pop displayed");
		}
		assertEquals(result, true);
	}

	@Then("the user should click on the Google sign-in")
	public void the_user_should_click_on_the_google_sign_in() {
	    boolean signIn = cars.clickOnSignIn();
	    cars.switchWindows();
	    if(!signIn) {
			BaseClass.getLogger().error("Unable to click on sigin in Button ");
		}else{
			BaseClass.getLogger().info("clicked on the sigin in Button");
		}
	    assertEquals(signIn, true); 
	}
}
