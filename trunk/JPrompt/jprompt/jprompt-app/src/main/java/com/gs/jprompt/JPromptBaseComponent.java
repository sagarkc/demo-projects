package com.gs.jprompt;

import java.awt.Component;

public abstract class JPromptBaseComponent {

	private Component parentComponent;
	private String name;
	
	public JPromptBaseComponent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the parentComponent
	 */
	public Component getParentComponent() {
		return parentComponent;
	}

	/**
	 * @param parentComponent the parentComponent to set
	 */
	public void setParentComponent(Component parentComponent) {
		this.parentComponent = parentComponent;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
