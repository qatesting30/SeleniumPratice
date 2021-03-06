package com.selenium.test.functional;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.generic.util.BaseClass;
import com.selenium.generic.util.DataExcel;
import com.selenium.generic.util.ExcelUtility;
import com.selenium.generic.util.HandleTestCases;
import com.selenium.generic.util.Switch;
import com.selenium.object.repo.HomePage;
import com.selenium.project.spec.BaseTest;
import com.selenium.project.spec.GetDriver;
import com.selenium.project.spec.SignIn;


public class DatePickerUC extends BaseClass {
	
	public static WebDriver driver;
	public static HomePage hp;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static DataExcel testCaseSheet;
	public static DataExcel testDataSheet;
	public static String className;
	public static String testCaseName;
	
	
	@BeforeTest()
	public static void launchBrowser(ITestContext context) 
	{
		
		    driver = GetDriver.getDriverInstance();
			hp = new HomePage(driver);
			//init();
			/*testCaseSheet = testcaseexcel;
			testDataSheet = testdataexcel;*/
			
			
		
	  }
	
	    
	//@Test
	public static void trialsRun()
	{
		
	}
	
	@Test(priority=1,enabled=true)
	public static void TC_001()throws Exception
	{
		//test = extent.createTest("functionality2Test1");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    String actualTitle = driver.getTitle().trim();
	    System.out.println("ACtual title: "+actualTitle);
		String expectedTitle = "Welcome";
		System.out.println("Expected title: "+expectedTitle);
		assertEquals(expectedTitle,actualTitle.trim());
	}
	@Test(priority=2,enabled=true)
	public static void TC_002() throws Exception
	{
		//test = extent.createTest("functionality2Test2");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		act.moveToElement(hp.widget()).perform();
		hp.widget().click();
	}
	@Test(priority=3,enabled=true)
	public static void TC_003()throws Exception
	{
		//test = extent.createTest("functionality2Test3");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.debug("Clicked on widget tab");
		hp.datePicker().click();
		log.debug("Clicked on date picker");
	}
	@Test(priority=4,enabled=true)
	public static void TC_004()throws Exception
	{
		
		//test = extent.createTest("functionality2Test3");
		Switch.switchWindowsAndtabs(driver, 1);
		Thread.sleep(3000);
		SignIn.autoSignIn(driver);
		log.debug("Auto sign completed");
		Thread.sleep(3000);
		
		
	}
	@Test(priority=5,enabled=true)
	public static void TC_005()throws Exception
	{
		hp.widget().click();
		log.debug("Clicked on widget tab");
		Thread.sleep(3000);
		hp.datePicker().click();
		log.debug("Navigated to date picker page");
	}
	
	/*@AfterTest
	public static WebDriver tearFown()
	{
		//extent.flush();
		return driver;
	}*/
		
	}
