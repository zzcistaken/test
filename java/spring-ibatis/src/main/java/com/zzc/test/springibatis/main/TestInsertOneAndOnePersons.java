package com.zzc.test.springibatis.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzc.test.springibatis.dto.Person;
import com.zzc.test.springibatis.service.PersonService;

public class TestInsertOneAndOnePersons {

	public static void main(String[] args) {
		
		Person p = new Person();
		p.setId((long) 101);
		p.setName("zzc-1");
		
		
		System.out.println("\n======================ClassPathXmlApplicationContext begin======================\n");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextiBatis.xml");
        System.out.println("\n======================ClassPathXmlApplicationContext end======================\n");
    
        System.out.println("\n======================getBean(personService) begin======================\n");
    	PersonService service = (PersonService)ctx.getBean("personService");
    	System.out.println("\n======================getBean(personService) end======================\n");

    	System.out.println("\n======================insertOnePerson begin======================\n");
    	service.insertOnePerson(p);;
    	System.out.println("\n======================insertOnePerson end======================\n");
    	
    	System.out.println("\n======================insert Another OnePerson begin======================\n");
    	service.insertOnePerson(p);
    	System.out.println("\n======================insert Another OnePerson end======================\n");

	}

}
