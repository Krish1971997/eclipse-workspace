package com.ty.jdbc_thirdstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DynamicandOtherthanselect {

	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl="jdbc:mysql://localhost:3306/java?user=root&password=root";
			System.out.println("Connection successfully...");
			Connection con=DriverManager.getConnection(dburl);
			//String query="insert into student values(?,?,?,?,?)";
			String query="insert into Student values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			
			System.out.println("Enter the employees:");
			int employees=s.nextInt();
			for (int i = 0; i < employees; i++) {
			
				System.out.println("Enter the sid:");
				int id=s.nextInt();
				System.out.println("Enter the Name:");
				String name=s.next();
				System.out.println("Enter the marks:");
				double marks=s.nextDouble();
				System.out.println("Enter the email:");
				String email=s.next(); 
				System.out.println("Enter the password:");
				String password=s.next();
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setDouble(3, marks);
				ps.setString(4, email);
				ps.setString(5, password);
				int result=ps.executeUpdate();
				System.out.println(result+" rows affected..");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception");
			e.getMessage();
		}
	}

}
