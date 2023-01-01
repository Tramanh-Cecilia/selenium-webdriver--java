package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	public void TC_01_Textbox_01() {
		driver.get("https://demo.guru99.com/v4/");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr463572");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("ujUhebA");
		
		driver.findElement(By.cssSelector("input[name='btnLogin']")).click();
		String textHeading =  driver.findElement(By.cssSelector("tr.heading3")).getText();
		Assert.assertEquals(textHeading, "Manger Id : mngr463572");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Anh");
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("07/12/1993");
		driver.findElement(By.xpath("//input[@name='addr']")).sendKeys("123 Address");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ho Chi Minh");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("California");
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0909009600");
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("tramanh+1@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Tramanh712!");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String customerID= driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("customerID ="+customerID);
		
		String customerName= driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
		Assert.assertEquals(customerName, "Anh");
		
		
		
		

	}

	@Test
	public void TC_02_ValidatePageTitle() {
		// Login Page title
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Facebook – log in or sign up");
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		// Login form displayed
		Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}
