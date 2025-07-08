package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveAdmin {
	static Connection con = null;
	static Statement stmt = null;
	static Scanner scan = new Scanner(System.in);
	static GetAdminList ls = new GetAdminList();
	static boolean isNotValid = true;
	
	public static void removeAdmin(Admin an) throws ClassNotFoundException, SQLException {
		ls.getAdminList();
		// implement batch delete

		con = DatabaseManagement.getCon();
		stmt = con.createStatement();
		String choice = "";
		try {


			do {
				// Get input 
				System.out.println("A Normal Delete");
				System.out.println("B Batch Delete");
				System.out.print("Enter Input: (A/B) ");
				choice = scan.nextLine();
				if(choice.equalsIgnoreCase("A") || choice.equalsIgnoreCase("B")) {
					isNotValid = false;
					continue;
				}
				else {
					System.out.println("Invalid Input");
				}
			}while(isNotValid);
		}catch(InputMismatchException e) {
			System.out.println("Error");
			scan.next();
}
		    
	
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
	
				String sqlDelete = "DELETE FROM admin "
						+"Where adminId = ?";

				PreparedStatement prep = con.prepareStatement(sqlDelete);
				prep.setInt(1, id);
				
				int rowsUpdated = prep.executeUpdate();
				if (rowsUpdated > 0) {
				    System.out.println("An existing user was updated successfully!");
					System.out.println("Displaying Database After Deletion...");
					
					ls.getAdminList();
				}
				else {
					System.out.println("No Valid Id Found");
				}
			
		
		}
		
		else if(choice.equalsIgnoreCase("B")) {
			int LoopId = 0;
			do {
			    try {
			        // Get input 
				
					System.out.print("Enter How Many To Delete: ");
					LoopId = scan.nextInt();
					int rows = countRows();
					int rowsUpdated = 0;
					if(LoopId > 0 && LoopId <= rows) {
						String sqlDelete = "DELETE FROM admin "
								+"Where adminId = ?";
						int[] ids = idsToDelete(LoopId);
						for(int i = 0; i < ids.length; i++ ) {
							PreparedStatement prep = con.prepareStatement(sqlDelete);
							prep.setInt(1, ids[i]);
							 rowsUpdated = prep.executeUpdate();						}
						
						if (rowsUpdated > 0) {
						    System.out.println("An existing user was updated successfully!");
							System.out.println("Displaying Database After Deletion...");
							
							ls.getAdminList();
						}
						else {
							System.out.println("No Valid Id Found");
						}
					
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
		
		String countRows = "SELECT COUNT(*) FROM admin;";
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
