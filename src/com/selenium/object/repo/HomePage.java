package com.selenium.object.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}

	public WebElement widget()
	{
	   return driver.findElement(By.xpath("//a[contains(text(),'Widget')]"));
	}
	public WebElement datePicker()
	{
		return driver.findElement(By.xpath("//a[contains(text(),'Datepicker')]"));
	}
}
