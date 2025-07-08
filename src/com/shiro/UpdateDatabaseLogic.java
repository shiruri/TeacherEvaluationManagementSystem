package com.shiro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateDatabaseLogic {
	static UpdateContext up = null;
	static GetAdminList ls = new GetAdminList();
	static Scanner scan = new Scanner(System.in);

	public static void UpdateLogic() throws Exception {
		String columnToUpdate = "";
		int idToUpdate = 0;
		String newData = "";
		//boolean isNumeric = columnToUpdate.chars().allMatch( Character::isDigit );
		ls.getAdminList(); // show the tables
		try {
			do {
				System.out.printf("-----------------------------------%n");
				System.out.printf("        Admin Columns              %n");
				System.out.printf("-----------------------------------%n");
				System.out.printf("        --update AdminName         %n");
				System.out.printf("        --update AdminId           %n");
				System.out.printf("        --update AdminEmail        %n");
				System.out.printf("        --update AdminContactNumber%n");
				System.out.printf("        --update AdminPassword     %n");
				System.out.printf("        --return                   %n");
				System.out.printf("-----------------------------------%n");

				columnToUpdate = scan.nextLine();
			}while(columnToUpdate.isEmpty());

		}catch(InputMismatchException e) {
			System.out.println("Invalid Input try again");
		}
		
		if(columnToUpdate.equalsIgnoreCase("--update AdminName")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextInt();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			UpdateContext context = new UpdateContext(new UpdateAdminName());

			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminId")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextInt();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminId());
			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminEmail")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextInt();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminEmail());
			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminContactNumber")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextInt();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminContactNumber());
			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminPassword")) {
			System.out.print("Enter Id To Update: ");
			 idToUpdate = Integer.parseInt(scan.nextLine());
			
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminPassword());
			context.performUpdate(idToUpdate, newData);
		}
		else {
			System.out.println("Invalid Input");
		}
		
		}
		/*
		 * do { try { System.out.println("Enter Column to update"); columnToUpdate =
		 * scan.nextLine(); System.out.println("Enter Id to update"); idToUpdate =
		 * scan.nextLine(); } catch (InputMismatchException e) {
		 * System.out.print("Invalid input"); scan.nextLine(); } } while
		 * (columnToUpdate.isBlank() || isNumeric);
		 */
}




