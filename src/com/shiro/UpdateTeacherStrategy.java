
package com.shiro;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException; 
import java.util.Scanner;

public class UpdateTeacherStrategy implements CrudStrategyTeacher {
	static Connection con = null;
	static Statement stmt = null;
	static UpdateContext up = null;
	static CrudContextTeacher readT = new CrudContextTeacher(new ReadTeacherStrategy()); 
	static Scanner scan = new Scanner(System.in);
	static Hashpassword p = new Hashpassword();

	@Override 
	public void process(Teacher teacher) throws Exception { String
		columnToUpdate = "";
		int idToUpdate1 = 0; 
		String newData = "";

		// display lists of teacher for updatimg
		System.out.println("Display Teachers"); 
		displayForCrud();
		String updateTypeFlag;
		try {
			do {
		 System.out.printf("-----------------------------------%n");
		System.out.printf("        Admin Columns              %n");
		System.out.printf("-----------------------------------%n");
		System.out.printf("        --update TeacherName         %n");
		System.out.printf("        --update TeacherId           %n");
		System.out.printf("        --update TeacherEmail        %n");
		System.out.printf("        --update TeacherContactNumber %n");
	//	System.out.printf("        --update TeacherPassword     %n");
		System.out.printf("        --return                   %n");
		System.out.printf("-----------------------------------%n");

		columnToUpdate = scan.nextLine();
		}
			while(columnToUpdate.isEmpty());
			if(columnToUpdate.equalsIgnoreCase("--update TeacherName")) {
				System.out.print("Enter Id To Update: ");
				 idToUpdate1 = Integer.parseInt(scan.nextLine());
				System.out.print("Enter New data: ");
				newData = scan.nextLine();
				Integer.parseInt(newData);
				updateTypeFlag = "a";
				UpdateTeacherDatabase(idToUpdate1,newData,updateTypeFlag);
			}
			else if(columnToUpdate.equalsIgnoreCase("--update TeacherId")) {
				System.out.print("Enter Id To Update: ");
				 idToUpdate1 = Integer.parseInt(scan.nextLine());
				System.out.print("Enter New data: ");
				newData = scan.nextLine();
				updateTypeFlag = "b";
				UpdateTeacherDatabase(idToUpdate1,newData,updateTypeFlag);

			}
			else if(columnToUpdate.equalsIgnoreCase("--update TeacherEmail")) {
				System.out.print("Enter Id To Update: ");
				 idToUpdate1 = Integer.parseInt(scan.nextLine());
				System.out.print("Enter New data: ");
				newData = scan.nextLine();
				updateTypeFlag = "c";
				UpdateTeacherDatabase(idToUpdate1,newData,updateTypeFlag);

			}
			else if(columnToUpdate.equalsIgnoreCase("--update TeacherContactNumber")) {
				System.out.print("Enter Id To Update: ");
				 idToUpdate1 = Integer.parseInt(scan.nextLine());
				System.out.print("Enter New data: ");
				newData = scan.nextLine();
				updateTypeFlag = "d";
				UpdateTeacherDatabase(idToUpdate1,newData,updateTypeFlag);

				
			}
			else if(columnToUpdate.equalsIgnoreCase("--update TeacherPassword")) {
				System.out.print("Enter Id To Update: ");
				 idToUpdate1 = Integer.parseInt(scan.nextLine());
				
				System.out.print("Enter New data: ");
				newData = scan.nextLine();
				
				updateTypeFlag = "e";
				UpdateTeacherDatabase(idToUpdate1,newData,updateTypeFlag);

			
			}
			else {
				System.out.println("Invalid Input");
			}
			
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input try again"); }



	}
	public static void displayForCrud() throws SQLException, ClassNotFoundException {
		con = DatabaseManagement.getCon();
		stmt = con.createStatement();
		String sql = "SELECT teachersinfo.TeacherId , teachersinfo.TeacherName,teachersinfo.TeacherEmail,teachersinfo.TeacherContactNumber,teacherposition.TeacherPosition FROM teachersinfo Inner JOIN teacherposition USING (TeacherId);  ";
		ResultSet rs = stmt.executeQuery(sql);
		// print this on top so its not going to be printed several times
		System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("                                  Admin Database                                                                   %n");
		System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
		System.out.printf("| %-15s | %-15s | %15s | %-20s | %-20s | %n", "TeacherId", "TeacherName", "TeacherEmail","TeacherContactNumber","Teacher-Position");
		while (rs.next()) {
			int TeacherId =  rs.getInt("TeacherId");
			String TeacherName = rs.getString("TeacherName");
			String TeacherEmail = rs.getString("TeacherEmail");
			String TeacherContact = rs.getString("TeacherContactNumber");
			String teacherPosition = rs.getString("TeacherPosition");
			System.out.printf("| %-15s | %-15s | %15s | %-20s | %-20s|%n", TeacherId,TeacherName,TeacherEmail,TeacherContact,teacherPosition);
			// https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
			// documentation for java printf for a table

			// same goes for this one
		}				
		System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
		return;
	}
	
	
	// update TeacherDatabaseLogic
	
	public static void UpdateTeacherDatabase(int id, String newData, String updateTypeFlag) throws SQLException, NoSuchAlgorithmException {
		try {
			con = DatabaseManagement.getCon();
		} catch (ClassNotFoundException | SQLException e) {
			
		}
		stmt = con.createStatement();
		if(updateTypeFlag.equalsIgnoreCase("a")) {
			String sqlUpdateTeacherName = "update teacherinfo set TeacherName = ? where TeacherId = ?";
			
			PreparedStatement prep = con.prepareStatement(sqlUpdateTeacherName); 
			prep.setInt(1, Integer.parseInt(newData));
			prep.setInt(2, id);
			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			System.out.println("Printing table after update..");
			try {
				displayForCrud();
			} catch (ClassNotFoundException | SQLException e) {

			}

		}

		else if(updateTypeFlag.equalsIgnoreCase("b")) {
			String sqlUpdateTeacherId = "update teacherinfo set TeacherId = ? where TeacherId = ?";

			PreparedStatement prep = con.prepareStatement(sqlUpdateTeacherId); 
			prep.setString(1, newData);
			prep.setInt(2, id);
			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			System.out.println("Printing table after update..");
			try {
				displayForCrud();
			} catch (ClassNotFoundException | SQLException e) {

			}
		}else if(updateTypeFlag.equalsIgnoreCase("c")) {
			String sqlUpdateTeacherEmail = "update teacherinfo set TeacherEmail = ? where TeacherId = ?";

			PreparedStatement prep = con.prepareStatement(sqlUpdateTeacherEmail); 
			prep.setString(1, newData);
			prep.setInt(2, id);
			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			System.out.println("Printing table after update..");
			try {
				displayForCrud();
			} catch (ClassNotFoundException | SQLException e) {

			}
		} else if(updateTypeFlag.equalsIgnoreCase("d")) {
			String sqlUpdateTeacherContact = "update teacherinfo set TeacherContactNumber = ? where TeacherId = ?";

			PreparedStatement prep = con.prepareStatement(sqlUpdateTeacherContact); 
			prep.setString(1, newData);
			prep.setInt(2, id);
			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			System.out.println("Printing table after update..");
			try {
				displayForCrud();
			} catch (ClassNotFoundException | SQLException e) {

			}
		}else if(updateTypeFlag.equalsIgnoreCase("e")) {
			
			byte[] salt = p.getSalt();
			String newPassword = p.getSecurePassword(newData, salt);
			
			String sqlUpdateTeacherPassword = "update teacherhash set TeacherPassword = ?, TeacherSalt = ?  where TeacherId = ?";

			PreparedStatement prep = con.prepareStatement(sqlUpdateTeacherPassword); 
			prep.setString(1, newPassword);
			prep.setBytes(2, salt);
			prep.setInt(3, id);

			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			System.out.println("Printing table after update..");
			try {
				displayForCrud();
			} catch (ClassNotFoundException | SQLException e) {

			}

		}
}
}
