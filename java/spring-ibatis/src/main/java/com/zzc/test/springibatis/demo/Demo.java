package com.zzc.test.springibatis.demo;

import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zzc.test.springibatis.dto.Person;

public class Demo {
	
	private JdbcTemplate jdbcTemplate;
	private DataSourceTransactionManager transManager;
	private TransactionStatus status;

	public void setTransManager(DataSourceTransactionManager transManager) {
		this.transManager = transManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//开启事务
	public void beginTransaction() {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();//事务定义类
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		status = transManager.getTransaction(def);// 返回事务对象
		System.out.println("beginTransaction()");
	}
	
	//结束事务
	public void endTransaction() {
		try {
			transManager.commit(status);
	    } catch (Exception ex) {
	    	transManager.rollback(status);
	    }
	}
	
	public void init () {
		System.out.println("================================\n");
		
		beginTransaction();
		
		try {
			String sql="update person set name=? where id=?";
			jdbcTemplate.update(sql,new Object[]{"zzc-1001",101});
			
			sql="select id,name from person where id=?";
			RowMapper<Person> rowMapper=new BeanPropertyRowMapper<Person>(Person.class);
			Person user= jdbcTemplate.queryForObject(sql, rowMapper,101);
			System.out.println(user.getName());
			
			sql="insert into person (id, name) values (?,?)";
			int count= jdbcTemplate.update(sql, new Object[]{102, "zzc-102"});
			System.out.println(count);
			
			transManager.commit(status);
			System.out.println("commit");
			
		} catch (Exception e) {
			transManager.rollback(status);
			System.out.println("rollback");
		}		
	}
}
