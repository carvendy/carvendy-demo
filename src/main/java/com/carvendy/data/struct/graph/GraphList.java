package com.carvendy.data.struct.graph;


public class GraphList {
	public GraphNode first;
	public GraphNode last;
	public boolean   visitable;
	public int getAjd(int[] ajd) {
		GraphNode current = first;
		int length = 0;
		while(current != null) {
			ajd[length++] = current.info;
			current = current.link;
		}
		return length;
	}
	public void addNode(int v) {
		GraphNode node = new GraphNode();
		node.info = v;
		if(first == null) {
			first = node;
			last = node;
		} else {
			last.link = node;
			last = node;
		}
	}
}
