package com.selenium.generic.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelData {
	
	public File src;
	public Workbook wb;
	public Sheet sh;
	
	public ExcelData(String fileName)
	{
		try{
		src = new File ("./DataArchive/"+fileName);
		FileInputStream fis = new FileInputStream(src);
		String extnsn = fileName.substring(fileName.indexOf("."));
		if(extnsn.equals(".xls")||extnsn.equals(".xlsx"))
		{
			wb = WorkbookFactory.create(fis);
		}
		sh =wb.getSheetAt(0);
	}
		catch(Exception e)
		{
			System.out.println("Error in getting excel filePath: "+e.getMessage());
		}
    }
	
	
}
