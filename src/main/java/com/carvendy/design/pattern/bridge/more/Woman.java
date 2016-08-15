package com.carvendy.design.pattern.bridge.more;

public class Woman extends People {
	@Override
	void run() {
		super.run();
		System.out.print("女人开着");
		road.run();
	}
}
