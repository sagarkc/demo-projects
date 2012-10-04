package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Target {

	private String fileName;
	private boolean hasHeader;
	private boolean insert;
	private boolean collection;
	private Map<String, Write> writes;
	private List<Collect> collects;
	private String separator = ",";
	
	public Target() {
		writes = new HashMap<String, Write>(0);
		collects = new ArrayList<Collect>(0);
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

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	public Map<String, Write> getWrites() {
		return writes;
	}

	public void setWrites(Map<String, Write> writes) {
		this.writes = writes;
	}

	public List<Collect> getCollects() {
		return collects;
	}

	public void setCollects(List<Collect> collects) {
		this.collects = collects;
	}

	@Override
	public String toString() {
		return "Target [fileName=" + fileName + "]";
	}
	
	public Write getWrite(String id){
		return null;
	}
	
	public void addWrite(Write write){
		
	}
	
	public Iterator<Write> getWriteIterator(){
		if(writes == null)
			return null;
		return getWrites().values().iterator();
	}
}
