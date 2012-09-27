package net.sf.tools4csv.core;

/**
 * @author sabuj.das
 *
 */
public final class CsvCoreContext {

	private static CsvCoreContext context;
	private CsvCoreContext() {	}
	
	public static CsvCoreContext getContext() {
		synchronized (CsvCoreContext.class) {
			if(null == context)
				context = new CsvCoreContext();
		}
		return context;
	}
	
	
}
