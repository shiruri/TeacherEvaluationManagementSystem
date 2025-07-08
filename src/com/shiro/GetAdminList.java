package com.shiro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GetAdminList {
	static Connection con = null;
	static Statement stmt = null;
	static Scanner scan = new Scanner(System.in);

	public static void getAdminList()   throws ClassNotFoundException, SQLException {
		con = DatabaseManagement.getCon();
		stmt = con.createStatement();

		String sql = "SELECT * FROM admin";
		ResultSet rs = stmt.executeQuery(sql);
		// print this on top so its not going to be printed several times
		System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("                                  Admin Database                                                                   %n");
		System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("| %-15s | %-15s | %15s | %-20s | %n", "adminId", "AdminName", "adminEmail","adminContactNumber");
		while (rs.next()) {
			int adminId =  rs.getInt("adminId");
			String adminName = rs.getString("adminName");
			String adminEmail = rs.getString("adminEmail");
			String adminContactNumber = rs.getString("adminContactNumber");
		System.out.printf("| %-15s | %-15s | %15s | %-20s | %n", adminId,adminName,adminEmail,adminContactNumber);
			// https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
			// documentation for java printf for a table

			// same goes for this one
		}				
	  System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
		return;

}
}
