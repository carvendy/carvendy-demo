package com.carvendy.test;

public class TestCharLength {

	public static void main(String[] args) {
		/*String str="你好hello";
		System.out.println(str.getBytes().length);*/
		//System.out.println(str.length());
		
		String str = new String(new char[]{'a','b','c'},1,2);
		System.out.println(str);
	}	
}
