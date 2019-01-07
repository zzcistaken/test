package com.zzc.test.mybatis.test;

import org.apache.ibatis.session.SqlSession;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;

public class AddTwoStudent {

	public static void main(String[] args) {
		
		Student s = new Student();
        s.setId(10001);
        s.setName("zzc-1");
        s.setSal(101.00);
        
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        
        try {	        
	        //连续插入两条记录，两个都回滚
	        sqlSession.insert("StudentID.add", s);
	        sqlSession.insert("StudentID.add", s);
	        
	        //手动提交
	        sqlSession.commit();
	        
	        System.out.println("add success.");
        } catch(Exception e) {
        	e.printStackTrace();
            sqlSession.rollback();
            System.out.println("add fail");
        } finally {
        	sqlSession.close();
        	System.out.println("close session.");
        }
        
	}

}
