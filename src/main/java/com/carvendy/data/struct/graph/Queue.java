package com.carvendy.data.struct.graph;


public class Queue {
	public GraphNode first;
	public GraphNode last;
	public int count;
	public void addQueue(int info) {
		GraphNode node = new GraphNode();
		node.info = info;
		if(first == null) {
			first = node;
			last = node;
		} else {
			last.link = node;
			last = last.link;
		}
		count++;
	} 
	
	public void deleteQueue() {
		if(first == null) {
			System.out.println("null queue");
		} else {
			first = first.link;
			count--;
		}
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public int front() {
		if(first == null) {
			return -1;
		}
		return first.info;
	}
	
	public int back() {
		if(last == null) {
			return -1;
		}
		return last.info;
	}
}