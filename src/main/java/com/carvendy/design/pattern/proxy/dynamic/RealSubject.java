package com.carvendy.design.pattern.proxy.dynamic;

public class RealSubject implements Subject {
	public void request() {
		System.out.println("From real subject.");
	}
}