package PageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import UitilltyFiles.HighlightObject;
import UitilltyFiles.ScreenshotClass;

public class BasePage {
	WebDriver driver;
	Actions act;
	JavascriptExecutor js;
	HighlightObject ho;
	ScreenshotClass sc;
	WebDriverWait wait;
		
	//parent class constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(this.driver);
		js = (JavascriptExecutor)this.driver;
		ho = new HighlightObject(this.driver);
		sc = new ScreenshotClass(this.driver);
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}
	
	public String getCurrentTitle() {
		return driver.getTitle();
	}
	
}
