package com.gmail.sabuj.career.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.gmail.sabuj.career.common.util.POIUtil;

public class CsvReverseProcessor implements Tasklet {

	private String sourceFileName;
	private String targetFileName;
	
	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		POIUtil.convert2Csv(getSourceFileName(), getTargetFileName());
		return null;
	}

}
