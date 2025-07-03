package com.shiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManagement  {

	private static String dbPassword = "pokemon2626";
	private static String dbUsername = "root";
	 private static Connection con = null;
	 private Statement stmt = null;
	//getters setters for abstraction
	
	 public static String getDbPassword() {
		return dbPassword;
	}

	 public static void setDbPassword(String dbPassword) {
		 DatabaseManagement.dbPassword = dbPassword;
	 }

	 public static String getDbUsername() {
		 return dbUsername;
	 }

	 public static void setDbUsername(String dbUsername) {
		 DatabaseManagement.dbUsername = dbUsername;
	 }

	
	
	public static synchronized  Connection getCon() throws ClassNotFoundException, SQLException {
		
		if(con == null ) {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldb2", getDbUsername(), getDbPassword());	
			System.out.println("Connection Started");

			}
		
		return con; // singleton design for initializing database
		
	
	}
	
	
	




	
	
	
}
