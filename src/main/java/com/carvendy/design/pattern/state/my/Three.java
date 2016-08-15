package com.carvendy.design.pattern.state.my;

public class Three implements Number{

	@Override
	public Number show() {
		System.out.println("3");
		return new One();
	}

	
}
