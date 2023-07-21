package com.ty.jdbc_secondstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class GetConnectionFirstway {
	public static void main(String[] args) {
		try {
			java.sql.Driver d=new Driver();
			DriverManager.registerDriver(d);
			System.out.println("Drivers Registered....");
			String dburl="jdbc:mysql://localhost:3306/java?user=root&password=root";
			Connection con=DriverManager.getConnection(dburl);
			if(con!=null)
			System.out.println("Connection is established");
		} catch (SQLException e) {
			System.out.println("Drivers not Registered");
			e.printStackTrace();
		}
	}
}
