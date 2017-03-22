package springbook.learningtest.spring.ioc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotatedHelloConfig {

	@Bean
	public annotatedHello annotatedHello() {
		return new annotatedHello();
	}
}
