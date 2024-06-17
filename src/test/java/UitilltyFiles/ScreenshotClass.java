package UitilltyFiles;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ScreenshotClass {
	
	WebDriver driver;
	public ScreenshotClass(WebDriver driver) {
		this.driver = driver;
	}
	
	public void screenShot(String name) {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File folderName = new File("screenshot");
		File source = ss.getScreenshotAs(OutputType.FILE);
		File destiation = new File(folderName.getAbsolutePath()+"//"+name+".png");
		try {
			FileUtils.copyFile(source,destiation);
		}catch (Exception e) {System.out.println(name+" could'nt take screenshot");}
	}
	
}
