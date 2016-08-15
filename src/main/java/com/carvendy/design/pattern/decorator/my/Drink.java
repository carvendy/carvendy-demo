package com.carvendy.design.pattern.decorator.my;

public abstract class Drink {

	Drink drink;
	
	public abstract String getName();
	public double getPrice(){
		return drink.getPrice();
	}
}
