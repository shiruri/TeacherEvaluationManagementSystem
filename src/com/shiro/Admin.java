package com.shiro;

public class Admin {
	private String adminName;
	private int adminId = 1;
	private String adminEmail;
	private String adminPassword;
	private String adminContactNumber;
	private int CurrentId;
	
	public int getCurrentId() {
		return CurrentId;
	}
	public void setCurrentId(int currentId) {
		CurrentId = currentId;
	}
	public Admin(String adminName, int adminId, String adminEmail, String  adminContactNumber, String adminPassword ) {
		super();
		this.adminName = adminName;
		this.adminId = adminId;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminContactNumber = adminContactNumber;
	}
	public Admin() {
		
	}
	
	public String getAdminContactNumber() {
		return adminContactNumber;
	}
	public void setAdminContactNumber(String adminContactNumber) {
		this.adminContactNumber = adminContactNumber;
	}
	
	//getters setters for abstraction

	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
