package com.carvendy.design.pattern.observer;

public class Client {
	public static void main(String[] args) {
		// 被观察者
		Children child = new Children();
		
		// 观察者
		Observer father = new Father();
		Observer mother = new Mother();
		
		// 注册进去
		Children.attach(father);
		Children.attach(mother);
		
		// 设置状态
		child.setState("fight");
		child.notifyObs();
		
		System.out.println();
		child.setState("scholarship");
		child.notifyObs();
	}
}
