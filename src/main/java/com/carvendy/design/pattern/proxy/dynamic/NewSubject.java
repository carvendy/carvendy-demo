package com.carvendy.design.pattern.proxy.dynamic;

public class NewSubject implements Subject{

	@Override
	public void request() {
		System.out.println("From new subject");
	}

	
}
