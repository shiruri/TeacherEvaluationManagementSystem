package com.shiro;

import java.util.InputMismatchException;
import java.util.Scanner;

// Update
public class UpdateTeacherStrategy implements CrudStrategyTeacher {
	static UpdateContext up = null;
	static CrudContextTeacher readT = new CrudContextTeacher(new ReadTeacherStrategy());
	static Scanner scan = new Scanner(System.in);
	
    @Override
    public void process(Teacher teacher) throws Exception {
    	String columnToUpdate = "";
		String idToUpdate = "";
		String newData = "";
		
		// display lists of teacher for updatimg
		System.out.println("Display Teachers");
		readT.performCrud(teacher);
		try {
			do {
				System.out.printf("-----------------------------------%n");
				System.out.printf("        Admin Columns              %n");
				System.out.printf("-----------------------------------%n");
				System.out.printf("        --update TeacherName         %n");
				System.out.printf("        --update TeacherId           %n");
				System.out.printf("        --update TeacherEmail        %n");
				System.out.printf("        --update TeacherContactNumber %n");
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
			idToUpdate = scan.nextLine();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			UpdateContext context = new UpdateContext(new UpdateAdminName());

			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminId")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextLine();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminId());
			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminEmail")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextLine();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminEmail());
			context.performUpdate(idToUpdate, newData);
		}
		else if(columnToUpdate.equalsIgnoreCase("--update AdminContactNumber")) {
			System.out.print("Enter Id To Update: ");
			idToUpdate = scan.nextLine();
			System.out.print("Enter New data: ");
			newData = scan.nextLine();
			
			UpdateContext context = new UpdateContext(new UpdateAdminContactNumber());
			context.performUpdate(idToUpdate, newData);
		}
		// only allow teachers to update their password directly
		/*
		 * else if(columnToUpdate.equalsIgnoreCase("--update TeacherPassword")) {
		 * System.out.print("Enter Id To Update: "); idToUpdate = scan.nextLine();
		 * System.out.print("Enter New data: "); newData = scan.nextLine();
		 * 
		 * UpdateContext context = new UpdateContext(new UpdateAdminPassword());
		 * context.performUpdate(idToUpdate, newData); }
		 */
		else {
			System.out.println("Invalid Input");
		}
		
		}
    }
