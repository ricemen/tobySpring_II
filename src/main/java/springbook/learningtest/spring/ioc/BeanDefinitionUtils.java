package springbook.learningtest.spring.ioc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 스프링이 등록한 빈 정보 조회 
 * @author wcho
 *
 */
public class BeanDefinitionUtils {

	public static void printBeanDefinitions(ApplicationContext ac) {
		List<List<String>> roleBeanInfos = new ArrayList<List<String>>();
		roleBeanInfos.add(new ArrayList<>());
		roleBeanInfos.add(new ArrayList<>());
		roleBeanInfos.add(new ArrayList<>()); 
		
		for(String name : ac.getBeanDefinitionNames()) {
			int role = ((GenericApplicationContext) ac).getBeanDefinition(name).getRole();
			List<String> beanInfos = roleBeanInfos.get(role);
			beanInfos.add(role + "\t" + name + "\t" + ac.getBean(name).getClass().getName());			
		}
		
		for(List<String> beanInfos : roleBeanInfos) {
			for(String beanInfo : beanInfos) {
				System.out.println(beanInfo);
			}
		}
	}
}
