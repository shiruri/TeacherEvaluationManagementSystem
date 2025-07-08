package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class StudentCrud implements Task{
	static Connection con = null;
	static Statement stmt = null;
	static Scanner scan = new Scanner(System.in);
	
	@Override
	public void addStudent(Student student) {
		try {
			con = DatabaseManagement.getCon();
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Erorr connecting to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erorr connecting to database");
		}
		

		String addTeacher =  "insert into students (StudentId,StudentName,StudentEmail,StudentContactNumber,SectionYrId) values (?,?,?,?,?);"; 
		try {
			PreparedStatement prep = con.prepareStatement(addTeacher);
			prep.setLong(1, student.getStudentId());
			prep.setString(2, student.getStudentName());
			prep.setString(3, student.getStudentEmail());
			prep.setString(4, student.getStudentContactNumber());
			prep.setString(5, student.getStudentYrSection());
			prep.executeUpdate();
			System.out.println("Student Added");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("Error Inserting Data to Database");
		}
		
		


	}

	@Override
	public void displayStudent() {
		// TODO Auto-generated method stub
		try {
			con = DatabaseManagement.getCon();
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Erorr connecting to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erorr connecting to database");
		}
		
		try {
			String sql = "select *from students;";
			ResultSet rs = stmt.executeQuery(sql);
			// print this on top so its not going to be printed several times
			System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("                                  Student Database                                                                   %n");
			System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
			System.out.printf("| %-15s | %-15s | %15s | %-20s | %-20s | %n", "StudentId", "StudentName", "StudentEmail","StudentContactNumber","StudentYearAndSection");
			while (rs.next()) {
				long StudentId =  rs.getLong("StudentId");
				String StudentName = rs.getString("StudentName");
				String StudentEmail = rs.getString("StudentEmail");
				String StudentContactNumber = rs.getString("StudentContactNumber");
				String StudentYearAndSection = rs.getString("SectionYrId");
				System.out.printf("| %-15s | %-15s | %15s | %-20s | %-20s |%n", StudentId,StudentName,StudentEmail,StudentContactNumber,StudentYearAndSection);
				// https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-print-table-format-printf-chart-console-scanner-println-line
				// documentation for java printf for a table

				// same goes for this one
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		System.out.println(e);
		}				
		System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
	}

	

	@Override
	public void updateStudent()  {
		displayStudent();
		long inputId  = 0;
		String newData = "";
		String whatToUpdate = "";
		try {
			 whatToUpdate = helperForUpdate();
			if(whatToUpdate.equalsIgnoreCase("Error")) {
				return;
			}
			System.out.print("Enter Student Id To Update: ");
			inputId = scan.nextLong();
			
			System.out.print("Enter New Data:  ");
			scan.nextLine();
			newData = scan.nextLine();
			
		}catch(Exception e) {
			System.out.println("Invalid Input");
		}
		
		try {
			con = DatabaseManagement.getCon();
		} catch (ClassNotFoundException | SQLException e) {

		}

		try {
			if(whatToUpdate.equalsIgnoreCase("update students set StudentId = ? where StudentId = ?")) {
				parseIdToLong(newData);
			}
			
			stmt = con.createStatement();

			PreparedStatement prep = con.prepareStatement(whatToUpdate); 
			prep.setString(1, newData);
			prep.setLong(2, inputId);
			prep.executeUpdate();
			int rowsUpdated = prep.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Occured");
		}

		System.out.println("Printing table after update..");
		displayStudent();
		}		
	
	String helperForUpdate() {
		boolean flag = true;
		String defaultString = "Error";
		String helperString = "";
		try {
			do {
				System.out.print("Select Column To Update: ");
				System.out.println("Format: eg --update ColumnName");
				helperString = scan.nextLine();
				flag = false;

			}while(flag);
		
		}catch(Exception e) {
			System.out.println("Invalid Input");
		}
		
		
		if(helperString.isBlank()) {
			System.out.println("Please Enter A Valid Column");
		}
		else {
			String sqlUpdateStudentId = "update students set StudentId = ? where StudentId = ?";
			String sqlUpdateStudentName = "update students set StudentName = ? where StudentId = ?";
			String sqlUpdateStudentEmail = "update students set StudentEmail = ? where StudentId = ?";
			String sqlUpdateStudentContactNumber = "update students set StudentContactNumber = ? where StudentId = ?";
			String sqlUpdateStudentYearAndSection = "update students set SectionYrId = ? where adminId = ?";
			
			if(helperString.equalsIgnoreCase("--update StudentId")) {
				
				return sqlUpdateStudentId;
			}
			else if(helperString.equalsIgnoreCase("--update StudentName")) {
				return sqlUpdateStudentName;
			}
			else if(helperString.equalsIgnoreCase("--update StudentEmail")) {
				return sqlUpdateStudentEmail;
			}
			else if(helperString.equalsIgnoreCase("--update StudentContactNumber")) {
				return sqlUpdateStudentContactNumber;

			}
			else if(helperString.equalsIgnoreCase("--update SectionYrId")) {
				return sqlUpdateStudentYearAndSection;

			}
			
		}	
		return defaultString;
	}
	long parseIdToLong(String newData) {
		long parsedLong = Long.parseLong(newData);
		return parsedLong;
	}
	

	@Override
	public void removeStudent() {
		// TODO Auto-generated method stub

		// implement batch delete

		try {
			con = DatabaseManagement.getCon();
			stmt = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("Error Connecting To Database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Connecting To Database");
		}
		String choice = "";

		displayStudent();
		// Get input 
	

		for(int invalidInput = 1; invalidInput < 5;invalidInput++) {
			// Get input 
			System.out.println("A Normal Delete");
			System.out.println("B Batch Delete");
			System.out.print("Enter Input: (A/B) ");
			
			choice = scan.nextLine();
			
			if(!choice.isEmpty() || choice.equalsIgnoreCase("A") || choice.equalsIgnoreCase("B")) {
				break;
			}
			else {
				System.out.println("Invalid Input Current Tries : "+ invalidInput);

			}
		
		}




		if(choice.equalsIgnoreCase("A")) {
			try {
				int id1 = 0;
				

				for(int invalidInput = 1; invalidInput < 5;invalidInput++) {
					// Get input 
					System.out.print("Enter Id: ");
					id1 = scan.nextInt();					
					if(!(id1 < 0)) {
						break;
					}
					else {
						System.out.println("Invalid Input Current Tries : "+ invalidInput);

					}
				
				}

		


			// = checkCurrentUser(id,an);
			//System.out.println(SENTINEL); // debug sentinel its always going to the return statement of 0

			String sqlDelete = "DELETE FROM students "
					+"Where StudentId = ?";

			PreparedStatement prep = con.prepareStatement(sqlDelete);
			prep.setInt(1, id1);
			int rowUpdated = prep.executeUpdate();
			if (rowUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			else {
				System.out.println("No Id Found");
			}

			System.out.println("Displaying Database After Deletion...");
			displayStudent();
			}catch(Exception e) {
				System.out.println("Error Deleting");
			}
		}

		else if(choice.equalsIgnoreCase("B")) {
			try {
				
		
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
						displayStudent();
					}
					else {
						System.out.println("Invalid");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid Input");
				}
				scan.nextLine(); // clears the buffer
			} while (choice.isEmpty());
			}catch(Exception e) {
				System.out.println("Error Exception");
			}
		}
	}
	public static int countRows() throws SQLException {

		String countRows = "SELECT COUNT(*) FROM students;";
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

}
