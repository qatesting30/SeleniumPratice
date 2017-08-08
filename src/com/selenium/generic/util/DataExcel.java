package com.selenium.generic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataExcel {
	
	public static  File src;
	public static Workbook wb;
	public static Sheet sh;
	
	public DataExcel(String fileName)
	{
		try{
		src = new File ("./DataArchive/"+fileName);
		FileInputStream fis = new FileInputStream(src);
		String extnsn = fileName.substring(fileName.indexOf("."));
		System.out.println("File name and extnsn is :"+fileName+" "+extnsn);
		if(extnsn.equals(".xlsx")||extnsn.equals(".xls")){
			wb = WorkbookFactory.create(fis);
			}
		sh = wb.getSheetAt(0);
		}
		catch(Exception e){
			System.out.println("Exception in getting file path");
		}
	}
	
	public   int retrievenoOfRows(String sName)
	{
		int sheetIndex = wb.getSheetIndex(sName);
		if(sheetIndex==-1)
			return 0;
		else{
			int totalRows = sh.getLastRowNum()+1;
			System.out.println("total no of rows: "+totalRows);
			return totalRows;
		}
	}
	
	public   int retrieveNoOfColoumns(String sName)
	{
		int sheetIndex = wb.getSheetIndex(sName);
		if(sheetIndex==-1){
			return 0;
		}
			else{
				int totalCols = sh.getRow(0).getLastCellNum();
				System.out.println("total no of columns: "+totalCols);
				return totalCols;
			}
		}
	
	public String retrieveTestData(String sName,int rNum,int cNum)
	{
		String data = null;
		int sheetIndex = wb.getSheetIndex(sName);
		//System.out.println("sheetIndes: "+sheetIndex);
		if(sheetIndex==-1){
			return null;
		}
		else{
			Row r = sh.getRow(rNum);
			Cell c = r.getCell(cNum);
			DataFormatter df = new DataFormatter();
			data = df.formatCellValue(c);
			return data;
		}
	}
	
	public boolean writeResult(String sName,String rName,String cName,String result )
	{
		try{
		int sheetIndex = wb.getSheetIndex(sName);
		//System.out.println("sheetIndex1: "+sheetIndex);
		if(sheetIndex==-1){
			return false;
		}
		
		int noOfRows = retrievenoOfRows(sName);
		int noOfColmns = retrieveNoOfColoumns(sName);
		int rowNum = -1;
		int colNum = -1;
		
		
		Row dataCol = sh.getRow(0);
		for(int i=0;i<noOfColmns;i++){
			if(dataCol.getCell(i).getStringCellValue().equals(cName.trim())){
			colNum = i;
			System.out.println("Column number is: "+colNum);
			break;
			}
		}
		if(colNum==-1)
			return false;
		for(int j=0;j<noOfRows;j++){
			Row dataRow = sh.getRow(j);
			if(dataRow.getCell(0).getStringCellValue().equals(rName.trim())){
				rowNum = j;
				System.out.println("Row number is: "+rowNum);
				break;
			}
		}
		if(rowNum==-1)
			return false;
		
		else{
		//System.out.println("Hii");
		Row r = sh.getRow(rowNum);
		Cell c = r.getCell(colNum);
		c.setCellValue(result);
		System.out.println("cell value: "+c.getStringCellValue());
		FileOutputStream fout = new FileOutputStream(src);
		wb.write(fout);
		fout.close();
		}
		
	}
		catch(Exception e)
		{
			System.out.println("Error in writting result: "+e.getMessage());
			return false;
		}
		return true;
	}
	
	public String getFlaq(String sName,String rName,String cName)
	{
		String data = null;
		List<String> sheetNames = null;
		try{
			
			sheetNames = new ArrayList<String>();
			for (int i=0; i<wb.getNumberOfSheets(); i++) {
			    sheetNames.add( wb.getSheetName(i) );
			}
		  System.out.println("extracted sheetName: "+sheetNames);
			
			
			
		int sheetIndex = wb.getSheetIndex(sName);
		System.out.println("sheetname1: "+sName);
		if(sheetIndex==-1){
			System.out.println("Invalid sheet Number: "+sheetIndex);
			//return null;
		}
		
		int noOfRows = retrievenoOfRows(sName);
		int noOfColmns = retrieveNoOfColoumns(sName);
		int rowNum = -1;
		int colNum = -1;
		
		
		Row dataCol = sh.getRow(0);
		for(int i=0;i<noOfColmns;i++){
			if(dataCol.getCell(i).getStringCellValue().equals(cName.trim())){
			colNum = i;
			System.out.println("Column number is: "+colNum);
			break;
			}
		}
		if(colNum==-1){
			System.out.println("Invalid Column Number");
			//return null;
		}
		for(int j=0;j<noOfRows;j++){
			Row dataRow = sh.getRow(j);
			if(dataRow.getCell(0).getStringCellValue().equals(rName.trim())){
				rowNum = j;
				System.out.println("Row number is: "+rowNum);
				break;
			}
		}
		if(rowNum==-1){
			System.out.println("Invalid Column Number");
			//return null;
		}
			
		
		else{
		//System.out.println("Hii");
		Row r = sh.getRow(rowNum);
		Cell c = r.getCell(colNum);
		DataFormatter df = new DataFormatter();
		data = df.formatCellValue(c);
		System.out.println("cell value: "+data);
		}
		
	}
		catch(Exception e)
		{
			System.out.println("Error in writting result: "+e.getMessage());
			return null;
		}
		return data;
	}
	public  ArrayList<String> extractExcelContentByColumnName(String sName,String columnName,String data)
	{
		System.out.println("hii");
		ArrayList<String> columndata = null;
		try{
			int sheetIndex = wb.getSheetIndex(sName);
			//System.out.println("sheetIndex1: "+sheetIndex);
			if(sheetIndex==-1){
				System.out.println("Invalid sheet");
				return null;
			}
			
			
			int noOfRows = retrievenoOfRows(sName);
			int noOfColmns = retrieveNoOfColoumns(sName);
			
			int columnIndex = -1;
			
			
			Row dataCol = sh.getRow(0);
			for(int i=0;i<noOfColmns;i++){
				if(dataCol.getCell(i).getStringCellValue().equals(columnName.trim())){
			    columnIndex = i;
				System.out.println("Column number is: "+columnIndex);
				break;
				}
			}
		   
         Iterator<Row> rowIterator = sh.iterator();
            columndata = new ArrayList<String>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(row.getRowNum() > 0){ //To filter column headings
                        if(cell.getColumnIndex() == columnIndex){// To match column index
                        	if(cell.getStringCellValue().equalsIgnoreCase(data)){
                        		row.getRowNum();
                        		System.out.println(" Skipped Row number is: "+row.getRowNum());
                        		columndata.add(row.getCell(0).getStringCellValue());
                        	}
                                   break;
                            }
                        }
                    }
                }
            
        System.out.println("values are: "+columndata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columndata;
       }
	
//	public static void main(String[] args) {
//	   dataExcel("testCases.xlsx");
//	  // retrievenoOfRows("LoginPage");
//	 //  retrieveNoOfColoumns("Loginpage");
//	   //writeResult("Credentials", "test", "PASSWORD", "PASS");
//	   
//	   extractExcelContentByColumnName("DatePickerUC", "Status");
//	}
}
