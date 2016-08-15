package com.carvendy.design.pattern.flyweight;

public class Test {
	
	/*
	 *  主要用于创建对象时，运用共享技术，减少对象对内存的占用.一个提高程序效率和性能的模式,会大大加快程序的运行速度.
		就是说在一个系统中如果有多个相同的对象，那么只共享一份就可以了，不必每个都去实例化一个对象。
	
		Flyweight(享元)模式中常出现Factory模式。Flyweight的内部状态是用来共享的,Flyweight factory负责维护
		一个对象存储池（Flyweight Pool）来存放内部状态的对象。
		Flyweight的关键思路,在于:
		新建对象时:
			先到hashtable中进行获取-->判断取得对象是否为空-->若是,则新建此对象,且放回hashtable -->若存在,则共享原来
		的对象.
	 */
	public static void main(String[] args) {
		CarFlyWeightFactory carFlyWeightFactory = new CarFlyWeightFactory();
		Car carf1 = carFlyWeightFactory.getCar("Ford");
		carf1.showCarName();
		Car carf2 = carFlyWeightFactory.getCar("Ford");
		carf2.showCarName();
		if (carf1 == carf2) {
			System.out.println("同一部车来的");
		} else {
			System.out.println("不同一部车来的");
		}
		System.out.println("车的数量是:" + carFlyWeightFactory.getNumber());
	}
}
