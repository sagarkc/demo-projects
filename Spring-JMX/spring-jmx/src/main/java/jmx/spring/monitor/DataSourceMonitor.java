package jmx.spring.monitor;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dataSourceMonitor")
public class DataSourceMonitor implements InitializingBean{

	@Autowired
	private BasicDataSource dataSource;
	
	private int minIdleSize;
	private int maxActiveSize;
	private int numberOfIdleConnections;
	private int numberOfActiveBusyConnections;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		minIdleSize = dataSource.getMinIdle();
		maxActiveSize = dataSource.getMaxActive();
		numberOfIdleConnections =dataSource.getNumIdle(); 
		numberOfActiveBusyConnections =dataSource.getNumActive();
	}

	public int getMinIdleSize() {
		minIdleSize = dataSource.getMinIdle();
		return minIdleSize;
	}

	public int getMaxActiveSize() {
		maxActiveSize = dataSource.getMaxActive();
		return maxActiveSize;
	}

	public int getNumberOfIdleConnections() {
		numberOfIdleConnections =dataSource.getNumIdle(); 
		return numberOfIdleConnections;
	}

	public int getNumberOfActiveBusyConnections() {
		numberOfActiveBusyConnections =dataSource.getNumActive();
		return numberOfActiveBusyConnections;
	}

	

}
