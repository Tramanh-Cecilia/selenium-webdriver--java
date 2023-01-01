package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

public class Topic_10_Radio_Button {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand;
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
		rand = new Random();
		jsExecutor = (JavascriptExecutor) driver;
		
		
		
	
		
	}

	@Test
	public void TC_01_Angular() {
		driver.get("https://material.angular.io/components/radio/examples");
		By summer = By.xpath("//input[@value='Summer']");
				if (!driver.findElement(summer).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(summer));
					}
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement checked = driver.findElement(By.xpath("//label[text()='Checked']//parent::div//input"));
		WebElement Intermediate = driver.findElement(By.xpath("//label[text()='Indeterminate']//parent::div//input"));
		
		clickCheckBox (checked);
		clickCheckBox (Intermediate);
		Assert.assertTrue(isElementSelected(checked));
		Assert.assertTrue(isElementSelected(Intermediate));
		
		UnclickCheckBox(checked);
		UnclickCheckBox(Intermediate);
		Assert.assertFalse(isElementSelected(checked));
		Assert.assertFalse(isElementSelected(Intermediate));
	}

	@Test
	public void TC_02_Kendo_UI_CustomCheckBox() {
		
	}

	@Test
	public void TC_03_Honda() {
		
				
	}

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickCheckBox (WebElement locator) {
		if(!locator.isSelected());
		jsExecutor.executeScript("arguments[0].click()", locator);
	}
	
	public boolean isElementSelected (WebElement locator) {
		return locator.isSelected();
	}
	public void UnclickCheckBox (WebElement locator) {
		if(locator.isSelected());
		jsExecutor.executeScript("arguments[0].click()", locator);
	}
	}
	
	
	
	
	
	
		
	
		
	


