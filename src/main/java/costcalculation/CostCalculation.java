package costcalculation;
//import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import costcalculationTest.TestClass;
public class CostCalculation {
	public WebDriver driver;
	
	public CostCalculation() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C:/Users/Ayan Banerjee/eclipse-workspace/AutomationWithUdemy/costcalculation/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		this.driver = new ChromeDriver();
	}	
	
	public void openweb(String url) {
		driver.get(url);
		driver.manage().window().maximize();

	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	
	
	

}
