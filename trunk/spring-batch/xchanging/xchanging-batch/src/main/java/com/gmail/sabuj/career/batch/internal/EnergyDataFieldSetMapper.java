package com.gmail.sabuj.career.batch.internal;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.gmail.sabuj.career.batch.model.EnergyData;

public class EnergyDataFieldSetMapper implements FieldSetMapper<EnergyData> {

	public static final int MSN_COLUMN = 0;
	public static final int YYYYMM_COLUMN = 1;
	public static final int VALUE_COLUMN = 2;
	public static final int COLUMN_ORDER_COLUMN = 3;
	public static final int DESCRIPTION_COLUMN = 4;
	public static final int UNIT_COLUMN = 5;
	
	@Override
	public EnergyData mapFieldSet(FieldSet fieldSet) throws BindException {
		EnergyData data = new EnergyData();
		
		data.setMsnCode(fieldSet.readString(MSN_COLUMN));
		data.setDate(fieldSet.readString(YYYYMM_COLUMN));
		data.setValue(fieldSet.readString(VALUE_COLUMN));
		data.setColumnOrder(fieldSet.readString(COLUMN_ORDER_COLUMN));
		data.setDescription(fieldSet.readString(DESCRIPTION_COLUMN));
		data.setUnit(fieldSet.readString(UNIT_COLUMN));
		
		
		return data;
	}

}
