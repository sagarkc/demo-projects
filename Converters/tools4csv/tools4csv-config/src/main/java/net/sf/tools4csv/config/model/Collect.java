package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.List;

public class Collect {

	private String id;
	private String targetClassName;
	private String orderBy;
	private List< Property> properties;
	
	public Collect() {
		properties = new ArrayList<Property>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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
