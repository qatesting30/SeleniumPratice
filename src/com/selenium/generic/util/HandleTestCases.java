package com.selenium.generic.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class HandleTestCases implements IAnnotationTransformer
	{
	
	public static DataExcel testCaseSheet;
	public static DataExcel testDataSheet;
	public static String className;
	public static String testCaseName;
	
	    @SuppressWarnings("rawtypes")
		public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
				Method testMethod) {
	    	
	    	if(!ExcelUtility.getTestRunFlag(testCaseSheet, className, testCaseName, "Execution")){
	    		annotation.setEnabled(false);
	    	}
	    	}
	    }

