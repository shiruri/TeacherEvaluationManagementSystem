package com.shiro;

import java.sql.SQLException;

public interface Task {
	/*
	 * C = add
	 * R = read
	 * U = update
	 * D = delete
	 */
		void addStudent(Student student); 
	    void displayStudent();
	    void updateStudent();
	    void removeStudent();
}
