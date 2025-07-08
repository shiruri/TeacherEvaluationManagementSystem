
package com.shiro;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateAdminPassword implements UpdateStrategy {
	static Connection con = null;
	static Statement stmt = null;
	static GetAdminList ls = new GetAdminList();
	static Hashpassword p = new Hashpassword();
	static EmailVerification em = new EmailVerification();
	@Override
	public void Update(int id, String newData) throws SQLException, NoSuchAlgorithmException {
		// implement a two factor authentication before up
		try {
			con = DatabaseManagement.getCon();
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
		String pass = "SELECT adminEmail from admin where adminId = ?;";
		PreparedStatement statement = con.prepareStatement(pass);   
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		String RetrievedEmail = "";
		while(res.next()) {
			 RetrievedEmail = res.getString("adminEmail");
		}		
		
		int flag = 0;
		try {
			flag = em.EmailVerification(RetrievedEmail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag == 1) {
			
		
			
			stmt = con.createStatement();
			
			String sqlUpdateAdminName = "update adminhash set adminPassword = ? where adminId = ?";
			String addPass = "update adminhash set adminPassword = ?, adminSalt = ? where adminId = ?;";
			
			byte[] Salt = p.getSalt();
			
			String newPassword = p.getSecurePassword(newData, Salt);
			
			PreparedStatement prep = con.prepareStatement(addPass); 
			

			prep.setString(1, newPassword);
			prep.setBytes(2, Salt);
			prep.setInt(3, id);
			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			/*
			 * System.out.println("Printing table after update.."); try { ls.getAdminList();
			 * } catch (ClassNotFoundException | SQLException e) {
			 * 
			 * }
			 */
			
		}
		else {
			System.out.println("Failed to Change Password");
		}
		}
		
	


	

}
