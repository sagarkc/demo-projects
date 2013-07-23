/**
 * File :: com.mercuria.etl.mgr.web.server.endpoint.JobMonitorServerEndpoint
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.server.endpoint;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mercuria.etl.mgr.web.client.service.JobMonitorService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Component(value=JobMonitorService.RPC_TARGET)
public class JobMonitorServerEndpoint implements JobMonitorService{

	/**
	 * 
	 */
	public JobMonitorServerEndpoint() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String loadHistoricalMonitorData() {
		System.out.println("@ Server: loadHistoricalMonitorData()");
		String json = "{ \"data\": [ " +
				"{\"jobName\": \"Job 001\", \"status\": \"Started\"  }," +
				"{\"jobName\": \"Job 002\", \"status\": \"Started\"  }," +
				"{\"jobName\": \"Job 003\", \"status\": \"Started\"  }," +
				"{\"jobName\": \"Job 004\", \"status\": \"Ready\"  }" +
				"]}";
		
		return json;
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
