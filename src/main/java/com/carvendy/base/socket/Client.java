package com.carvendy.base.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.20.164", 8080);
		
	}
}
