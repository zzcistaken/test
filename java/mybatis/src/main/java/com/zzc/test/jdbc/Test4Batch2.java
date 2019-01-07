package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test4Batch2 
{
	public static void main(String[] args) throws SQLException 
	{
		/*
	        * PreparedStatement批处理
	        *   优点：
	        *       SQL语句预编译了
	        *       对于同一种类型的SQL语句，不用编写很多条
	        *   缺点：
	        *       不能发送不同类型的SQL语句
	        *
	        * */
	        Connection connection = DBUtils.getConnection();

	        String sql = "INSERT INTO persons (FirstName,LastName) VALUES (?,?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);

	        for (int i = 1; i <= 205; i++) 
	        {
	            preparedStatement.setString(1, "first_" + i);
	            preparedStatement.setString(2, "last_" + i);

	            //添加到批处理中
	            preparedStatement.addBatch();

	            if (i %2 ==100) {

	                //执行批处理
	                preparedStatement.executeBatch();

	                //清空批处理【如果数据量太大，所有数据存入批处理，内存肯定溢出】
	                preparedStatement.clearBatch();
	            }

	        }
	        //不是所有的%2==100，剩下的再执行一次批处理
	        preparedStatement.executeBatch();

	        //再清空
	        preparedStatement.clearBatch();

	        DBUtils.release(connection, preparedStatement, null);
	}

}
