package com.shiro;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentObject  {

	static Hashpassword c= new Hashpassword();
	
	
	// lets make an admin object creator which take the values for the admin object 
	static Scanner scan = new Scanner(System.in);
	
	
	public static void addStudent()   {
		long studentId;
		String studentName;
		String studentEmail;
		String studentContactNumber;
		String studentYearAndSection;
		try {
			System.out.print("Enter Student Id: ");
			studentId = scan.nextLong();
			System.out.print("Enter Student Name: ");
			scan.nextLine();

			studentName = scan.nextLine();

//	System.out.print("Enter Admin Id: "); changed to auto increment
//  var AdminId = scan.nextLine();

			System.out.print("Enter Student Email: ");
			studentEmail = scan.nextLine();

			System.out.print("Enter Student Contact Number: ");
			studentContactNumber = scan.nextLine();

			System.out.print("Enter Student Year And Section: ");
			studentYearAndSection = scan.nextLine();
			try {
				Student s = new Student(studentId,studentName,studentEmail,studentContactNumber,studentYearAndSection);
				StudentCrud add = new StudentCrud();
				add.addStudent(s);
				
			}catch(Exception e) {
				System.out.println("Invalid Entered Data (Duplicate) " + e);
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error adding student");
		}
		
	
	  
		
		
		
		
		
	}
}

