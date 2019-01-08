package com.zzc.test.mybatis.test;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;
import com.zzc.test.mybatis.zzz.SqlSession;

public class SelectOneStudent {

	public static void main(String[] args) throws Exception {
		System.out.println(findById(10000).getName());
	}
	
	public static Student findById(int id) throws Exception {

		SqlSession sqlSession = MybatisUtil.getSqlSession();
        
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        try{
        	System.out.println("======================get selectOne begin======================");
            return sqlSession.selectOne("StudentID.findById",id);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
        	System.out.println("======================get selectOne end======================");
            MybatisUtil.closeSqlSession();
        }
    }

}
