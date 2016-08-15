package com.carvendy.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.mongodb.util.Hash;

public class DuplicateRemoval {

	
	final static String ENCODING = "gbk";
	
	public static void main(String[] args) {

		String name = "D:/ip/ip2015.10.9-num.txt";
		Map<String, String> dataMap = new HashMap<String, String>();

		for (int i = 2; i <= 14; i++) {
			String fileName = name.replaceAll("num", i + "");
			System.out.print("read:"+fileName);
			loadIp(dataMap, fileName);
		}
		
		List<String> dataList = new ArrayList<String>();
		for(String value:dataMap.values()){
			dataList.add(value);
		}
		
		String fileName = name.replaceAll("num", "last");
		try {
			FileUtils.writeLines(new File(fileName), dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void loadIp(Map<String, String> dataMap, String fileName) {
		try {
			List<String> lineList = FileUtils.readLines(new File(fileName), ENCODING);
			System.out.println(",size:"+lineList.size());
			for (String line : lineList) {
				dataMap.put(line.split(",")[0], line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
