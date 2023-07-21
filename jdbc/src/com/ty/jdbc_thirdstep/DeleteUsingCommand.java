package com.ty.jdbc_thirdstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUsingCommand {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL="jdbc:mysql://localhost:3306/java?user=root&password=root";
			Connection con=DriverManager.getConnection(dbURL);
			String query="Delete from student where name=?";
			PreparedStatement prep=con.prepareStatement(query);
			int rowsAffected=prep.executeUpdate();
			if(rowsAffected!=0)
				System.out.println("Records Deleted...");
			else
				System.out.println("Records not deleted...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
