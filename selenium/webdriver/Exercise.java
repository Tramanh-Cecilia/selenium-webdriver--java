package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;
	Select select;
	Actions action;
	JavascriptExecutor jsExecutor;
	
	
	WebDriverWait explicitWait;
	


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
		
		explicitWait = new WebDriverWait(driver,30);
		action = new Actions(driver);
		
	

		
	}

	@Test(enabled= false)
	public void TC_01_Alert_PopUp() {

driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
		
		WebElement iframeResult = driver.findElement(By.cssSelector("iframe#iframeResult"));
		
		// Switch vào frame để click nút try it
		driver.switchTo().frame(iframeResult);
		
	
		
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		System.out.println("iframe switch successfully");
		
		alert = driver.switchTo().alert();
		//step 4
		alert.dismiss();
		System.out.println("alert dismiss successfully");
		//step 5
		
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		alert = driver.switchTo().alert();
		alert.sendKeys("TRAM ANH");
		alert.accept();
		String demoText = driver.findElement(By.cssSelector("p#demo")).getText();
		Assert.assertTrue(demoText.contains("TRAM ANH"));
	
	}

	@Test(enabled= false)
	public void TC_02_Auto_Suggestion() {
		
		driver.findElement(By.xpath("//a[text()= 'Login By Credentials']")).click();
		driver.findElement(By.cssSelector("input#username")).sendKeys("thaovo");
		driver.findElement(By.cssSelector("input#password")).sendKeys("kms");
		driver.findElement(By.cssSelector("input#btnSubmit")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Provide Feedback']")));
		driver.findElement(By.xpath("//a[text()='Provide Feedback']")).click();
		
		Select provideFB = new Select(driver.findElement(By.cssSelector("div select#Feedback_ProvideFBFor")));
		provideFB.selectByVisibleText("Other Colleague");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.ui-autocomplete-input.field.text")));
		driver.findElement(By.cssSelector("input.ui-autocomplete-input.field.text")).sendKeys("Phuong");
		
		List<WebElement> searchPhuong = driver.findElements(By.xpath("//li[@class='ui-menu-item']"));
		for (WebElement searchItems : searchPhuong) {
			String textItem = searchItems.getText();
			if (textItem.contains("Hoang Truong")) {
				searchItems.click();
				break;
				
			}
		}
		System.out.println("1");
//		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(searchPhuong));
//		SleepInSecond(3);
//		String selectedReceiver =driver.findElement(By.cssSelector("input.ui-autocomplete-input")).getText();
//		System.out.println(selectedReceiver);
//		Assert.assertTrue(selectedReceiver.contains("Hoang"));
		
		Select employee= new Select(driver.findElement(By.cssSelector("select#Feedback_Receiver")));
		String selectedReceiver = employee.getFirstSelectedOption().getText();
		System.out.println("selected"+ selectedReceiver);
		
		
	}

	@Test(enabled= false)
	public void TC_03_Checkbox() {
		
		driver.findElement(By.xpath("//a[text()= 'Login By Credentials']")).click();
		driver.findElement(By.cssSelector("input#username")).sendKeys("thaovo");
		driver.findElement(By.cssSelector("input#password")).sendKeys("kms");
		driver.findElement(By.cssSelector("input#btnSubmit")).click();
		List<WebElement> statusCheckbox = driver.findElements(By.xpath("//input[@class='FBStatus']"));
		
		//select the checkbox
		SelectMultipleCheckboxes(By.xpath("//input[@class='FBStatus']"));
		
		VerifyCheckBoxisSelected(statusCheckbox);
		System.out.print("Checkbox is selected successfully");
		SleepInSecond(3);
		
		DeSelectMultipleCheckboxes(By.xpath("//input[@class='FBStatus']"));
		
		//verify checkbox is deselected
		VerifyCheckBoxisdelected(statusCheckbox);
		
		
		//Print the number of checkboxes in the page
		List<WebElement> numberOfCHeckBoxes= driver.findElements(By.xpath("//input[@type='checkbox']"));
		int countnumberOfCHeckBoxes= numberOfCHeckBoxes.size();
		System.out.println("Number of Checkboxes = "+ countnumberOfCHeckBoxes);
		
		
		// lấy ra name của button
		for (WebElement singleButton : numberOfCHeckBoxes) {
			String buttonName= singleButton.getAttribute("value");
			System.out.println("Button name is = "+ buttonName);
			
		}
		
		// scroll to footer
		driver.get("http://192.168.59.48:8080");
		

		WebElement footer= driver.findElement(By.cssSelector("div#footer"));
		SleepInSecond(3);
		action.moveToElement(footer).perform();
		
		
		
	}
	@Test(enabled= false)
	public void TC_04_Dropdown() {
		
		driver.findElement(By.xpath("//a[text()= 'Login By Credentials']")).click();
		driver.findElement(By.cssSelector("input#username")).sendKeys("thaovo");
		driver.findElement(By.cssSelector("input#password")).sendKeys("kms");
		driver.findElement(By.cssSelector("input#btnSubmit")).click();
		driver.findElement(By.xpath("//a[text()='Export feedbacks']")).click();
		
		Select exportOption= new Select(driver.findElement(By.cssSelector("select#ExportFor")));
		exportOption.selectByIndex(0);
		String IndexText= exportOption.getFirstSelectedOption().getText();
		Assert.assertEquals(IndexText, "--Select--");
		exportOption.selectByValue("0");
		String valueText= exportOption.getFirstSelectedOption().getText();
		Assert.assertEquals( valueText, "Feedbacks on me");
		
		// select by visible text
		exportOption.selectByVisibleText("Feedbacks on my subordinates");
		String VisibleText= exportOption.getFirstSelectedOption().getText();
		Assert.assertEquals(VisibleText, "Feedbacks on my subordinates");
		
		// go to w3 school
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
		WebElement iframeResult =driver.findElement(By.id("iframeResult"));
		driver.switchTo().frame(iframeResult);
		Select chooseCar = new Select(driver.findElement(By.id("cars")));
		// print the number of option
		List<WebElement> optionslist =chooseCar.getOptions();
		System.out.println("number of options = "+ optionslist.size());
		
		for (WebElement optionItem : optionslist) {
			System.out.println(optionItem.getText());
		};
		
		chooseCar.selectByIndex(0);
		System.out.println(chooseCar.getFirstSelectedOption().getText());
		chooseCar.selectByValue("volvo");
		System.out.println("SelectByValue"+chooseCar.getFirstSelectedOption().getText());
		SleepInSecond(3);
		chooseCar.deselectByValue("volvo");
		chooseCar.selectByVisibleText("Audi");
		System.out.println("SelectByText = "+ chooseCar.getFirstSelectedOption().getText());
		
		String SelectedOption = chooseCar.getFirstSelectedOption().getText();
		System.out.println("Current selected Option = " + SelectedOption);
		chooseCar.deselectByVisibleText(SelectedOption);
		chooseCar.selectByIndex(1);
		System.out.println(chooseCar.getFirstSelectedOption().getText());
		Assert.assertFalse(SelectedOption.equals(chooseCar.getFirstSelectedOption().getText()) );
		
		
		
		//print the text of all options
		
		
	}
	
	@Test
	public void TC_05_Iframe() {
		driver.get("http://192.168.59.48:8080/Feedbacks/Create/");
		driver.findElement(By.xpath("//a[text()= 'Login By Credentials']")).click();
		driver.findElement(By.cssSelector("input#username")).sendKeys("thaovo");
		driver.findElement(By.cssSelector("input#password")).sendKeys("kms");
		driver.findElement(By.cssSelector("input#btnSubmit")).click();
		WebElement fbIframe= driver.findElement(By.id("mce_5_ifr"));
		driver.switchTo().frame(fbIframe);
		driver.findElement(By.id("tinymce")).sendKeys("TA feedback");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	 public void SleepInSecond(int i) {
		 try {
			 Thread.sleep(i*1000);
		 }
		 catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 
	 
	}
	 
	 
	 public void VerifyCheckBoxisSelected(List<WebElement> Locators) {
			 for (WebElement singleLocator : Locators) {
				Assert.assertTrue(singleLocator.isSelected());
			}
			
			
		}
	 public void VerifyCheckBoxisdelected(List<WebElement> Locators) {
		 for (WebElement singleLocator : Locators) {
			Assert.assertFalse(singleLocator.isSelected());
		}
		
		
	}
	 
	 
	public void SelectMultipleCheckboxes (By Locator) {
		List<WebElement> statusCheckbox = driver.findElements(Locator);	
		for (WebElement webElement : statusCheckbox) {
			if (!webElement.isSelected()) {
				webElement.click();
			}
			
		}
	
	}
	public void DeSelectMultipleCheckboxes (By Locator) {
		List<WebElement> statusCheckbox = driver.findElements(Locator);	
		for (WebElement webElement : statusCheckbox) {
			if (webElement.isSelected()) {
				webElement.click();
			}
			
		}
	
	}
	public void scrollToElement (String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(locator)));
		}
}
	
	
	
	
	
	
	
	
	
	
	
	
		
	
		
	


