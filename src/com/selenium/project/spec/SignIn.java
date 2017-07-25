package com.selenium.project.spec;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {
	
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static void autoSignIn(WebDriver driver)
	{
	   WebDriverWait wait = new WebDriverWait(driver, 30);
	   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Signin')]")))).click();
	   log.debug("Clicked on signIn button");
	   List<WebElement>hidden = driver.findElements(By.cssSelector("div[style*='block']"));
	   System.out.println("hidden elements: "+hidden.size());
	   WebElement ele = hidden.get(1);
	   ele.findElement(By.name("username")).sendKeys("qatesting30@gmail.com");
	   log.debug("username Entered");
		ele.findElement(By.name("password")).sendKeys("Mobile@123");
		log.debug("password Entered");
		ele.findElement(By.className("button")).click();
		log.debug("Clicked on submit button");
	   
	   
	}

}
