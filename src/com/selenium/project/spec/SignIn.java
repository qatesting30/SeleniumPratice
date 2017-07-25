package com.selenium.project.spec;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
	
	
	public static void autoSignIn(WebDriver driver)
	{
	   WebDriverWait wait = new WebDriverWait(driver, 30);
	   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Signin')]")))).click();
	}

}
