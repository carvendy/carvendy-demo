package com.carvendy.design.pattern.decorator;

/*
 * 配料
 */
public class Chilli extends Condiment {
	
	Humburger humburger;
	
	public Chilli(Humburger humburger){
		this.humburger = humburger;
		
	}

	@Override
	public String getName() {
		return humburger.getName()+" 加辣椒";
	}

	@Override
	public double getPrice() {
		return humburger.getPrice();  //辣椒是免费的哦
	}

}
