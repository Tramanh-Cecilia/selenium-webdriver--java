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

public class Topic_11_Custom_Radio_Button {
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
		jsExecutor =(JavascriptExecutor) driver;
		
		
	
		
	}

	@Test
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//li//a[text()='Đăng nhập']")).click();
		WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
		
		//Kiểm tra xem nút có bị ẩn k
		Assert.assertFalse(loginButton.isEnabled());
		SleepInSecond(3);
		
		// lấy ra màu
		String buttonLoginColorOrigin = loginButton.getCssValue("background-color");
		
		//đổi mã màu từ rgba sang hexa
		String buttonLoginColorOriginHexa= Color.fromString(buttonLoginColorOrigin).asHex().toUpperCase();
		
		System.out.println("hexaColorOrigin = "+ buttonLoginColorOriginHexa);
		
		//verify background là màu đó
		Assert.assertEquals(buttonLoginColorOriginHexa, "#000000");
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("tramanh"+rand.nextInt(99999)+"@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("1234567");
		
		
		Assert.assertTrue(loginButton.isEnabled());
		SleepInSecond(3);
		String buttonLoginColor= loginButton.getCssValue("background-color");
		String buttonLoginColorHexa= Color.fromString(buttonLoginColor).asHex().toUpperCase();
		Assert.assertEquals(buttonLoginColorHexa, "#C92127");
		
		
	}

	@Test
	public void TC_02_Kendo_UI_CustomCheckBox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		SleepInSecond(3);
		scrollToElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
		
		selectCheckBox (By.xpath("//label[text()='Dual-zone air conditioning']"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']")).isSelected());
		
		unselectCheckBox (By.xpath("//label[text()='Dual-zone air conditioning']"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']")).isSelected());
	
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		selectRadioButton(By.xpath("//label[text()='2.0 Petrol, 147kW']"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']")).isSelected());
	
	}

	@Test
	public void TC_03_Honda() {
		
				
	}

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public static void main (String[] args) {
		Random rand = new Random();
		rand.nextInt(9999);
	}	
	
	 public void SleepInSecond(int i) {
		 try {
			 Thread.sleep(i*1000);
		 }
		 catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 
	 
	}
	 
	public void selectCheckBox (By locator) {
		WebElement element = driver.findElement(locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void unselectCheckBox (By locator) {
		WebElement element = driver.findElement(locator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void selectRadioButton(By locator) {
		WebElement radioButton = driver.findElement(locator);
		if(!radioButton.isEnabled()) {
			radioButton.click();
		}
	}
	
	//hàm để kiểm tra chọn chưa
	public boolean isElementSelected(String xpathlocator) {
		return driver.findElement(By.xpath(xpathlocator)).isSelected();
	}
	
	public void scrollToElement (By locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(locator));
		}
	
	
	
	// Cach đê chọn hết các checkbox
	public void multipleSelected(By locator) {
		// 
		List<WebElement> allcheckboxes = driver.findElements(locator);
		
		// dùng vòng lặp duyệt qua
		for (WebElement checkBoxInLoop : allcheckboxes) {
			if (!checkBoxInLoop.isSelected()) {
				checkBoxInLoop.click();
				SleepInSecond(1);
			}
			
		}
	}
	
	//Dùng vòng lặp để kiểm tra nó đã được chọn
	public void CheckMultipleSelected(By locator) {
		List<WebElement> allcheckboxes = driver.findElements(locator);
		for (WebElement checkBoxInLoop : allcheckboxes) {
			Assert.assertTrue(checkBoxInLoop.isSelected());
		}
	
	}
	}
	
	
	
	
	
	
		
	
		
	


