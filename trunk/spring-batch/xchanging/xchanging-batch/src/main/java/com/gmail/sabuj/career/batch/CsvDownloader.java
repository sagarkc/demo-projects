package com.gmail.sabuj.career.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.gmail.sabuj.career.common.util.IOUtil;

public class CsvDownloader implements Tasklet{

	private String sourceUrl;
	private String targetFileName;
	
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
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
		IOUtil.download(getSourceUrl(), getTargetFileName());
		System.out.println("CsvDownloader.execute");
		return null;
	}
	
}
