package com.ty.jdbc_secondstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnectionSecondWay {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Drivers Registered");
			String dburl="jdbc:mysql://localhost:3306/java";
			String user="sppu";
			String password="sppu";
			
			String s1=decrypt(user);
			String s2=decrypt(password);
			
			Connection con=DriverManager.getConnection(dburl,s1, s2);
			System.out.println("Connection established");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found Exception");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}
	}

	private static String decrypt(String s) {
		String res="";
		for (int i = 0; i < s.length(); i++) {
			char ch=s.charAt(i);
			ch=(char) (ch-1);
			res=res+ch;
		}
		return res;
	}
}
