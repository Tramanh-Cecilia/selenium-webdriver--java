package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
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

public class Topic_15_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;

	
	


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
	public void TC_01_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		WebElement fbIframe = driver.findElement(By.xpath("//iframe[contains(@src,'www.facebook.com')]"));
		Assert.assertTrue(isElementdisplayed(fbIframe));
		
		driver.switchTo().frame(fbIframe);
		String numberofLike = driver.findElement(By.xpath("//div[@class='lfloat']//div[@class='_1drq']")).getText();
		Assert.assertTrue(numberofLike.contains("165K"));
		
		driver.switchTo().defaultContent();
		sleepInSecond(1);
		WebElement chatIframe = driver.findElement(By.cssSelector("iframe#cs_chat_iframe"));
		sleepInSecond(1);
		driver.switchTo().frame(chatIframe);
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Tram Anh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("090670382");
		Select service = new Select(driver.findElement(By.cssSelector("select#serviceSelect")));
		service.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		
		
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java bootcampt");
		driver.findElement(By.cssSelector("input.submit")).click();
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
	
		List<WebElement> excelCourses = driver.findElements(By.cssSelector("ul#w0 li.col-xl-4"));
		
		Assert.assertEquals(excelCourses.size(),9);
		
		
	}

	@Test
	public void TC_02_Frame() {
	driver.get("https://netbanking.hdfcbank.com/netbanking/");
	
	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
	driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("42156");
	driver.findElement(By.cssSelector("a.login-btn")).click();
	sleepInSecond(3);
	boolean password = driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed();
	Assert.assertTrue(password);
	
	}
	

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isElementdisplayed (WebElement locator) {
		return locator.isDisplayed();
		
	}
	public void sleepInSecond (long timeInsecond) {
		try {
		Thread.sleep(timeInsecond*1000);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	}
	
	
	
	
	
	
	
	
	
		
	
		
	


