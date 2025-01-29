package costcalculationTest;
import costcalculation.CostCalculation;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public String selectedvalue1;
	public WebElement dropdown;
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
	@Test(priority =1)
	public void pagetitle() {
		objTest.openweb("https://www.shino.de/parkcalc/");
		String Title = objTest.getPageTitle();
		//System.out.println(Title);
		Assert.assertEquals(Title,"Parking Cost Calculator");
		
	}
	
	@Test(priority =2)
	public void getRates() {
		objTest.openweb("https://www.shino.de/parkcalc/");
		List<WebElement> ratelist=objTest.driver.findElements(By.tagName("p"));
		for(WebElement i : ratelist) {
			String Text = i.getText();
			System.out.println(Text);
			//Assert.assertTrue(Text.contains(""), "Rates not found");
		}
	}
	@Test(priority =3)
	public void parkinglot() {
		WebElement selectdropdown = driver.findElement(By.xpath("//option[@value = 'Valet']"));
		selectdropdown.click();
		String selectedvalue = selectdropdown.getAttribute("value");
		Assert.assertEquals(selectedvalue,"Valet","value not found");
	}
	@Test(priority =4)
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
	@Test(priority =5)
	public void valet6hour() {
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("06");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("18"),"wrong cost displaying");
	}
	@Test(priority =6)
	public void valet5hour() {
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("05");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("12"),"wrong cost displaying");
	}
	@Test(priority =7)
	public void valet4hour59min() {
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("04:59");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("12"),"wrong cost displaying");
	}
	@Test(priority =8)
	public void valetTimeblank() {
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		//driver.findElement(By.id("StartingTime")).sendKeys("");
		//driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		//driver.findElement(By.id("LeavingTime")).sendKeys("04:59");
		//driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("0"),"wrong cost displaying");
	}
	@Test(priority = 9)
	public void valetDateblank() {
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'StartingDate']")));
		element.clear();
		//driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		//driver.findElement(By.id("StartingTime")).sendKeys("");
		//driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		//driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		//driver.findElement(By.id("LeavingTime")).sendKeys("04:59");
		//driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("0"),"wrong cost displaying");
	}
	
public void checkdropdown() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value = 'Economy']"))); // Change this ID based on actual dropdown ID
    //Select select = new Select(dropdown);
    //select.selectByVisibleText("Economy Parking");  // Select by visible text
    String selectedvalue1 = dropdown.getAttribute("value");
    if(selectedvalue1=="Economy") {
    	System.out.println("Value is set to economy");
    }
    else {
    	System.out.println("value is not set to economy,changing it");
    	dropdown.click();
    }
}
	
	@Test(priority=10)
	public void Economyparkinglot() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value = 'Economy']"))); // Change this ID based on actual dropdown ID
	    //Select select = new Select(dropdown);
	    //select.selectByVisibleText("Economy Parking");  // Select by visible text
		dropdown.click();
	    String selectedvalue1 = dropdown.getAttribute("value");
	    Assert.assertEquals(selectedvalue1, "Economy", "Economy not found");
	}
	@Test(priority =11)
	public void Economy24hour() {
		//String leavingDate = "01/12/2025";
		//String startingDate = "01/11/2025";
			checkdropdown();
			System.out.println("Already set to economy");
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
			Assert.assertTrue(costDisplayed.contains("9"),"wrong cost displaying");
		}
	@Test(priority =12)
	public void Economy6hour() {
		checkdropdown();
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("06");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("9"),"wrong cost displaying");
	}
	@Test(priority =13)
	public void Economy1hour30min() {
		checkdropdown();
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("01:30");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("4"),"wrong cost displaying");
	}
	@Test(priority =14)
	public void Economy4hour59min() {
		checkdropdown();
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("04:59");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("9"),"wrong cost displaying");
	}
	@Test(priority =15)
	public void EconomyTimeblank() {
		checkdropdown();
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		//driver.findElement(By.id("StartingTime")).sendKeys("");
		//driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		//driver.findElement(By.id("LeavingTime")).sendKeys("04:59");
		//driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("0"),"wrong cost displaying");
	}
	@Test(priority = 16)
	public void EconomyDateblank() {
		checkdropdown();
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'StartingDate']")));
		element.clear();
		//driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		//driver.findElement(By.id("StartingTime")).sendKeys("");
		//driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		//driver.findElement(By.id("LeavingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		//driver.findElement(By.id("LeavingTime")).sendKeys("04:59");
		//driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("0"),"wrong cost displaying");
	}
	@Test(priority=16)
	public void Economy20hour() {
		checkdropdown();
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/13/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("08");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='AM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("9"),"wrong cost displaying");
	}
	@Test(priority=17)
	public void Economy7day() {
		checkdropdown();
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("11/12/2025");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.id("StartingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'StartingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.id("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("11/19/2025");
		driver.findElement(By.id("LeavingTime")).clear();
		driver.findElement(By.id("LeavingTime")).sendKeys("12");
		driver.findElement(By.xpath("//input[@name = 'LeavingTimeAMPM' and @value='PM']")).click();
		driver.findElement(By.xpath("//input[@name = 'Submit']")).click();
		String costDisplayed = driver.findElement(By.cssSelector("b")).getText();
		Assert.assertTrue(costDisplayed.contains("54"),"wrong cost displaying");
	}
}
