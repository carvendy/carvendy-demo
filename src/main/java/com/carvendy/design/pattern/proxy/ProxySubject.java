package com.carvendy.design.pattern.proxy;

public class ProxySubject extends Subject {
	private RealSubject realSubject; // 以真实角色作为代理角色的属性

	public ProxySubject() {
		realSubject = new RealSubject();
	}

	public void request(){ // 与原方法名相同
		preRequest();
		realSubject.request(); // 此处执行真实对象的request方法
		postRequest();
	}

	private void preRequest() {
		// something you want to do before requesting
		System.out.println("pre:");
	}

	private void postRequest() {
		// something you want to do after requesting
		System.out.println("after:");
	}
}