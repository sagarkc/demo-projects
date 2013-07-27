/**
 * File :: com.mercuria.etl.mgr.web.server.endpoint.JobMonitorServerEndpoint
 * Date :: 24-Jul-2013
 */
package com.mercuria.etl.mgr.web.server.endpoint;

import java.util.Date;
import java.util.List;

import net.sf.jsonizer.core.FlexigridJsonCollection;
import net.sf.jsonizer.service.impl.FlexigridJsonOutputMapper;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercuria.etl.mgr.model.vo.JobMonitorVo;
import com.mercuria.etl.mgr.service.JobDetailMonitorService;
import com.mercuria.etl.mgr.web.client.service.JobMonitorService;

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
	public String loadHistoricalMonitorData() {
		List<JobMonitorVo> list = jobDetailMonitorService.getAllJobHistory();
		/*System.out.println("@ Server: loadHistoricalMonitorData()");
		String json = "{ \"data\": [ " +
				"{\"jobName\": \"Job 001\", \"status\": \"Started\"  }," +
				"{\"jobName\": \"Job 002\", \"status\": \"Started\"  }," +
				"{\"jobName\": \"Job 003\", \"status\": \"Started\"  }," +
				"{\"jobName\": \"Job 004\", \"status\": \"Ready\"  }" +
				"]}";
		
		List<JobMonitorVo> list = new ArrayList<JobMonitorVo>();
		JobMonitorVo monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 001");
		monitorVo.setStatus("Ready");
		list.add(monitorVo);
		
		monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 002");
		monitorVo.setStatus("Running");
		list.add(monitorVo);
		
		monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 003");
		monitorVo.setStatus("Running");
		list.add(monitorVo);
		
		monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 004");
		monitorVo.setStatus("Wait");
		list.add(monitorVo);
		
		monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 005");
		monitorVo.setStatus("Running");
		list.add(monitorVo);
		
		monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 006");
		monitorVo.setStatus("Running");
		list.add(monitorVo);
		
		monitorVo = new JobMonitorVo();
		monitorVo.setJobName("A 007");
		monitorVo.setStatus("Wait");
		list.add(monitorVo);*/
		
		FlexigridJsonCollection<JobMonitorVo> collection
			= new FlexigridJsonCollection<JobMonitorVo>(list);
		FlexigridJsonOutputMapper jsonOutputMapper = new FlexigridJsonOutputMapper();
		String jsonStr = "";
		try {
			jsonStr = jsonOutputMapper.getJonOutput(collection);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(logger.isDebugEnabled()){
			logger.debug("Generated JSON: " + jsonStr);
		}
		return jsonStr;
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
