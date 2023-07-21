package com.ty.setterinjectionforpdtwrapperstring.beanscom.ty.setterinjectionforpdtwrapperstring.driver;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.setterinjectionforpdtwrapperstring.beans.Test;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("setter-injection-config.xml");
		Test t=(Test) context.getBean("t");
		System.out.println(t);
	}
}
