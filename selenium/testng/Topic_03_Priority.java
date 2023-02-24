package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_03_Priority {
	
	
  @Test (priority= 1)
  public void Enduser_Create_New_Employee() {
	  System.out.println("Testcase 01");
  }
  @Test (priority= 2)
  public void Enduser_View_New_Employee() {
	  System.out.println("Testcase 02");
  }
  @Test (priority= 3, description = "bổ sung mô tả")
  public void Enduser_Edit_New_Employee() {
	  System.out.println("Testcase 03");
  }
  @Test(enabled= false) // skip test case
  public void Enduser_Move_New_Employee() {
	  System.out.println("Testcase 04");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("After method");
  }



 
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("After Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("After Test");
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
