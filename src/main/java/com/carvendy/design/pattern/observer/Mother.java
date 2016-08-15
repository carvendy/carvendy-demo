package com.carvendy.design.pattern.observer;

public class Mother implements Observer {
	public void update(Children child) {
		if (child.getState().equals("fight")) {
			System.out.println("告诉Mother，他和别人打架了");
		} else if (child.getState().equals("scholarship")) {
			System.out.println("告诉Mother,他得到了奖学金");
		}
	}
}
