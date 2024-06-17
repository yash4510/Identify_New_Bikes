package PageObject;

import java.io.Closeable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bouncycastle.oer.its.etsi103097.extension.EtsiOriginatingHeaderInfoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.node.IntNode;


public class LoginPage extends BasePage{
	@FindBy(xpath="//input[@type='email']") WebElement email;
	@FindBy(xpath="//button//span[normalize-space()='Next']") WebElement nextBtn;
	@FindBy(xpath="//span[@class='AfGCob']/parent::div") WebElement errorMsg;
	@FindBy(xpath="//a[contains(@class,'brandlogoLink')]") WebElement logo;
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//pass email value into theinput box
	public boolean EmailValidation(String gmail) {
		wait.until(ExpectedConditions.visibilityOf(email));
		try {
			if(email.isDisplayed()) {
				email.clear();
				email.sendKeys(gmail);
				sc.screenShot("email");	
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//click on the next button
	public boolean clickOnNextBtn() {
		try {
			if(nextBtn.isDisplayed()) {
				ho.highlight(nextBtn.findElement(By.xpath("./ancestor::button")));
				nextBtn.click();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//capture  the error message
	public String captureErrorMessage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(errorMsg));
			if(errorMsg.isDisplayed()) {
				ho.highlight(errorMsg);
				sc.screenShot("errorMsg");
				String message = errorMsg.getText();
				System.out.println(message);
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//close the browser
	public void closeSignIn() {
		    Set<String> handleSet = driver.getWindowHandles();
		    List<String> winList = new ArrayList<>(handleSet);
		    driver.close();
		    driver.switchTo().window(winList.get(0));
		    driver.navigate().refresh();
		    logo.click();
		    try {
				Thread.sleep(2000);
			} catch (Exception e) {}
	}
	
}

	
