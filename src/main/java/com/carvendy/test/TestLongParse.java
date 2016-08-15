package com.carvendy.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLongParse {

	
	public static void main(String[] args) {
		
		Date temp = new Date();
		temp.setTime(Long.parseLong("1426618555718"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(temp);
		System.out.println(format);
	}
}
