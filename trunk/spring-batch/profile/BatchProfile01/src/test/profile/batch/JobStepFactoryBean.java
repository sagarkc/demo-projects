package test.profile.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JobStepFactoryBean implements ApplicationContextAware, FactoryBean<TaskletStep> {

    private ApplicationContext applicationContext;
    private String stepName;

    public JobStepFactoryBean(String stepName) {
        this.stepName = stepName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public TaskletStep getStep() {
        return (TaskletStep) this.applicationContext.getBean(stepName);
    }

    @Override
    public TaskletStep getObject() throws Exception {
        return getStep();
    }

    @Override
    public Class<?> getObjectType() {
        return TaskletStep.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
