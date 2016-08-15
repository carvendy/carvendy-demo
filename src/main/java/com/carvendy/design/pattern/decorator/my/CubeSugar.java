package com.carvendy.design.pattern.decorator.my;

public class CubeSugar extends Condiment{

	public CubeSugar(Drink drink){
		this.drink=drink;
	}
	
	@Override
	public String getName() {
		return drink.getName()+"+一块方糖";
	}

}
