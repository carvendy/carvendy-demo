package com.carvendy.data.struct.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public class BinaryTree {
	 
	
	public static void main(String[] args) {
				
		Node root = initTree();
		
		System.out.println("前序遍历:");
		breforeOrder(root);
		
		System.out.println("\n中序遍历:");
		middleOrder(root);
		
		System.out.println("\n后序遍历:");
		afterOrder(root);
		
		
	}
	
	public static Node initTree(){
		List<Node> nodeList=new ArrayList<Node>();
		int[] numArray={1,2,3,4,5,6,7};
		
		for(int i=0;i<numArray.length;i++){
			Node node = new Node();
			node.data=numArray[i];
			nodeList.add(node);
		}
		
		Node root=nodeList.get(0);
		
		for(int i=0;i<numArray.length/2;i++){
			Node node = nodeList.get(i);
			node.left= nodeList.get(i*2+1);
			node.right= nodeList.get(i*2+2);
		}
		
		return root;
	}


	public static void breforeOrder(Node node){
		if(node==null){
			return;
		}
		
		System.out.print(node.data+"  ");
		breforeOrder(node.left);
		breforeOrder(node.right);
	}
	
	
	public static void middleOrder(Node node){
		if(node==null){
			return;
		}
		
		middleOrder(node.left);
		System.out.print(node.data+"  ");
		middleOrder(node.right);
		
	}
	
	public static void afterOrder(Node node){
		if(node==null){
			return;
		}
		
		afterOrder(node.right);
		afterOrder(node.left);
		System.out.print(node.data+"  ");
	}
	
	
	/**先序非递归遍历二叉树*/
	public static void nrPreOrderTraverse(Node root){
		System.out.println("非递归-前序遍历：");
	    Stack<Node> stack = new Stack<Node>();
	    Node node = root;
	    while(node != null || !stack.isEmpty()){
	      while(node != null){
	        System.out.print(node.data+"  ");
	        stack.push(node);
	        node = node.left;//先遍历左边
	      }
	      node = stack.pop();
	      node = node.right;
	    }
	  }
	
	
	/**先序非递归遍历二叉树*/
	public static void nrInOrderTraverse(Node root){
		System.out.println("\n非递归-中序遍历：");
	    Stack<Node> stack = new Stack<Node>();
	    Node node = root;
	    while(node != null || !stack.isEmpty()){
	      while(node != null){
	        stack.push(node);
	        node = node.left;//先遍历左边
	      }
	      node = stack.pop();
	      System.out.print(node.data+"  ");
	      node = node.right;
	    }
	  }
	
	
	/**先序非递归遍历二叉树*/
	public static void nrPostOrderTraverse(Node root){
		System.out.println("\n非递归-后序遍历：");
	   
		 Stack<Node> stack = new Stack<Node>();
		    Node node = root;
		    Node preNode = null;	//记录之前遍历的右结点
		    while(node != null || !stack.isEmpty()){
		      while(node != null){
		        stack.push(node);
		        node = node.left;
		      }
		     // node=stack
		      
		      /**如果右结点为空，或者右结点之前遍历过，打印根结点*/
		      if(node.right == null || node.right == preNode){
		        System.out.print(node.data);
		        node = stack.pop();
		        preNode = node;
		        node = null;
		      }
		      else{
		        node = node.right;
		      }
		    }
	  }
	
	
	
	 /**层次遍历二叉树*/
	  public void levelTraverse(){
	    //levelTraverse(root);
	  }
	 /* private void levelTraverse(Node node) {
	    Queue<Node> queue = new Queue<Node>();
	    queue.push(node);
	    while(!queue.isEmpty()){
	      node = queue.pop();
	      if(node != null){
	        System.out.println(node.data);
	        queue.push(node.left);
	        queue.push(node.right);
	      }
	    }
	  }*/
}
