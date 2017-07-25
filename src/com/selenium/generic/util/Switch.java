package com.selenium.generic.util;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Switch {
	
	
	public static void switchWindowsAndtabs(WebDriver driver,int num)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ArrayList<String>tabs =  new ArrayList<String> (driver.getWindowHandles());
		//System.out.println(tabs.size());
		driver.switchTo().window(tabs.get(num));
	}

}
