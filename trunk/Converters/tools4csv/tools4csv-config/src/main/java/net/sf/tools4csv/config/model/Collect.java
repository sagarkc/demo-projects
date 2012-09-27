package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.List;

public class Collect {

	private String targetClassName;
	private List< Property> properties;
	
	public Collect() {
		properties = new ArrayList<Property>();
	}
	
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
	
	
}
