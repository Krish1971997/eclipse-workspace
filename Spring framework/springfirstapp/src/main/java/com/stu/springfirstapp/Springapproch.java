package com.stu.springfirstapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Springapproch {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-config.xml");
		
		Test t1= (Test) context.getBean("t");
		t1.m1();
		
	}

}
