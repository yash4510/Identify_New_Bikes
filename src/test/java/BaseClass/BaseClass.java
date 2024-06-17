package BaseClass;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import UitilltyFiles.ExcelUtils;



public class BaseClass {

	static WebDriver driver;
	public static Properties properties;
	static Logger logger;
	
	//Assigning the driver
	public static WebDriver setUpDriver() throws IOException {
		
		//get the properties file
		properties = getProperties();
		
		//getting the browser name from "config.properites" file
		String browser = properties.getProperty("browser").toLowerCase();
		
		switch (browser) {
		case "chrome"://If the driver is chrome then chromedriver  object will created
			driver = new ChromeDriver();
			break;
		case "edge"://If the driver is edge then edgedriver  object will created
			driver = new EdgeDriver();
			break;
		default:
			return null;
		}
		
		getLogger().info("Driver is created suceesfully");
		//Adding wait statement
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//creating the Excel file
		ExcelUtils.createExcelSheet();
		return driver;
	}
	
	public static Properties getProperties() throws IOException {
		
		//reading the file
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		//creating a Properties object
		properties = new Properties();
		//Load the file into the object
		properties.load(file);
		return properties;
	}
	
	public static WebDriver getDriver() {
		System.out.println("getDriver"+ driver);
		return driver;
	}
	
	public static Logger getLogger() {
		logger=LogManager.getLogger();;
		return logger;
	}
	
}
