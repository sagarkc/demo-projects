package net.sf.tools4csv.config;

import java.io.File;
import java.io.IOException;

import net.sf.tools4csv.config.model.Collect;
import net.sf.tools4csv.config.model.Column;
import net.sf.tools4csv.config.model.Configuration;
import net.sf.tools4csv.config.model.ConnectionProperties;
import net.sf.tools4csv.config.model.Converter;
import net.sf.tools4csv.config.model.ConverterTypeEnum;
import net.sf.tools4csv.config.model.Property;
import net.sf.tools4csv.config.model.Query;
import net.sf.tools4csv.config.model.Select;
import net.sf.tools4csv.config.model.Source;
import net.sf.tools4csv.config.model.Sql;
import net.sf.tools4csv.config.model.Target;
import net.sf.tools4csv.config.model.Write;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

public class CsvConfigTransformer {

	private static Logger logger = Logger.getLogger(CsvConfigTransformer.class);
	
	public static Configuration loadConfiguration(File file) throws Exception{
		logger.info("Loading configuration from file: " + file.getAbsolutePath());
		
		XmlOptions xmloptions = new XmlOptions();
		xmloptions.setDocumentType(XmlBeans.typeForClass(net.sf.tools4csv.xbeans.impl.CsvConfigurationDocumentImpl.class));
		
		net.sf.tools4csv.xbeans.CsvConfigurationDocument csvConfigDocument = null;
		try{
			csvConfigDocument = net.sf.tools4csv.xbeans.CsvConfigurationDocument.Factory.parse(file, xmloptions);
			
		} catch (IOException e){
			throw e;
		} catch (XmlException e){
			throw new Exception(e);
		}
		
		if(null == csvConfigDocument)
			return null;
		Configuration configuration = new Configuration();
		
		if(null != csvConfigDocument.getCsvConfiguration()){
			net.sf.tools4csv.xbeans.ConvertersDocument.Converters converters 
				= csvConfigDocument.getCsvConfiguration().getConverters();
			if(null != converters){
				net.sf.tools4csv.xbeans.ConverterDocument.Converter[] converterArray
					= converters.getConverterArray();
				configuration.setParallelConverters(converters.getParallel());
				configuration.setExitOnError(converters.getExitOnError());
				if(null != converterArray && converterArray.length > 0){
					for (net.sf.tools4csv.xbeans.ConverterDocument.Converter converter : converterArray) {
						if(!converter.getActive()){
							continue;
						}
						if(null != converter.getId() && !"".equals(converter.getId().trim())){
							configuration.addConverter(populateToConverter(converter));
						} else {
							throw new Exception("Illegal converter configuration...");
						}
					}
				}
			}
		}
		
		return configuration;
	}
	
	private static Converter populateToConverter(
			net.sf.tools4csv.xbeans.ConverterDocument.Converter converter) throws Exception{
		Converter modelConverter = new Converter();
		modelConverter.setId(converter.getId());
		modelConverter.setConverterType(ConverterTypeEnum.getConverterType(converter.getType().toString()));
		modelConverter.setMkdirs(converter.getMkdirs());
		if(null != converter.getOrder())
			modelConverter.setOrder(converter.getOrder().intValue());
		modelConverter.setOverwrite(converter.getOverwrite());
		modelConverter.setSourcePath(converter.getSourcePath());
		modelConverter.setTargetPath(converter.getTargetPath());
		
		if(null != converter.getSource() && null != converter.getTarget()){
			modelConverter.setSource(populateToSource(converter.getSource()));
			modelConverter.getSource().setFileName(
					modelConverter.getSourcePath() + File.separator + modelConverter.getSource().getFileName()
					);
			modelConverter.setTarget(populateToTarget(converter.getTarget()));
		} else {
			throw new Exception("Illegal converter configuration...");
		}
		
		return modelConverter;
	}
	
	private static Target populateToTarget(
			net.sf.tools4csv.xbeans.TargetDocument.Target target) throws Exception {
		Target modelTarget = new Target();
		modelTarget.setParallel(target.getParallel());
		if(null != target.getCollectArray() && target.getCollectArray().length > 0){
			for (int i = 0; i < target.getCollectArray().length; i++) {
				net.sf.tools4csv.xbeans.CollectDocument.Collect collect = 
						target.getCollectArray()[i];
				if(null != collect)
					modelTarget.addCollect(populateToCollect(collect));
			}
		}
		if(null != target.getWriteArray() && target.getWriteArray().length > 0){
			for (int i = 0; i < target.getWriteArray().length; i++) {
				net.sf.tools4csv.xbeans.WriteDocument.Write write = 
						target.getWriteArray()[i];
				if(null != write)
					modelTarget.addWrite(populateToWrite(write));
			}
		}
		return modelTarget;
	}

