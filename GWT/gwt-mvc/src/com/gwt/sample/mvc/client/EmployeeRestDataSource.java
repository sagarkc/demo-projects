package com.gwt.sample.mvc.client;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public class EmployeeRestDataSource extends AbstractRestDataSource {

	private static EmployeeRestDataSource instance = null;

	public static EmployeeRestDataSource getInstance() {
		if (instance == null) {
			instance = new EmployeeRestDataSource("personEditDS");
		}
		return instance;
	}

	private EmployeeRestDataSource(String id) {
		super(id);
	}

	@Override
	protected String getServiceRoot() {
		return "employee";
	}

	@Override
	protected void init() {
		setDataFormat(DSDataFormat.JSON);
		setJsonRecordXPath("/");

		DataSourceField idField = new DataSourceField("id", FieldType.INTEGER, "Employee ID");
		idField.setPrimaryKey(true);
		idField.setCanEdit(false);
		addField(idField);
		DataSourceField nameField = new DataSourceField("name", FieldType.TEXT, "Name");
		addField(nameField);
		DataSourceField salaryField = new DataSourceField("salary", FieldType.FLOAT, "Job Start Time");
		addField(salaryField);

	}

}
