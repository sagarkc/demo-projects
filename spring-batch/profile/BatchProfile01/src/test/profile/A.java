package test.profile;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

public class A implements InitializingBean, ItemReader<String> {

	private static int instanceCount = 0;

	public A() {
		instanceCount++;
		System.out.println("A:: Instance created with ID: " + hashCode());
		System.out.println("A:: Instance count: " + instanceCount);
	}

	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		System.out.println("A:: Instance GC with ID: " + hashCode());
		System.out.println("A:: Instance count: " + instanceCount);
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
			return "Hello from A...";
		}
		return null;
	}
}
