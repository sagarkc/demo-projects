package test.profile;

import java.util.Random;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

public class D implements InitializingBean, ItemReader<String> {

	private static int instanceCount = 0;

	public D() {
		instanceCount++;
		//System.out.println("D:: Instance created with ID: " + hashCode());
		//System.out.println("D:: Instance count: " + instanceCount);
	}

	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		//System.out.println("D:: Instance GC with ID: " + hashCode());
		//System.out.println("D:: Instance count: " + instanceCount);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	private String input;
	private boolean hasRun = false;

	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException {

		if (!hasRun) {
			hasRun = true;
			//Thread.sleep(1000*25);
			Random rand = new Random(35);
			int x = rand.nextInt();
			if(x % 13 == 0 || x % 19 == 0 || x % 11 == 0){
				throw new Exception("Unknown error: [ " + x + " ]");
			}
			return input + " from D...";
		}
		return null;
	}
}
