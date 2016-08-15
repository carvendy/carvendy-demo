package com.carvendy.data.struct.tree;

import java.util.ArrayList;
import java.util.List;

public class MyTree {

	private String AREA_ID; // 主键ID  
    private String AREA_NAME;   // 用来显示的名称  
    private String PARENT_ID;   // 父ID  参照AREA_ID  
    private MyTree parentObj; // 父节点对象  
    private List<MyTree> childrenList = new ArrayList<MyTree>();    // 子节点  
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String aREA_ID) {
		AREA_ID = aREA_ID;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public MyTree getParentObj() {
		return parentObj;
	}
	public void setParentObj(MyTree parentObj) {
		this.parentObj = parentObj;
	}
	public List<MyTree> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<MyTree> childrenList) {
		this.childrenList = childrenList;
	}

    
}
