package com.project.onlinevoting;

public class User {

	private String studentId;
	private String name;
	private String password;

	// Constructor
	public User(String studentId, String name, String password) {
		this.studentId = studentId;
		this.name = name;
		this.password = password;
	}

	// Getters
	public String getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
