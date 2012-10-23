/**
 * 
 */
package net.sf.tools.gsplit.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class FileMetaData implements Serializable{

	/**
	 * serialVersionUID = 306852703898295610L;
	 */
	private static final long serialVersionUID = 306852703898295610L;

	private int totalPartCount;
	private List<PartMetaData> partMetaDataList;
	private transient String currentDirectory;
	
	/**
	 * 
	 */
	public FileMetaData() {
		totalPartCount = -1;
		partMetaDataList = new ArrayList<PartMetaData>(0);
	}

	/**
	 * @return the totalPartCount
	 */
	public int getTotalPartCount() {
		return totalPartCount;
	}

	/**
	 * @param totalPartCount the totalPartCount to set
	 */
	public void setTotalPartCount(int totalPartCount) {
		this.totalPartCount = totalPartCount;
	}

	/**
	 * @return the partMetaDataList
	 */
	public List<PartMetaData> getPartMetaDataList() {
		return partMetaDataList;
	}

	/**
	 * @param partMetaDataList the partMetaDataList to set
	 */
	public void setPartMetaDataList(List<PartMetaData> partMetaDataList) {
		this.partMetaDataList = partMetaDataList;
	}

	/**
	 * @return the currentDirectory
	 */
	public String getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * @param currentDirectory the currentDirectory to set
	 */
	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
	
}
