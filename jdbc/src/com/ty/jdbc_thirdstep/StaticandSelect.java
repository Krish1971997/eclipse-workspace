package com.ty.jdbc_thirdstep;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Properties;

public class StaticandSelect {

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
			String query="select *from student";
			Statement stat=con.createStatement();
			ResultSet rs=stat.executeQuery(query);
			
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String email=rs.getString(3);
				Date date=rs.getDate(4);
				System.out.println(id+"\t"+name+"\t"+email+"\t"+date);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}