package com.shiro;

public class Student {
	private long StudentId;
	private String StudentName;
	private String StudentEmail;
	private String StudentContactNumber;
	private String StudentYearAndSection;
	
	public Student(long studentId, String studentName, String studentEmail, String studentContactNumber,String StudentYearAndSection) {
		this.StudentId = studentId;
		this.StudentName = studentName;
		this.StudentEmail = studentEmail;
		this.StudentContactNumber = studentContactNumber;
		this.StudentYearAndSection = StudentYearAndSection;
	}
	public Student() {
		
	}

	
	
	public long getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getStudentEmail() {
		return StudentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		StudentEmail = studentEmail;
	}
	public String getStudentContactNumber() {
		return StudentContactNumber;
	}
	public void setStudentContactNumber(String studentContactNumber) {
		StudentContactNumber = studentContactNumber;
	}
	public String getStudentYrSection() {
		return StudentYearAndSection;
	}
	public void setStudentYrSection(String studentYrSection) {
		StudentYearAndSection = studentYrSection;
	}
	
	
	//getters setters for abstraction


}
