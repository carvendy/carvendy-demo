package com.carvendy.design.pattern.bridge;

public class Car extends AbstractCar {
	@Override
	void run() {
		super.run();
		System.out.print("小汽车");
	}
}
