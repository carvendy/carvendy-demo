package com.carvendy.design.pattern.state.my;

public class Two implements Number{

	@Override
	public Number show() {
		System.out.println("2");
		return new Three();
	}

	
}
