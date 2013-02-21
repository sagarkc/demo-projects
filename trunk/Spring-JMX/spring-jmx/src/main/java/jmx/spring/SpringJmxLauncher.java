package jmx.spring;

import jmx.spring.ui.SpringTestFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sabuj.das
 *
 */

public class SpringJmxLauncher {

	
	public static void main(String[] args) {
		ApplicationContext context =  new ClassPathXmlApplicationContext(
				new String[]{"context/application-context.xml"}
				);
		if(null != context){
			System.out.println("created");
			SpringTestFrame frame = new SpringTestFrame(context);
			frame.setVisible(true);
		}
		
	}

}
