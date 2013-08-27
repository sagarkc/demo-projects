/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.web.client.jmx.ds.MBeanSchedulerNamesDataSource
 * Date:	Aug 27, 2013  11:34:19 AM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.web.client.jmx.ds;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.FieldType;
import com.xchanging.etl.mgr.model.scheduler.SchedulerDetail;
import com.xchanging.etl.mgr.model.vo.JobMonitorHistoryVo;
import com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource;
import com.xchanging.etl.mgr.web.client.endpoint.RemoteServiceEndpointFactory;
import com.xchanging.etl.mgr.web.client.service.JmxMBeanInfoServiceAsync;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class MBeanSchedulerNamesDataSource extends GwtRpcObjectDataSource {

	private static MBeanSchedulerNamesDataSource instance = null;
	
	private final JmxMBeanInfoServiceAsync monitorService 
		= RemoteServiceEndpointFactory.getInstance().getJmxMBeanInfoServerEndpoint();
	
    public static MBeanSchedulerNamesDataSource getInstance() {  
        if (instance == null) {  
            instance = new MBeanSchedulerNamesDataSource("MBeanSchedulerNamesDataSource");  
        }  
        return instance;  
    }  
	/**
	 * 
	 */
	private MBeanSchedulerNamesDataSource(String id) {
		setID(id);
		DataSourceField nameField = new DataSourceField(
				SchedulerDetail.Fields.SCHEDULER_NAME, FieldType.TEXT);
		setFields(nameField);
	}
	
	
	@Override
	protected void executeFetch(final String requestId, final DSRequest request,
			final DSResponse response) {
		invalidateCache();
		/*String hostName = "";
		if(null != request.getCriteria()){
			hostName = request.getCriteria().getAttribute("hostName");
		}
		int portNumber = 8500;
		if(null != request.getCriteria()){
			portNumber = request.getCriteria().getAttributeAsInt("portNumber");
		}*/
		monitorService.getSchedulerMbeanNames("localhost", 9010, new AsyncCallback<List<String>>() {
			@Override
			public void onSuccess(List<String> result) {
				Record[] records = new Record[0];
				if(null != result && result.size() > 0){
					records = new Record[result.size()];
					for (int i = 0; i < result.size(); i++) {
						String jobName = result.get(i);
						Record record = new Record();
						record.setAttribute(SchedulerDetail.Fields.SCHEDULER_NAME, jobName);
						records[i] = record;
					}
				}
				
				response.setData(records);
				processResponse(requestId, response);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource#executeAdd(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeAdd(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource#executeUpdate(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeUpdate(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.xchanging.etl.mgr.web.client.core.GwtRpcObjectDataSource#executeRemove(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeRemove(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub

	}

}
