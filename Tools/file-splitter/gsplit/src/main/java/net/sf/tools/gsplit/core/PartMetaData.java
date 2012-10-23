/**
 * 
 */
package net.sf.tools.gsplit.core;

import java.io.Serializable;
import java.nio.ByteBuffer;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class PartMetaData implements Serializable, Comparable<PartMetaData>{

	/**
	 * serialVersionUID = 935008680538411195L;
	 */
	private static final long serialVersionUID = 935008680538411195L;
	
	public static final int HEADER_LENGTH = 40;
	
	private String partName;
	private int totalPartCount;
	private int currentPartNumber;
	private byte[] checkSum;

	public PartMetaData(String partName) {
		this.partName = partName;
	}

	/**
	 * @return the partName
	 */
	public String getPartName() {
		return partName;
	}

	/**
	 * @param partName the partName to set
	 */
	public void setPartName(String partName) {
		this.partName = partName;
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
	 * @return the currentPartNumber
	 */
	public int getCurrentPartNumber() {
		return currentPartNumber;
	}

	/**
	 * @param currentPartNumber the currentPartNumber to set
	 */
	public void setCurrentPartNumber(int currentPartNumber) {
		this.currentPartNumber = currentPartNumber;
	}

	/**
	 * @return the checkSum
	 */
	public byte[] getCheckSum() {
		return checkSum;
	}

	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(byte[] checkSum) {
		this.checkSum = checkSum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPartNumber;
		result = prime * result
				+ ((partName == null) ? 0 : partName.hashCode());
		result = prime * result + totalPartCount;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PartMetaData)) {
			return false;
		}
		PartMetaData other = (PartMetaData) obj;
		if (currentPartNumber != other.currentPartNumber) {
			return false;
		}
		if (partName == null) {
			if (other.partName != null) {
				return false;
			}
		} else if (!partName.equals(other.partName)) {
			return false;
		}
		if (totalPartCount != other.totalPartCount) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PartMetaData [partName=" + partName + ", currentPartNumber="
				+ currentPartNumber + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(PartMetaData o) {
		if(null == o){
			return -1;
		}
		return new Integer(this.currentPartNumber).compareTo(new Integer(o.currentPartNumber));
	}
	
	public byte[] getWritableBytes(){
		
		ByteBuffer b1 = ByteBuffer.allocate(4);
		b1.putInt(totalPartCount);
		ByteBuffer b2 = ByteBuffer.allocate(4);
		b2.putInt(currentPartNumber);
		
		
		ByteBuffer b4 = ByteBuffer.allocate(40);
		b4.put(b1.array(), 0, 4);
		b4.put(b2.array(), 0, 4);
		b4.put(checkSum, 0, 32);
		
		return b4.array();
	}
}
