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
import org.openqa.selenium.interactions.Actions;
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

public class Topic_21_Page_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
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
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,30);
		action = new Actions(driver);
		
		

		
	}

	@Test
	public void TC_01_Polling() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertFalse(driver.findElements(By.cssSelector("div.oxd-pie-chart")).isEmpty());
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//label[text()='Employee Name']//parent::div//following-sibling::div//input[@placeholder='Type for hints...']")).
		sendKeys("Peter Mac");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
	
	
	
	}
		
		
		
		
	

	@Test
	public void TC_02_NopCommerse() {
		driver.get("https://admin-demo.nopcommerce.com/");
		Assert.assertTrue(isPageLoadedSuccess());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button-1.login-button")));
		driver.findElement(By.cssSelector("button.button-1.login-button")).click();
		
		WebElement ajax =driver.findElement(By.cssSelector("div#ajaxBusy"));
		explicitWait.until(ExpectedConditions.invisibilityOf(ajax));
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		String pageName= driver.findElement(By.cssSelector("div.page-title")).getText();
		Assert.assertEquals(pageName, "Admin area demo");
		
		
		
	}
	
	@Test
	public void TC_03_TestIO() {
		driver.get("https://blog.testproject.io/");
		// hover chuột vào heading
		WebElement heading =driver.findElement(By.cssSelector("h1.main-heading.with-subheading"));
		action.moveToElement(heading).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		String keyword="Selenium";
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.main-heading")));
		Assert.assertTrue(isPageLoadedSuccess());
		List<WebElement> seleniumHeadings =driver.findElements(By.cssSelector("h3.post-title"));
		for (WebElement article : seleniumHeadings) {
			Assert.assertTrue(article.getText().contains(keyword));
			
			
		}
	
	
	}
	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public boolean waitforEjaxLoadingInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(null));
	}
	
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override		
	public Boolean apply(WebDriver driver) {
		return(Boolean)jsExecutor.executeScript("return(window.jQuery != null) && (jQuery.active === 0);");
		
	}
	};
	ExpectedCondition<Boolean>  jsLoad = new ExpectedCondition<Boolean>() {
		@Override	
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			
		}
		};
	return explicitWait.until(jQueryLoad) && explicitWait.until((jsLoad));
    }
	}
	
	
	
	
	
	
	
	
	
		
	
		
	


