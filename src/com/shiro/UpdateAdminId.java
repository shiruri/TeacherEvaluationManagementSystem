package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateAdminId implements UpdateStrategy {
	static Connection con = null;
	static Statement stmt = null;
	static GetAdminList ls = new GetAdminList();
	Admin an = new Admin();

	@Override
	public void Update(int id, String newData) throws SQLException {
		if(an.getCurrentId() == id) {
			an.setCurrentId(id);
		}
		try {
			con = DatabaseManagement.getCon();
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		
		stmt = con.createStatement();
		
		String sqlUpdateAdminName = "update admin set adminId = ? where adminId = ?";

		PreparedStatement prep = con.prepareStatement(sqlUpdateAdminName); 
		prep.setString(1, newData);
		prep.setInt(2, id);
		prep.executeUpdate();
		int rowsUpdated = prep.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("An existing user was updated successfully!");
		}

		
		System.out.println("Printing table after update..");
		try {
			ls.getAdminList();
		} catch (ClassNotFoundException | SQLException e) {

		}

		
	}

	

}
