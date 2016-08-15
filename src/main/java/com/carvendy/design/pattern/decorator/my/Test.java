package com.carvendy.design.pattern.decorator.my;

public class Test {

	/*
	 *    
	 * 
	 */
	public static void main(String[] args) {
		Drink coffee=new Coffee();
		
		coffee = new Milk(new Milk(coffee));
		coffee = new CubeSugar(coffee);
		System.out.println(coffee.getName()+"价格："+coffee.getPrice()+"元");
		
		Drink tea=new Tea();
		System.out.println(tea.getName()+"价格："+tea.getPrice()+"元");
	}
}