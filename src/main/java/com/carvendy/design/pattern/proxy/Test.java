package com.carvendy.design.pattern.proxy;

public class Test {

	/*
	 * (1)
		代理的好处:
		--->是可以在间接访问对象的同时,要其前或后,添加其它的逻辑代码.
		--->对原来逻辑进行添加其它逻辑,最终生成新的逻辑.即:对类的方法添加一些额外的逻辑,生成新的方法逻辑.
		
		(2)
		静态代理: 
		-->一个原类与一个代理类要一一对应。
		-->两者都实现共同的接口或继承相同的抽象类；
		-->只是在代理类中,实例化原类，在原类方法的前后添加新的逻辑。
	 * 
	 */
	public static void main(String[] args) {
		Subject sub=new ProxySubject();
		sub.request();
	}
}
