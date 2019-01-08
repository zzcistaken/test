package com.zzc.test.mybatis.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;

import com.zzc.test.mybatis.zzz.SqlSession;
import com.zzc.test.mybatis.zzz.SqlSessionFactory;
import com.zzc.test.mybatis.zzz.SqlSessionFactoryBuilder;

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
	 * 绂佹澶栫晫閫氳繃new鏂规硶鍒涘缓 
	 */
	private MybatisUtil(){}
	
	
	/**
	 * 鑾峰彇SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession()
	{
		//浠庡綋鍓嶇嚎绋嬩腑鑾峰彇SqlSession瀵硅薄
		SqlSession sqlSession = threadLocal.get();
		
		//濡傛灉SqlSession瀵硅薄涓虹┖
		if(sqlSession == null)
		{
			//鍦⊿qlSessionFactory闈炵┖鐨勬儏鍐典笅锛岃幏鍙朣qlSession瀵硅薄
			sqlSession = sqlSessionFactory.openSession();
			
			//灏哠qlSession瀵硅薄涓庡綋鍓嶇嚎绋嬬粦瀹氬湪涓�璧�
			threadLocal.set(sqlSession);
		}
		
		return sqlSession;
	}
	
	
	public static SqlSession getSqlSession2()
	{
		//浠庡綋鍓嶇嚎绋嬩腑鑾峰彇SqlSession瀵硅薄
		SqlSession sqlSession = threadLocal.get();
		
		//濡傛灉SqlSession瀵硅薄涓虹┖
		if(sqlSession == null)
		{
			//鍦⊿qlSessionFactory闈炵┖鐨勬儏鍐典笅锛岃幏鍙朣qlSession瀵硅薄
			sqlSession = sqlSessionFactory.openSession(true);
			
			//灏哠qlSession瀵硅薄涓庡綋鍓嶇嚎绋嬬粦瀹氬湪涓�璧�
			threadLocal.set(sqlSession);
		}
		
		return sqlSessionFactory.openSession(true);
	}
	
	
	/**
	 * 鍏抽棴SqlSession涓庡綋鍓嶇嚎绋嬪垎寮�
	 */
	public static void closeSqlSession()
	{
		//浠庡綋鍓嶇嚎绋嬩腑鑾峰彇SqlSession瀵硅薄
		SqlSession sqlSession = threadLocal.get();
		
		//濡傛灉SqlSession瀵硅薄闈炵┖
		if(sqlSession != null)
		{
			//鍏抽棴SqlSession瀵硅薄
			sqlSession.close();
			
			//鍒嗗紑褰撳墠绾跨▼涓嶴qlSession瀵硅薄鐨勫叧绯伙紝鐩殑鏄GC灏芥棭鍥炴敹
			threadLocal.remove();
		}
	}
	
	
	public static void main(String[] args)
	{
		Connection conn = MybatisUtil.getSqlSession().getConnection();
		System.out.println(conn!=null?"杩炴帴鎴愬姛":"杩炴帴澶辫触");
	}
}
