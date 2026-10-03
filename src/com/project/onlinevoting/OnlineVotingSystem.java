package com.project.onlinevoting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnlineVotingSystem {

	// Store registered users
	private Map<String, User> users;

	// Track whether each student has voted
	private Map<String, Boolean> userVotes;

	// Store candidates
	private ArrayList<Candidate> candidates;

	// Store candidate vote counts
	private Map<String, Integer> candidateVotes;

	// Constructor
	public OnlineVotingSystem() {

		users = new HashMap<>();
		userVotes = new HashMap<>();
		candidates = new ArrayList<>();
		candidateVotes = new HashMap<>();

		// Create candidates
		Candidate candidate1 = new Candidate(
				1, "Arun", "Computer Science");

		Candidate candidate2 = new Candidate(
				2, "Priya", "Information Technology");

		Candidate candidate3 = new Candidate(
				3, "Rahul", "Electronics");

		// Add candidates to ArrayList
		candidates.add(candidate1);
		candidates.add(candidate2);
		candidates.add(candidate3);

		// Initialize vote count for candidates
		candidateVotes.put(candidate1.getCandidateName(), 0);
		candidateVotes.put(candidate2.getCandidateName(), 0);
		candidateVotes.put(candidate3.getCandidateName(), 0);
	}

	// Register user
	public void registerUser(String studentId, String name, String password) {

		if (users.containsKey(studentId)) {

			throw new IllegalArgumentException(
					"Student ID is already registered.");
		}

		User user = new User(studentId, name, password);

		// Store user
		users.put(studentId, user);

		// Initially student has not voted
		userVotes.put(studentId, false);

		System.out.println("Registration successful.");
	}

	// Login user
	public User loginUser(String studentId, String password) {

		if (!users.containsKey(studentId)) {

			throw new IllegalArgumentException(
					"Student ID is not registered.");
		}

		User user = users.get(studentId);

		if (!user.getPassword().equals(password)) {

			throw new IllegalArgumentException(
					"Incorrect password.");
		}

		return user;
	}

	// Display candidate list
	public void displayCandidates() {

		System.out.println("\n----- CANDIDATE LIST -----");

		for (Candidate candidate : candidates) {

			candidate.displayCandidate();
		}
	}

	// Cast vote
	public void castVote(User user, int candidateId) {

		String studentId = user.getStudentId();

		// Check whether student has already voted
		if (userVotes.get(studentId)) {

			throw new IllegalStateException(
					"You have already voted. Each student can vote only once.");
		}

		Candidate selectedCandidate = null;

		// Find candidate
		for (Candidate candidate : candidates) {

			if (candidate.getCandidateId() == candidateId) {

				selectedCandidate = candidate;
				break;
			}
		}

		// Invalid candidate
		if (selectedCandidate == null) {

			throw new IllegalArgumentException(
					"Invalid candidate ID.");
		}

		String candidateName =
				selectedCandidate.getCandidateName();

		// Get current votes
		int currentVotes =
				candidateVotes.get(candidateName);

		// Increase candidate vote count
		candidateVotes.put(
				candidateName,
				currentVotes + 1);

		// Mark student as voted
		userVotes.put(studentId, true);

		System.out.println(
				"Vote successfully cast for "
				+ candidateName + ".");
	}

	// Display total votes and results
	public void displayResults() {

		System.out.println(
				"\n----- VOTING RESULTS -----");

		int totalVotes = 0;

		// Display votes of each candidate
		for (Candidate candidate : candidates) {

			String candidateName =
					candidate.getCandidateName();

			int votes =
					candidateVotes.get(candidateName);

			System.out.println(
					candidateName + " : "
					+ votes + " votes");

			totalVotes = totalVotes + votes;
		}

		System.out.println(
				"------------------------------------");

		System.out.println(
				"Total Votes : " + totalVotes);

		// Display finalist and runner-up
		findFinalistAndRunnerUp();
	}

	// Find finalist and runner-up automatically
	private void findFinalistAndRunnerUp() {

		int highestVotes = -1;
		int secondHighestVotes = -1;

		// Find highest vote count
		for (Candidate candidate : candidates) {

			int votes =
					candidateVotes.get(
							candidate.getCandidateName());

			if (votes > highestVotes) {

				highestVotes = votes;
			}
		}

		// Find second-highest vote count
		for (Candidate candidate : candidates) {

			int votes =
					candidateVotes.get(
							candidate.getCandidateName());

			if (votes < highestVotes
					&& votes > secondHighestVotes) {

				secondHighestVotes = votes;
			}
		}

		System.out.println(
				"\n----- FINAL RESULT -----");

		// Display finalist
		System.out.println("Finalist:");

		for (Candidate candidate : candidates) {

			int votes =
					candidateVotes.get(
							candidate.getCandidateName());

			if (votes == highestVotes) {

				System.out.println(
						candidate.getCandidateName()
						+ " - " + votes + " votes");
			}
		}

		// Display runner-up
		if (secondHighestVotes >= 0) {

			System.out.println("\nRunner-Up:");

			for (Candidate candidate : candidates) {

				int votes =
						candidateVotes.get(
								candidate.getCandidateName());

				if (votes == secondHighestVotes) {

					System.out.println(
							candidate.getCandidateName()
							+ " - " + votes + " votes");
				}
			}

		} else {

			System.out.println(
					"\nRunner-Up: Not available");
		}
	}
}