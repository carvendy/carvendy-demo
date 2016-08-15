package com.carvendy.data.struct.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树生成、遍历工具
 * 
 * @author Walker
 * @version 1.0.0.0
 */
public class ManyNodeTree {
	/** 树根 */
	private ManyTreeNode root;

	/**
	 * 构造函数
	 */
	public ManyNodeTree() {
		root = new ManyTreeNode(new TreeNode("root"));
	}

	/**
	 * 生成一颗多叉树，根节点为root
	 * 
	 * @param treeNodes
	 *            生成多叉树的节点集合
	 * @return ManyNodeTree
	 */
	public ManyNodeTree createTree(List<TreeNode> treeNodes) {
		if (treeNodes == null || treeNodes.size() < 0)
			return null;

		ManyNodeTree manyNodeTree = new ManyNodeTree();

		// 将所有节点添加到多叉树中
		for (TreeNode treeNode : treeNodes) {
			if (treeNode.getParentId().equals("root")) {
				// 向根添加一个节点
				manyNodeTree.getRoot().getChildList()
						.add(new ManyTreeNode(treeNode));
			} else {
				addChild(manyNodeTree.getRoot(), treeNode);
			}
		}

		return manyNodeTree;
	}

	/**
	 * 向指定多叉树节点添加子节点
	 * 
	 * @param manyTreeNode
	 *            多叉树节点
	 * @param child
	 *            节点
	 */
	public void addChild(ManyTreeNode manyTreeNode, TreeNode child) {
		for (ManyTreeNode item : manyTreeNode.getChildList()) {
			if (item.getData().getNodeId().equals(child.getParentId())) {
				// 找到对应的父亲
				item.getChildList().add(new ManyTreeNode(child));
				break;
			} else {
				if (item.getChildList() != null
						&& item.getChildList().size() > 0) {
					addChild(item, child);
				}
			}
		}
	}

	/**
	 * 遍历多叉树
	 * 
	 * @param manyTreeNode
	 *            多叉树节点
	 * @return
	 */
	public String iteratorTree(ManyTreeNode manyTreeNode) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("\n");

		if (manyTreeNode != null) {
			for (ManyTreeNode index : manyTreeNode.getChildList()) {
				buffer.append(index.getData().getNodeId() + ",");

				if (index.getChildList() != null
						&& index.getChildList().size() > 0) {
					buffer.append(iteratorTree(index));
				}
			}
		}

		buffer.append("\n");

		return buffer.toString();
	}

	public ManyTreeNode getRoot() {
		return root;
	}

	public void setRoot(ManyTreeNode root) {
		this.root = root;
	}

	
	public static void main(String[] args) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		treeNodes.add(new TreeNode("系统权限管理", "root"));
		treeNodes.add(new TreeNode("用户管理", "系统权限管理"));
		treeNodes.add(new TreeNode("角色管理", "系统权限管理"));
		treeNodes.add(new TreeNode("组管理", "系统权限管理"));
		treeNodes.add(new TreeNode("用户菜单管理", "系统权限管理"));
		treeNodes.add(new TreeNode("角色菜单管理", "系统权限管理"));
		treeNodes.add(new TreeNode("用户权限管理", "系统权限管理"));
		
		treeNodes.add(new TreeNode("站内信", "root"));
		treeNodes.add(new TreeNode("写信", "站内信"));
		treeNodes.add(new TreeNode("收信", "站内信"));
		treeNodes.add(new TreeNode("草稿", "站内信"));
		
		
		treeNodes.add(new TreeNode("通知", "root"));
		treeNodes.add(new TreeNode("通知1", "通知"));
		treeNodes.add(new TreeNode("通知2", "通知"));
		treeNodes.add(new TreeNode("通知3", "通知"));
		
		

		ManyNodeTree tree = new ManyNodeTree();

		ManyNodeTree manyNodeTree = tree.createTree(treeNodes);
		ManyTreeNode showRoot = manyNodeTree.getRoot();
		System.out.println(tree.iteratorTree(showRoot));
	}

}
