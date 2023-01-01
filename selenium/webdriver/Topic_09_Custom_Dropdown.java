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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitwait;
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
		
		//khởi tạo wait, timeout khoảng 30s
		explicitwait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor) driver;
		
		
	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//1.click vao dropdown
		driver.findElement(By.cssSelector("span#number-button")).click();
		
		// 2 chờ cho tất cả item load ra thành công trong HTML/ k quan tâm hiển thị hay k trên giao diện
		// bắt 1 locator đại diện cho tất cả các thèng (locator đó phải chưa 19 thằng)
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
		
		//nếu item cần chọn hiển thị thì chọn lun
		// nếu item cần chọn k hiển thị thì scroll chuột xuống
		// duyệt qua từng item lấy ra text nếu = text mong mướn thì click vào
		
		// lưu trữ tất cả items
		List<WebElement> allItems01 = driver.findElements(By.cssSelector("ul#number-menu div"));
		// duyệt qua từng items dùng vòng lặp
		
		for (WebElement item : allItems01) {
			String itemLoopText = item.getText();
			if (itemLoopText.equals("7")) {
				item.click();
				} 
	
		}
		
		
	}

	@Test
	public void TC_02_Jquery_multiple_choices() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// dùng hàm để chọn
		selectedMultipleItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");		
		
	}

	@Test
	public void TC_03_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		scrollToElement("//div[text()='Chọn xe']");
		selectedMultipleItemInDropdown("//button[@class='btn dropdown-toggle']","//div[@class='dropdown-menu show']/a", "CITY L");
		
	}

	
	
	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	
	
	
	
	// hàm roll into view
	public void scrollToElement (String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(locator)));
	}
	// Viết hàm để chọn một cái item trong custom drop down list
	
	public void selectedMultipleItemInDropdown(String parentlocator, String childxpath, String expectedItemText) {
	
		// click vào cái dropdown cho nó xổ ra
		driver.findElement(By.xpath(parentlocator)).click();
		
		// chờ tới khi tất cả các giá trị trong dropdown dược load ra thành công
		explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childxpath)));
		
		// lấy một list hứng tất cả item
		List<WebElement> listItems = driver.findElements(By.xpath(childxpath));
		
		// tổng số lượng trong dropdown là bao nhiêu
		System.out.println("item size ="+ listItems.size());
		
		// duyệt qua từng item trong listItems 
		for (WebElement itemLoop: listItems) {
			String actualItemText= itemLoop.getText();
			// dùng if nếu text giống vs cái mình muốn chọn thì click roi thoát khỏi vòng lặp
			
			if (actualItemText.equals(expectedItemText)) {
				// cần scroll đến item chọn, nếu hiển thị roi thi k can scroll
				itemLoop.click();
				break;
				
			}
		}
		
	}
		
	}


