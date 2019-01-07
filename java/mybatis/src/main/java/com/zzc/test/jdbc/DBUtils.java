package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils 
{
 	/*
    * 连接数据库的driver，url，username，password通过配置文件来配置，可以增加灵活性
    * 当我们需要切换数据库的时候，只需要在配置文件中改以上的信息即可
    */

    private static String  url = null;
    private static String  username = null;
    private static String password = null;

    static 
    {
        url = "jdbc:mysql://localhost:3306/zzc?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		username = "root";
		password = "zzc";

    }

    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(url,username,password);
    }
	    
    public static void release(Connection connection, Statement statement, ResultSet resultSet) 
    {
        
        if (resultSet != null) 
        {
            try 
            {
                resultSet.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
