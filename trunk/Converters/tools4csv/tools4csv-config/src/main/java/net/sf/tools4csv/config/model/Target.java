package net.sf.tools4csv.config.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Target {

	private boolean parallel = false;
	private Map<String, Write> writes;
	private List<Write> writeList;
	private Map<String, Collect> collects;
	private List<Collect> collectList;

	public Target() {
		writes = new HashMap<String, Write>(0);
		writeList = new ArrayList<Write>(0);
		collects = new HashMap<String, Collect>(0);
		collectList = new ArrayList<Collect>(0);
	}

	public boolean isParallel() {
		return parallel;
	}

	public void setParallel(boolean parallel) {
		this.parallel = parallel;
	}

	public Map<String, Write> getWrites() {
		return writes;
	}

	public void setWrites(Map<String, Write> writes) {
		this.writes = writes;
	}

	public Map<String, Collect> getCollects() {
		return collects;
	}

	public void setCollects(Map<String, Collect> collects) {
		this.collects = collects;
	}

	public List<Write> getWriteList() {
		return writeList;
	}

	public void setWriteList(List<Write> writeList) {
		this.writeList = writeList;
	}

	public List<Collect> getCollectList() {
		return collectList;
	}

	public void setCollectList(List<Collect> collectList) {
		this.collectList = collectList;
	}

	public Write getWrite(String id) {
		if(null != writes)
			return writes.get(id);
		return null;
	}

	public void addWrite(Write write) {
		if(null != write && null != write.getId() && !"".equals(write.getId().trim())){
			writes.put(write.getId(), write);
			writeList.add(write);
		}
	}

	public Collect getCollect(String id) {
		if(null != collects)
			return collects.get(id);
		return null;
	}

	public void addCollect(Collect collect) {
		if(null != collect && null != collect.getId() && !"".equals(collect.getId().trim())){
			collects.put(collect.getId(), collect);
			collectList.add(collect);
		}
	}


}
