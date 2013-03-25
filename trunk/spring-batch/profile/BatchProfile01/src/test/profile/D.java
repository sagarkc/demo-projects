package test.profile;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

public class D implements InitializingBean, ItemReader<String> {

	private static int instanceCount = 0;

	public D() {
		instanceCount++;
		System.out.println("D:: Instance created with ID: " + hashCode());
		System.out.println("D:: Instance count: " + instanceCount);
	}

	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		System.out.println("D:: Instance GC with ID: " + hashCode());
		System.out.println("D:: Instance count: " + instanceCount);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	private boolean hasRun = false;

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException {

		if (!hasRun) {
			hasRun = true;
			return "Hello from D...";
		}
		return null;
	}
}
