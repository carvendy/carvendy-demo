package com.carvendy.design.pattern.decorator;

public class Test {

	
	public static void main(String[] args) {
		Humburger humburger = new ChickenBurger();
		System.out.println(humburger.getName()+"  价钱："+humburger.getPrice());
		
		Lettuce lettuce = new Lettuce(humburger);
		System.out.println(lettuce.getName()+"  价钱："+lettuce.getPrice());
		
		Chilli chilli = new Chilli(humburger);
		System.out.println(chilli.getName()+"  价钱："+chilli.getPrice());
		
		Chilli chilli2 = new Chilli(lettuce);
		System.out.println(chilli2.getName()+"  价钱："+chilli2.getPrice());
	}

}
