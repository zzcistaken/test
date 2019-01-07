package com.zzc.test.mybatis.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil 
{
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	
	public static SqlSessionFactory sqlSessionFactory;
	
	static
	{
		try 
		{
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
            throw new RuntimeException(e);
		}
	}
	
	/**
	 * 禁止外界通过new方法创建 
	 */
	private MybatisUtil(){}
	
	
	/**
	 * 获取SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession()
	{
		//从当前线程中获取SqlSession对象
		SqlSession sqlSession = threadLocal.get();
		
		//如果SqlSession对象为空
		if(sqlSession == null)
		{
			//在SqlSessionFactory非空的情况下，获取SqlSession对象
			sqlSession = sqlSessionFactory.openSession();
			
			//将SqlSession对象与当前线程绑定在一起
			threadLocal.set(sqlSession);
		}
		
		return sqlSession;
	}
	
	
	public static SqlSession getSqlSession2()
	{
		//从当前线程中获取SqlSession对象
		SqlSession sqlSession = threadLocal.get();
		
		//如果SqlSession对象为空
		if(sqlSession == null)
		{
			//在SqlSessionFactory非空的情况下，获取SqlSession对象
			sqlSession = sqlSessionFactory.openSession(true);
			
			//将SqlSession对象与当前线程绑定在一起
			threadLocal.set(sqlSession);
		}
		
		return sqlSessionFactory.openSession(true);
	}
	
	
	/**
	 * 关闭SqlSession与当前线程分开
	 */
	public static void closeSqlSession()
	{
		//从当前线程中获取SqlSession对象
		SqlSession sqlSession = threadLocal.get();
		
		//如果SqlSession对象非空
		if(sqlSession != null)
		{
			//关闭SqlSession对象
			sqlSession.close();
			
			//分开当前线程与SqlSession对象的关系，目的是让GC尽早回收
			threadLocal.remove();
		}
	}
	
	
	public static void main(String[] args)
	{
		Connection conn = MybatisUtil.getSqlSession().getConnection();
		System.out.println(conn!=null?"连接成功":"连接失败");
	}
}
