package com.zzc.test.mybatis.test;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;
import com.zzc.test.mybatis.zzz.SqlSession;

public class AddTwoStudent {

	public static void main(String[] args) {
		
		Student s = new Student();
        s.setId(10001);
        s.setName("zzc-1");
        s.setSal(101.00);
        
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        
        try {
        	//默认的commit级别是false-不自动提交
//        	System.out.println(sqlSession.getConnection().getAutoCommit());
        	
        	//连续插入两条记录，两个都回滚
	        sqlSession.insert("StudentID.add", s);
	        System.out.println("=========================add one over=====================");
	        sqlSession.insert("StudentID.add", s);
	        
	        //手动提交
	        sqlSession.commit();
	        
	        System.out.println("add success.");
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("\n");
        	System.out.println("=======================roll back begin=======================");
            sqlSession.rollback();
            System.out.println("=======================roll back end=======================");
            System.out.println("\n");
        } finally {
        	System.out.println("\n");
        	System.out.println("=======================close begin=======================");
        	sqlSession.close();
        	System.out.println("=======================close end=======================");
        	System.out.println("\n");
        }
        
	}

}
