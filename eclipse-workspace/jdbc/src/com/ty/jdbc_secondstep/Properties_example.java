package com.ty.jdbc_secondstep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Properties_example {
	public static void main(String[] args) {
		File f= new File("./files/property.properties");
		try {
			FileReader reader= new FileReader(f);
			
			Properties prop=new Properties();
			prop.load(reader);
			System.out.println("File found");
			
			String name=prop.getProperty("name");
			System.out.println(name);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
