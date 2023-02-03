package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Topic_16_Window {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	Alert alert;

	
	


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

		
	}

	@Test
	public void TC_01_AutomationFC() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String automationParentPage = driver.getWindowHandle();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		System.out.println("automationID = "+automationParentPage);
		// lấy ra ID của tab đang đứng 
		// lấy hết ID ra-> dùng vòng lặp duyệt qua --> khác vd ID parents thì switch qua
		// lấy ra tất cả ID window đang mở
		
		sleepInSecond(2);
		Set<String> allWindowIDs = driver.getWindowHandles();
		System.out.println("allIds = "+ allWindowIDs);
		sleepInSecond(2);
		
		for (String id : allWindowIDs) {
				if (!id.equals(automationParentPage)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
			}
		
		sleepInSecond(3);
		String currentPage = driver.getTitle();
		System.out.println("Title = "+currentPage);
//		Assert.assertEquals(currentPage, "Google");
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("selenium");
	
		}
//	
//		driver.quit();
//		
//		
//	}
//
//	@Test
//	public void TC_02_LivetechPanda() {
//		driver.get("http://live.techpanda.org/");
//		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
//		
//		driver.findElement(By.xpath("//a[@title='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//ul//li//a[@class='link-compare']")).click();
//		String sonyMsg= driver.findElement(By.cssSelector("li.success-msg")).getText();
//		Assert.assertEquals(sonyMsg, "The product Sony Xperia has been added to comparison list.");
//		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//ul//li//a[@class='link-compare']")).click();
//		String samsungMsg= driver.findElement(By.cssSelector("li.success-msg")).getText();
//		Assert.assertEquals(samsungMsg, "The product Samsung Galaxy has been added to comparison list.");
//		driver.findElement(By.xpath("//button[@title='Compare']")).click();
//		
//		
//		String techpandaID = driver.getWindowHandle();
//		switchToWindow(techpandaID);
//		
//		sleepInSecond(2);
//		String tabTitle= driver.getTitle();
//		Assert.assertEquals("Products Comparison List - Magento Commerce", tabTitle);
//		String tabID = driver.getWindowHandle();
//		driver.close();
//		switchToWindow(tabID);
//		sleepInSecond(2);
//		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
//		driver.switchTo().alert();
//		alert.accept();
//		sleepInSecond(2);
//		String clearallMsg= driver.findElement(By.cssSelector("li.success-msg")).getText();
//		Assert.assertEquals(clearallMsg, "The comparison list was cleared.");
//		
	}
	

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
		Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public void switchToWindow (String otherID) {
		Set<String> allIDs= driver.getWindowHandles();
		
		for (String idloop : allIDs) {
			if (!idloop.equals(allIDs)) {
				driver.switchTo().window(idloop);
				
			}
		}
		}
	
	//dùng cho nhiều window và nhiều tab 
	public void switchToWindowPageTitle (String expectedpageTitle) {
		Set<String> allIDs= driver.getWindowHandles();
		for (String idloop : allIDs) {
			//switch to từng ID trước để lấy ra title
			driver.switchTo().window(idloop);
			String actualPageTitle= driver.getTitle();
			if (actualPageTitle.equals(expectedpageTitle)) {
				break;
				
			}
		}
	}
		
	}

	
	
	
	
	
	
	
	
	
	
		
	
		
	


