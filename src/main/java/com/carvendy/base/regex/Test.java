package com.carvendy.base.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		String regex="^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z-]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String str="little_dragon@fox_mail.com";
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(str);
		System.out.println(str+","+m.matches());
		str = "little_dragon@foxmail.com";
		m = p.matcher(str);
		System.out.println(str+","+m.matches());
	}
}
