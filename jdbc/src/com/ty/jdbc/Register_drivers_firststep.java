package com.ty.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class Register_drivers_firststep {
	public static void main(String[] args) {
		try {
			java.sql.Driver d=new Driver();
			DriverManager.registerDriver(d);
			System.out.println("Driver Registered..");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}