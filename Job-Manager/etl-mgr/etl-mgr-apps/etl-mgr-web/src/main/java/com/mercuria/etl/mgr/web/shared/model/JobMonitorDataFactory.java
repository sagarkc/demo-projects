/**
 * File :: com.mercuria.etl.mgr.web.shared.model.JobMonitorDataFactory
 * Date :: 27-Jul-2013
 */
package com.mercuria.etl.mgr.web.shared.model;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface JobMonitorDataFactory extends AutoBeanFactory {

	AutoBean<JobMonitorData> toJobMonitorDataBean();
	
}
