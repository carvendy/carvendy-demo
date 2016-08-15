package com.carvendy.design.pattern.state.my;

public class One implements Number{

	public Number show(){
		System.out.println("1");
		return new Two();
	}
	
	
}
