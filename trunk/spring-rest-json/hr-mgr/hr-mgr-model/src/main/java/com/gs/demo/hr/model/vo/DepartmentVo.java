/**
 * File :: com.gs.demo.hr.model.vo.DepartmentVo
 * Date :: Apr 14, 2013
 */
package com.gs.demo.hr.model.vo;

import net.sf.jsonizer.annotation.JsonDynamicProperty;
import net.sf.jsonizer.annotation.JsonObject;
import net.sf.jsonizer.annotation.JsonProperty;
import net.sf.jsonizer.core.Jsonizable;

import com.gs.demo.hr.common.annotations.Conversion;
import com.gs.demo.hr.common.annotations.FieldMapper;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
@Conversion(targetClassName="com.gs.demo.hr.model.entity.Department")
@JsonObject(dynamicProperties = { 
		@JsonDynamicProperty(name = "links", 
				textFormat = "<a href=\"department/edit.htm?selectedId=%d\">%s</a>", 
				propertyNames = {
					"prop:id", "key:lbl.department.edit" }) })
public class DepartmentVo implements Jsonizable{

	@JsonProperty(order = 0)
	private long id;
	@JsonProperty(order = 1)
	
	private String name;

	@FieldMapper(targetFieldName="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@FieldMapper(targetFieldName="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
