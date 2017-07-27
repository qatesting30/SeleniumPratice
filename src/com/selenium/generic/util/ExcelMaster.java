package com.selenium.generic.util;

public class ExcelMaster {
	
	public static DataExcel testcaseexcel = null;
	public static DataExcel testdataexcel = null;
	
	public static void init()
	{
		testcaseexcel = new DataExcel("TestCases.xlsx");
		testdataexcel = new DataExcel("TestData.xlsx");
	}
	
	
	
	
	
	

}
