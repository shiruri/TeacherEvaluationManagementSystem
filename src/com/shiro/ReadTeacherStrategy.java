package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


//Read
public class ReadTeacherStrategy implements CrudStrategyTeacher {
	static Connection con = null;
	static Statement stmt = null;
	static Scanner scan = new Scanner(System.in);
	boolean isValidInput = false;
	@Override
	public void process(Teacher teacher) throws ClassNotFoundException, SQLException {

		con = DatabaseManagement.getCon();
		stmt = con.createStatement();
		String choice = "";
		while(!isValidInput) {
			System.out.println("A Display List");
			System.out.println("B Display Part-Time Teachers Lists");
			System.out.println("C Display Full-Time Teachers Lists");
			try {
				choice = scan.nextLine();
				if(choice.equalsIgnoreCase("A")) {
					isValidInput = true;
				}
				else if(choice.equalsIgnoreCase("B")) {
					isValidInput = true;
				}
				else if(choice.equalsIgnoreCase("C")) {
					isValidInput = true;
				}
			}catch(InputMismatchException e) {
				System.out.println("Invalid Input");
				scan.next();
			}
		}
		if (choice.equalsIgnoreCase("A")) {


			String sql = "SELECT teachersinfo.TeacherId , teachersinfo.TeacherName,teachersinfo.TeacherEmail,teachersinfo.TeacherContactNumber,teacherposition.TeacherPosition FROM teachersinfo Inner JOIN teacherposition USING (TeacherId);  ";
			ResultSet rs = stmt.executeQuery(sql);
			// print this on top so its not going to be printed several times
			System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("                                  Teacher Database                                                                   %n");
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
			isValidInput = false;
		}
		else if(choice.equalsIgnoreCase("B")) {
			String sql = "SELECT teachersinfo.TeacherId , teachersinfo.TeacherName,teachersinfo.TeacherEmail,teachersinfo.TeacherContactNumber,teacherposition.TeacherPosition FROM teachersinfo Inner JOIN teacherposition USING (TeacherId) where TeacherPosition = ?;  ";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, "Part-Time");
			ResultSet rs = prep.executeQuery();
			// print this on top so its not going to be printed several times
			System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("                                  Teacher Database                                                                   %n");
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
			isValidInput = false;
		}
		else if(choice.equalsIgnoreCase("C")) {
		
			String sql = "SELECT teachersinfo.TeacherId , teachersinfo.TeacherName,teachersinfo.TeacherEmail,teachersinfo.TeacherContactNumber,teacherposition.TeacherPosition FROM teachersinfo Inner JOIN teacherposition USING (TeacherId) where TeacherPosition = ?;  ";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, "Full-Time");
			ResultSet rs = prep.executeQuery();
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
			isValidInput = false;
		}
	}
	
	}



