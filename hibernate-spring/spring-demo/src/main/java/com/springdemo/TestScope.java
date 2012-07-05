package com.springdemo;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springdemo.Address.HomeAddress;

public class TestScope {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = null;
		try {
            applicationContext = new ClassPathXmlApplicationContext(
                    new String[] { "/application-context.xml"});
            System.out.println("ApplicationContext is loaded.....................................");
        } catch (Exception e) {
        	System.out.println("Cannot Load ApplicationContext.....................................");
        	e.printStackTrace();
            System.exit(0);
        }
		
		if(null != applicationContext){
			/*BeanFactory f = applicationContext;
			BeanFactory bf = applicationContext.getBeanFactory();
			TimeSheet s = (TimeSheet) applicationContext.getBean("t2");
			System.out.println(s.getAreaCode());
			HomeAddress hs = (HomeAddress)applicationContext.getBean("haddress");
			System.out.println(hs.getCity());*/
			TimeSheet s = (TimeSheet) applicationContext.getBean("t1");
			System.out.println(s.getAreaCode());
			/*File f = (File) applicationContext.getBean("file");
			if(f.exists()){
				System.out.println(f.getAbsolutePath());
			}*/
		}
	}

}
