package com.yt.cms.model;

import java.util.List;

public class MenuLeve1 extends Menu{
	
    /**
     * 子节点
     */
    private List<MenuLeve2> nodes;
    
   
	public List<MenuLeve2> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuLeve2> nodes) {
		this.nodes = nodes;
	}


    
}