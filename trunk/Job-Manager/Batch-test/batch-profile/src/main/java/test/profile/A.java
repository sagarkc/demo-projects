package test.profile;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

public class A implements InitializingBean, ItemReader<String> {
	private static Logger logger = Logger.getLogger(A.class);
	private static int instanceCount = 0;

	public A() {
		instanceCount++;
		//System.out.println("A:: Instance created with ID: " + hashCode());
		//System.out.println("A:: Instance count: " + instanceCount);
	}
 
	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		//System.out.println("A:: Instance GC with ID: " + hashCode());
		//System.out.println("A:: Instance count: " + instanceCount);
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
		logger.info("Start Reader ........");
		if (!hasRun) {
			hasRun = true;
			//Thread.sleep(1000*20);
			Random rand = new Random(35);
			int x = rand.nextInt();
			if(x % 31 == 0 || x % 17 == 0){
				throw new Exception("Unknown error: [ " + x + " ]");
			}
			return input + " from A...";
		}
		return null;
	}
}