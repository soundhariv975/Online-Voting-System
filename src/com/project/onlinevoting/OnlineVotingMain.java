package com.project.onlinevoting;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineVotingMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		OnlineVotingSystem system =
				new OnlineVotingSystem();

		int choice = 0;

		do {

			System.out.println(
					"\n----ONLINE VOTING SYSTEM---- ");

			System.out.println("1. Register");
			System.out.println("2. Login and Vote");
			System.out.println("3. View Results");
			System.out.println("4. Exit");

			System.out.print("Enter your choice: ");

			try {

				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {

				case 1:

					// Registration
					System.out.print(
							"Enter Student ID: ");

					String studentId =
							sc.nextLine();

					System.out.print(
							"Enter Student Name: ");

					String name =
							sc.nextLine();

					System.out.print(
							"Create Password: ");

					String password =
							sc.nextLine();

					system.registerUser(
							studentId,
							name,
							password);

					break;

				case 2:

					// Login
					System.out.print(
							"Enter Student ID: ");

					String loginId =
							sc.nextLine();

					System.out.print(
							"Enter Password: ");

					String loginPassword =
							sc.nextLine();

					User user =
							system.loginUser(
									loginId,
									loginPassword);

					System.out.println(
							"\nWelcome, "
							+ user.getName() + "!");

					// Display candidates
					system.displayCandidates();

					System.out.print(
							"Enter Candidate ID to vote: ");

					int candidateId =
							sc.nextInt();

					sc.nextLine();

					// Cast vote
					system.castVote(
							user,
							candidateId);

					break;

				case 3:

					// View results
					system.displayResults();

					break;

				case 4:

					System.out.println(
							"\nThank you for using "
							+ "Online Voting System.");

					break;

				default:

					System.out.println(
							"Invalid choice. Please try again.");
				}

			} catch (InputMismatchException e) {

				System.out.println(
						"Invalid input. Please enter a number.");

				sc.nextLine();

			} catch (IllegalArgumentException e) {

				System.out.println(
						"Error: " + e.getMessage());

			} catch (IllegalStateException e) {

				System.out.println(
						"Error: " + e.getMessage());
			}

		} while (choice != 4);

		sc.close();
	}
}