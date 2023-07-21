package com.ty.jdbc_secondstep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnectionThirdway {
	public static void main(String[] args) {
		File f=new File("./files/data.properties");
		try {
			FileReader reader= new FileReader(f);
			
			Properties prop=new Properties();
			prop.load(reader);
			
			String dburl=prop.getProperty("dbUrl");
			String driverClass=prop.getProperty("driverClass");
			
			Class.forName(driverClass);
			Connection con=DriverManager.getConnection(dburl, prop);
					System.out.println("Connection successfully");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
