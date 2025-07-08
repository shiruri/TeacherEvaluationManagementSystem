package com.shiro;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminObject {
	static Hashpassword c= new Hashpassword();
	// lets make an admin object creator which take the values for the admin object 
	static Admin an = new Admin();
	static Scanner scan = new Scanner(System.in);

	public static void adminObject() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException  {
		System.out.print("Enter Admin Name: ");
		var AdminName = scan.nextLine();

	//	System.out.print("Enter Admin Id: "); changed to auto increment
	//  var AdminId = scan.nextLine();

		System.out.print("Enter Admin Email: ");
		var adminEmail = scan.nextLine();

		System.out.print("Enter Admin Contact Number: ");
		var adminContactNumber = scan.nextLine();

		System.out.print("Enter Admin Password: ");
		var adminPassword = scan.nextLine();
	    byte[] salt = c.getSalt();
		String hashedPassword = c.getSecurePassword(adminPassword,salt);
		
		AddAdmin ap = new AddAdmin();
		
		
		try {
			an.setAdminName(AdminName);
			an.setAdminId(an.getAdminId());
			an.setAdminEmail(adminEmail);
			an.setAdminContactNumber(adminContactNumber);
			an.setAdminPassword(hashedPassword);
			
			ap.addAdmin(an);

		}catch(Exception e) {
			System.out.println("Invalid Entered Data (Duplicate) " + e);
		}
			
		
		
	}
}
