package com.ty.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Register_drivers_secondway {

	public static void main(String[] args) {
		
		String url="jdbc:mysql://localhost:3306/java?user=root&password=root";
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers Registerred!!");
			Connection c=DriverManager.getConnection(url);
			System.out.println("Connection is successful!!....");
		} catch (ClassNotFoundException e) {
			System.out.println("Class name is wrong");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection is Failed....");
			e.printStackTrace();
		}
		
	}
}
