package com.selenium.generic.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
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

public class BaseClass implements IAnnotationTransformer {
	
	public static DataExcel testcaseexcel = null;
	public static DataExcel testdataexcel = null;
	
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
		
		
		if(!ExcelUtility.getTestRunFlag(testcaseexcel, className, testcaseName, "Execution", "Y"))
    	{
    	   annotation.setEnabled(false);
    	   ExcelUtility.writeStatus(testcaseexcel, className, testcaseName, "Status", "skipped");
        }
		
	}
	
	
	 @BeforeSuite
	    public static void setUp(ITestContext context) throws Exception
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
	        
	        
	        String className = context.getAllTestMethods()[0].getInstance().getClass().getName();
	    }
	       
	        @BeforeMethod
	        public static void getStatus(Method method )
	        {
	        	/*String className = method.getClass().getSimpleName();
	        	String testcaseName = method.getName();
	        	*/
	        	
	       // String className = context.getAllTestMethods()[0].getInstance().getClass().getName();
	       // Class<?> c = Class.forName(className);
	        
	      }
	     
	    @AfterMethod
	    public static void getResult(ITestResult result) throws Exception
	    {
	    	
	    	String testclassName = result.getTestClass().getRealClass().getSimpleName();
	    	
//	    	 Class<?> c = Class.forName(result.getTestClass().getRealClass().getName());
//		        
//		        Method[] methods = c.getDeclaredMethods();
//		        
//		        for(Method m : methods)
//		          {
//		        	  System.out.println("Declared methods are: "+m.getName());
//		        	  if(m.getName().equals("TC_002"))
//		        	  {
//		        		 // result.setStatus(ITestResult.SKIP);
//		        	  }
//		          }
	    	
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
	          //  test.fail(result.getThrowable());
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
