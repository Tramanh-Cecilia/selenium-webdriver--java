package tiki.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;





public class Shopper_02_Manage_Cart {
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriver driver;
	
	@BeforeClass(alwaysRun= true)
	public void initBrower() {
		
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
	}
	
	
	 @Test(groups={"admin","cart"})
	  public void TC_01_Create_Cart() {
	  }
	  
	  @Test(groups={"admin","cart"})
	  public void TC_02_View_Cart() {
	  }
	  @Test(groups={"admin","cart"})
	  public void TC_03_Update_Cart() {
	  }
	  
	  @Test(groups={"admin","cart"})
	  public void TC_04_Delete_Cart() {
	  }
}
