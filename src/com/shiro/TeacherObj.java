package com.shiro;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TeacherObj {
	static Hashpassword c= new Hashpassword();
	// lets make an admin object creator which take the values for the admin object 
	static Scanner scan = new Scanner(System.in);
	static Teacher t = new Teacher();

	public static void TeacheroObj() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, Exception  {
		String input = " ";
		System.out.println("A - Add Part-Time Teacher");
		System.out.println("B - Add Full-Time Teacher");
		System.out.print("Input: ");

		try {
			do {
	

				input = scan.nextLine();
			}while(input.isEmpty() );
			
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}
		// part time
		if (input.equalsIgnoreCase("A")) {
			System.out.print("Enter Teacher Id: ");
			var teacherId = scan.nextInt();

			System.out.print("Enter Teacher Name: ");
			scan.nextLine();
			var teacherName = scan.nextLine();


			//	System.out.print("Enter Admin Id: "); changed to auto increment
			//  var AdminId = scan.nextLine();

			System.out.print("Enter Teacher Email: ");
			var teacherEmail = scan.nextLine();

			System.out.print("Enter Teacher Contact Number: ");
			String teacherContactNumber = scan.nextLine();
			

			
			/*
			 * System.out.print("Enter Admin Password: "); String teacherPassword =
			 * scan.nextLine();
			 */
			
	//		byte[] salt = c.getSalt();
			
		//	String hashedPassword = c.getSecurePassword(teacherPassword,salt);

			CrudContextTeacher a = new CrudContextTeacher(new AddTeacherStrategy());
			
			try {
				t.setSchoolId(teacherId);

				t.setFullName(teacherName);
				t.setTeacherEmail(teacherEmail);
				t.setTeacherContactNumber(teacherContactNumber);
				t.setTeacherPosition("Part-Time");
			//	t.setTeacherpassword(hashedPassword);

				a.performCrud(t);
				
			}catch(Exception e) {
				System.out.println("Invalid Entered Data (Duplicate) " + e);
			}

		}
		// fulltime
		else if(input.equalsIgnoreCase("B")) {

			System.out.print("Enter Teacher Id: ");
			var teacherId = scan.nextInt();
			
			System.out.print("Enter Teacher Name: ");
			scan.nextLine();

			var teacherName = scan.nextLine();


			System.out.print("Enter Teacher Email: ");
			var teacherEmail = scan.nextLine();

			System.out.print("Enter Teacher Contact Number: ");
			var teacherContactNumber = scan.nextLine();
			

			
			System.out.print("Enter Admin Password: ");
			var teacherPassword = scan.nextLine();
			
			byte[] salt = c.getSalt();
			
			String hashedPassword = c.getSecurePassword(teacherPassword,salt);

			CrudContextTeacher a = new CrudContextTeacher(new AddTeacherStrategy());
			
			try {
				t.setSchoolId(teacherId);

				t.setFullName(teacherName);
				t.setTeacherEmail(teacherEmail);
				t.setTeacherContactNumber(teacherContactNumber);
				t.setTeacherPosition("Full-Time");
				//t.setTeacherpassword(hashedPassword);

				a.performCrud(t);
				
			}catch(Exception e) {
				System.out.println("Invalid Entered Data (Duplicate) " + e);
			}


		}
		else {
			System.out.println("Erorr Occured");
		}
		
		


	}
}


