package PageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.apache.poi.ss.formula.atp.Switch;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseClass.BaseClass;
import UitilltyFiles.HighlightObject;
import UitilltyFiles.ScreenshotClass;
import io.cucumber.java.be.I.Is;

public class UsedCarsPage extends BasePage {
	public String parentWin;
	public String childWin;

	@FindBy(xpath="//span[normalize-space()='Read more']") WebElement readMore;
	@FindBy(xpath="//header/div/div[2]/div") WebElement user;
	@FindBy(xpath="//div[@id='signinDiv']/div") WebElement sigin;
	@FindBy(xpath = "//tbody/tr/td[1]") List<WebElement> carName;
	@FindBy(xpath="//input[@type='email']") WebElement email;
	
	public UsedCarsPage(WebDriver driver) {
		super(driver);
	}
	
	//click on the "read more"
	public boolean clickOnReadMore() {
		try {
			wait.until(ExpectedConditions.visibilityOf(readMore));
			if(readMore.isDisplayed()) {
				ho.highlight(readMore);
				js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", readMore);
				readMore.click();	
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//get the Total number of popular cars
	public int getCarSize() {
		wait.until(ExpectedConditions.visibilityOfAllElements(carName));
		sc.screenShot("popularCarName");
		return carName.size();	
	}
	
	//get the popular cars
	public String fetchPopularCars(int index) {
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'})", carName.get(index));
		ho.highlight(carName.get(index));
		return carName.get(index).getText();
		
	}
	
	//click on the login button
	public boolean loginUser() {
		try {
			if(user.isDisplayed()) {
				ho.highlight(user);
				sc.screenShot("user");
				user.click();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//waiting for the login popup
	public boolean waitingForPopup() {
		try {
			wait.until(ExpectedConditions.visibilityOf(sigin));
			if(sigin.isDisplayed()) {
				return true;
			}
		}catch (Exception e) {
			driver.navigate().refresh();
	        loginUser();
	        waitingForPopup();
		}
		return false;
	}
	
	//click on the sigin
	public boolean clickOnSignIn() {
		try {
			if(sigin.isDisplayed()) {
				ho.highlight(sigin);
				sc.screenShot("signIn");
				sigin.click();
			}
			Thread.sleep(3000);
			return true;
		} catch (Exception e) {
			  
		}
		return false;
	}
	
	//switch to the browser window 
	public  void switchWindows() {
		Set<String> windowsId = driver.getWindowHandles();
		ArrayList<String> windowsIdList = new ArrayList<String>(windowsId);
		//if the sign in window is appear change the driver to that specific window or retry the login steps
		if (windowsIdList.size() > 1) {
	        parentWin = windowsIdList.get(0); // Parent window
	        childWin = windowsIdList.get(1); // Child window
	        driver.switchTo().window(childWin);
	        System.out.println("Window got switched");
	    } else {
	        System.out.println("No child window to switch to... retrying");
	        loginUser();
	        waitingForPopup();
	        clickOnSignIn();
	        switchWindows();
	      
	    }
	}

}
