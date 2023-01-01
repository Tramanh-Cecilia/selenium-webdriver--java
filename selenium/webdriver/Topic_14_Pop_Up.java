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

public class Topic_14_Pop_Up {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;
	Random rand;
	
	


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
		rand = new Random();

		
	}

	@Test
	public void TC_01_PopUp_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		WebElement popUpLogin =driver.findElement(By.cssSelector("div#modal-login-v1>div[class='modal-dialog']"));
		Assert.assertFalse(isPopUpdisplayed(popUpLogin));
		
		driver.findElement(By.cssSelector("div#button-login-dialog")).click();
		sleepInSecond(1);
		Assert.assertTrue(isPopUpdisplayed(popUpLogin));
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("Automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("1234567");
		driver.findElement(By.cssSelector("button.btn-v1.btn-login-v1.buttonLoading")).click();
		sleepInSecond(1);
		String errorMessage = driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText();
		Assert.assertEquals("Tài khoản không tồn tại!", errorMessage);
	
	
	}

	@Test
	public void TC_02_Cancel() {
	driver.get("https://tiki.vn/");
	driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
	WebElement tikiLogin = driver.findElement(By.cssSelector("div.styles__Left-sc-2hr4xa-1.iwneWf"));
	Assert.assertTrue(isPopUpdisplayed(tikiLogin));
	
	driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
	
	driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	String errormessage= driver.findElement(By.xpath("//span[contains(text(),'Email không được để trống')]")).getText();
	Assert.assertEquals(errormessage,"Email không được để trống");
	
	
	
	
	}

	@Test
	public void TC_03_Random_PopUp_In_Dom() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(4);
		
		if (driver.findElement(By.xpath("//div[@id='lepopup-popup-122']/div")).isDisplayed()) {
			sleepInSecond(1);
			driver.findElement(By.xpath("//div[@class='lepopup-input']//input[@type='email']")).sendKeys("Tramanh+"+rand.nextInt(9999)+"@gmail.com");
			driver.findElement(By.xpath("//a[@class='lepopup-button lepopup-button-zoom-out ']")).click();
						
		}
		
		driver.findElement(By.xpath("//input[@name='s']")).sendKeys("Selenium");
		String searchResult = driver.findElement(By.xpath("//a[contains(text(),'JavaScript And Selenium')]")).getText();
		Assert.assertTrue(searchResult.contains("JavaScript And Selenium"));
	
		
	}
	@Test
	public void TC_04_Random_PopUp_Not_In_Dom() {
		driver.get("https://dehieu.vn/");
		List<WebElement> popUpNotInDOme = driver.findElements(By.cssSelector("div.popup-content"));
	 if(popUpNotInDOme.size()>0 && popUpNotInDOme.get(0).isDisplayed()) {
		 driver.findElement(By.cssSelector("button#close-popup")).click();
	 }
	 
	 
	
	}


	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	public boolean isPopUpdisplayed (WebElement locator) {
		return locator.isDisplayed();
		
	}
	public void sleepInSecond (int i) {
		try {
			Thread.sleep(i*1000);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public static void main (String[] args) {
		Random rand = new Random();
		rand.nextInt(9999);
	}
	
	}
	
	
	
	
	
	
		
	
		
	


