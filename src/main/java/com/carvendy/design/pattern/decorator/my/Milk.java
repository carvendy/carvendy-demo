package com.carvendy.design.pattern.decorator.my;

public class Milk extends Condiment{

	public Milk(Drink drink){
		this.drink=drink;
	}
	
	@Override
	public String getName() {
		return drink.getName()+"+一份牛奶";
	}

	public double getPrice(){
		return drink.getPrice()+0.5;
	}
}
