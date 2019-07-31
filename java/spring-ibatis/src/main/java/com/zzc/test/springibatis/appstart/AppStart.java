package com.zzc.test.springibatis.appstart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzc.test.springibatis.service.PersonService;

public class AppStart {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextiBatis.xml");
		PersonService service = (PersonService)ctx.getBean("personService");
		service.testQuery(101);
	}

}
