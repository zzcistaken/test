package com.zzc.test.springibatis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzc.test.springibatis.dao.PersonDao;
import com.zzc.test.springibatis.dto.Person;
import com.zzc.test.springibatis.zzz.SqlMapClientTemplate;

public class PersonDaoImpl implements PersonDao {
	
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public void insert(Person person) {
		sqlMapClientTemplate.insert("personiBatis.insert", person);
	}

	public Person queryById(Integer id) {
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("id", id);
	  return (Person)sqlMapClientTemplate.queryForObject("personiBatis.find", map);
	}
	
	@SuppressWarnings("unchecked")
	 public List<Person> queryAll() {
		return (List<Person>)sqlMapClientTemplate.queryForList("personiBatis.find", null);
	 }
}
