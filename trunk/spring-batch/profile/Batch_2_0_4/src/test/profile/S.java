package test.profile;

public class S {

	private static int instanceCount = 0;
	public S(){
		instanceCount++;
		System.out.println("S:: Instance created with ID: " + hashCode());
		System.out.println("S:: Instance count: " + instanceCount);
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		instanceCount--;
		System.out.println("S:: Instance GC with ID: " + hashCode());
		System.out.println("S:: Instance count: " + instanceCount);
	}
	
	private A a;
	private B b;
	private C c;
	
	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	public C getC() {
		return c;
	}

	public void setC(C c) {
		this.c = c;
	}
	
	
}
