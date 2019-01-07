package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Test2SetCommit 
{
	public static void main (String[] args) throws SQLException
	{
        Connection connection = DBUtils.getConnection();
        
        //默认获取到的connection为true-自动提交
        System.out.println(connection.getAutoCommit());
        
        String sql = "INSERT INTO persons (P_id, FirstName, LastName, Address, City)" +
	                " VALUES(302, 'nihao','123','ss@qq.com','NJ')";
        
        try
        {
        	//修改为false
        	connection.setAutoCommit(false);
        	
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	
	        int resultSet = preparedStatement.executeUpdate();
	
	        System.out.println(resultSet);
	        
	        resultSet = preparedStatement.executeUpdate();
	
	        System.out.println(resultSet);
	        
	        connection.commit();
        }
        catch(Exception e)
        {
        	//两笔都回滚成功
        	e.printStackTrace();
        	connection.rollback();
        }
        finally
        {
        	connection.close();
        }
        
        //释放资源
//        DBUtils.release(connection, preparedStatement, null);
        
        
        
	}
}
