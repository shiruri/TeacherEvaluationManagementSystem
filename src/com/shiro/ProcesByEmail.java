package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ProcesByEmail implements ProcessLoginBy{
	static Connection con = null;
	static Statement stmt =null;	
	static Hashpassword c= new Hashpassword();


	@Override
	public boolean process(String input,String password,Admin an) throws SQLException, ClassNotFoundException {
		if(input.isBlank() || password.isBlank()) {
			System.out.println("Please enter valid input");
			return false;
		}
		String RetrievedEmail = "";
		String RetrievedPassword = "";

		try {
			con = DatabaseManagement.getCon();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exeception occured");
		}
		stmt = con.createStatement();

		byte[] Salt = null;
		PreparedStatement getSalt = con.prepareStatement("  SELECT adminSalt FROM admin LEFT JOIN adminhash  ON admin.adminId = adminhash.adminId WHERE adminEmail  = ?;");   
		getSalt.setString(1,input);
		ResultSet rs = getSalt.executeQuery();
		while(rs.next()) {
			Salt = rs.getBytes("adminSalt");

		}
		String hashedPassword = "";
		
		try {
			hashedPassword = c.getSecurePassword(password,Salt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error Loggin in");
		}


		PreparedStatement statement = con.prepareStatement("SELECT adminEmail,adminPassword FROM admin LEFT JOIN adminhash  ON admin.adminId = adminhash.adminId WHERE adminEmail  = ?;");   
		statement.setString(1,input);

		ResultSet res = statement.executeQuery();

		while(res.next()) {
			RetrievedEmail = res.getString("adminEmail");
			RetrievedPassword = res.getString("adminPassword");



		}
		


		if(RetrievedEmail.equals(input)) {
			if(RetrievedPassword.equals(hashedPassword)) {
				setCurrentAdmin(RetrievedEmail,an);
				return true;
			}

		}
		else {
			return false;
		}
		return false;
	}

	public static void setCurrentAdmin(String RetrievedEmail,Admin an) throws ClassNotFoundException, SQLException {
		String currentAdmin = "";
		int currentAdminId = 0;
		con = DatabaseManagement.getCon();
		stmt = con.createStatement();
		PreparedStatement statement = con.prepareStatement("SELECT adminName,adminId from admin where adminEmail = ?");   
		statement.setString(1,RetrievedEmail);
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			currentAdmin = rs.getString("adminName");
			currentAdminId = rs.getInt("adminId");

		}
		an.setAdminName(currentAdmin);
		an.setCurrentId(currentAdminId);

	}
}
