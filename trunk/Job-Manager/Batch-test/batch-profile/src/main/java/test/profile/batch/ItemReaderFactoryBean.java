package test.profile.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ItemReaderFactoryBean implements FactoryBean<ItemReader<?>>, ApplicationContextAware {
	
	private String readerBeanName;
	private ApplicationContext applicationContext;
	
	public ItemReaderFactoryBean(String name){
		readerBeanName = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public ItemReader<?> getObject() throws Exception {
		return applicationContext.getBean(readerBeanName, ItemReader.class);
	}

	@Override
	public Class<?> getObjectType() {
		return ItemReader.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	
}
