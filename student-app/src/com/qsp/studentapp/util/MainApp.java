package com.qsp.studentapp.util;

import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.studentapp.dao.StudentDatabaseOperations;
import com.qsp.studentapp.dto.Student;

public class MainApp {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		StudentDatabaseOperations operation = new StudentDatabaseOperations();
		System.out.println("*****Welcome******");
		while (true) {
			System.out.println("---------------------------------");
			System.out.println("1.Registeration\n2.Login\n3.Update password\n4.Exit");
			System.out.println("---------------------------------");

			System.out.println("Enter your choice");
			int choice = scan.nextInt();

			switch (choice) {
			case 1:
				Student s = new Student();
				System.out.println("Enter the ID");
				int id = scan.nextInt();
				System.out.println("Enter the name");
				String name = scan.next();
				System.out.println("Enter your marks");
				double marks = scan.nextDouble();
				System.out.println("Enter the email ID");
				String emailID = scan.next();
				System.out.println("Enter the password");
				String password = scan.next();

				s.setId(id);
				s.setName(name);
				s.setMarks(marks);
				s.setEmailId(emailID);
				s.setPassword(password);

				boolean isInersted = operation.insertStudent(s);
				if (isInersted)
					System.out.println("Student registered....");
				else
					System.out.println("Student not registered");
				break;
			case 2:
				System.out.println("Enter the Email ID");
				String email = scan.next();
				System.out.println("Enter the Password");
				String pswd = scan.next();

				Student logedInStudent = operation.loginValidate(email, pswd);
				if (logedInStudent == null) {
					System.out.println("Invalid credentials. Please check Email/Password!!! ");
				} else {
					System.out.println("Welcome " + logedInStudent.getName() + "!!!");

					while (true) {
						System.out.println("\n************************************************");
						System.out.println("1.Display all details\n2.Search by ID\n3.Update\n4.Delete");
						System.out.println("5.Display based on Marks\n6.Logout");
						System.out.println("************************************************");

						System.out.println("Enter your choice");
						int subChoice = scan.nextInt();

						if (subChoice == 1) {
							ArrayList<Student> allStudents = operation.getAllStudents();
							System.out.println("ID\t\tName\t\tMarks\t\tEmail");
							for (Student s1 : allStudents) {
								System.out.println(s1.getId() + "\t\t" + s1.getName() + "\t\t" + s1.getMarks() + "\t\t"
										+ s1.getEmailId());
							}

						} else if (subChoice == 2) {
							//search student
							System.out.println("Enter the ID ");
							int sid = scan.nextInt();
							
							Student searchStudents=operation.searchStudent(sid);
							
							if(searchStudents==null)
								System.out.println("No records found for the ID "+sid);
							else {
								System.out.println("ID\t\tName\t\tMarks\t\tEmail");
							System.out.println(searchStudents.getId() + "\t\t" + searchStudents.getName() + "\t\t" + searchStudents.getMarks() + "\t\t"
									+ searchStudents.getEmailId());
							}
							
						} else if (subChoice == 3) {
							//Update student
							System.out.println("Enter student ID:");
							int sid=scan.nextInt();
							Student stu=operation.searchStudent(sid);
							if(stu==null)
								System.out.println("Data cannot be be updated  bacause data not found for id "+sid);
							else {
								//update 
							
								int hashCodeBeforeChange=stu.hashCode();
								System.out.println("**********************");
								System.out.println("a.Name\nb.Marks\nc.Email ID");
								System.out.println("**********************");
								System.out.println("Enter your choice");
								char updateChoice=scan.next().charAt(0);
							
								if(updateChoice=='a') {
									System.out.println("Enter the Name to be updated");
									String updatedName=scan.next();
									stu.setName(updatedName);
								}
								else if(updateChoice=='b') {
									System.out.println("Enter the Marks to be updated");
									double updatedMarks=scan.nextDouble();
									stu.setMarks(updatedMarks);
								}
								else if(updateChoice=='c') {
									System.out.println("Enter the Email ID to be updated");
									String updatedEmail=scan.next();
									stu.setEmailId(updatedEmail);
								}
								else
									System.out.println("Invalid update choice!!!");
								
								int hashCodeAfterChange=stu.hashCode();
								
								if(hashCodeBeforeChange!=hashCodeAfterChange) {
									boolean isUpdated=operation.updateStudentdetails(stu);
									if(isUpdated)
										System.out.println("Data updated!!!");
									else
										System.out.println("Data not updated...");
								}
							}					
							
						} else if (subChoice == 4) {
							System.out.println("Enter the ID:");
							int deleteId=scan.nextInt();
							
							Student deleteStudent=operation.searchStudent(deleteId);
							if(deleteStudent == null)
								System.out.println("Data not deleted because ID not found "+deleteId);
							else {
								boolean isDeleted=operation.deleteStudent(deleteId);
								if(isDeleted)
									System.out.println("Data is deleted...");
								else 
									System.out.println("Data is not deleted...");
							}
						} else if (subChoice == 5) {
							//Display based on Marks

							System.out.println("Enter the lower mark: ");
							int lower=scan.nextInt();
							System.out.println("Enter the higher mark: ");
							int higher=scan.nextInt();
							
							ArrayList<Student> displayStudentMarks=operation.getDisplayBasedonMarks(lower,higher);
									
							if(displayStudentMarks.isEmpty())
								System.out.println("No students found between "+lower+" and "+higher+" range");
							else {
								System.out.println("ID\t\tName\t\tMarks\t\tEmail");
								for (Student s1 : displayStudentMarks) 
									System.out.println(s1.getId() + "\t\t" + s1.getName() + "\t\t" + s1.getMarks() + "\t\t"
								+ s1.getEmailId());
							}
						} else if (subChoice == 6) {
							System.out
									.println("Logged out Successfully!!!!!!!!\nThank you " + logedInStudent.getName());
							break;
						} else
							System.out.println("Invalid choice....");
					}

				}

				break;
			case 3:
				System.out.println("Enter the mail ID:");
				String email_id=scan.next();
				System.out.println("Enter the new password");
				String newPassword=scan.next();
				System.out.println("Enter the Retype password");
				String retypePassword=scan.next();
				
				while(! newPassword.equals(retypePassword)) {
					System.out.println("new password and retype password is not matching");
					System.out.println("Enter the new password");
					newPassword=scan.next();
					System.out.println("Enter the Retype password");
					retypePassword=scan.next();
				}
				
				boolean isupdatd =operation.updatePassword(newPassword,email_id);
				if(isupdatd)
					System.out.println("Password is updated");
				else
					System.out.println("Some thing went wrong while updating the password");
				break;
			case 4:
				System.out.println("****Thank you****");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}

		}

	}
}
