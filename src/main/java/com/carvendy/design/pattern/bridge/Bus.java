package com.carvendy.design.pattern.bridge;

public class Bus extends AbstractCar {
	@Override
	void run() {
		super.run();
		System.out.print("公交车");
	}
}
