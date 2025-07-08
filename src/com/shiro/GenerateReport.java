package com.shiro;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
public class GenerateReport {
	static Statement stmt = null;
	static Connection con = null;
	static Scanner scan = new Scanner(System.in);

	public static void generateInvoice() throws IOException {
		System.out.println("Generating Reports Please Wait....");
		System.out.println("This Process May Take A While");

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
		// couldve used a hashmap with String and teacherEval object but dont wanna deal with iterations
		String sql = "select TeacherPosition,TeacherName ,Mastery,Empathy,Fair,Engagement,Ontime from evaluations;";
		PriorityQueue<String> teacherNames = new PriorityQueue<String>();
		PriorityQueue<String> teacherPosition = new PriorityQueue<String>();
		PriorityQueue<Integer> Mastery = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Empathy = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Fair = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Engangement = new PriorityQueue<Integer>();
		PriorityQueue<Integer> Ontime = new PriorityQueue<Integer>();

		HashMap<String,ArrayList<String>> map = new HashMap<>();
		try {
			String TeacherName;
			String TeacherPosition;
			int mastery;
			int empathy;
			int fair;
			int engagement;
			int ontime;

			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				TeacherName = rs.getString("TeacherName");
				TeacherPosition = rs.getString("TeacherPosition");
				mastery = rs.getInt("Mastery");
				empathy = rs.getInt("Empathy");
				fair = rs.getInt("Fair");
				engagement = rs.getInt("Engagement");
				ontime = rs.getInt("Ontime");

				try {
					String FILEPATH =  "invoices//"+TeacherName + "Evaluation.txt";

					String fileFormat = "####################################\n"
							+ "Teacher Evaluation Invoice          \n"
							+ "Teacher: "+ TeacherName+"   \n"
							+ "Teacher Position: "+TeacherPosition+" \n"
							+ "Teacher Evaluation Scores           \n"
							+ "Mastery: "+mastery+" \n"
							+ "Empathy: "+empathy+" \n"
							+ "Fair: "+fair+" \n"
							+ "Engangement: "+engagement+" \n"
							+ "Ontime: "+ontime+" \n"
							+ "####################################";
					FileWriter file = new FileWriter(FILEPATH,true);
					file.write(fileFormat);
					file.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error" + e);
				}

			}

			String YnN = "";
			try {
				System.out.println("Open Folder? (Y/N)");
				YnN = scan.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid Input");
			}

			if(YnN.equalsIgnoreCase("Y")) {
				System.out.println("Opening Folder");

				Desktop desktop = Desktop.getDesktop();
				File dirToOpen = null;
				try {
					dirToOpen = new File("invoices//");
					desktop.open(dirToOpen);
					System.out.println("Folder Opened");
				} catch (IllegalArgumentException iae) {
					System.out.println("File Not Found");
				}
			}
			else {
				System.out.println("Returning..");
			}
		}catch(Exception e) {
			System.out.println("Error" + e);
		}
		
	}
}


