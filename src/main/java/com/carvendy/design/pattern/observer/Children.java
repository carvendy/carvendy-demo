package com.carvendy.design.pattern.observer;

import java.util.Vector;

public class Children {
	static private Vector<Observer> obs;
	static private String state = null;
	static {
		obs = new Vector<Observer>();
	}

	public static void attach(Observer o) {
		obs.addElement(o);
	}

	public static void detach(Observer o) {
		obs.removeElement(o);
	}

	public void setState(String str) {
		state = str;
	}

	public String getState() {
		return state;
	}

	public void notifyObs() {
		for (Observer o : obs) {
			o.update(this);
		}
	}
}
