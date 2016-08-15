package com.carvendy.design.pattern.proxy.dynamic;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public class DynamicSubject implements InvocationHandler {
	private Object sub;

	public DynamicSubject(Object obj) {
		sub = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before calling " + method);
		method.invoke(sub, args);
		System.out.println("after calling " + method);
		return null;
	}
}