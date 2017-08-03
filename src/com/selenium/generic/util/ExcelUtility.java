package com.selenium.generic.util;

public class ExcelUtility {
	
	public static boolean writeStatus(DataExcel dx,String sheetName,String rowName,String colName,String status){
		  return dx.writeResult(sheetName, rowName, colName, status);
	}
	public static String getTestData(DataExcel dx,String SheetName,int rowNumber,int colNumber){
		  return dx.retrieveTestData(SheetName, rowNumber, colNumber);
	}
	public static boolean getTestRunFlag(DataExcel dx,String sheetName,String rowName,String colName){
		
		boolean flag = false;
		if(dx.getFlaq(sheetName,rowName,colName).equalsIgnoreCase("Y")){
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
	}
	
	//public static Arraylist<String>getExecutionValue()
	

}
