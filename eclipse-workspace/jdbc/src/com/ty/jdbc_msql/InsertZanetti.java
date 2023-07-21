package com.ty.jdbc_msql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertZanetti {
	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			System.out.println("Drivers Registered..");
			//String dburl="jdbc:sqlserver://76.169.92.109:2433;encrypt=true;databaseName=wf50;user=sa;password=15848;trustServerCertificate=true";
			String dburl="jdbc:sqlserver://76.169.92.109:2433;databaseName=wf50;user=sa;password=15848;trustServerCertificate=true";
			Connection con=DriverManager.getConnection(dburl);
			System.out.println("Connection established...");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
