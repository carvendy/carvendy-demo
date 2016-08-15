package com.carvendy.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindClass {

	public static void main(String[] args) {
		
		//ClassLoader;
		//DocumentBuilder
		
		//Cloneable
		//Object
		//InputStreamReader
		
		//Pattern regex = Pattern.compile("\\d+");
		Pattern regex = Pattern.compile("12");
		Matcher matcher = regex.matcher("123");
		System.out.println(matcher.find());
		
		//Integer
		System.out.println(Integer.parseInt("-1"));
	}
}
