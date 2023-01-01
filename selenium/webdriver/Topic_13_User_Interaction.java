package webdriver;

import static org.testng.Assert.assertEquals;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.util.thread.TryExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Topic_13_User_Interaction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

//		driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		
		
	}

	@Test
	public void TC_01_Hover_To_Element() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement tooltip1 = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(tooltip1).perform();
		WebElement content =driver.findElement(By.cssSelector("div.ui-tooltip-content"));
		Assert.assertTrue(content.isDisplayed());
	
	}

	@Test
	public void TC_02_Myntra() {
		driver.get("https://www.myntra.com/");
		sleepInSecond(2);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Home & Bath']")).click();
		
		WebElement homeBath= driver.findElement(By.xpath("//h1[text()='Kids Home Bath']"));
		Assert.assertTrue(homeBath.isDisplayed());
			
	}

	@Test
	public void TC_03_Fahasa() {
		driver.get("https://www.fahasa.com/");
		
				
	}

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(int i) {
		try {
			Thread.sleep(i*1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	}
	
	
	
	
	
	
		
	
		
	


