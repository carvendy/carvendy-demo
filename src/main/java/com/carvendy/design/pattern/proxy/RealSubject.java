package com.carvendy.design.pattern.proxy;

public class RealSubject extends Subject {
	public void request() {
		System.out.println("From real subject.");
	}
}