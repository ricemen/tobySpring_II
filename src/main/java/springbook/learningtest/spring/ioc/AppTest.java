package springbook.learningtest.spring.ioc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import springbook.learningtest.spring.ioc.bean.Hello;
import springbook.learningtest.spring.ioc.bean.Printer;

public class AppTest {

	private String basePath = StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(getClass())) + "/";
	
	@Test
	public void appTest() {
		ApplicationContext parent = new GenericXmlApplicationContext(basePath + "parentContext.xml");
		GenericApplicationContext child = new GenericApplicationContext(parent);
		
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions(basePath + "childContext.xml");
		child.refresh();
		
		Hello hello = child.getBean("hello", Hello.class);
		hello.print();
		
		Printer printer = child.getBean("printer", Printer.class);
		
		assertThat(printer, is(notNullValue()));
		assertThat(printer.toString(), is("Hello Child"));
		
		Hello hello1 = parent.getBean("hello", Hello.class);
		//hello1.print();
		
		assertThat(parent.getBean("printer").toString(), is("Hello"));
		
		
	}
	
}
