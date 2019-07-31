package com.zzc.test.springibatis.dao;

import java.sql.SQLException;
import java.util.List;

import com.zzc.test.springibatis.dto.Person;

public interface PersonDao {
	public void insert(Person person);
    public Person queryById(Integer id);
    public List<Person> queryAll();
    public void commit() throws SQLException;
    public void start () throws SQLException;
}
