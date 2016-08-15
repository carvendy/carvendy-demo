package com.carvendy.design.pattern.flyweight.my;

public class TestFruit {

	public static void main(String[] args) {
		Fruit apple = Fruit.getFruit("apple");
		Fruit apple2 = Fruit.getFruit("apple");
		Fruit banana = Fruit.getFruit("banana");
		
		equals(apple, apple2);
		equals(apple, banana);
		
	}

	private static void equals(Fruit fruit, Fruit fruit2) {
		if(fruit==fruit2){
			System.out.println("同一个对象");
		}else{
			System.out.println("不同对象");
		}
	}
}
