package com.ty.jdbc_thirdstep;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Test {
	static Scanner s=new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		File f=new File("./files/data.txt");
		FileReader reader=new FileReader(f);
		
		Properties prop=new Properties();
		prop.load(reader);
		
		String dburl=prop.getProperty("dburl");
		String driverClass=prop.getProperty("driverClass");
		
		try {
			Class.forName(driverClass);
			Connection con=DriverManager.getConnection(dburl, prop);
			System.out.println("Connections successfully");
			//3.
			String query="insert into Student_table values(?,?,?,?)";
			PreparedStatement prep=con.prepareStatement(query);
			
			System.out.println("How records need to enter: ");
			int records=s.nextInt();
			for (int i = 0; i < records; i++) {
				System.out.println("Enter the ID: ");
				int id=s.nextInt();
				System.out.println("Enter the Name:");
				String name=s.next();
				System.out.println("Enter the email ID: ");
				String email=s.next();
				System.out.println("Enter the DOB");
				String date=s.next();
				
//				SimpleDateFormat sDate=new SimpleDateFormat("yyyy-mm-dd");
//				Date date2=null;
//				date2=sDate.parse(date);
				prep.setInt(1, id);
				prep.setString(2, name);
				prep.setString(3, email);
				//prep.setDate(4, (java.sql.Date) date2);
				prep.setString(4, date);
				int rowAffected=prep.executeUpdate();
				System.out.println(rowAffected+" Rows Inserted");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}

