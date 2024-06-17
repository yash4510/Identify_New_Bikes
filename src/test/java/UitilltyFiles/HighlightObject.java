package UitilltyFiles;

import javax.swing.JScrollBar;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import BaseClass.BaseClass;

public class HighlightObject extends BaseClass {
	WebDriver driver;
	static JavascriptExecutor js;
	
	
	public HighlightObject(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public void highlight(WebElement element) {
		js.executeScript("arguments[0].style.border='2px solid red'",element);
	}
	
	public void removeHighlight(WebElement element) {
		js.executeScript("arguments[0].style.border='none'",element);
	}
	
	
}
