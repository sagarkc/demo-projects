package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.List;

public class Write {

	private String id;
	private String type;
	private int from;
	private int to;
	private int batchSize;
	private int order;
	private ConnectionProperties connectionProperties;
	private List<Sql> sqls;
	private List<Column> columns;
	
	public Write() {
		sqls = new ArrayList<Sql>(0);
		columns = new ArrayList<Column>(0);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ConnectionProperties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(ConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	public List<Sql> getSqls() {
		return sqls;
	}

	public void setSqls(List<Sql> sqls) {
		this.sqls = sqls;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
}
