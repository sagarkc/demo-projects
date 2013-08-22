/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.model.criteria.RTJobFilterCriteria
 * Date:	Aug 22, 2013  3:04:57 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.model.criteria;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class RTJobFilterCriteria implements IsSerializable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3222698360343552580L;

	private String criteriaName;
	private Set<String> jobNames;
	private Date startedOnOrAfter;
	
	/**
	 * 
	 */
	public RTJobFilterCriteria() {
		/*Comparator<String> jobNameComparator = new Comparator<String>() {
			@Override
			public int compare(String name1, String name2) {
				return name1.compareTo(name2);
			}
		};*/
		jobNames = new LinkedHashSet<String>();
	}

	/**
	 * @return the criteriaName
	 */
	public String getCriteriaName() {
		return criteriaName;
	}

	/**
	 * @param criteriaName the criteriaName to set
	 */
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	/**
	 * @return the jobNames
	 */
	public Set<String> getJobNames() {
		return jobNames;
	}

	/**
	 * @param jobNames the jobNames to set
	 */
	public void setJobNames(Set<String> jobNames) {
		this.jobNames = jobNames;
	}

	/**
	 * @return the startedOnOrAfter
	 */
	public Date getStartedOnOrAfter() {
		return startedOnOrAfter;
	}

	/**
	 * @param startedOnOrAfter the startedOnOrAfter to set
	 */
	public void setStartedOnOrAfter(Date startedOnOrAfter) {
		this.startedOnOrAfter = startedOnOrAfter;
	}

	/**
	 * @return
	 */
	public boolean hasCriteria() {
		if((null == jobNames || jobNames.size() <= 0) && (null == startedOnOrAfter))
			return false;
		else if((null == jobNames || jobNames.size() <= 0) && (null != startedOnOrAfter))
			return true;
		else if((null != jobNames && jobNames.size() > 0) && (null == startedOnOrAfter))
			return true;
		else if((null != jobNames && jobNames.size() > 0) && (null != startedOnOrAfter))
			return true;
		return false;
	}
	
}
