package com.carvendy.base.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * @author hailin
 * @version 1.0
 * @date 2016年5月16日 上午9:45:34 
 * 类说明 :
 */

public class TestListSort {

	public static void main(String[] args) {
		List<Integer> numList = new ArrayList<Integer>();
		numList.add(21);
		numList.add(2);
		numList.add(3);
		Collections.sort(numList);
		System.out.println(numList.get(0));
		System.out.println(numList.get(1));
		System.out.println(numList.get(2));
	}
}


