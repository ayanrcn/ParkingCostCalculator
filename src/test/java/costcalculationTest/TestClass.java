package costcalculationTest;
import costcalculation.CostCalculation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
public class TestClass {
	private CostCalculation objTest;
	private WebDriver driver;
	@BeforeClass
	public void setup() {
		objTest = new CostCalculation();
		driver = objTest.driver;
	}
	//open browser and open url

public static void calculateDifferenceUsingLocalDate(String startingDate, String leavingDate) 
	{ 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate d1 = LocalDate.parse(startingDate, formatter); 
		LocalDate d2 = LocalDate.parse(leavingDate, formatter);
		long differenceInDays = Duration.between(d1.atStartOfDay(), d2.atStartOfDay()).toDays(); 
		System.out.println("Difference using LocalDate: " + differenceInDays + " days");
	}
	@Test
	public void pagetitle() {
		objTest.openweb("https://www.shino.de/parkcalc/");
		String Title = objTest.getPageTitle();
		//System.out.println(Title);
		Assert.assertEquals(Title,"Parking Cost Calculator");
		
	}
	
	@Test
	public void getRates() {
		objTest.openweb("https://www.shino.de/parkcalc/");
		List<WebElement> ratelist=objTest.driver.findElements(By.tagName("p"));
		for(WebElement i : ratelist) {
			String Text = i.getText();
			System.out.println(Text);
			//Assert.assertTrue(Text.contains(""), "Rates not found");
		}
	}
	@Test
	public void parkinglot() {
		WebElement selectdropdown = driver.findElement(By.xpath("//option[@value = 'Valet']"));
		selectdropdown.click();
		String selectedvalue = selectdropdown.getAttribute("value");
		Assert.assertEquals(selectedvalue,"Valet","value not found");
	}
	@Test
	public void valet24hour() {
		//String leavingDate = "01/12/2025";
		//String startingDate = "01/11/2025";
		//
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("10");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/13/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("10");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("18"),"wrong cost displaying");
		
		
		
		


		
	}
	
}
