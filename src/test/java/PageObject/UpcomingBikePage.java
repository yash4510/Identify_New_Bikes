package PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import BaseClass.BaseClass;
import UitilltyFiles.HighlightObject;
import UitilltyFiles.ScreenshotClass;
import freemarker.core.ReturnInstruction.Return;

public class UpcomingBikePage extends BasePage {
	
	double priceLimit;

	List<String> bikeNameList  = new ArrayList<String>();
	List<String> priceNameList = new ArrayList<String>();
	List<String> carNameList = new ArrayList<String>();
	
	@FindBy(xpath="//div[@id='scrollResult']//li/div//img[contains(@title,'Honda')][1]") 
	WebElement bikeVisibilty;
	@FindBy(xpath="//div[@id='scrollResult']//span[contains(normalize-space(),'Load More')]")
	WebElement loadMore;
	@FindBy(xpath = "(//select)[1]") 
	WebElement brand;
	@FindBy(xpath = "//a[contains(@title,'Honda')]/following-sibling::div/div[1]/div[1]") 
	List<WebElement> BikePrice;
	@FindBy(xpath="//div[@id='scrollResult']//a[contains(@title,'Honda')]") 
	List<WebElement> bikeName;
	@FindBy(xpath="//li[contains(@id,'menuusedcars')]") 
	WebElement UsedCars;
	@FindBy(xpath = "(//span[contains(normalize-space(),'Chennai')])[1]") 
	WebElement usedCarsCHN;
	@FindBy(xpath="//span[normalize-space()='Read more']") 
	WebElement readMore;
	
	
	public UpcomingBikePage(WebDriver driver) {
		super(driver);
		
	}

	//check on the select tag is displayed
	public boolean clickOnChooseBrand() {
		//wait for the select tag to be displayed
		wait.until(ExpectedConditions.visibilityOf(brand));
		//scroll to the select tag is present
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", brand);
		try {
			if(brand.isDisplayed()) {
				ho.highlight(brand);
				sc.screenShot("choose_brand");
			}
			return true;
		} catch (Exception e) {e.printStackTrace();}
		return false;
	}
	
	//Select "Honda" from the select tag
	public boolean chooseBrand() {
		try {	
			if(brand.isDisplayed()) {
				Select chooseBike = new Select(brand);
				chooseBike.selectByVisibleText("Honda");
				wait.until(ExpectedConditions.visibilityOf(bikeVisibilty));
				return true;
			}
		}catch (Exception e) {e.printStackTrace();}
		return false;
	}
	
	//display all bikes By clicking the load more button
	public String displayAllBikes() throws InterruptedException {
		//wait for "load More" button to be displayed
		wait.until(ExpectedConditions.visibilityOf(loadMore));
		int count=0;
		try {
			while(loadMore.isDisplayed()) {
				//Scroll till the scroll the "Load More" button.
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", loadMore);
				ho.highlight(loadMore);
				sc.screenShot("loadMore");
				Thread.sleep(2000);
				js.executeScript("arguments[0].click()", loadMore);
				count++;
			}
			
		}catch(Exception e) {}
		//If the count is greater than 0 then return "loaded" else it will return "null"
		return (count>0)? "loaded":null;
	}
	
	//return the displayed Bikes Name
	public String getAllBikeNames(int index) {
		return bikeName.get(index).getText();
	}
	
	//check weather bikeName List is Empty
	public boolean visibleOfBikes() {
		if(bikeName.isEmpty()) {
			return false;
		}
		return true;
	}
	
	//Get the size of the Bike List
	public int getBikeSize() {
		wait.until(ExpectedConditions.visibilityOfAllElements(BikePrice));
		return BikePrice.size();
	}
	
	//set the price
	public void setPrice(double price) {
		priceLimit = price;
	}
	
	//Fetch the Bikes under specified price
	public String FetchBikeDetails(int index) {
		    //getting the bike price and reversing it. (₹ 12.00 Lakh* -> *hkaL 00.21 ₹)
			String revString = new StringBuilder(BikePrice.get(index).getText()).reverse()+"";
			//take the specific value from the reverse string (00.21)
			String temp = revString.substring(revString.indexOf("L")+2,revString.indexOf("₹")-1);
			//convert the temp string into double
			double price = Double.parseDouble(new StringBuilder(temp).reverse()+"");
			//If the price is less than priceLimit then If condition will get executed
			if(price<=priceLimit) {
				//scroll to the specific bike displayed on the page
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", bikeName.get(index));
				//get the bike name storing it in the string
				String bikeString = bikeName.get(index).getText();
				//get the bike price and storing it in the string
				String priceString = BikePrice.get(index).getText();
				ho.highlight(bikeName.get(index));
				ho.highlight(BikePrice.get(index));
				//return the bike name and bike price
				return bikeString+" :- ₹"+priceString.substring(priceString.indexOf("₹")+2,priceString.indexOf("L")-1);
			}
			return null;
		}
	
	//click on the cars
	public boolean hoverOnUsedCars() {
		//scroll to where the car is displayed
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", UsedCars);
		try {
			if(UsedCars.isDisplayed()) {
				ho.highlight(UsedCars);
				sc.screenShot("UsedCars");
				act.moveToElement(UsedCars).perform();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	//select the "User cars in Chennai" option from the menu
	public boolean clickOnUsedCarsChennai() {
		//wait for the element to be displayed
		try {
			wait.until(ExpectedConditions.visibilityOf(usedCarsCHN));
			if(usedCarsCHN.isDisplayed()){
				ho.highlight(usedCarsCHN);
				sc.screenShot("UsedInChennai");
				js.executeScript("arguments[0].click()",usedCarsCHN);
				Thread.sleep(3000);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//get the title of the next page
	public boolean navigateToUsedCars(){
		wait.until(ExpectedConditions.visibilityOf(readMore));
		return driver.getTitle().contains("Used Cars");
	}
	
}

