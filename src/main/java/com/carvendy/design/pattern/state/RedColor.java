package com.carvendy.design.pattern.state;
public class RedColor implements Color {
	Light light;

	public RedColor(Light light) {
		this.light = light;
	}

	public void show() {
		System.out.println("the color is red,the car must stop !");
		System.out
				.println("write down all logic shoud do this in this state.....");
		light.setColor(new GreenColor(light));
	}
}

