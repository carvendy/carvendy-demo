package com.carvendy.data.struct.graph;


public class Graph {
	private int length;
	private GraphList[] list;
	private int[][]     weight;
	
	public Graph(int length) {
		this.length = length;
		list = new  GraphList[length];
		weight = new int[length][length];
	}
	
	public void   dfs(int v) {
		int[] ajd = new int[length];
		int ajdlength = list[v].getAjd(ajd);
		list[v].visitable = true;
		System.out.print(v + " ");
		for (int i = 0; i < ajdlength; i++) {
			int w = ajd[i];
			if (!list[w].visitable) {
				dfs(w);
			}
		}
	}
	
	// 深度优先遍历
	public void dfsTravel() {
		for (int i = 0; i < length; i++) {
			list[i].visitable = false;
		}
		for (int i = 0; i < length; i++) {
			if (!list[i].visitable) {
				dfs(i);
			}
		}
	}

	// 广度优先遍历
	public void bfsTravel() {
		for (int i = 0; i < length; i++) {
			list[i].visitable = false;
		}
		bfs();
	}

private void bfs() {
		Queue   queue =  new Queue();
		for (int index = 0; index < length; index++) {
			if (!list[index].visitable) {
				queue.addQueue(index);
				list[index].visitable = true;
				System.out.print(index + " ");
				while (!queue.isEmpty()) {
					int temp = queue.front();
					queue.deleteQueue();
					int[] ajd = new int[length];
					int ajdlength = list[temp].getAjd(ajd);
					for (int i = 0; i < ajdlength; i++) {
						int w = ajd[i];
						if (!list[w].visitable) {
							System.out.print(w + " ");
							queue.addQueue(w);
							list[w].visitable = true;
						}
					}
				}
			}

		}
	}

	// 最短路径
	private int[] shortPath(int v) {
		int[] shortPath = new int[length];
		boolean[]   weightFound = new boolean[length];
		for (int i = 0; i < length; i++) {
			                                    // 趋近无穷
			shortPath[i] =   9999;
			weightFound[i] = false;
		}
			
		shortPath[v] = 0;
		weightFound[v] = true;
		Queue queue = new Queue();
		queue.addQueue(v);
		
		while (!queue.isEmpty()) {
			int temp = queue.front();
			queue.deleteQueue();
			int[]  ajd = new int[length];
			int ajdlength =   list[temp].getAjd(ajd);
			
			for (int i = 0; i < ajdlength; i++) {
				int w = ajd[i];
				if (!weightFound[w]) {
					if (shortPath[w] > shortPath[temp] + weight[temp][w]){
						shortPath[w] = shortPath[temp] + weight[temp][w];
					}
				}
			}
			
			int minWeightNode = 0;	
			for (int i = 0; i < length; i++) {
				if (!weightFound[i]) {
					minWeightNode = i;
					for (int j = 0; j < length; j++) {
						if (!weightFound[j]) {
							if (shortPath[j] < shortPath[minWeightNode]) {
								minWeightNode = j;
							}
						}
					}
					break;
				}
			}

			if (!weightFound[minWeightNode]) {
				weightFound[minWeightNode] = true;
				queue.addQueue(minWeightNode);
			}
		}
		          return shortPath;
	}
	
	// 长度
	public int  length() {
		System.out.println("length="+length);
		return length;
	}
	
	
	public boolean isEmpty() {
		return  length == 0;
	}

	
	// 添加节点
	public void addGraph(int info) {
		for (int i = 0; i < length; i++) {
			if (list[i] == null) {
				GraphList g = new GraphList();
				g.addNode(info);
				list[i] = g;
				break;
			}
		}
	}

	// 添加边
	public void addEdge(int vfrom, int vto, int value) {
		list[vfrom].addNode(vto);
		weight[vfrom][vto] = value;
	}

	// 打印图
	public void print() {
		for (int i = 0; i < length; i++) {
			GraphNode current =  list[i].first;
			while (current != null) {
				System.out.print(current.info + " ");
				current = current.link;
			}
			   System.out.println("");
		}
	}


public static void main(String[] args) {
		Graph graph = new Graph(5);  
		System.out.println("create graph start");
		for (int i = 0; i < 5; i++) {
			  graph.addGraph(i);
		}
		graph.addEdge(0, 1, 16);
		graph.addEdge(0, 3, 2);
		graph.addEdge(0, 4, 3);
		graph.addEdge(3, 4, 7);
		graph.addEdge(3, 1, 12);
		graph.addEdge(4, 1, 10);
		graph.addEdge(4, 3, 5);
		graph.addEdge(4, 2, 4);
		graph.addEdge(2, 1, 3);
		graph.addEdge(1, 2, 5);
		graph.print();
		System.out.println("create graph end");	
		
		int[] shortPath =  graph.shortPath(0);
		for (int i = 0; i < shortPath.length; i++) {
			 System.out.print(shortPath[i] + " ");
		}
	}
}