	private static Collect populateToCollect(
			net.sf.tools4csv.xbeans.CollectDocument.Collect collect) {
		Collect modelCollect = new Collect();
		if(null != collect){
			modelCollect.setTargetClassName(collect.getTargetClassName());
			net.sf.tools4csv.xbeans.PropertyDocument.Property[]  properties = collect.getPropertyArray();
			if(null != properties && properties.length > 0){
				for (net.sf.tools4csv.xbeans.PropertyDocument.Property property : properties) {
					Property modelProperty = new Property();
					
					modelProperty.setName(property.getName());
					modelProperty.setDefaultValue(property.getDefault());
					modelProperty.setMapTo(property.getMapTo());
					modelProperty.setFormat(property.getFormat());
					modelProperty.setPattern(property.getPattern());
					modelProperty.setType(property.getType().toString());
					if(null != property.getIndex())
						modelProperty.setIndex(property.getIndex().intValue());
					
					modelCollect.getProperties().add(modelProperty);
				}
			}
		}
		return modelCollect;
	}

	private static Write populateToWrite(
			net.sf.tools4csv.xbeans.WriteDocument.Write write) throws Exception {
		Write modelWrite = new Write();
		if(null != write){
			if(null != write.getBatchSize())
				modelWrite.setBatchSize(write.getBatchSize().intValue());
			modelWrite.setFrom(write.getFrom().intValue());
			if(null != write.getTo())
				modelWrite.setTo(write.getTo().intValue());
			net.sf.tools4csv.xbeans.ConnectionDocument.Connection connection = write.getConnection();
			if(null != connection){
				ConnectionProperties connectionProperties = new ConnectionProperties();
				connectionProperties.setUrl(connection.getUrl());
				connectionProperties.setDriverClass(connection.getDriverClass());
				connectionProperties.setUserName(connection.getUserName());
				connectionProperties.setPassword(connection.getPassword());
				modelWrite.setConnectionProperties(connectionProperties);
			}
			
			if(null != write.getSqlArray() && write.getSqlArray().length > 0){
				for(net.sf.tools4csv.xbeans.SqlDocument.Sql sql : write.getSqlArray()){
					Sql modelSql = new Sql();
					if(null != sql.getIndex())
						modelSql.setIndex(sql.getIndex().intValue());
					modelSql.setQuery(populateToQuery(sql.getQuery()));
					modelSql.setSelect(populateToSelect(sql.getSelect()));
					modelWrite.getSqls().add(modelSql);
				}
			}
			
			if(null != write.getColumnArray() && write.getColumnArray().length > 0){
				for (net.sf.tools4csv.xbeans.ColumnDocument.Column column : write.getColumnArray()) {
					Column modelColumn = populateToColumn(column);
					modelWrite.getColumns().add(modelColumn);
				}
			}
			
		} 
		return modelWrite;
	}

	private static Query populateToQuery(
			net.sf.tools4csv.xbeans.QueryDocument.Query query) {
		Query modelQuery = new Query();
		modelQuery.setNamedParam(query.getNamedParam());
		modelQuery.setText(query.getStringValue());
		return modelQuery;
	}

	private static Source populateToSource(
			net.sf.tools4csv.xbeans.SourceDocument.Source source) throws Exception {
		Source modelSource = new Source();
		modelSource.setFileName(source.getFile());
		modelSource.setHasHeader(source.getHasHeader());
		if(null != source.getSeparator()){
			modelSource.setSeparator(getSeprator(source.getSeparator()));
		}
		if(null != source.getSelect()){
			modelSource.setSelect(populateToSelect(source.getSelect()));
		} else {
			throw new Exception("Illegal converter configuration...");
		}
		
		return modelSource;
	}
	
	public static String getSeprator(String sep){
		if("\\t".equals(sep)){
			return new Character('\t').toString();
		}
		return sep;
	}

	private static Select populateToSelect(
			net.sf.tools4csv.xbeans.SelectDocument.Select select) {
		Select modelSelect = new Select();
		if(null != select.getFrom())
			modelSelect.setFrom(select.getFrom().intValue());
		if(null != select.getTo())
			modelSelect.setTo(select.getTo().intValue());
		if(null != select.getBatchSize())
			modelSelect.setBatchSize(select.getBatchSize().intValue());
		if(null != select.getColumnArray() && select.getColumnArray().length > 0){
			for (net.sf.tools4csv.xbeans.ColumnDocument.Column column : select.getColumnArray()) {
				Column modelColumn = populateToColumn(column);
				modelSelect.getColumns().add(modelColumn);
			}
		}
		return modelSelect;
	}

	private static Column populateToColumn(
			net.sf.tools4csv.xbeans.ColumnDocument.Column column) {
		Column modelColumn = new Column();
		modelColumn.setName(column.getName());
		if(null != column.getIndex())
			modelColumn.setIndex(column.getIndex().intValue());
		if(null != column.getType())
			modelColumn.setType(column.getType().toString());
		modelColumn.setHeader(column.getHeader());
		modelColumn.setDefaultValue(column.getDefault());
		modelColumn.setFormat(column.getFormat());
		modelColumn.setPattern(column.getPattern());
		modelColumn.setUseQuote(column.getUseQuote());
		return modelColumn;
	}

	public static void writeConfiguration(Configuration configuration, File file) throws Exception{
		
	}
}
