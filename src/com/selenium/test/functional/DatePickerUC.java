package com.selenium.test.functional;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.selenium.generic.util.DataExcel;
import com.selenium.generic.util.ExcelMaster;
import com.selenium.generic.util.ExcelUtility;
import com.selenium.generic.util.Switch;
import com.selenium.object.repo.HomePage;
import com.selenium.project.spec.GetDriver;
import com.selenium.project.spec.SignIn;

public class DatePickerUC extends ExcelMaster{
	
	public static WebDriver driver;
	public static HomePage hp;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static DataExcel testCaseSheet;
	public static DataExcel testDataSheet;
	public static String className;
	public static String testCaseName;
	
	@BeforeMethod
	public static void beforeHomepage(Method method)
	{
		className = method.getDeclaringClass().getSimpleName();
	    System.out.println("ClassName is: "+className);
	    testCaseName = method.getName();
	    System.out.println("Name of the testcase is: "+testCaseName);
	    if(!ExcelUtility.getTestRunFlag(testCaseSheet, className, testCaseName, "Execution")){
			  throw new SkipException("Skipping the testcase");
		   }
	 }
	
	@BeforeTest
	public static void launchBrowser()
	{
		driver = GetDriver.getDriverInstance();
		hp = new HomePage(driver);
		init();
		testCaseSheet = testcaseexcel;
		testDataSheet = testdataexcel;
		
	}
	//@Test
	public static void trialsRun()
	{
		
	}
	@Test(priority=1)
	public static void TC_001()throws Exception
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    String actualTitle = driver.getTitle().trim();
	    System.out.println("ACtual title: "+actualTitle);
		String expectedTitle = "Welcome";
		System.out.println("Expected title: "+expectedTitle);
		assertEquals(expectedTitle,actualTitle.trim());
	}
	@Test(priority=2)
	public static void TC_002() throws Exception
	{
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		act.moveToElement(hp.widget()).perform();
		hp.widget().click();
	}
//	@Test(priority=3)
	public static void TC_003()throws Exception
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.debug("Clicked on widget tab");
		hp.datePicker().click();
		log.debug("Clicked on date picker");
	}
	//@Test(priority=4)
	public static void TC_004()throws Exception
	{
		Switch.switchWindowsAndtabs(driver, 1);
		Thread.sleep(3000);
		SignIn.autoSignIn(driver);
		log.debug("Auto sign completed");
		Thread.sleep(3000);
	}
//	@Test(priority=5)
	public static void TC_005()throws Exception
	{
		hp.widget().click();
		log.debug("Clicked on widget tab");
		Thread.sleep(3000);
		hp.datePicker().click();
		log.debug("Navigated to date picker page");
	}
	
	@AfterMethod
	public static void getStatus(ITestResult result)
	{
		String className = result.getTestClass().getRealClass().getSimpleName();
		String testCaseName = result.getMethod().getMethodName();
		System.out.println("ClasssName is: "+className);
		System.out.println("TestCasename is: "+testCaseName);
		
		if(result.getStatus()==ITestResult.SUCCESS){
			ExcelUtility.writeStatus(testCaseSheet, className, testCaseName, "Status", "PASS");
		}
			
			else if (result.getStatus()==ITestResult.FAILURE){
				ExcelUtility.writeStatus(testCaseSheet, className, testCaseName, "Status", "FAIL");
			}
			
		
	}
	

}
