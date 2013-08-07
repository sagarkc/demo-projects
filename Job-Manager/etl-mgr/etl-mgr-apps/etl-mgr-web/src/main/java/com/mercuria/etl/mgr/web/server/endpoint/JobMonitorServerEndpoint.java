/**
 * File :: com.xchanging.etl.mgr.web.server.endpoint.JobMonitorServerEndpoint
 * Date :: 24-Jul-2013
 */
package com.xchanging.etl.mgr.web.server.endpoint;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.service.JobDetailMonitorService;
import com.xchanging.etl.mgr.web.client.service.JobMonitorService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Component(value=JobMonitorService.RPC_TARGET)
public class JobMonitorServerEndpoint implements JobMonitorService{

	private static Logger logger = Logger.getLogger(JobMonitorServerEndpoint.class);
	
	@Autowired
	private JobDetailMonitorService jobDetailMonitorService;
	
	/**
	 * 
	 */
	public JobMonitorServerEndpoint() {
		// TODO Auto-generated constructor stub
	}
	

	public void setJobDetailMonitorService(
			JobDetailMonitorService jobDetailMonitorService) {
		this.jobDetailMonitorService = jobDetailMonitorService;
	}




	@Override
	public List<JobMonitorHistoryVo> loadHistoricalMonitorData() {
		return jobDetailMonitorService.getAllJobMonitorHistory();
	}


	@Override
	public String loadHistoricalMonitorData(List<String> jobNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadHistoricalMonitorData(
			List<String> jobNames, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadCurrentMonitorData(List<String> jobNames) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
