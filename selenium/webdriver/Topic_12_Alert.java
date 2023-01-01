package webdriver;

import static org.testng.Assert.assertEquals;

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

public class Topic_12_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
	public void TC_01_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals("I am a JS Alert", alert.getText());
		
		alert.accept();
		String result = driver.findElement(By.cssSelector("p#result")).getText();
		Assert.assertEquals(result,"You clicked an alert successfully");
		
	
	}

	@Test
	public void TC_02_Cancel() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals("I am a JS Confirm", alert.getText());
		alert.dismiss();
		String result2 = driver.findElement(By.cssSelector("p#result")).getText();
		Assert.assertEquals(result2,"You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals("I am a JS prompt", alert.getText());
		alert.sendKeys("TramAnh");
		alert.accept();
		String result3 = driver.findElement(By.cssSelector("p#result")).getText();
		Assert.assertEquals(result3,"You entered: TramAnh");
		
		
	}


	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	}
	
	
	
	
	
	
		
	
		
	


