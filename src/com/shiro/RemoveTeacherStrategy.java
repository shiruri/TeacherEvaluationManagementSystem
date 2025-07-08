package com.shiro;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
public class RemoveTeacherStrategy implements CrudStrategyTeacher{
	static Connection con = null;
	static Statement stmt = null;
	static Scanner scan = new Scanner(System.in);
	static boolean SENTINEL = true;
	@Override
	public void process(Teacher teacher) throws SQLException, ClassNotFoundException, Exception { 
	

			// implement batch delete

			con = DatabaseManagement.getCon();
			stmt = con.createStatement();
			String choice = "";

			displayForCrud();
			// Get input 
			System.out.println("A Normal Delete");
			System.out.println("B Batch Delete");
			System.out.print("Enter Input: (A/B) ");
			choice = scan.nextLine();




			if(choice.equalsIgnoreCase("A")) {
				int id = 0;
				do {
					try {
						// Get input 
						System.out.print("Enter Id: ");
						id = scan.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Invalid Input");
					}
				} while (id <= 0);


				// = checkCurrentUser(id,an);
				//System.out.println(SENTINEL); // debug sentinel its always going to the return statement of 0

				String sqlDelete = "DELETE FROM teachersinfo "
						+"Where TeacherId = ?";

				PreparedStatement prep = con.prepareStatement(sqlDelete);
				prep.setInt(1, id);
				int rowUpdated = prep.executeUpdate();
				if (rowUpdated > 0) {
				    System.out.println("An existing user was updated successfully!");
				}
				else {
					System.out.println("No Id Found");
				}

				System.out.println("Displaying Database After Deletion...");
				displayForCrud();

			}

			else if(choice.equalsIgnoreCase("B")) {
				int LoopId = 0;
				do {
					try {
						// Get input 

						System.out.print("Enter How Many To Delete: ");
						LoopId = scan.nextInt();
						int rows = countRows();
						if(LoopId > 0 && LoopId <= rows) {
							String sqlDelete = "DELETE FROM teachersinfo "
									+"Where TeacherId = ?";
							int[] ids = idsToDelete(LoopId);
							for(int i = 0; i < ids.length; i++ ) {
								PreparedStatement prep = con.prepareStatement(sqlDelete);
								prep.setInt(1, ids[i]);
								prep.executeUpdate();
							}
							System.out.println("Displaying Database After Deletion...");
							displayForCrud();
						}
						else {
							System.out.println("Invalid");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid Input");
					}
					scan.nextLine(); // clears the buffer
				} while (choice.isEmpty());

			}
		}

		/*
		 * public static boolean isNumeric(String str) { return
		 * str.matches("-?\\d+(\\.\\d+)?"); //match a number with optional '-' and
		 * decimal. }
		 */
		/*
		 * public static boolean checkCurrentUser(int id,Admin an) { String YnN = "";
		 * int currentIdSession = an.getCurrentId(); //ex 123 //
		 * System.out.println(currentIdSession); // System.out.println(id);
		 * //System.out.println(an.getAdminId()); debug if(currentIdSession == (id)) {
		 * 
		 * System.out.println("Deleting this account will remove your Admin access");
		 * System.out.print("Continue?: (Y/N) "); YnN = scan.nextLine();
		 * 
		 * System.out.println(YnN); if(YnN.equalsIgnoreCase("Y")) {
		 * System.out.println("Processing.."); return true; } else
		 * if(YnN.equalsIgnoreCase("N")) { System.out.println("Canceling process");
		 * return false; } } return true; }
		 */
		public static int countRows() throws SQLException {

			String countRows = "SELECT COUNT(*) FROM teachersinfo;";
			ResultSet rs = stmt.executeQuery(countRows);
			rs.next();
			int totalRows = rs.getInt(1);
			return totalRows;

		}
		public static int[] idsToDelete(int LoopId) {
			int[] idsTodelete = new int[LoopId]; 
			for(int i = 0; i <= LoopId ;i++) {
				System.out.print("Enter Ids: ");
				idsTodelete[i] = scan.nextInt();

			}
			return idsTodelete;
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
}

