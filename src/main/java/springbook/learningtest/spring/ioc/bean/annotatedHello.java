package springbook.learningtest.spring.ioc.bean;

import org.springframework.stereotype.Component;

@Component("myAnn")
public class annotatedHello {

	String name;
	Printer printer;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	public String sayHello() {
		return "Hello " + name;
	}
	
	public void print() {
		this.printer.print(sayHello());
	}
}
