package com.carvendy.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class DLL2 {
	  public interface TestJnaLib extends Library{
		  TestJnaLib INSTANCE = (TestJnaLib)Native.loadLibrary("abc", TestJnaLib.class);
		  int add(int first, int second); 
	  }
	  
	  public static void main(String[] args) {
		System.out.println("java:"+TestJnaLib.INSTANCE.add(23, 34));
      }
}