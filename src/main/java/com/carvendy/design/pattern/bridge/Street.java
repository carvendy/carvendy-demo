package com.carvendy.design.pattern.bridge;

public class Street extends AbstractRoad {
	@Override
	public void run() {
		super.run();
		aCar.run();
		System.out.println("在市区街道行驶");
	}
}
