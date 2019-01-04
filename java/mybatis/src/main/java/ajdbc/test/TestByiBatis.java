package ajdbc.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ajdbc.bean.Person;
import ajdbc.service.FootBallService;
import junit.framework.TestCase;

public class TestByiBatis extends TestCase {
	ApplicationContext ctx = null;
	 
    @Override
    protected void setUp() throws Exception {
    	System.out.println("%%%%%%%%%%%%%");
        ctx = new ClassPathXmlApplicationContext("applicationContextiBatis.xml");
    }
 
    /**
     * ²âÊÔiBatis
     */
    public void testiBatis(){
     FootBallService footBallService = (FootBallService)ctx.getBean("footBallServiceImpl101iBatis");
     // insert·½·¨
//     footBallService.insert(new Person("Ææ¼£"));
     Person ps = footBallService.queryById(90);
     System.out.println(ps.getId() + "-->"+ ps.getName());
     System.out.println("-----------------------------------------------");
     List<Person> personLs = footBallService.queryAll();
     for(Person p : personLs){
      System.out.println(p.getId() + "-->"+ p.getName());
     }
    }
}
