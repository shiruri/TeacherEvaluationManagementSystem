package com.shiro;


public class Main {
	


	public static void main(String[] args) throws Exception {
		RunnableMain m = new RunnableMain();
		m.start();
	}
}
// implement the freesqldatabase update the connection here on DatabaseConnection.java the configure google fporms make a new schema and udpate cascade on it and configure pabbly
// july 7 2025 AAAAAAAAAAAAAAAAAAAAA

// add student creation to fix pabbly insert into database 
// fix the -- view Teacher command wont execute probably from the scanner 

// jul 6 2025 changed the code to runnable main class instead on the main java to make the main method clean as possible
// added emailverification aswell
/*EMAIL VERIFICATIION LOGIC
 * will create random 6 digit code which will then be sent to email via smtp and some dependencies
 * max tries 3 if not correct code inputted then will stop
 * if correct will return integer 1 as flag for the whole program to continue or not
 * 
 */

// found a design pattern for crud but gonna let the design pattern strategy for the other crud for now but for future use composite for crud 

		// DatabaseManagement.connection(); // to access database connection
		// log in system and authorization first
		// add admin logic first

		// implement auth using java stmp or something java mail api
		// nvm that i forgot i dont have maven for this look for other libraries

		// assign admin roles using a new sepperate object roles with boolean values to
		// accss different parts of the code
		// log in feature

		// last feature was log in retrieve data from database and compare with the
		// input data if true
		// implement overall log in for Students and Admin
		// implement hashing of password using AES engcryption

		// LOGIC FOR LOG IN
		// new addition implement a hash feature for password using sha 256 or other
		// algprithm if possible and then
		// take the password
		// hashthe password
		// save the hash and salt to the database
		// when logging in hash the input password
		// retrieve both the actual password on the database
		// hash the password input
		// compare both

		// make teachers able to add students and delete and edit

		

		/*
		 * try { crypt.init(); String test = "pokemon2626"; String engcyptedText =
		 * crypt.engcypt(test); System.out.println(engcyptedText); String decryptedText
		 * = crypt.decrypt(engcyptedText); System.out.println(decryptedText);
		 * }catch(Exception e) { System.out.println(e); }
		 */
		// login feature done

		// follow the database and create new stuff
		// make students and teacher dashboard
		// do teacher first
		// Day 3 2025 JUNE 24





// this will prin the adminList from the database

// delete method for admin
