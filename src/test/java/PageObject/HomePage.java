package PageObject;

import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseClass.BaseClass;

public class HomePage extends BasePage {

	@FindBy(xpath = "(//div[contains(normalize-space(),'push notifications')])[4]")
	WebElement notification;
	@FindBy(xpath = "//span[contains(normalize-space(),'Maybe later')]")
	WebElement laterBtn;
	@FindBy(xpath = "//li[@id='menubike1']")
	WebElement bikeMenu;
	@FindBy(xpath = "//li[contains(@id,'submenu')]/a[contains(@title,'Upcoming Bikes')]")
	WebElement upcomingBikes;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	//check the notification is displayed
	public String checkNotification() {
		 try {
			  if(notification.isDisplayed()) {
				  return "Displayed";
			  }
	        }
		 //If it catch the NoSuchElementException it will return Element is not displayed
		 catch (NoSuchElementException e) {
	             return "Not Displayed";
	        } 
		 //If it catch other than NoSuchElementException then  print the error trace
		 catch (Exception e) {
	        	e.printStackTrace();
	        }
			return "Error";
	   }
	
	//click Maybe Later button on the notification
	public boolean clickOnNotification() {
		ho.highlight(laterBtn);
		sc.screenShot("notification");
		try {
			if (laterBtn.isEnabled()) {
				laterBtn.click();
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//hover on the Bikes
	public String hoverOnBikeMenu() {
		ho.highlight(bikeMenu);
		sc.screenShot("bikeMenu");
		act.moveToElement(bikeMenu).perform();
		return bikeMenu.findElement(By.xpath("./span")).getAttribute("title");
	}

	//clicking on the upcoming bikes
	public boolean navigateToUpcomingBikes() {
		//wait for the element to be displayed
		wait.until(ExpectedConditions.visibilityOf(upcomingBikes));
		try {
			if (upcomingBikes.isDisplayed()) {
				ho.highlight(upcomingBikes);
				sc.screenShot("upcomingBikes");
				upcomingBikes.click();
				ho.removeHighlight(bikeMenu);
				Thread.sleep(1000);
			}
			return true;
		} 
		catch (Exception e) {}
		return false;
	}

	//getting the title of the upcoming page.
	public boolean getUpcomingPage() throws IOException {
		return driver.getTitle().contains("Upcoming Bikes");
	}

}
