package com.carvendy.design.pattern.decorator.my;

public class Coffee extends Drink{

	public String getName(){
		return "【咖啡】";
	}
	
	public double getPrice(){
		return 15;
	}
}
