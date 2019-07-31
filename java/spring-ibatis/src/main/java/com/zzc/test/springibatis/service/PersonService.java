package com.zzc.test.springibatis.service;

import java.sql.SQLException;

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
	
	public void insertOnePerson (Person p) {
		dao.insert(p);
		System.out.println("======================insert one success===================");
	}
	
	//在dataSource的defaultAutoCommit为默认值true时，第一次Insert可以成功
	//在dataSource的defaultAutoCommit设置为false后，第一次insert也回滚了
	public void testInsertTwoPersons(Person p) {
		System.out.println("======================start insert one===================");
		dao.insert(p);
		System.out.println("======================insert one success===================");
		dao.insert(p);
	}
	
	//那么，我们在第一次插入后面加入commit操作，看一下效果
	//这种方式并没有成功。。。
	public void testInsertTwoPersonsWithCommit(Person p) {
		System.out.println("======================start insert one===================");
		try {
			dao.start();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		dao.insert(p);
		try {
			dao.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
