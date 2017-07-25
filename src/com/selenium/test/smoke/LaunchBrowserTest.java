package com.selenium.test.smoke;

import org.testng.annotations.Test;

import com.selenium.project.spec.GetDriver;

public class LaunchBrowserTest {
	
	@Test
	public static void launchBrowser()
	{
		GetDriver.getDriverInstance();
	}
	

}
