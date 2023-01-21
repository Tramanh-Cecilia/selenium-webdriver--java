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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Topic_18_Uploadfile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	
	


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
		explicitWait = new WebDriverWait(driver, 15);
		

		
	}

	@Test
	public void TC_01_telerik() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.calendarContainer")));
		WebElement ajaxAlert =driver.findElement(By.cssSelector("div.RadAjaxPanel span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertTrue(isElementdisplayed(ajaxAlert));
		driver.findElement(By.xpath("//div[@class='rcMain']//a[text()='20']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*=ContentPlaceholder1_RadCalendar1] div.raDiv")));
		String selectedDate= driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText();
		Assert.assertTrue(selectedDate.contains("20"));
		
		
		
	}

	@Test
	public void TC_02_Gofile() {
		driver.get("https://gofile.io/welcome");
		
		
	}
	

	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public boolean isElementdisplayed (WebElement locator) {
		return locator.isDisplayed();
		
	}
	

	}
	
	
	
	
	
	
	
	
	
		
	
		
	


