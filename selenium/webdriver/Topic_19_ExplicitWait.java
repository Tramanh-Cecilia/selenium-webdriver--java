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

public class Topic_19_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String computerName = "computer.jpg";
	String catName ="cats.jpg";
	String computerPath= projectPath+"\\pictures\\"+computerName;
	String catPath =projectPath+"\\pictures\\"+catName;

	
	


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
	public void TC_01_Jquery() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// đặt tên file và project path trước đã 
	
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(computerPath +"\n"+catPath);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody.files")));
		
		Assert.assertTrue(isElementdisplayed(driver.findElement(By.xpath("//p[text()='cats.jpg']"))));
		Assert.assertTrue(isElementdisplayed(driver.findElement(By.xpath("//p[text()='computer.jpg']"))));
		
		// click chọn nút start
		driver.findElement(By.xpath("//p[text()='cats.jpg']//parent::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
		driver.findElement(By.xpath("//p[text()='computer.jpg']//parent::td//following-sibling::td//button[@class='btn btn-primary start']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.col-lg-5.fileupload-progress div.progress-bar.progress-bar-success")));
		String catSRC = driver.findElement(By.xpath("//a[@title='cats.jpg']/img")).getAttribute("src");
		Assert.assertTrue(catSRC.contains(catName));
		
	}

	@Test
	public void TC_02_Gofile() {
		driver.get("https://gofile.io/welcome");
		driver.findElement(By.cssSelector("div.col-auto button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#filesUpload button")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(catPath);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(@id,'progressString')]")));
		WebElement alert= driver.findElement(By.cssSelector("div.border-success.text-white"));
		Assert.assertTrue(isElementdisplayed(alert));
		
		String homeTab = driver.getWindowHandle();
		driver.findElement(By.cssSelector("a#filesSuccessDownloadLink")).click();
		
		Set<String> tabList = driver.getWindowHandles();
		
		for (String tabid : tabList) {
			if (!tabid.equals(homeTab)) {
				driver.switchTo().window(tabid);
				
				
			}
			
		}
		
		driver.findElement(By.xpath("//span[text()='cats.jpg']"));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='cats.jpg']")));
		
		String catText=driver.findElement(By.xpath("//span[text()='cats.jpg']//parent::a//parent::div//following-sibling::div//img")).getAttribute("src");
		
		Assert.assertTrue(catText.contains(catName));
		
		
	}
	

	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public boolean isElementdisplayed (WebElement locator) {
		return locator.isDisplayed();
		
	}
	

	}
	
	
	
	
	
	
	
	
	
		
	
		
	


