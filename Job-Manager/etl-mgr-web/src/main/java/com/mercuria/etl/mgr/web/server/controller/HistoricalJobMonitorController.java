/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.mercuria.etl.mgr.web.server.controller.HistoricalJobMonitorController
 * Date:	Jul 31, 2013
 * 
 * Author:	Sabuj Das | sabuj.das@asia.xchanging.com
 * -------------------------------------------------------------------------- *
 */
package com.mercuria.etl.mgr.web.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercuria.etl.mgr.model.vo.JobMonitorVo;
import com.mercuria.etl.mgr.service.JobDetailMonitorService;
import com.mercuria.etl.mgr.web.WebConstants;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
@Controller
@RequestMapping("/" + WebConstants.SERVICE_ROOT_HISTORICAL_JOB_MONITOR)
public class HistoricalJobMonitorController {

	@Autowired
	private JobDetailMonitorService jobDetailMonitorService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<JobMonitorVo> getAllJobMonitorData() {
		try{
			List<JobMonitorVo> list = jobDetailMonitorService.getAllJobHistory();
			if(null != list)
				return list;
		} catch (Exception e){
			
		}
		return new ArrayList<JobMonitorVo>();
	}
	
}
