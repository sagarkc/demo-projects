/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.server.controller.JobMonitorController
 * Date:	Jul 30, 2013
 * 
 * Author:	Sabuj Das | sabuj.das@asia.xchanging.com
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xchanging.etl.mgr.model.vo.JobMonitorVo;
import com.xchanging.etl.mgr.service.JobDetailMonitorService;
import com.xchanging.etl.mgr.web.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Controller
public class JobMonitorController {
	private static Logger logger = Logger.getLogger(JobMonitorController.class);
	
	@Autowired
	private JobDetailMonitorService jobDetailMonitorService;
	
	/**
	 * 
	 */
	public JobMonitorController() {
		// TODO Auto-generated constructor stub
	}
	

	public void setJobDetailMonitorService(
			JobDetailMonitorService jobDetailMonitorService) {
		this.jobDetailMonitorService = jobDetailMonitorService;
	}
	
	@RequestMapping(value=WebConstants.FETCH_HISTORICAL_JOB_MONITOR_DATA, 
			method=RequestMethod.POST, produces="application/json" )
	@ResponseBody
	public  List<JobMonitorVo> loadHistoricalMonitorData(){
		List<JobMonitorVo> list = jobDetailMonitorService.getAllJobHistory();
		return list;
	}
	
	@RequestMapping(value="a001", method=RequestMethod.GET, produces="application/json" )
	@ResponseBody
	public  List<JobMonitorVo> getHistoricalMonitorData(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		List<JobMonitorVo> list = jobDetailMonitorService.getAllJobHistory();
		return list;
	}
	
}
