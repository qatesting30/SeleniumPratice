package com.selenium.test.smoke;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.selenium.project.spec.GetDriver;

public class LaunchBrowserTest {
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	@Test
	public static void launchBrowser()
	{
		GetDriver.getDriverInstance();
		log.debug("Browser launched");
	}
	

}
