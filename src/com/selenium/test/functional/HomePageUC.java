package com.selenium.test.functional;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.selenium.generic.util.Switch;
import com.selenium.object.repo.HomePage;
import com.selenium.project.spec.GetDriver;
import com.selenium.project.spec.SignIn;

public class HomePageUC {
	
	public static WebDriver driver;
	public static HomePage hp;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	@BeforeClass
	public static void beforeHomepageClass(ITestContext context)
	{
		/*String className = context.getCurrentXmlTest().
	    System.out.println("ClassName is: "+className);*/
	}
	
	@BeforeTest
	public static void launchBrowser()
	{
		driver = GetDriver.getDriverInstance();
		hp = new HomePage(driver);
	}
	//@Test
	public static void testRun()
	{
		
	}
	
	
	@Test
	public static void HomePageTC_001() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		act.moveToElement(hp.widget()).perform();
		hp.widget().click();
		log.debug("Clicked on widget tab");
		hp.datePicker().click();
		log.debug("Clicked on date picker");
		Switch.switchWindowsAndtabs(driver, 1);
		Thread.sleep(3000);
		SignIn.autoSignIn(driver);
		
		
	}

}
