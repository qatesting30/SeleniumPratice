package com.selenium.generic.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.ITestAnnotation;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.gson.LongSerializationPolicy;

public class BaseClass implements IAnnotationTransformer{
	
	public static DataExcel testcaseexcel = null;
	public static DataExcel testdataexcel = null;
	public static String className = null;
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Object obj;
	
	public static void initTestCaseData()
	{
		
		testcaseexcel = new DataExcel("TestCases.xlsx");
		}
	
	public static void initTestData()
	{
		testdataexcel = new DataExcel("TestData.xlsx");
	}
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,Method testMethod) 
	{
		initTestCaseData();
		String className = testMethod.getDeclaringClass().getSimpleName();
		System.out.println("1: "+className);
		String testcaseName = testMethod.getName();
		System.out.println("2: "+testcaseName);
		
		
		if(ExcelUtility.getTestRunFlag(testcaseexcel, className, testcaseName, "Execution", "N"))
    	{
    	   annotation.setEnabled(false);
    	   ExcelUtility.writeStatus(testcaseexcel, className, testcaseName, "Status", "skipped");
        }
		
	}
	
	
	 @BeforeSuite
	    public static void setUp(ITestContext context) throws Exception
	    {
		 
		    //System.out.println("beforeSuite Class");
	        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/checkExtentReport.html");
		    //htmlReporter = new ExtentHtmlReporter("./test-output/newExtentreport.html");
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
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        
	        
	       className = context.getAllTestMethods()[0].getInstance().getClass().getSimpleName();
	    }
	       
	        @BeforeMethod
	        public static void getStatus(Method method )
	        {
	        	String testcaseName = method.getName();
	        	test = extent.createTest(testcaseName);
	        	/*String className = method.getClass().getSimpleName();
	        	String testcaseName = method.getName();
	        	*/
	        	
	       // String className = context.getAllTestMethods()[0].getInstance().getClass().getName();
	      }
	     
	    @AfterMethod
	    public static void getResult(ITestResult result) throws Exception
	    {
	    	
	    	String testclassName = result.getTestClass().getRealClass().getSimpleName();
	    	String testCaseName = result.getName();
	    	System.out.println("Dmethod names: "+testCaseName);
	    	/* Class<?> c = Class.forName(result.getTestClass().getRealClass().getName());
	        
	        Method[] methods = c.getDeclaredMethods();
	        
	        ArrayList<String>skippingTestCaseName = ExcelUtility.matchtestCaseName(testcaseexcel, testclassName, "Status", "skipped");
	        System.out.println("Skipped TC_Name: "+skippingTestCaseName);
	        for(Method m : methods)		          {
	        	  System.out.println("Declared methods are: "+m.getName());
		        	  if(skippingTestCaseName.contains(m.getName())) {
		        		  System.out.println("values matched are: "+m.getName());
	        		  result.setStatus(ITestResult.SKIP);
		        	  }
	        	  }*/
	          
	    	if(result.getStatus() == ITestResult.FAILURE)
	        {
	        	ExcelUtility.writeStatus(testcaseexcel, testclassName, testCaseName, "Status", "FAIL");
	          
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	        	ExcelUtility.writeStatus(testcaseexcel, testclassName, testCaseName, "Status", "PASS");
	        
	        }
	        else if (result.getStatus()==ITestResult.SKIP)
	        {
	        	ExcelUtility.writeStatus(testcaseexcel, testclassName, testCaseName, "Status", "SKIPPED");
	        }
	        
	       
	    	
	        
	    }
	    @AfterTest
	    public static void checkStatus()
	    {
	    	 
	    	ArrayList<String>testcases = ExcelUtility.matchtestCaseName(testcaseexcel, className, "Status", "ALL");
	    	System.out.println("testcase names are: "+testcases);
	    	
	    	
	   
	    for(String testcaseName: testcases)
	    {
	    	    System.out.println("Current testcase in after test method is: "+testcaseName);
	            if(ExcelUtility.getTestRunFlag(testcaseexcel, className, testcaseName, "Status", "PASS"))
		    	{
	            	System.out.println("Execution status of "+testcaseName+" is: PASS");
		    		test.log(Status.PASS, MarkupHelper.createLabel(testcaseName+" Test Case PASSED",ExtentColor.GREEN));
		    	}
		    	else if(ExcelUtility.getTestRunFlag(testcaseexcel, className, testcaseName, "Status", "FAIL"))
		    	{
		    		System.out.println("Execution status of "+testcaseName+" is: FAIL");
		    		test.log(Status.FAIL, MarkupHelper.createLabel(testcaseName+" Test Case FAILED",ExtentColor.RED));
		    	}
		    	    	
		    	else if(ExcelUtility.getTestRunFlag(testcaseexcel, className, testcaseName, "Status", "SKIPPED"))
		    	{
		    		System.out.println("Execution status of "+testcaseName+" is: SKIP");
		    		test.log(Status.SKIP, MarkupHelper.createLabel(testcaseName+" Test Case SKIPPED",ExtentColor.YELLOW));
		    	}
	    	
	    }
	    extent.flush();
	    }
}
	  
			
			
			
			
		

	
	
	
	
	

