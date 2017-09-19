package com.selenium.generic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector {
	
	
   private static String databaseURLQA= "jdbc:sqlserver://192.168.6.113\\HEALTHECDEMO;";
   private static String databaseURLSTAGE= "jdbc:sqlserver://192.168.4.136\\HECSTG;";
   private static String dbusername = "guruprasadshivashankar";
   private static String dbpassword = "Lenovo@1234";
   
   public static String executeSQLQuery(String testEnv,String DBName,String sqlQuery) {
       String connectionUrl="";
       Connection connection;
       String resultValue = "";
       ResultSet rs;
       
       if(testEnv.equalsIgnoreCase("QA")){
           connectionUrl = databaseURLQA;
           dbusername = "guruprasadshivashankar";
           dbpassword = "Lenovo@1234";
       }
       //To connect with Stage Database
       else if(testEnv.equalsIgnoreCase("STAGE")) {
           connectionUrl = databaseURLSTAGE;
           dbusername = "guruprasadshivashankar";
           dbpassword = "Lenovo@1234";
       }
       
       try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       }catch(ClassNotFoundException e) {
           e.printStackTrace();
       }
       
       try {
           connection = DriverManager.getConnection(connectionUrl+"databaseName="+DBName,dbusername,dbpassword);
           
           System.out.println(connectionUrl+"databaseName="+DBName);
           if(connection!=null) {
               System.out.println("Connected to " +DBName+" database in "+testEnv+" Environment");
               System.out.println();
           }else {
               System.out.println("Database connection failed to "+testEnv+" Environment");
           }
           Statement stmt = connection.createStatement();
           rs=stmt.executeQuery(sqlQuery);
           
           ResultSetMetaData metadata = rs.getMetaData();
           int columnCount = metadata.getColumnCount();
           System.out.println("Column size: "+columnCount);
           
           for (int i=1; i<=columnCount; i++) 
           {
        	   String columnName = metadata.getColumnName(i);
               System.out.print(" "+columnName+" == ");
               try {
                   while(rs.next()){
                	   for(int j=1;j<=columnCount;j++)
                		   {
                       resultValue = rs.getString(j).toString()+", ";
                       System.out.print(" "+resultValue);
                       
                   }
                	  // System.out.println();
                   }
               } catch (SQLException e) {
                   e.getMessage();
               }
               catch (NullPointerException err) {
                   System.out.println("No Records obtained for this specific query");
                   err.getMessage();
               }
               
               System.out.println();
           }

          /* try {
               while(rs.next()){
                   resultValue = rs.getString(3).toString();
               }
           } catch (SQLException e) {
               e.getMessage();
           }
           catch (NullPointerException err) {
               System.out.println("No Records obtained for this specific query");
               err.getMessage();
           }*/
           connection.close();

       }catch(SQLException sqlEx) {
           System.out.println( "SQL Exception11:" +sqlEx.getMessage());
       }
       System.out.println(resultValue);
       return resultValue;
   }
   
   public static void main(String[] args) {
	String query = executeSQLQuery("QA","PROVIDERADMIN", "select * from dbo.users");
	//System.out.println(query);
	
}
}



