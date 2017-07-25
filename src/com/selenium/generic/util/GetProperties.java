package com.selenium.generic.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GetProperties {
	
	public static String getDataFromProperties(String fileName,String key)
	{
		String data = null;
		try{
		File src = new File("./Config/"+fileName+".properties");
		FileInputStream fis = new FileInputStream(src);
		Properties pro = new Properties();
		pro.load(fis);
		data = pro.getProperty(key);
	}
		catch(Exception e){
			System.out.println("Exception in getting data from propertyFile: "+e.getMessage());
		}
		return data;
  }
}
