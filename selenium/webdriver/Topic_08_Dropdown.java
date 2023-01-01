package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
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
		
	}

	@Test
	public void TC_01_Dropdwon_Demo() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Tram");
		
		rand = new Random();
		
		
		// dùng select chọn một dropdown
		Select dob= new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		// chọn theo text
		dob.selectByVisibleText("1");
		//đếm số trong dropdown có bao nhiêu giá trị
		Assert.assertEquals(32, dob.getOptions().size());
		
		
		Select month= new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		month.selectByVisibleText("May");
		Assert.assertEquals(13, month.getOptions().size());
		
		
		Select year= new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		year.selectByVisibleText("1980");
		Assert.assertEquals(112, year.getOptions().size());
		
		driver.findElement(By.xpath("//div[@class='inputs']//input[@type='email']")).sendKeys("712tramanh+"+ rand.nextInt(9999)+ "@gmail.com");
		driver.findElement(By.xpath("//div[@class='inputs']//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//div[@class='inputs']//input[@id='ConfirmPassword']")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#register-button")).click();
		
		String result = driver.findElement(By.xpath("//div[@class='result']")).getText();
		Assert.assertEquals(result, "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Select dateAcc = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		String dateAccText = dateAcc.getFirstSelectedOption().getText();
		Assert.assertEquals(dateAccText,"1");

	}

	@Test
	public void TC_02_Rodetobuy() {
		driver.get("https://rode.com/en/support/where-to-buy");
		Select country = new Select(driver.findElement(By.cssSelector("select#country")));
		//verify cái dropdown k thể chọn multiple 
		Assert.assertFalse(country.isMultiple());
		
		country.selectByVisibleText("Vietnam");
		
		String chosenCountry =country.getFirstSelectedOption().getText();
		Assert.assertEquals(chosenCountry, "Vietnam");
		
		
		// lấy giá trị trả về bằng size
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		int numberDealers = driver.findElements(By.xpath("//div[@class='my-3']//div[@class='p-1']")).size();
		Assert.assertEquals(numberDealers, 40);
		
		
		
		
		
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public static void main (String [] args ) {
		Random rand = new Random();
		rand.nextInt(9999);
		
	}
	 public void sleepInSecond(Long timeInSecond) {
		 try {
			 Thread.sleep(timeInSecond*1000);
		 }
		 catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 
	 }

}
