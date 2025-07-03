package com.shiro;


import javax.mail.Authenticator;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

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
		final String toEmail = Email;

		Random rand = new Random();
		int randomCode = rand.nextInt(1000,9999);

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
			message.setSubject("Password Reset");
			message.setText("Code: "+ randomCode);
			Transport.send(message);
			System.out.println("Password Reset sent successfully!");


			boolean flag = true;
			int tries = 0;
			while(flag == true) {
				String InputCode = scan.nextLine();
				if(Integer.parseInt(InputCode) == randomCode) {

					flag =false;
					return 1;
				}
				else if(tries == 5) {
					System.out.println("Tries limit reached");
					System.out.println("Terminating process");
					return 0;
				}

				else {
					System.out.println("Incorrect Code Try again");
					tries += 1;
				}
			}

		} catch (MessagingException e) {
			System.err.println("Failed to send email: " + e.getMessage());
			throw e; // Rethrow exception for higher-level handling if necessary
		}
		return 0;
	}
}


