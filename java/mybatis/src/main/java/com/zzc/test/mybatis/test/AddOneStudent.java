package com.zzc.test.mybatis.test;

import org.apache.ibatis.session.SqlSession;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;

public class AddOneStudent {

	public static void main(String[] args) {
		
		Student s = new Student();
        s.setId(10000);
        s.setName("zzc");
        s.setSal(100.00);
        
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        
        try {	        
	        //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
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
