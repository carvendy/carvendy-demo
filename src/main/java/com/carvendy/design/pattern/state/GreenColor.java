package com.carvendy.design.pattern.state;
public class GreenColor implements Color {
	Light light;

	public GreenColor(Light light) {
		this.light = light;
	}

	public void show() {
		System.out.println("the color is green,the car can run !");
		System.out
				.println("write down all logic shoud do this in this state.....");
		light.setColor(new YellowColor(light));
	}
}

