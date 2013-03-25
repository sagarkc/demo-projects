package test.profile;

import java.util.ArrayList;
import java.util.List;

public class P {

	private static int instanceCount = 0;
	public P(){
		instanceCount++;
		System.out.println("P:: Instance created with ID: " + hashCode());
		System.out.println("P:: Instance count: " + instanceCount);
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		System.out.println("P:: Instance GC with ID: " + hashCode());
		System.out.println("P:: Instance count: " + instanceCount);
	}
	
	
	private List<S> steps = new ArrayList<S>();
	
	public List<S> getSteps() {
		return steps;
	}

	public void setSteps(List<S> steps) {
		this.steps = steps;
	}
	
}
