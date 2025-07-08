package com.shiro;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

public class RunnableMain {
	static GenerateReport gen = new GenerateReport();
	static boolean logged = false;
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
	static CrudContextTeacher remT = new CrudContextTeacher(new RemoveTeacherStrategy());
	static StudentObject s = new StudentObject();
	static TeacherObj TeacherObj = new TeacherObj();
	static StudentCrud read = new StudentCrud();
	static StudentCrud update = new StudentCrud();
	static StudentCrud remove = new StudentCrud();
	static Teacher teacher = new Teacher();
	static EmailVerification em = new EmailVerification();
	static UpdateDatabaseLogic up = new UpdateDatabaseLogic();
	// static boolean isNumeric = adminInput.chars().allMatch( Character::isDigit );
	public static void start() throws Exception {
		con = DatabaseManagement.getCon();

		while(logged == false) {
			if(logged == false) {
				logged = AdminLogIn.AdminLogin(logged,an);
			}
		  }
		while(true) {
				printDashboard();
			}
			// UPDATE BEFORE LEAVIMNG
			// UPDATE THE INT ID AND IMPLEMENT ADDITION OF STUDENTS INTO DATABASE AND OTHER UNIMPLEMENTED CRUD FOR TEACHERS AND FIGUR OUT THE CALENDAR SYSTEM AN EMAIL SENDING AND OTHER VERUFUCATUIB ABD ERROR HANLING
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
				System.out.printf("        --send Eval             %n"); // implemented add admin
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
		case "--send Eval" -> sendEmail();
		case "deleteFolder" -> deleteFolder();
		case "--exit" -> exitView();
		}

	}
	public static void deleteFolder() throws IOException {
		File directory = new File("invoices/");
		FileUtils.cleanDirectory(directory); 

	}

	private static void sendEmail() {
		em.sendEvaluations();
		
	}

	private static void exitView() throws SQLException, SQLException {
		System.out.println("Exiting Program");

		con.close();
		scan.close();
		System.exit(0);

	}

	private static void printGenRep() throws IOException {
		gen.generateInvoice();
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
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

	

		if(adminInput.equalsIgnoreCase("--update Admin") || adminInput.startsWith("--update A")) {up.UpdateLogic();}
		else if(adminInput.equalsIgnoreCase("--update Teacher") || adminInput.startsWith("--update T")) { updateT.performCrud(teacher);}
		else if(adminInput.equalsIgnoreCase("--update Student") || adminInput.startsWith("--update S")) {update.updateStudent();}
		else if(adminInput.equalsIgnoreCase("--return") || adminInput.startsWith("--return")) { returnBack();}

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
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");
				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		if(adminInput.equalsIgnoreCase("--rem Admin") || adminInput.startsWith("--rem A")) {rem.removeAdmin(an);}
		else if(adminInput.equalsIgnoreCase("--rem Teacher") || adminInput.startsWith("--rem T")) {remT.performCrud(teacher);}
		else if(adminInput.equalsIgnoreCase("--rem Student") || adminInput.startsWith("--rem S")) {remove.removeStudent();}
		else if(adminInput.equalsIgnoreCase("--return") || adminInput.startsWith("--return")) { returnBack();}
	}

	private static void printView() throws Exception {
		String adminInput = "";
		try {
			do {
				System.out.printf("--------------------------------%n");
				System.out.printf("        Admin Dashboard         %n");
				System.out.printf("        Admin: " + an.getAdminName() + "%n");
				System.out.printf("--------------------------------%n");
				System.out.printf("        --view Admin       	   %n");
				System.out.printf("        --view Teacher     	   %n");
				System.out.printf("        --view Student    	   %n");
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

	

	
		
		if(adminInput.equalsIgnoreCase("--view Admin") || adminInput.startsWith("--view A")) {ls.getAdminList();}
		else if(adminInput.equalsIgnoreCase("--view Teacher") || adminInput.startsWith("--view T")) {readT.performCrud(teacher);}
		else if(adminInput.equalsIgnoreCase("--view Student") || adminInput.startsWith("--view S")) { read.displayStudent();}
		else if(adminInput.equalsIgnoreCase("--return") || adminInput.startsWith("--return")) { returnBack();}
		else {
			System.out.println("Error");
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
				System.out.printf("        --return                %n");
				System.out.printf("--------------------------------%n");

				adminInput = scan.nextLine();
			} while (adminInput.isEmpty());

		} catch (InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}

		if(adminInput.equalsIgnoreCase("--add Admin") || adminInput.startsWith("--add A")) {AdminObject.adminObject();}
		else if(adminInput.equalsIgnoreCase("--add Teacher") || adminInput.startsWith("--add T")) {TeacherObj.TeacheroObj();}
		else if(adminInput.equalsIgnoreCase("--add Student") || adminInput.startsWith("--add S")) {s.addStudent();}
		else if(adminInput.equalsIgnoreCase("--return") || adminInput.startsWith("--return")) { returnBack();}

		}
	}
	

