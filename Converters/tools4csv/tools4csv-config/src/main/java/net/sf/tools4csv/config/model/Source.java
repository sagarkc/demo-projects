package net.sf.tools4csv.config.model;

public class Source {

	private String fileName;
	private boolean hasHeader;
	private String separator = ",";
	private Select select;
	
	public Source() {
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

	public Select getSelect() {
		return select;
	}

	public void setSelect(Select select) {
		this.select = select;
	}

	@Override
	public String toString() {
		return "Source [fileName=" + fileName + "]";
	}
	
}
