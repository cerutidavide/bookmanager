package com.ceruti.bookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookmanagerApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext( BatchConfiguration.class);
//		JdbcTemplate jdbct= (JdbcTemplate) context.getBean("applicationJdbcTemplate");
//		System.out.println(jdbct.getDataSource().toString());
		applicationContext=SpringApplication.run(BookmanagerApplication.class, args);
		int n=0;
		for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {

			System.out.println(n+" "+beanDefinitionName);
			n++;
		}



	}



}
