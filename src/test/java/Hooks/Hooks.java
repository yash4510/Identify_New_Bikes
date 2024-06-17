package Hooks;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



import BaseClass.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	  static WebDriver driver;
	  Properties properties;
	  static boolean isInitialized = false;
	  public static boolean isQuit = false;
	
	@Before
	public void appSetup() throws IOException {
		
		//If isInitialized is not true then If statement get executed
		if(!isInitialized) {
			//getting the driver from the Base class
			driver = BaseClass.setUpDriver();
			//getting properties from the "config.properties"
			properties = BaseClass.getProperties();
			//reading the "appURL" and passing it to the get method
			driver.get(properties.getProperty("appURL"));
			//maximizing the window
			driver.manage().window().maximize();
			isInitialized = true;
		}
	}
	
	@After
	public void quitAplication() throws IOException {
		//If isQuit is true then If statement get executed
		if(isQuit){
			driver.quit();
			}
	}
	
	   @AfterStep
	    public void addScreenshot(Scenario scenario) {
	        if(scenario.isFailed()) {       	
	        	TakesScreenshot ts=(TakesScreenshot) driver;
	        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
	        	scenario.attach(screenshot, "image/png",scenario.getName());
	        	            
	        }
	      
	    }
}
