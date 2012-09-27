package net.sf.tools4csv.config.model;

import java.util.Comparator;

public class Converter implements Comparator<Converter>{

	private boolean active = true;
	private String id;
	private ConverterTypeEnum converterType;
	private String sourcePath;
	private String targetPath;
	private boolean mkdirs;
	private boolean overwrite;
	private int order;
	
	private Source source;
	private Target target;
	
	public Converter() {
		// TODO Auto-generated constructor stub
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ConverterTypeEnum getConverterType() {
		return converterType;
	}

	public void setConverterType(ConverterTypeEnum converterType) {
		this.converterType = converterType;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public boolean isMkdirs() {
		return mkdirs;
	}

	public void setMkdirs(boolean mkdirs) {
		this.mkdirs = mkdirs;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Converter)) {
			return false;
		}
		Converter other = (Converter) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public int compare(Converter c1, Converter c2) {
		return new Integer(c1.order).compareTo(new Integer(c2.order));
	}
	
}
