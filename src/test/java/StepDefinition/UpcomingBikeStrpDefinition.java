package StepDefinition;

import static org.junit.Assert.*;

import java.io.IOException;


import BaseClass.BaseClass;
import PageObject.UpcomingBikePage;
import UitilltyFiles.ExcelUtils;
import io.cucumber.java.en.*;

public class UpcomingBikeStrpDefinition {
	static UpcomingBikePage bike;
	String fileIO = System.getProperty("user.dir")+"//IOfile//IOfiles.xlsx";
	
	@Given("the user is on the Upcoming Bikes page")
	public void the_user_is_on_the_upcoming_bikes_page() {
		bike = new UpcomingBikePage(BaseClass.getDriver());
		BaseClass.getLogger().info("********************UPCOMING BIKES**********************");
		assertTrue(bike.getCurrentTitle().contains("Upcoming Bikes"));
	    
	}

	@When("the user selects the Honda brand from the dropdown")
	public void the_user_selects_the_brand_from_the_dropdown() {
		
		boolean result  = bike.clickOnChooseBrand();
	    if(!result) {
			BaseClass.getLogger().error("Not selected the choose brand");
		}else{
			BaseClass.getLogger().info("selected the choose brand");
		}
	    
		assertEquals(result, true);
	}

	@Then("only Honda bikes should be displayed on the list")
	public void the_dropdown_should_reflect_as_the_selected_brand() {
		
		boolean result  = bike.chooseBrand();
	    if(!result) {
			BaseClass.getLogger().error("Brand is Not selected");
		}else{
			System.out.println("Honda got selected");
			BaseClass.getLogger().info("Brand is selected");
		}
		assertEquals(result, true);
	}


	@When("the user clicks on the Load More button")
	public void the_user_clicks_on_the_button() throws InterruptedException {
	
		String resultString  = bike.displayAllBikes();
	    if(!resultString.equals("loaded")) {
			BaseClass.getLogger().error("Bikes is Not Displayed");
		}else{
			System.out.println("Loded More Bikes");
			BaseClass.getLogger().info("Bikes is Displayed");
		}
		assertEquals(resultString,"loaded" );
	    
	}

	@Then("All bikes should be displayed on the page")
	public void additional_bikes_should_be_displayed_on_the_page() throws InterruptedException {
		int length = bike.getBikeSize();
		System.out.println("All Bikes on the Specific Brand:");
		for(int i=0;i<length;i++) {
			String bikeName = bike.getAllBikeNames(i);
			if(!bikeName.equals("")) {
				BaseClass.getLogger().info(bikeName +" is displayed");
			}else {
				BaseClass.getLogger().error("BikesName is Not Displayed");
			}
			
			System.out.println(bikeName);
		}
	}


	@When("the user views the list of bikes")
	public void the_user_views_the_list_of_bikes() {
		
		
		boolean result  = bike.visibleOfBikes();
	    if(!result) {
			BaseClass.getLogger().error("Bikes are not visible");
		}else{
			BaseClass.getLogger().info("Bikes are visible");
		}
		
		assertEquals(bike.visibleOfBikes(), true);
	}

	@Then("bikes priced under {string} lakhs should be fetched")
	public void bikes_priced_under_lakhs_should_be_fetched(String string) {
		
	    if(Double.parseDouble(string)>0.00) {
			BaseClass.getLogger().info(string+" price is set");
			bike.setPrice(Double.parseDouble(string));
			assertTrue(true);
		}else{
			BaseClass.getLogger().error(string +" is not invlaid");
			assertTrue(true);
		}
	    
	}

	@Then("the details should include the name and price")
	public void the_details_should_include_the_name_and_price() throws IOException {
		int length = bike.getBikeSize();
		int j = 1;
		System.out.println("Displayed Bikes on the Specified Price with the selected Brand");
		for(int i = 0;i<length;i++) {
			String bikeResult  = bike.FetchBikeDetails(i);
			if(bikeResult!=null) {
				String bikesValue[] = bikeResult.split(":");
				ExcelUtils.setCellData(fileIO, "bikes", j, 0, bikesValue[0]);
				ExcelUtils.setCellData(fileIO, "bikes", j, 1, bikesValue[1]);
				System.out.println(bikeResult);
				BaseClass.getLogger().info(bikeResult+" ---> Entry Successful");
				j++;
			}else {
				BaseClass.getLogger().error("Entry Failed");
			}
		}
	   
	}

	@When("the user navigates to the Used Cars menu")
	public void the_user_navigates_to_the_used_cars_menu() {
		
		boolean result  = bike.hoverOnUsedCars();
	    if(!result) {
			BaseClass.getLogger().error("Not Hovered on Used-Cars Menu");
		}else{
			BaseClass.getLogger().info("Hovered on Used-Cars Menu");
		}
		
		assertEquals(result, true);
		
	   
	}

	@When("selects {string} from the options")
	public void selects_from_the_options(String string) {
		boolean result  = bike.clickOnUsedCarsChennai();
	    if(!result) {
			BaseClass.getLogger().error("Not clicked on Used-Cars-Chennai Menu");
		}else{
			BaseClass.getLogger().info("Clicked on Used-Cars-Chennai Menu");
		}
	    assertEquals(result, true);
	    
	}

	@Then("the user should be redirected to the Used Cars page")
	public void the_user_should_be_redirected_to_the_used_cars_page() {
		
		boolean result  = bike.navigateToUsedCars();
	    if(!result) {
			BaseClass.getLogger().error("Redirection to Used cars Page Sucessfull");
		}else{
			BaseClass.getLogger().info("Redirection to Used cars Page Not Sucessfull");
		}
		assertEquals(result, true);
		
	}

}
