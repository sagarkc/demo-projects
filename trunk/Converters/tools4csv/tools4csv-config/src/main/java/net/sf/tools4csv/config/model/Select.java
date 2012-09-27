package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Select {

	private int from;
	private int to;
	private int batchSize;
	private List<Column> columns;
	private Map<Integer, Column> columnMapByIndex;

	public Select() {
		columns = new ArrayList<Column>();
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public Map<Integer, Column> getColumnMapByIndex(){
		if(null == columnMapByIndex && columns.size() > 0){
			for (Column c : columns) {
				columnMapByIndex.put(c.getIndex(), c);
			}
		}
		return columnMapByIndex;
	}

	public Column getColumnByName(String name) {
		if(columns.size() > 0){
			for (Column c : columns) {
				if(c.getName().equals(name))
					return c;
			}
		}
		return null;
	}
}
