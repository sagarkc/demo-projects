package test.profile.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;

public class PrototypeMapJobRegistry extends MapJobRegistry {

	@Override
	public Job getJob(String name) throws NoSuchJobException {
		return super.getJob(name);
	}
	
}
