package StepDefinition;

import static org.junit.Assert.*;

import java.io.IOException;

import org.bouncycastle.pqc.jcajce.provider.BIKE;

import BaseClass.BaseClass;
import PageObject.HomePage;
import io.cucumber.java.en.*;


public class HomeStepDefiniton {
	static HomePage home;
	String notificationString;
	String fileIO = System.getProperty("user.dir")+"//IOfile//IOfiles.xlsx";
	
	@Given("user on the Home page")
	public void user_on_the_home_page() {
		//getting the driver from the base class
		home = new HomePage(BaseClass.getDriver());
		BaseClass.getLogger().info("*******************HOME PAGE*********************");
	    assertTrue(home.getCurrentTitle().contains("New"));    
	}

	@When("user checks for any visbile push notifications")
	public void user_checks_for_any_visbile_push_notifications() {
		notificationString = home.checkNotification();
		if(notificationString.equals("Equals")) {
			BaseClass.getLogger().error(" Some Error occured while displaying the Push Notification.");
			fail();
		}else{
			if(notificationString.equals("Displayed")) {
				BaseClass.getLogger().error("Push Notification is displayed");	
			}else {
				BaseClass.getLogger().error("Push Notification is not displayed");					
			}
			assertTrue(true);
		}
		  
	}

	@Then("the user should able to dismiss the notification sucessfully")
	public void the_user_should_able_to_dismiss_the_notification_sucessfully() {
		if(notificationString.equals("Equals")) {
			boolean result = home.clickOnNotification();
			if(!result) {
				BaseClass.getLogger().error("Push Notification is not cilcked");
			}else{
				BaseClass.getLogger().info("Push Notification got clicked");
			}
			
			assertEquals(result, true);
		}else {
			assertTrue(true);
		}
		
	    
	}

	@When("the user hovers the bike menu")
	public void the_user_hovers_the_bike_menu() {
	    String resultString  = home.hoverOnBikeMenu();
	    if(!resultString.equals("Bikes")) {
			BaseClass.getLogger().error("Not Hovered on Bike Menu ");
		}else{
			System.out.println("Bike Menu Selected");
			BaseClass.getLogger().info("Hovered on Bike Menu");
		}
	     assertEquals(resultString, "Bikes");
	    
	}

	@When("the users selects the upciming bikes option from menu")
	public void the_users_selects_the_upciming_bikes_option_from_menu() {
	    
	    boolean result  = home.navigateToUpcomingBikes();
	    if(!result) {
	    	System.out.println("UpcomingBike Menu Selected");
			BaseClass.getLogger().error("Not clicked on Upcoming Bike ");
		}else{
			BaseClass.getLogger().info("Cilcked on Upcoming Bikes");
		}
	    assertEquals(result, true);
	     
	}

	@Then("the user should redirect to the upcoming bikes section")
	public void the_user_should_redirect_to_the_upcoming_bikes_section() throws IOException {
		boolean result  = home.getUpcomingPage();
	    if(!result) {
			BaseClass.getLogger().error("Navigate to Next Page is Not Sucessful");
		}else{
			BaseClass.getLogger().info("Navigate to Next Page is Sucessful");
		}
	    assertEquals(result, true);
	    
	}

}
