package com.zzc.test.springibatis.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.zzc.test.springibatis.dao.PersonDao;
import com.zzc.test.springibatis.dto.Person;

public class PersonService {
	
	private PersonDao dao;
	private TransactionTemplate transactionTemplate;

	public void setDao(PersonDao dao) {
		this.dao = dao;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

	
	public void testQuery (int id) {
		Person p = dao.queryById(id);
		System.out.println(p.getName());
	}
	
	public void testInsertTwoPersons(Person p) {
		dao.insert(p);
		System.out.println("======================insert one success===================");
		dao.insert(p);
	}
	
	public void testInsertTwoPersonsUseTransaction(final Person p) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				dao.insert(p);
				System.out.println("======================insert one success===================");
				dao.insert(p);
			}
			
		});
	}
	
}
