package springbook.learningtest.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class SimpleService {

	@Test
	public void simepleService() {
		ApplicationContext ac = new GenericXmlApplicationContext(SimpleService.class, "beanrole.xml");
		SimpleConfig sc = ac.getBean(SimpleConfig.class);
		
		sc.hello.sayHello();
		
		BeanDefinitionUtils.printBeanDefinitions(ac);
		
	}
	
	@Test
	public void getEnv() {
		ApplicationContext ac = new GenericXmlApplicationContext(SimpleService.class, "beanrole.xml");
		System.out.println(ac.getEnvironment().getProperty("os.name"));
		System.out.println(ac.getEnvironment().getProperty("Path"));
	}
}
