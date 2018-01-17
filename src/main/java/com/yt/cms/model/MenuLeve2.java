package com.yt.cms.model;

import java.util.List;

public class MenuLeve2 extends Menu{
	
    
    /**
     * 子节点
     */
    private List<Button> buttons;
    
   
	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}



    
}