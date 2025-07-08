package com.shiro;

import javax.mail.Authenticator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;

public class EmailVerification {
	static Scanner scan = new Scanner(System.in);

	public int EmailVerification(String Email) throws Exception {

		apiKey a = new apiKey();
		final String fromEmail = a.getAppEmail();
		final String password = a.getAppPasword();
		 String toEmail = Email;

		Random rand = new Random();
		int randomCode = rand.nextInt(999999);

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host","smtp.gmail.com.");  // host is gmail.com 
		prop.put("mail.smtp.port","587");  
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {
			// Construct and send email
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail)); // Set the sender's email
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			
			
			message.setSubject("School Password Reset");
			message.setText("School Password Reset Code");
			message.setText("Code: "+ randomCode);

			System.out.println("Sending Password Reset Request...");

			Transport.send(message);
			Thread.sleep(5000);
			System.out.println("Password Reset sent successfully!");


			int tries = 0;
			while(tries != 3) {
				System.out.print("Enter Reset Code: ");

				String InputCode = scan.nextLine();
				if(Integer.parseInt(InputCode) == randomCode) {
					return 1;
				}
				else {
					System.out.println("Incorrect Code Try again");
					tries += 1;
				}
			}
			System.out.println("Tries Reached");

		} catch (MessagingException e) {
			System.err.println("Failed to send email: " + e.getMessage());
			throw e; // Rethrow exception for higher-level handling if necessary
		}
		return 0;
	}

	
	public static void sendEvaluations() {
        PriorityQueue<String> emails = new PriorityQueue<String>();
        
		Calendar c = Calendar.getInstance();
		apiKey a = new apiKey();
		final String fromEmail = a.getAppEmail();
		final String password = a.getAppPasword();
		try {
			Connection con = con = DatabaseManagement.getCon();
			Statement stmt = con.createStatement();
			String sql = "SELECT StudentEmail from students;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				emails.add(rs.getString("StudentEmail"));
			}
		}catch(Exception e) {
			System.out.println("Error Occured");
		}
		

		Random rand = new Random();
		int randomCode = rand.nextInt(999999);

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host","smtp.gmail.com.");  // host is gmail.com 
		prop.put("mail.smtp.port","587");  
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {
			int emailCount = 0;
			if(emails.isEmpty()) {
				System.out.println("No Email Entries For Students Table");
			}
			else {
				System.out.println("Sending Emails Please Wait...");

				while(!emails.isEmpty()) {
					emailCount++;
					
					String currentQueue = emails.peek();
					String toEmail = currentQueue;

					// Construct and send email
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromEmail)); // Set the sender's email
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
					
					
					message.setSubject("School Teacher Evaluation");
				
					message.setText("Good Day! \nPlease Fill Up The Teacher Evaluation Form with comeplete Student LRN");
					message.setText("https://docs.google.com/forms/d/e/1FAIpQLSf0sWUptMbDrCNVkANJ3KsmdWMpIA6Fo5FJf1W6hBm72jX3PA/viewform?usp=header");
		
					message.setSentDate(c.getTime());
					

					Transport.send(message);
					
					emails.poll();
				}
				System.out.println("Emails sent successfully!");
				System.out.println("Number Of Sent Emails: " + emailCount);
			}
			
	
		

	}catch(Exception e) {
		System.out.println("Error Exception");
	}
	
}
}


