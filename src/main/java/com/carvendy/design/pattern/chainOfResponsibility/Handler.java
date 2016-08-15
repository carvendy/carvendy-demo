package com.carvendy.design.pattern.chainOfResponsibility;

public interface Handler {
	 //判断依据，需要一个公共使用的参数
	  public void handleRequest(Boy boy);
}
