package com.shiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static boolean logged = false;
	static EmailVerification email = new EmailVerification();
	static Connection con = null;
	static Statement stmt = null;
	static Student st = new Student();
	static Teacher tr = new Teacher();
	static Admin an = new Admin();
	static AES aes = new AES(); // aes engcryption and decryption for password
	static Scanner scan = new Scanner(System.in);
	static AdminLogIn in = new AdminLogIn();
	static RemoveAdmin rem = new RemoveAdmin();
	static GetAdminList ls = new GetAdminList();
	static AdminObject add = new AdminObject();
	static CrudContextTeacher readT = new CrudContextTeacher(new ReadTeacherStrategy());
	static CrudContextTeacher updateT = new CrudContextTeacher(new UpdateTeacherStrategy());

	static TeacherObj TeacherObj = new TeacherObj();
	static Teacher teacher = new Teacher();
	static UpdateDatabaseLogic up = new UpdateDatabaseLogic();
	// static boolean isNumeric = adminInput.chars().allMatch( Character::isDigit );

	public static void main(String[] args) throws Exception {
		// DatabaseManagement.connection(); // to access database connection
		// log in system and authorization first
		// add admin logic first

		// implement auth using java stmp or something java mail api
		// nvm that i forgot i dont have maven for this look for other libraries

		// assign admin roles using a new sepperate object roles with boolean values to
		// accss different parts of the code
		// log in feature

		// last feature was log in retrieve data from database and compare with the
		// input data if true
		// implement overall log in for Students and Admin
		// implement hashing of password using AES engcryption

		// LOGIC FOR LOG IN
		// new addition implement a hash feature for password using sha 256 or other
		// algprithm if possible and then
		// take the password
		// hashthe password
		// save the hash and salt to the database
		// when logging in hash the input password
		// retrieve both the actual password on the database
		// hash the password input
		// compare both

		// make teachers able to add students and delete and edit

		con = DatabaseManagement.getCon();

		/*
		 * try { crypt.init(); String test = "pokemon2626"; String engcyptedText =
		 * crypt.engcypt(test); System.out.println(engcyptedText); String decryptedText
		 * = crypt.decrypt(engcyptedText); System.out.println(decryptedText);
		 * }catch(Exception e) { System.out.println(e); }
		 */
		// login feature done

		// follow the database and create new stuff
		// make students and teacher dashboard
		// do teacher first
		// Day 3 2025 JUNE 24


		while(logged == false) { if(logged == false) {


			logged = AdminLogIn.AdminLogin(logged,an); } } while(true) {

				printDashboard();

			}
			/*
		 * System.out.print("Enter Email: "); String Email = scan.nextLine();
		 * email.EmailVerification(Email);
		 */

	}

	// rem.removeAdmin();

	// standard initialization of jdbc

	// lets add the add method for now so i can make the log in system

	// i want to make the main method as clean as possible so ill just make the
	// method on the DatabaseManagement.java file

	// using a github repo for cheetsheet of jdbc
	/*
	 * "INSERT INTO employees (name, salary, address) " + "VALUES ('" + name + "', "
	 * + salary + ", '" + address + "')";
	 */

	public static void printDashboard() throws Exception {
		String adminInput = "";

		try {
			do {
				System.out.printf("--------------------------------%n");
				System.out.printf("        Admin Dashboard         %n");
				System.out.printf("        Admin: " + an.getAdminName() + "%n");
				System.out.printf("--------------------------------%n");
				System.out.printf("        --view to view lists    %n"); // implemented add admin
				System.out.printf("        --add to add data       %n"); // implemented view admin
				System.out.printf("        --rem to remove         %n"); // implemented add admin
				System.out.printf("        --update to update data %n"); // implemented rem admin
				System.out.printf("        --genrep generate report%n"); // implemented add admin
				System.out.printf("        --exit                  %n"); // implemented return and exit
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		switch (adminInput) {
		case "--view" -> printView();
		case "--add" -> printAdd();
		case "--rem" -> printrem();
		case "--update" -> printedit();
		case "--genrep" -> printGenRep();
		case "--exit" -> exitView();

		}

	}

	private static void exitView() throws SQLException, SQLException {
		System.out.println("Exiting Program");

		con.close();
		scan.close();
		System.exit(0);

	}

	private static Object printGenRep() {

		return null;
	}

	private static void printedit() throws Exception {
		String adminInput = "";

		try {
			do {
				System.out.printf("--------------------------------%n");
				System.out.printf("        Admin Dashboard         %n");
				System.out.printf("        Admin: " + an.getAdminName() + "%n");
				System.out.printf("--------------------------------%n");
				System.out.printf("        --update Admin             %n");
				System.out.printf("        --update Teacher           %n");
				System.out.printf("        --update Student           %n");
				System.out.printf("        --update Subject           %n");
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		switch (adminInput) {
		case "--update Admin" -> up.UpdateLogic(); // update admin logic
		case "--update Teacher" -> updateT.performCrud(teacher); // tba
		case "--update Student" -> printrem();// tba
		case "--update Subject " -> printGenRep();// tba
		case "--return" -> returnBack();

		}

	}

	private static void printrem() throws Exception {
		String adminInput = "";

		try {
			do {
				System.out.printf("--------------------------------%n");
				System.out.printf("        Admin Dashboard         %n");
				System.out.printf("        Admin: " + an.getAdminName() + "%n");
				System.out.printf("--------------------------------%n");
				System.out.printf("        --rem Admin             %n");
				System.out.printf("        --rem Teacher           %n");
				System.out.printf("        --rem Student           %n");
				System.out.printf("        --rem Subject           %n");
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");
				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		switch (adminInput) {
		case "--rem Admin" -> rem.removeAdmin(an);
		case "--rem Teacher" -> printAdd(); // tba
		case "--rem Student" -> printrem();// tba
		case "--rem Subject " -> printGenRep();// tba
		case "--return" -> returnBack();

		}
	}

	private static void printView() throws Exception {
		String adminInput = "";
		try {
			do {
				System.out.printf("--------------------------------%n");
				System.out.printf("        Admin Dashboard         %n");
				System.out.printf("        Admin: " + an.getAdminName() + "%n");
				System.out.printf("--------------------------------%n");
				System.out.printf("        --view  Admin       	   %n");
				System.out.printf("        --view  Teacher     	   %n");
				System.out.printf("        --view  Student    	   %n");
				System.out.printf("        --view  Subject         %n");
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		switch (adminInput) {
		case "--view Admin" -> ls.getAdminList();
		case "--view Teacher" -> readT.performCrud(teacher);// added
		case "--view Student" -> printrem();// tba
		case "--view Subject" -> printedit();// tba
		case "--return" -> returnBack();

		}
	}

	private static void returnBack() {
		System.out.println("Returning");
		return;
	}

	private static void printAdd() throws Exception {
		String adminInput = "";
		try {
			do {
				System.out.printf("--------------------------------%n");
				System.out.printf("        Admin Dashboard         %n");
				System.out.printf("        Admin: " + an.getAdminName() + "%n");
				System.out.printf("--------------------------------%n");
				System.out.printf("        --add  Admin       	   %n");
				System.out.printf("        --add  Teacher     	   %n");
				System.out.printf("        --add  Student    	   %n");
				System.out.printf("        --add  Subject         %n");
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		switch (adminInput) {
		case "--add Admin" -> AdminObject.adminObject();
		case "--add Teacher" -> TeacherObj.TeacheroObj(); // added
		case "--add Student" -> printrem();// tba
		case "--add Subject" -> printedit();// tba
		case "--return" -> returnBack();

		}
	}

}

// this will prin the adminList from the database

// delete method for admin
