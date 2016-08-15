package com.carvendy.jpa.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		//new StudentServiceImpl().insert("hailin0508-05", 321);
		
		ClassPathXmlApplicationContext ctx = 
				 new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml"); 
		
		System.out.println(ctx);
	}
}
