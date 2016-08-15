package com.carvendy.design.pattern.decorator.my;

public class Tea extends Drink{

	public String getName(){
		return "【茶】";
	}
	
	public double getPrice(){
		return 10;
	}
}
