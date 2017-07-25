package com.selenium.test.functional;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.selenium.object.repo.HomePage;
import com.selenium.project.spec.GetDriver;

public class HomePageUC {
	
	public static WebDriver driver;
	public static HomePage hp;
	
	@BeforeTest
	public static void launchBrowser()
	{
		driver = GetDriver.getDriverInstance();
		hp = new HomePage(driver);
	}
	@Test
	public static void HomePageTC_001()
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		act.moveToElement(hp.widget()).perform();
		hp.widget().click();
		hp.datePicker().click();
	}

}
