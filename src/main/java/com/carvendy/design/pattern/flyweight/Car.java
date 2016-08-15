package com.carvendy.design.pattern.flyweight;

import java.util.Hashtable;

public interface Car {
	public void showCarName();
}

class BMWCar implements Car {
	public void showCarName() {
		System.out.println("this is the BMWCar .");
	}
}

class FordCar implements Car {
	public void showCarName() {
		System.out.println("this is the FordCar .");
	}
}

class CarFactory {
	public static Car car;

	public static Car getCar(String name) {
		if ("BMW".equals(name)) {
			car = new BMWCar();
		}
		if ("Ford".equals(name)) {
			car = new FordCar();
		}
		return car;
	}
}

class CarFlyWeightFactory {
	public Car car;
	private Hashtable<String, Car> carPool = new Hashtable<String, Car>();

	public Car getCar(String name) {
		if ("BMW".equals(name)) {
			car = carPool.get(name);
			if (car == null) {
				car = new BMWCar();
				carPool.put(name, car);
			}
		}

		if ("Ford".equals(name)) {
			car = carPool.get(name);
			if (car == null) {
				car = new FordCar();
				carPool.put(name, car);
			}
		}
		return car;
	}

	public int getNumber() {
		return carPool.size();//.getSize();
	}
}
