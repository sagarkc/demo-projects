
package com.jnp.core;

import com.jnp.JnpConstants;

/**
 *
 * @author sabuj.das
 */
public class SplitterProperties {

    public static final String PART_SUFFIX = "_PART_";
    public static final int PART_COUNT_START = 1;

    private String inputFilePath;
    private String inputFileName;
    private String outputDirName;
    private int maximumSizePerPart = -1;
    private String maximumSizeUnit = JnpConstants.INVALID_SIZE_TEXT;

    private long totoalLines;
    private double fileSize;
    private double fileSizeInBytes;

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public double getFileSizeInBytes() {
        return fileSizeInBytes;
    }

    public void setFileSizeInBytes(double fileSizeInBytes) {
        this.fileSizeInBytes = fileSizeInBytes;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputDirName() {
        return outputDirName;
    }

    public void setOutputDirName(String outputDirName) {
        this.outputDirName = outputDirName;
    }

    public long getTotoalLines() {
        return totoalLines;
    }

    public void setTotoalLines(long totoalLines) {
        this.totoalLines = totoalLines;
    }
        public int getMaximumSizePerPart() {
        return maximumSizePerPart;
    }

    public void setMaximumSizePerPart(int maximumSizePerPart) {
        this.maximumSizePerPart = maximumSizePerPart;
    }

    public String getMaximumSizeUnit() {
        return maximumSizeUnit;
    }

    public void setMaximumSizeUnit(String maximumSizeUnit) {
        this.maximumSizeUnit = maximumSizeUnit;
    }

    public boolean isValid(){
        boolean valid = true;

        

        return valid;
    }
}
