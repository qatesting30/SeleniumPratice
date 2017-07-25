package com.selenium.project.spec;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.selenium.generic.util.GetProperties;
public class GetDriver {
	
	public static WebDriver driver;
	
	public static WebDriver getDriverInstance()
	{
		String browserName = GetProperties.getDataFromProperties("data", "browser");
		System.out.println(browserName);
		String URL = GetProperties.getDataFromProperties("data", "URL");
		
		if(browserName.equalsIgnoreCase("CHROME")){
			System.setProperty("webdriver.chrome.driver", "./DriverSource/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("FIREFOX")){
			System.setProperty("webdriver.gecko.driver", "./DriverSource/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("OPERA")){
			System.setProperty("webdriver.opera.driver", "./DriverSource/operadriver.exe");
			driver = new OperaDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1366, 768));
		driver.navigate().to(URL);
		return driver;
	}

}
