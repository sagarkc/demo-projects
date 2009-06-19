package com.jnp.core;

/**
 *
 * @author sabuj.das
 */
public class XmlSplitterProperties extends SplitterProperties{

    private long totalNoOfRecords;
    private long numberOfRecordsPerPart;

    private String xsltFilePath;
    private String xsltFileName;

    private String rootElementName;
    private String recordElementName;

    public long getNumberOfRecordsPerPart() {
        return numberOfRecordsPerPart;
    }

    public void setNumberOfRecordsPerPart(long numberOfRecordsPerPart) {
        this.numberOfRecordsPerPart = numberOfRecordsPerPart;
    }

    public String getRecordElementName() {
        return recordElementName;
    }

    public void setRecordElementName(String recordElementName) {
        this.recordElementName = recordElementName;
    }

    public String getRootElementName() {
        return rootElementName;
    }

    public void setRootElementName(String rootElementName) {
        this.rootElementName = rootElementName;
    }

    public long getTotalNoOfRecords() {
        return totalNoOfRecords;
    }

    public void setTotalNoOfRecords(long totalNoOfRecords) {
        this.totalNoOfRecords = totalNoOfRecords;
    }

    public String getXsltFileName() {
        return xsltFileName;
    }

    public void setXsltFileName(String xsltFileName) {
        this.xsltFileName = xsltFileName;
    }

    public String getXsltFilePath() {
        return xsltFilePath;
    }

    public void setXsltFilePath(String xsltFilePath) {
        this.xsltFilePath = xsltFilePath;
    }

    @Override
    public boolean isValid() {
        boolean valid = super.isValid();
        
        return valid;
    }
}
