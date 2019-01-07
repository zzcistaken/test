package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test5SetCommit 
{
	public static void main (String[] args) throws SQLException
	{
		/*
        * 我们来模拟A向B账号转账的场景
        *   A和B账户都有1000块，现在我让A账户向B账号转500块钱
        */
		
		Connection connection = DBUtils.getConnection();
		
        //JDBC默认的情况下是关闭事务的，下面我们看看关闭事务去操作转账操作有什么问题

        //A账户减去500块
        String sql = "UPDATE a SET money=money-500 ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        //B账户多了500块
        String sql2 = "UPDATE b SET money=money+500";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.executeUpdate();
        
        //----------------------------------------------------------//
        
        //A账户减去500块
        String sql3 = "UPDATE a SET money=money-500 ";
        preparedStatement = connection.prepareStatement(sql3);
        preparedStatement.executeUpdate();
        
        //这里模拟出现问题
        int a = 3 / 0;


        String sql4 = "UPDATE b SET money=money+500";
        preparedStatement = connection.prepareStatement(sql4);
        preparedStatement.executeUpdate();
        
        
        //---------------------------------------------------//
        
        
      //开启事务,对数据的操作就不会立即生效。
      try
      {
        connection.setAutoCommit(false);
        
        //A账户减去500块
        String sql5 = "UPDATE a SET money=money-500 ";
        preparedStatement = connection.prepareStatement(sql5);
        preparedStatement.executeUpdate();

        //在转账过程中出现问题
        int b = 3 / 0;

        //B账户多500块
        String sql6 = "UPDATE b SET money=money+500";
        preparedStatement = connection.prepareStatement(sql6);
        preparedStatement.executeUpdate();
        
        //如果程序能执行到这里，没有抛出异常，我们就提交数据
        connection.commit();

        //关闭事务【自动提交】
        connection.setAutoCommit(true);
        

    } 
      catch (SQLException e) 
      {
        try {
            //如果出现了异常，就会进到这里来，我们就把事务回滚【将数据变成原来那样】
            connection.rollback();
            
            //关闭事务【自动提交】
            connection.setAutoCommit(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
      }
	}
}
