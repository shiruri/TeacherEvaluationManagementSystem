package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAdmin {
	 // to make a clean main method decided to put it in its own class
	static Connection con = null;
	static Statement stmt = null;
	 static int last_inserted_id;
		static Hashpassword c= new Hashpassword();

	public static void addAdmin(Admin an) throws ClassNotFoundException, SQLException  {
		con = DatabaseManagement.getCon();
		stmt = con.createStatement();
		// how do you add more whats the syntax  Statement.RETURN_GENERATED_KEYS

	
		String addAdmin =  "  insert into admin (adminName,adminEmail,adminContactNumber)"
				+ "values (?,?,?);"; 
		
		String addPass = "insert into adminhash (adminId,adminPassword,adminSalt) values (?,?,?);";
		// preparedStatement
		PreparedStatement prep = con.prepareStatement(addAdmin, Statement.RETURN_GENERATED_KEYS);
		prep.setString(1, an.getAdminName());
		prep.setString(2, an.getAdminEmail());
		prep.setString(3, an.getAdminContactNumber());
		prep.executeUpdate();

		ResultSet rs=prep.getGeneratedKeys();
        if(rs.next())
        {
             last_inserted_id = rs.getInt(1);
        }
		byte[] currentsalt = c.getCurrentSalt();
		
		//prep.setString(5, an.getAdminPassword());
		PreparedStatement hashTable = con.prepareStatement(addPass);
		hashTable.setInt(1,last_inserted_id);
		hashTable.setString(2,an.getAdminPassword());
		hashTable.setBytes(3, currentsalt);
		hashTable.executeUpdate();
		System.out.println("Admin Added");

		// using this as guide for the getters
		//public Admin(String adminName, String adminId, String adminEmail, String adminPassword, String adminContactNumber) {

	}
}
