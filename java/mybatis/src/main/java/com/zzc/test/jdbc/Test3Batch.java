package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test3Batch 
{
	public static void main (String[] args) throws SQLException
	{
		/*
	        * Statement执行批处理
	        *
	        * 优点：
	        *       可以向数据库发送不同的SQL语句
	        * 缺点：
	        *       SQL没有预编译
	        *       仅参数不同的SQL，需要重复写多条SQL
	        * */
	        Connection connection = DBUtils.getConnection();

	        Statement statement = connection.createStatement();
	        String sql1 = "UPDATE persons SET FirstName='Ben' WHERE P_id='1'";
	        String sql2 = "INSERT INTO persons (FirstName, LastName, Address, City)" +
	                " VALUES('nihao','123','ss@qq.com','NJ')";

	        //将sql添加到批处理
	        statement.addBatch(sql1);
	        statement.addBatch(sql2);

	        //执行批处理
	        statement.executeBatch();

	        //清空批处理的sql
	        statement.clearBatch();

	        DBUtils.release(connection, statement, null);
	}
}
