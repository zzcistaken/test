package ajdbc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import ajdbc.bean.Person;
import ajdbc.service.FootBallService;

public class FootBallServiceImpl101iBatis2 extends SqlMapClientDaoSupport implements FootBallService {
	
	public void insert(Person person) {
		this.getSqlMapClientTemplate().insert("personiBatis.insert", person);
	}
	
	public Person queryById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return (Person)this.getSqlMapClientTemplate().queryForObject("personiBatis.find", map);
	}

	@SuppressWarnings("unchecked")
	public List<Person> queryAll() {
		return (List<Person>)this.getSqlMapClientTemplate().queryForList("personiBatis.find", null);
	}
}
