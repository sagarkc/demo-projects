package test.profile.batch;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.core.JobRunShell;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.quartz.JobDetailBean;


public class CustomTaskExecutor extends SimpleAsyncTaskExecutor {

	private String threadName;

	private Map<String, Integer> threadCountMap = new HashMap<String, Integer>();

	private boolean locked = false;
	private static int count =0;

	@Override
	public Thread createThread(Runnable runnable) {
//		 Integer count = threadCountMap.get(threadName);
//		 if (count == null) {
//		 count = 0;
//		 }
//		 threadCountMap.put(threadName, count);
		Thread thread = new Thread(getThreadGroup(), runnable, threadName+"-"+(count++));
		thread.setPriority(getThreadPriority());
		thread.setDaemon(isDaemon());
		locked = false;
		return thread;
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		// TODO Auto-generated method stub
		super.execute(task, startTimeout);
	}

	@Override
	public void execute(Runnable task) {
		// TODO Auto-generated method stub
		if (locked) {
			System.err.println("Potentially misnamed thread!!!!!!!!!!!!!!!!!");
		}
		locked = true;
		JobRunShell shell = (JobRunShell) task;

		try {
			JobExecutionContext ctx = (JobExecutionContext) getField(shell, "jec");
			JobDetailBean detail = (JobDetailBean) getField(ctx, "jobDetail");
			threadName = (String) getField(detail, "beanName");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.execute(task);
	}

	public CustomTaskExecutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomTaskExecutor(String threadNamePrefix) {
		super(threadNamePrefix);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the value of the field on the specified object. The name
	 * parameter is a <code>String</code> specifying the simple name of the
	 * desired field.
	 * <p>
	 * 
	 * The object is first searched for any matching field. If no matching field
	 * is found, the superclasses are recursively searched.
	 * 
	 * @exception NoSuchFieldException
	 *                if a field with the specified name is not found.
	 */
	public static Object getField(Object object, String name) throws NoSuchFieldException {
		if (object == null) {
			throw new IllegalArgumentException("Invalid null object argument");
		}
		for (Class cls = object.getClass(); cls != null; cls = cls.getSuperclass()) {
			try {
				Field field = cls.getDeclaredField(name);
				field.setAccessible(true);
				return field.get(object);
			} catch (Exception ex) {
				/*
				 * in case of an exception, we will throw a new
				 * NoSuchFieldException object
				 */
			}
		}
		throw new NoSuchFieldException("Could get value for field " + object.getClass().getName() + "." + name);
	}
}
