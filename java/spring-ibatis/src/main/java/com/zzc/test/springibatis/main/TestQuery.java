package com.zzc.test.springibatis.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzc.test.springibatis.dto.Person;
import com.zzc.test.springibatis.service.PersonService;

public class TestQuery {

	public static void main(String[] args) {
		System.out.println("\n======================ClassPathXmlApplicationContext begin======================\n");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextiBatis.xml");
        System.out.println("\n======================ClassPathXmlApplicationContext end======================\n");
    
        System.out.println("\n======================getBean(personService) begin======================\n");
    	PersonService service = (PersonService)ctx.getBean("personService");
    	System.out.println("\n======================getBean(personService) end======================\n");

    	System.out.println("\n======================service.testQuery(90) begin======================\n");
    	service.testQuery(90);
    	System.out.println("\n======================service.testQuery(90) end======================\n");

	}

}
