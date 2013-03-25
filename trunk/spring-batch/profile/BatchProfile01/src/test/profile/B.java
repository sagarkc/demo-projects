package test.profile;

import org.springframework.batch.item.ItemProcessor;

public class B implements ItemProcessor<String, String>{

	private static int instanceCount = 0;
	public B(){
		instanceCount++;
		System.out.println("B:: Instance created with ID: " + hashCode());
		System.out.println("B:: Instance count: " + instanceCount);
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		System.out.println("B:: Instance GC with ID: " + hashCode());
		System.out.println("B:: Instance count: " + instanceCount);
	}
	
	@Override
	public String process(String b) throws Exception {
		return b;
	}
}
