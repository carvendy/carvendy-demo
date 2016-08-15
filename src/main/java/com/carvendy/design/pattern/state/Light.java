package com.carvendy.design.pattern.state;
public class Light {
	Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Light() {
		color = new RedColor(this);
	}

	public void showColor() {
		color.show();
	}

}

