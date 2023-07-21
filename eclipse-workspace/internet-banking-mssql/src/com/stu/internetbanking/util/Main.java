package com.stu.internetbanking.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.stu.internetbanking.dao.DatabaseOperations;
import com.stu.internetbanking.dto.Bank;
import com.stu.internetbanking.dto.BankPassbook;
import com.stu.internetbanking.dto.Bank_transfer;

public class Main {
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		DatabaseOperations operation = new DatabaseOperations();
		boolean passbook = false;

		System.out.println("Welcome!!!");
		while (true) {
			System.out.println("\n*******************************");
			System.out.println("1.Registration\n2.Login\n3.Forgot password\n4.Exit");
			System.out.println("*******************************");
			int choice = s.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter the username:");
				String username = s.next();
				System.out.println("Enter the password:");
				String password = s.next();
				System.out.println("Enter the Re-password:");
				String re_password = s.next();

				while (!password.equals(re_password)) {
					System.out.println("Password and re-typed password is not matched..\n");
					System.out.println("Enter the password:");
					password = s.next();
					System.out.println("Enter the Re-password:");
					re_password = s.next();
				}

				System.out.println("Enter the amount:");
				double amount = s.nextDouble();
				System.out.println("Enter the email:");
				String bank_email = s.next();
				System.out.println("Enter the phone:");
				long phone = s.nextLong();

				Bank bank = new Bank();
				bank.setUsername(username);
				bank.setPassword(password);
				bank.setAmount(amount);
				bank.setEmail(bank_email);
				bank.setPhone(phone);

				boolean isregistered = operation.insertaccountdetails(bank);
				if (isregistered) {
					BankPassbook obj = new BankPassbook();
					System.out.println("\nRegistered the account. \nYour account number is " + bank.getAc_number());
					obj.setAc_number(bank.getAc_number());
					obj.setType("Account opened");
					obj.setAmount(0.00);
					obj.setAvail_bal(bank.getAmount());
					passbook = operation.transactionHistory(obj);
					if (passbook)
						System.out.println("Passbook entry successfull...");

				} else
					System.out.println("Not registed the account...");

				break;
			case 2:
				System.out.println("Enter the account number");
				int ac_no = s.nextInt();
				System.out.println("Enter the password");
				String pass = s.next();

				Bank login = operation.validateLogin(ac_no, pass);
				if (login == null) {
					System.out.println("Invalid credentials. Please check account/Password!!! ");
				} else {
					System.out.println("\nWelcome " + login.getUsername() + "!!!");
					while (true) {
						System.out.println("****************************");
						System.out.println("\n1.Check balance\n2.Deposit\n3.Withdraw\n4.Money Transfer");
						System.out.println("5.Transaction History\n6.Update account details");
						System.out.println("7.Can close the account\n8.Logout");
						System.out.println("****************************");

						System.out.println("Enter the choice:");
						int subChoice = s.nextInt();

						if (subChoice == 1) {
							// Check Balance
							SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
							Date d = new Date();
							String date = formatter.format(d);

							String header = "Account Number\tUser Name\tAvail_Balance"
									+ "\tEmail\t\tPhone\t\tCreated_date\t\tLast_Updated_date";
							String detail = login.getAc_number() + "\t\t" + login.getUsername() + "\t\t"
									+ login.getAmount() + "\t\t" + login.getEmail() + "\t\t" + login.getPhone() + "\t"
									+ login.getCreated_date() + "\t\t" + login.getUpdated_date();
							System.out.println(header);
							System.out.println(detail);
							File file = new File("C:\\AMD\\test" + date + ".txt");
							try {
								FileWriter fw = new FileWriter(file);
								fw.write(header + "\n" + detail);
								fw.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("Success...");

						} else if (subChoice == 2) {
							// Deposit
							BankPassbook obj = new BankPassbook();
							System.out.println("Please enter the amount to deposit:");
							double d_amount = s.nextDouble();
							login = operation.validateLogin(ac_no, pass);
							boolean isdespoited = operation.deposit(login.getAc_number(), login.getAmount(), d_amount);
							if (isdespoited) {
								System.out.println("Amount has been deposied....");
								login = operation.validateLogin(ac_no, pass);

								obj.setAc_number(ac_no);
								obj.setType("Deposit");
								obj.setAmount(d_amount);
								obj.setAvail_bal(login.getAmount());

								passbook = operation.transactionHistory(obj);
								if (passbook)
									System.out.println("Your available balance is " + login.getAmount()
											+ " Passbook entry is successful!!");
								else
									System.out.println("Your available balance is " + login.getAmount());
							} else
								System.out.println("Deposit failed...please try again..");

						} else if (subChoice == 3) {
							// Withdraw

							BankPassbook obj = new BankPassbook();
							System.out.println("Please enter the amount to withdraw:");
							double w_amount = s.nextDouble();
							login = operation.validateLogin(ac_no, pass);

							if (login.getAmount() < w_amount) {
								System.out.println("Your entered amount greater than available balance..");

							} else if (login.getAmount() >= w_amount) {
								login = operation.validateLogin(ac_no, pass);
								boolean withdraw = operation.withdraw(login.getAc_number(), login.getAmount(),
										w_amount);
								if (withdraw) {

									System.out.println("Your money has been withdrawn successfully....");
									login = operation.validateLogin(ac_no, pass);
									obj.setAc_number(ac_no);
									obj.setType("Withdrawn");
									obj.setAmount(w_amount);
									obj.setAvail_bal(login.getAmount());

									passbook = operation.transactionHistory(obj);

									if (passbook)
										System.out.println("Your available balance is " + login.getAmount()
												+ " Passbook entry is successful!!");
									else
										System.out.println("Your available balance is " + login.getAmount());
								} else
									System.out.println("Amount withdrawn is failed.. Please try again");
							}

						} else if (subChoice == 4) {
							// Money Transfer
							BankPassbook obj = new BankPassbook();
							System.out.println("Please enter the amount to transfer:");
							double t_amount = s.nextDouble();
							login = operation.validateLogin(ac_no, pass);

							while (login.getAmount() < t_amount) {
								System.out.println(
										"Insufficient balance..Available balance is " + login.getAmount() + "\n");
								System.out.println("Please enter the amount to transfer:");
								t_amount = s.nextDouble();
							}
							System.out.println("Please enter the account number:");
							int tranfer_account = s.nextInt();
							System.out.println("Please enter the IFSC number:");
							String ifsc = s.next();

							Bank_transfer sent = new Bank_transfer();

							if (login.getAmount() >= t_amount) {
								login = operation.validateLogin(ac_no, pass);
								boolean transfer = operation.withdraw(login.getAc_number(), login.getAmount(),
										t_amount);
								// Sent_record
								sent.setAc_number(login.getAc_number());
								sent.setTransfer_acno(tranfer_account);
								sent.setIfsc(ifsc);
								sent.setAmount(t_amount);

								if (transfer) {
									sent.setStatus("Sucess");
									System.out.println("Your money has been transferred successfully....");
									// passbook entry
									login = operation.validateLogin(ac_no, pass);
									obj.setAc_number(ac_no);
									obj.setType("Transfer");
									obj.setAmount(t_amount);
									obj.setAvail_bal(login.getAmount());

									passbook = operation.transactionHistory(obj);

									if (passbook)
										System.out.println("Your available balance is " + login.getAmount()
												+ " Passbook entry is successful!!");
									else
										System.out.println("Your available balance is " + login.getAmount());
								} else {
									sent.setStatus("Failed");
									System.out.println("Amount transferred is failed.. Please try again");
								}
								operation.tranferentry(sent);
							}

						} else if (subChoice == 5) {
							// Transaction History
							SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
							Date d = new Date();
							String date = formatter.format(d);
							login = operation.validateLogin(ac_no, pass);
							System.out.println("passbook is generating....");
							ArrayList<BankPassbook> al = operation.getPassbook(login.getAc_number());

							if (al.isEmpty()) {
								System.out.println("No records found");
							} else {
								File file = new File("C:\\AMD\\Passbook" + date + ".txt");
								String header = "SNo\tAC_Number\ttrasaction_date\t\ttype\t\tAmount\t\tavailable_bal";
								try {
									FileWriter fw = new FileWriter(file);
									fw.write(header);
									for (BankPassbook b : al) {
										String detail = b.getSno() + "\t" + b.getAc_number() + "\t\t" + b.getT_date()
												+ "\t" + b.getType() + "\t\t" + b.getAmount() + "\t\t"
												+ b.getAvail_bal();
										fw.write("\n" + detail);
									}
									fw.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								System.out.println("Success...");
							}

						} else if (subChoice == 6) {
							// Update account details
							System.out.println("Please select which data you want to edit");
							System.out.println("1.Username\n2.Email\n3.phone");
							login = operation.validateLogin(ac_no, pass);
							int hashCodeBeforeChange = login.hashCode();
							int editchoice = s.nextInt();
							switch (editchoice) {
							case 1:
								System.out.println("Enter the username:");
								String user_name = s.next();
								login.setUsername(user_name);
								break;
							case 2:
								System.out.println("Enter the Email:-");
								String e_email = s.next();
								login.setEmail(e_email);
								break;
							case 3:
								System.out.println("Enter the phone:");
								long e_phone = s.nextLong();
								login.setPhone(e_phone);
								break;

							default:
								System.out.println("Enter the valid input:");
								break;
							}

							int hashCodeAfterChange = login.hashCode();
							if (hashCodeBeforeChange != hashCodeAfterChange) {
								boolean isUpdated = operation.updateAccountDetails(login);
								if (isUpdated) {
									System.out.println("Details updated!!!");
								} else
									System.out.println("Details not updated!!!");
							}

						} else if (subChoice == 7) {
							// Can close the account
							BankPassbook obj = new BankPassbook();
							System.out.println("Please confirm to close the account permantly(Yes/no)");
							String confirm_close = s.next();
							if (confirm_close.equalsIgnoreCase("Yes")) {
								login = operation.validateLogin(ac_no, pass);
								obj.setAc_number(ac_no);
								obj.setType("Account closed");
								obj.setAmount(0.00);
								obj.setAvail_bal(login.getAmount());
								boolean isDeleted = operation.closeAccount(login.getAc_number());
								if (isDeleted) {
									passbook = operation.transactionHistory(obj);
									if (passbook) {
										System.out.println("Successfully account closed!!! with passbook entry");
									} else
										System.out.println("Successfully account closed!!!");
								} else
									System.out.println("Account not close...");
							} else if (confirm_close.equalsIgnoreCase("No")) {
								System.out.println("account closing is failed!!!");
							} else {
								System.out.println("Invalid choice");
							}

						} else if (subChoice == 8) {
							System.out.println("Logged out successfully...\nThank you " + login.getUsername());
							break;

						} else {
							System.out.println("Invalid choice...");
						}

					}
				}
				break;
			case 3:
				System.out.println("Please enter your account no:");
				int acc_no = s.nextInt();
				Bank getAllDetails = operation.getAccountDetails(acc_no);
				if (getAllDetails == null) {
					System.out.println("Account number is not found " + acc_no);
					break;
				}
				String email = getAllDetails.getEmail();
				operation.forgotpassword(email, acc_no);
				System.out.println("Enter the OTP recieved in email");
				int email_otp = s.nextInt();
				getAllDetails = operation.getAccountDetails(acc_no);
				if (email_otp == getAllDetails.getOtp()) {
					System.out.println("Enter the new password:");
					String u_password = s.next();
					boolean isUpdatedPassword = operation.updatepassword(acc_no, u_password);
					if (isUpdatedPassword)
						System.out.println("Password changed is successfully...");
					else
						System.out.println("Password is not changed...");
				} else
					System.out.println("OTP is incorrect. Password not changed...");

				break;
			case 4:
				System.out.println("Thank you....");
				System.exit(0);
				break;

			default:
				System.out.println("Entered invalid choice");
				break;
			}

		}
	}

}
