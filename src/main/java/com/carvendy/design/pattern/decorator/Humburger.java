package com.carvendy.design.pattern.decorator;

public abstract class Humburger {
	
	protected  String name ;
	
	public String getName(){
		return name;
	}
	
	public abstract double getPrice();

}
