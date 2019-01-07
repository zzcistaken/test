package com.zzc.test.mybatis.test;

import org.apache.ibatis.session.SqlSession;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;

public class AddTwoStudentSetCommit {

	public static void main(String[] args) {
		
		Student s2 = new Student();
        s2.setId(10002);
        s2.setName("zzc-2");
        s2.setSal(102.00);
        
        Student s3 = new Student();
        s3.setId(10003);
        s3.setName("zzc-3");
        s3.setSal(103.00);
        
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession2();
        
        try {
        	//设置commit级别是true-自动提交
        	System.out.println(sqlSession.getConnection().getAutoCommit());	//true
        	
	        //先插入一条记录，自动提交
	        sqlSession.insert("StudentID.add", s2);
	        
	        //设置commit级别为false-不自动提交，再连续插入两条记录
	        //理论上讲，应该两条都插入失败，实际上第一条插入成功
	        sqlSession.getConnection().setAutoCommit(false);
	        System.out.println(sqlSession.getConnection().getAutoCommit());	//false
	        sqlSession.insert("StudentID.add", s3);
	        sqlSession.insert("StudentID.add", s3);
	        
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
