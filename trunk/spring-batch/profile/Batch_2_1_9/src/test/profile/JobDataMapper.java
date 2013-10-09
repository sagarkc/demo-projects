package test.profile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Sabuj
 *
 */
public class JobDataMapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6287074265700259569L;
	
	private Map<String, BatchJobDetail> batchJobDetailMap = new HashMap<String, BatchJobDetail>();
	
	public BatchJobDetail getBatchJobDetail(String jobName){
		return batchJobDetailMap.get(jobName);
	}
	
	public String getBatchJobDetailName(String jobName){
		return null != batchJobDetailMap.get(jobName) ? batchJobDetailMap.get(jobName).getJobDetailName() : null;
	}
	
	public void addBatchJobDetail(BatchJobDetail batchJobDetail){
		batchJobDetailMap.put(batchJobDetail.getTargetJobName(), batchJobDetail);
	}
}
