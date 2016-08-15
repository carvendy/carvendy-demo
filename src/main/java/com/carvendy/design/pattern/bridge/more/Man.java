package com.carvendy.design.pattern.bridge.more;

public class Man extends People {
	@Override
	void run() {
		super.run();
		System.out.print("男人开着");
		road.run();
	}
}
