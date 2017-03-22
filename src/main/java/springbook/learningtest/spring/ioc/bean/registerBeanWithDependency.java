package springbook.learningtest.spring.ioc.bean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public class registerBeanWithDependency {
	
	@Test
	public void beginContainer() {
		// IoC �����̳� ����, ������ ���ÿ� �����̳ʷ� �����Ѵ�.
		StaticApplicationContext ac = new StaticApplicationContext();
		// Hello.class�� hello1 �̶�� �̸��� ������ ����Ѵ�.
		ac.registerSingleton("hello1", Hello.class);
		
		// test
		Hello hello1 = ac.getBean("hello1", Hello.class);
		assertThat(hello1, is(notNullValue()));
		
		// bean ��Ÿ������ ���� ������Ʈ�� �����.
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		ac.registerBeanDefinition("hello2", helloDef);
		
		Hello hello2 = ac.getBean("hello2", Hello.class);
		assertThat(hello2.sayHello(), is("Hello Spring"));
		
		assertThat(hello1, is(not(hello2)));
		
		assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
	}
	
	@Test
	public void registerBean() { 
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
		
		
		// ������Ƽ ������ ���� ���
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		// DI 
		helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
		ac.registerBeanDefinition("hello", helloDef);
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	}
	
	@Test
	public void genericApplicationContext() {
		GenericApplicationContext ac = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		reader.loadBeanDefinitions("springbook/learningtest/spring/ioc/bean/ApplicationContext.xml");
		ac.refresh();		// app �����̳� �ʱ�ȭ ���
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
				
	}
	
	@Test
	public void genericXmlApplicationContext() {
		GenericXmlApplicationContext ac = new GenericXmlApplicationContext("springbook/learningtest/spring/ioc/bean/ApplicationContext.xml");
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	}
	
	
	
	@Test
	public void simpleBeanScanning() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("springbook.learningtest.spring.ioc.bean");
		annotatedHello hello = ctx.getBean("myAnn", annotatedHello.class);
		
		assertThat(hello, is(notNullValue()));
		
	}
	
	@Test 
	public void sourceBeanTest() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);
		annotatedHello hello = ctx.getBean("annotatedHello", annotatedHello.class);
		
		assertThat(hello, is(notNullValue()));
		
		AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);
		assertThat(config, is(notNullValue()));
		
		assertThat(config.annotatedHello(), is(not(sameInstance(hello))));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
