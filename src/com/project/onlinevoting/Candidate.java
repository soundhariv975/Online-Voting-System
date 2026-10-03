package com.project.onlinevoting;

public class Candidate {

	private int candidateId;
	private String candidateName;
	private String department;

	// Constructor
	public Candidate(int candidateId, String candidateName, String department) {
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.department = department;
	}

	// Getters
	public int getCandidateId() {
		return candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public String getDepartment() {
		return department;
	}

	// Display candidate details
	public void displayCandidate() {
		System.out.println(candidateId + " - " + candidateName
				+ " (" + department + ")");
	}
}