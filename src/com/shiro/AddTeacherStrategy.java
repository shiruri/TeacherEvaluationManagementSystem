package com.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Create
	public class AddTeacherStrategy implements CrudStrategyTeacher {
		static Connection con = null;
		static Statement stmt = null;
		static Hashpassword c= new Hashpassword();

	    @Override
	    public void process(Teacher t) throws SQLException, ClassNotFoundException {
	    	con = DatabaseManagement.getCon();
			stmt = con.createStatement();
			// how do you add more whats the syntax  Statement.RETURN_GENERATED_KEYS

		
			String addTeacher =  "insert into teachersinfo (TeacherId,TeacherName,TeacherEmail,TeacherContactNumber) values (?,?,?,?);"; 
			
		//	String addPass = "insert into teacherhash (TeacherId,TeacherPassword,TeacherSalt) values (?,?,?);";
			
			String addTeacherPostion = "insert into teacherposition (TeacherId,TeacherName,TeacherPosition) values (?,?,?);";
			
			// preparedStatement
			// for teachersinfo
			PreparedStatement prep = con.prepareStatement(addTeacher);
			prep.setInt(1, t.getSchoolId());
			prep.setString(2, t.getFullName());
			prep.setString(3, t.getTeacherEmail());
			prep.setString(4, t.getTeacherContactNumber());

			
			//byte[] currentsalt = c.getCurrentSalt();
			 // for teachershash
			//prep.setString(5, an.getAdminPassword());
		//	PreparedStatement hashTable = con.prepareStatement(addPass);
		//	hashTable.setInt(1, t.getSchoolId());
		//	hashTable.setString(2,t.getTeacherpassword());
		//	hashTable.setBytes(3, currentsalt);

			//for teachersFulltimePartime
			PreparedStatement role = con.prepareStatement(addTeacherPostion);
			role.setInt(1, t.getSchoolId());
			role.setString(2,t.getFullName());
			role.setString(3,t.getTeacherPosition());
			// fuckass this needs to be in order or will cause error foreign key update child AHHHHHH
			
			role.executeUpdate();
			prep.executeUpdate();
		//	hashTable.executeUpdate();


			
			
			System.out.println("Teacher Added");
	    }
	}


