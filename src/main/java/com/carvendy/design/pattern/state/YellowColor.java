package com.carvendy.design.pattern.state;
public class YellowColor implements Color {
	Light light;

	public YellowColor(Light light) {
		this.light = light;
	}

	public void show() {
		System.out.println("the color is yellow,the car shoud stop !");
		System.out
				.println("write down all logic shoud do this in this state.....");
		light.setColor(new RedColor(light));
	}
}