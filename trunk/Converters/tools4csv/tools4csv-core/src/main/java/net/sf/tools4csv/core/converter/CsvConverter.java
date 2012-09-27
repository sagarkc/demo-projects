package net.sf.tools4csv.core.converter;

import net.sf.tools4csv.config.model.Source;
import net.sf.tools4csv.config.model.Target;

public interface CsvConverter {

	String getId();
	void setId(String id);
	void setSource(Source source);
	void setTarget(Target target);
	void processConversion();
	Object getOutputData();
	
}
