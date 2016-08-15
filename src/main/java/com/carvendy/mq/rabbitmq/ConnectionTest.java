package com.carvendy.mq.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionTest {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.20.213");
			factory.setPort(5672);
			factory.setUsername("mquser");
			factory.setPassword("mquser");
			factory.setVirtualHost("vhostpath");
			Connection connection;
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			System.out.println(channel);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}