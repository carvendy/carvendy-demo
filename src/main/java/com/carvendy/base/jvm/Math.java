package com.carvendy.base.jvm;

public class Math {

	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		getNum(a,b);
	}
	
	public static int getNum(int a, int b){
		int c = (a+b) * 10;
		return c;
	}
}
