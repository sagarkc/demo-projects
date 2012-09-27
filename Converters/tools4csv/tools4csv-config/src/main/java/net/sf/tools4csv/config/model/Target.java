package net.sf.tools4csv.config.model;

public class Target {

	private String fileName;
	private boolean hasHeader;
	private boolean insert;
	private boolean collection;
	private Write write;
	private Collect collect;
	private String separator = ",";
	
	public Target() {
		// TODO Auto-generated constructor stub
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isHasHeader() {
		return hasHeader;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public Write getWrite() {
		return write;
	}

	public void setWrite(Write write) {
		this.write = write;
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	public Collect getCollect() {
		return collect;
	}

	public void setCollect(Collect collect) {
		this.collect = collect;
	}

	@Override
	public String toString() {
		return "Target [fileName=" + fileName + "]";
	}
	
}
