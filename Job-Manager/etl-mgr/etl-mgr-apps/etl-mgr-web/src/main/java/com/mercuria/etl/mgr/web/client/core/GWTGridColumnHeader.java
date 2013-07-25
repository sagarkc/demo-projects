package com.mercuria.etl.mgr.web.client.core;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;

public class GWTGridColumnHeader {

	private String title;
	private String attribute;
	private int width = -1;
	private Alignment alignment;
	private ListGridFieldType cellType;
	private String imageURLPrefix;
	private String imageURLSuffix;
	
	/**
	 * @param title
	 * @param attribute
	 */
	public GWTGridColumnHeader(String title, String attribute) {
		this.title = title;
		this.attribute = attribute;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public ListGridFieldType getCellType() {
		return cellType;
	}

	public void setCellType(ListGridFieldType cellType) {
		this.cellType = cellType;
	}

	public String getImageURLPrefix() {
		return imageURLPrefix;
	}

	public void setImageURLPrefix(String imageURLPrefix) {
		this.imageURLPrefix = imageURLPrefix;
	}

	public String getImageURLSuffix() {
		return imageURLSuffix;
	}

	public void setImageURLSuffix(String imageURLSuffix) {
		this.imageURLSuffix = imageURLSuffix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute == null) ? 0 : attribute.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GWTGridColumnHeader)) {
			return false;
		}
		GWTGridColumnHeader other = (GWTGridColumnHeader) obj;
		if (attribute == null) {
			if (other.attribute != null) {
				return false;
			}
		} else if (!attribute.equals(other.attribute)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GWTGridColumnHeader [title=").append(title).append("]");
		return builder.toString();
	}
	
	
	public ListGridField createListGridField(){
		ListGridField listGridField = new ListGridField(attribute, title);
		
		if(width>0){
			listGridField.setWidth(width);
		} else {
			listGridField.setWidth("*");
		}
		
		if(null != alignment){
			listGridField.setAlign(alignment);
		}
		
		if(null != cellType){
			listGridField.setType(cellType);
		}
		
		if(ListGridFieldType.IMAGE.equals(cellType) 
				&& null != imageURLPrefix 
				&& null != imageURLSuffix){
			listGridField.setImageURLPrefix(imageURLPrefix);
			listGridField.setImageURLSuffix(imageURLSuffix);
		}
		
		
		return listGridField;
	}
}
