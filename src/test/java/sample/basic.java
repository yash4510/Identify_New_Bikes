package sample;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class basic {
	List<String> bikeNameList  = new ArrayList<String>();
	List<String> priceNameList = new ArrayList<String>();
	List<String> carNameList = new ArrayList<String>();
	
	
	@FindBy(xpath="//div[@id='scrollResult']//span[contains(normalize-space(),'Load More')]") static WebElement loadMore;
	@FindBy(xpath = "(//select)[1]") static WebElement brand;
	@FindBy(xpath = "//a[contains(@title,'Honda')]/following-sibling::div/div[1]/div[1]") static List<WebElement> BikePrice;
	@FindBy(xpath="//div[@id='scrollResult']//a[contains(@title,'Honda')]") static List<WebElement> bikeName;
	@FindBy(xpath = "//tbody/tr/td[1]") static List<WebElement> carName;
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver  = new ChromeDriver();
		driver.get("https://www.91wheels.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		
		basic bc= new basic();
		PageFactory.initElements(driver, bc);
		By bikeMenu = By.xpath("//li[@id='menubike1']");
		By upcomingBikes = By.xpath("//li[contains(@id,'submenu')]/a[contains(@title,'Upcoming Bikes')]");
		//By brand = By.xpath("(//select)[1]");
		By UsedCars = By.xpath("//li[contains(@id,'menuusedcars')]");
		By usedCarsCHN = By.xpath("(//span[contains(normalize-space(),'Chennai')])[1]");
		By readMore = By.xpath("//span[normalize-space()='Read more']");
		By user = By.xpath("//header/div/div[2]/div");
		By sigin = By.xpath("//div[@id='signinDiv']/div");
		By email = By.xpath("//input[@type='email']");
		By nextBtn = By.xpath("//button//span[normalize-space()='Next']"); 
		By errorMsg = By.xpath("//span[@class='AfGCob']/parent::div");
		
		
		try {
			if(driver.findElement(By.xpath("(//div[contains(normalize-space(),'push notifications')])[4]")).isDisplayed()) {
				driver.findElement(By.xpath("//span[contains(normalize-space(),'Maybe later')]")).click();
			}
		}catch(Exception e) {
			
		}
		
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(bikeMenu)).perform();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click()", driver.findElement(bikeMenu));
		
		
		js.executeScript("arguments[0].click()",driver.findElement(upcomingBikes) );
		
	
		Select chooseBike = new Select(brand);
		chooseBike.selectByVisibleText("Honda");
		
		js.executeScript("arguments[0].scrollIntoView(true)", loadMore);
		
		try {
			while(loadMore.isDisplayed()) {
				js.executeScript("arguments[0].click()", loadMore);
			}
		}catch(Exception e) {
			
		}
		
		js.executeScript("arguments[0].scrollIntoView(true)", brand);
		
		for(int i=0;i<BikePrice.size();i++) {
			String revString = new StringBuilder(BikePrice.get(i).getText()).reverse()+"";
			String temp = revString.substring(revString.indexOf("L")+2,revString.indexOf("₹")-1);
			//System.out.println(new StringBuilder(temp).reverse());
			double price = Double.parseDouble(new StringBuilder(temp).reverse()+"");
			if(price<=4.00) {
				String bikeString = bikeName.get(i).getText();
				String priceString = BikePrice.get(i).getText();
				System.out.println(bikeString+" - ₹"+priceString.substring(priceString.indexOf("₹")+2,priceString.indexOf("L")-1));
			}
		}
		
		act.moveToElement(driver.findElement(UsedCars)).perform();
		js.executeScript("arguments[0].click()", driver.findElement(usedCarsCHN));
		
		driver.findElement(readMore).click();
		
		for(WebElement i:carName) {
			System.out.println(i.getText());
		}
		
		
		driver.findElement(user).click();
		
		//driver.switchTo().frame(0);
		driver.findElement(sigin).click();
		
		Set<String> windowsId = driver.getWindowHandles();
		ArrayList<String> windowsIdList = new ArrayList<String>(windowsId);
		
		String parentWin = windowsIdList.get(0);
		String childWin = windowsIdList.get(1);
		
		driver.switchTo().window(childWin);
		
		driver.findElement(email).sendKeys("hcudhsiu8@gmail.com");
		driver.findElement(nextBtn).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMsg));
		String message = driver.findElement(errorMsg).getText();
		System.out.println(message);
		driver.close();
		
		driver.switchTo().window(parentWin);
		driver.quit();
		
	}

}
