/**
 * 
 */
package com.gs.demo.jxls.vo;

/**
 * @author sabuj.das
 *
 */
public class DropDownVO {

	private String DROP_DOWN_TYPE, DESCRIPTION, ACTIVE_IND, ADMIN_MANAGED_IND;

	/**
	 * @return the dROP_DOWN_TYPE
	 */
	public String getDROP_DOWN_TYPE() {
		return (null != DROP_DOWN_TYPE) ? DROP_DOWN_TYPE.trim() : "";
	}

	/**
	 * @param drop_down_type the dROP_DOWN_TYPE to set
	 */
	public void setDROP_DOWN_TYPE(String drop_down_type) {
		DROP_DOWN_TYPE = drop_down_type;
	}

	/**
	 * @return the dESCRIPTION
	 */
	public String getDESCRIPTION() {
		return (null != DESCRIPTION) ? DESCRIPTION.trim() : "";
	}

	/**
	 * @param description the dESCRIPTION to set
	 */
	public void setDESCRIPTION(String description) {
		DESCRIPTION = description;
	}

	/**
	 * @return the aCTIVE_IND
	 */
	public String getACTIVE_IND() {
		return (null != ACTIVE_IND) ? ACTIVE_IND.trim() : "";
	}

	/**
	 * @param active_ind the aCTIVE_IND to set
	 */
	public void setACTIVE_IND(String active_ind) {
		ACTIVE_IND = active_ind;
	}

	/**
	 * @return the aDMIN_MANAGED_IND
	 */
	public String getADMIN_MANAGED_IND() {
		return (null != ADMIN_MANAGED_IND) ? ADMIN_MANAGED_IND.trim() : "";
	}

	/**
	 * @param admin_managed_ind the aDMIN_MANAGED_IND to set
	 */
	public void setADMIN_MANAGED_IND(String admin_managed_ind) {
		ADMIN_MANAGED_IND = admin_managed_ind;
	}
	public static String formatToColumnName(String txt){
		return txt.toUpperCase().replaceAll(" ", "_").replaceAll("-", "");
	}
	
	@Override
	public String toString() {
		return "\"" + formatToColumnName(getDROP_DOWN_TYPE()) 
		+ "\", \"" + getDESCRIPTION() + "\", \""
		+ getACTIVE_IND() + "\", \"" + getADMIN_MANAGED_IND() + "\"";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((DROP_DOWN_TYPE == null) ? 0 : DROP_DOWN_TYPE.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DropDownVO other = (DropDownVO) obj;
		if (DROP_DOWN_TYPE == null) {
			if (other.DROP_DOWN_TYPE != null)
				return false;
		} else if (!DROP_DOWN_TYPE.equals(other.DROP_DOWN_TYPE))
			return false;
		return true;
	}

	
}
