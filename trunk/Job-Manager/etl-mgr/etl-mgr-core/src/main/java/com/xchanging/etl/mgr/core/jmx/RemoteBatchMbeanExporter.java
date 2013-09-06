/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.RemoteBatchMbeanExporter
 * Date:	Sep 5, 2013  12:31:38 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class RemoteBatchMbeanExporter extends MBeanExporter implements SmartLifecycle {

	private static final Log logger = LogFactory.getLog(RemoteBatchMbeanExporter.class);

	private final AnnotationJmxAttributeSource attributeSource = new AnnotationJmxAttributeSource();
	private final ReentrantLock lifecycleLock = new ReentrantLock();
	
	/**
	 * 
	 */
	public RemoteBatchMbeanExporter() {
		super();
		setAutodetect(false);
		setNamingStrategy(new MetadataNamingStrategy(attributeSource));
		setAssembler(new MetadataMBeanInfoAssembler(attributeSource));
	}

	@Override
	public void start() {
		this.lifecycleLock.lock();
		try {
			if (!this.running) {
				this.doStart();
				this.running = true;
				if (logger.isInfoEnabled()) {
					logger.info("started " + this);
				}
			}
		}
		finally {
			this.lifecycleLock.unlock();
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPhase() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAutoStartup() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stop(Runnable callback) {
		// TODO Auto-generated method stub
		
	}

	/* -- Other APIs -- */
	
	protected void doStart() {
		registerJobs();
		registerSteps();
	}
	
	private void registerJobs() {
		for (String jobName : jobService.listJobs(0, Integer.MAX_VALUE)) {
			if (!jobKeys.contains(jobName)) {
				jobKeys.add(jobName);
				logger.info("Registering job execution " + jobName);
				registerBeanNameOrInstance(jobExecutionMetricsFactory.createMetricsForJob(jobName),
						getBeanKeyForJobExecution(jobName));
			}
		}
	}

}
