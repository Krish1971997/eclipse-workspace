package com.ty.jdbc_thirdstep;

	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Properties;

	public class Selectandotherthanselect {
		public static void main(String[] args) {
			File file =new File("./files/data.txt");
			try {
				FileReader reader=new FileReader(file);
				
				Properties prop=new Properties();
				prop.load(reader);
				
				String dburl=prop.getProperty("dburl");
				String driverClass=prop.getProperty("driverClass");
				Class.forName(driverClass);
				System.out.println("Driver Registered");
				Connection con=DriverManager.getConnection(dburl, prop);
				if(con!=null)
				System.out.println("Connection succesfully");
				
				Statement stat=con.createStatement();
				String query="update student set name ='support' where sid=1";
				//String query="insert into student values(1,'krishna','krishna@gmail.com')";
				int result=stat.executeUpdate(query);
				System.out.println(result +" rows affected");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Connection failed");
				e.printStackTrace();
			}
		}

	}
