package com.carvendy.design.pattern.chainOfResponsibility;

public class Boy {

	private boolean hasCar; // 是否有车
	private boolean hasHouse; // 是否有房
	private boolean hasResponsibility; // 是否有责任心

	public Boy() {
	}

	public Boy(boolean hasCar, boolean hasHouse, boolean hasResponsibility) {
		this.hasCar = hasCar;
		this.hasHouse = hasHouse;
		this.hasResponsibility = hasResponsibility;
	}

	public boolean isHasCar() {
		return hasCar;
	}

	public void setHasCar(boolean hasCar) {
		this.hasCar = hasCar;
	}

	public boolean isHasHouse() {
		return hasHouse;
	}

	public void setHasHouse(boolean hasHouse) {
		this.hasHouse = hasHouse;
	}

	public boolean isHasResponsibility() {
		return hasResponsibility;
	}

	public void setHasResponsibility(boolean hasResponsibility) {
		this.hasResponsibility = hasResponsibility;
	}
}