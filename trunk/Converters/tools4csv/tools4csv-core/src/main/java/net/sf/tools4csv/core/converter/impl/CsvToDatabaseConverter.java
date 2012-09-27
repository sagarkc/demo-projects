package net.sf.tools4csv.core.converter.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.tools4csv.config.model.ConnectionProperties;
import net.sf.tools4csv.core.converter.AbstractCsvConverter;

import org.apache.commons.dbcp.BasicDataSource;


public class CsvToDatabaseConverter extends AbstractCsvConverter {

	private BasicDataSource dataSource;
	
	@Override
	protected void writeTargetHeader(String targetHeader) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void closeWriter() throws IOException {
		if(null != dataSource){
			try {
				dataSource.close();
			} catch (SQLException e) {
				throw new IOException(e.getMessage(), e);
			}
		}
	}

	@Override
	protected void writeToTarget(Map<String, String> targetColumns)
			throws IOException {
		if(null == dataSource){
			initDataSource(getTarget().getWrite().getConnectionProperties());
		}
		
	}

	private int executeInsert(String sql, Object[] values){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < values.length; i++) {
				preparedStatement.setObject(i+1, values[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			if(null != preparedStatement){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if(null != connection){
				try {
					connection.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
		return -1;
	}
	
	
	private int executeProcedure(String sql, Object[] values){
		
		return -1;
	}

	
	private void initDataSource(ConnectionProperties connectionProperties) {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(connectionProperties.getDriverClass());
		dataSource.setUrl(connectionProperties.getUrl());
		dataSource.setUsername(connectionProperties.getUserName());
		dataSource.setPassword(connectionProperties.getPassword());
		
		dataSource.setMaxActive(10);
	}

	@Override
	protected void writeToTarget(List<Map<String, String>> targetColumnMaps)
			throws IOException {
		
	}

	@Override
	public boolean validateTarget() {
		if(null == getTarget())
			return false;
		if(null == getTarget().getWrite())
			return false;
		if(null == getTarget().getWrite().getConnectionProperties())
			return false;
		if(null == getTarget().getWrite().getSqls() || getTarget().getWrite().getSqls().size() <= 0)
			return false;
		return true;
	}

	

}
