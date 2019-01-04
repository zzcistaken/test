package ajdbc.service;

import java.util.List;

import ajdbc.bean.Person;

public interface FootBallService {
	public void insert(Person person);
    public Person queryById(Integer id);
    public List<Person> queryAll();
}
