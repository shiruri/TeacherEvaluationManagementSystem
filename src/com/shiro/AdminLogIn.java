package com.shiro;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminLogIn {
	static Connection con = null;
	static Statement stmt = null;
	static Scanner scan = new Scanner(System.in);

	public static boolean AdminLogin(boolean logged,Admin an) throws ClassNotFoundException, SQLException {
		con = DatabaseManagement.getCon();
		stmt = con.createStatement();
		String input;
		String password;
		System.out.println("Enter Email Or Username");
		System.out.println("A Enter Email");
		System.out.println("B Enter Username");

		String choice = scan.nextLine();
		
		if(choice.equalsIgnoreCase("A")) {
			System.out.print("Enter Email: ");
			input = scan.nextLine();
			System.out.print("Enter Password: ");
			password = scan.nextLine();

			ProcessLoginContext context = new ProcessLoginContext(new ProcesByEmail());
			logged = context.performProcess(input, password,an);

		}
		if(choice.equalsIgnoreCase("B")) {
			System.out.print("Enter Username: ");
			String inputUsername = scan.nextLine();
			System.out.print("Enter Password: ");
			password = scan.nextLine();
			ProcessLoginContext context = new ProcessLoginContext(new ProcesByName());

			logged = context.performProcess(inputUsername, password,an);

		}
		return logged;




		/*
		 * } else if(choice.equalsIgnoreCase("B")) {
		 * System.out.print("Enter Username: "); String Username = scan.nextLine();
		 * String sqlRetrieveName = "Select adminName , adminPassword From admin " +
		 * "Where adminName = ?"; PreparedStatement prep =
		 * con.prepareStatement(sqlRetrieveName); prep.setString(1, Username);
		 * 
		 * ResultSet rs = stmt.executeQuery(sqlRetrieveName);
		 * 
		 * while(rs.next()) { String adminNameRetrieved = rs.getString("adminName");
		 * String adminPasswordRetrieved = rs.getString("adminPassword");
		 * 
		 * }
		 * 
		 * }
		 */
}
}

