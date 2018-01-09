package com.yt.cms.model;

import java.util.List;


public class MenuLeve1 extends Menu{
	
    /**
     * 子节点
     */
    private List<MenuLeve2> nodes;
    
    private Boolean selectable;
	
	public Boolean getSelectable() {
		return selectable;
	}

	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	public List<MenuLeve2> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuLeve2> nodes) {
		this.nodes = nodes;
	}


    
}