package ajdbc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import ajdbc.bean.Person;
import ajdbc.service.FootBallService;


public class FootBallServiceImpl101iBatis implements FootBallService {
	
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
