package com.zzc.test.mybatis.test;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;
import com.zzc.test.mybatis.zzz.DefaultSqlSession;
import com.zzc.test.mybatis.zzz.SqlSession;

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
        	System.out.println("\n=====================add s2 begin=====================");
	        sqlSession.insert("StudentID.add", s2);
	        System.out.println("=====================add s2 end=====================\n");
	        
	        //设置commit级别为false-不自动提交，再连续插入两条记录
	        //理论上讲，应该两条都插入失败，实际上第一条插入成功
	        //jdbc test里面，直接使用connection，是正常的
	        
	        //原因在于，DefaultSqlSession里：
	        //return (!autoCommit && dirty) || force 的布尔值为true时，才真正调用connection的rollback
	        //autoCommit在获取sqlSession的时候赋值的，所以也需要修改它的值才行
	        System.out.println("\n=====================set false begin=====================");
	        sqlSession.getConnection().setAutoCommit(false);
	        System.out.println("=====================set false end=====================\n");
	        
	        /*
	         * 验证上述场景,在DefaultSqlSession里加了setAutoCommit的方法
	         * 果然没有S3被插入数据库
	         */
	        if(sqlSession instanceof DefaultSqlSession) {
	        	((DefaultSqlSession) sqlSession).setAutoCommit(false);
	        }	        
	        
	        System.out.println(sqlSession.getConnection().getAutoCommit());	//false
	        
	        System.out.println("\n=====================add s3 begin=====================");
	        sqlSession.insert("StudentID.add", s3);
	        System.out.println("=====================add s3 end=====================\n");
	        
	        System.out.println("\n=====================add s3 begin=====================");
	        sqlSession.insert("StudentID.add", s3);
	        System.out.println("=====================add s3 end=====================\n");
	        
	        //手动提交
	        sqlSession.commit();
	        
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
