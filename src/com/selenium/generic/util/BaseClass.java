package com.selenium.generic.util;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	
	public static DataExcel testcaseexcel = null;
	public static DataExcel testdataexcel = null;
	
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
	
	public static void init()
	{
		//testcaseexcel = new DataExcel("TestCases.xlsx");
		//testdataexcel = new DataExcel("TestData.xlsx");
	}
	
	 @BeforeSuite
	    public static void setUp()
	    {
	        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Extentreport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	         
	        extent.setSystemInfo("OS", "Windows");
	        extent.setSystemInfo("Host Name", "Akash");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("User Name", "akash.mahato");
	         
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
	        htmlReporter.config().setReportName("ExtentReport");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setTheme(Theme.DARK);
	    }
	     
	    @AfterMethod
	    public static void getResult(ITestResult result)
	    {
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
	            test.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	        }
	        else
	        {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	    }
	     
	    @AfterSuite
	    public static void tearDown()
	    {
	        extent.flush();
	    }
	
	
	
	

}
