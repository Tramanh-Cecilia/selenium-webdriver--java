package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Topic_20_Fluent_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
	

	
	


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
	public void TC_01_startButton() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#start button")))).click();
		
		
		
		// Khởi tạo.
		FluentWait<WebDriver> fluentDriver= new FluentWait<WebDriver>(driver);
		//set timeout
		fluentDriver.withTimeout(Duration.ofSeconds(15))
		//set số lần lặp lại
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		//apply diêu kiện (wait cho đến khi cái nút đó hiển thị ra để find element
		String textButton =fluentDriver.until(new Function<WebDriver, String>() {

			@Override
			public  String apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div#finish")).getText();
			}
		});
		
		Assert.assertEquals(textButton,"Hello World!");
		
		
	}
		
		
		
		
	

	@Test
	public void TC_02_Gofile() {
		
		
		
	}
	

	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public boolean isElementdisplayed (WebElement locator) {
		return locator.isDisplayed();
		
	}
	
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
				
	public Boolean apply(WebDriver driver) {
		return(Boolean)jsExecutor.executeScript("return.jQuery != null && (jQuery.active === 0);");
		
	}
	};
	ExpectedCondition<Boolean>  jsLoad = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return(Boolean)jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			
		}
		};
	return explicitWait.until(jQueryLoad) && explicitWait.until((jsLoad));
    }
	}
	
	
	
	
	
	
	
	
	
		
	
		
	


