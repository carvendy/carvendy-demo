package com.carvendy.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
		
		String a="<a href=\"http://www.baidu.com\">test</a>," +
				"<a href=\"http://www.ba2idu.com\">tes2t</a>";
		
		String regex="(?<=href=\").+?(?=\")";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(a);
		while(m.find()){
			System.out.println(m.group());
		}
	}
}
