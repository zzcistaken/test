package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test6 
{
	public static void main(String[] args) throws SQLException 
	{
		Connection con = null;
		
		try
		{
			con = DBDataSources.getConnection();
            con.setAutoCommit(false);
            
            Statement st = con.createStatement();
            
            String sql1 = "UPDATE persons SET FirstName='BenBenBen' WHERE P_id='1'";
            st.execute(sql1);
            
            System.out.println("====================1===================");
            
            String sql2 = "INSERT INTO persons (FirstName, LastName, Address, City)" +
	                " VALUES('HA_HA','12345678','ss@qq.com','NJ')";
            st.execute(sql2);
            
            System.out.println("主线程准备提交...");
            con.commit();
            System.out.println("主线程提交完毕...");            
		}
		catch (Exception e)
		{
			try {
                con.rollback();
                System.out.println("主线程回滚了...");
            } catch (SQLException e1) {
                throw new RuntimeException("主线程事务回滚失败!", e1);
            }
		}
		finally
		{
			try {
                if(con!=null){
                    con.setAutoCommit(true);
//                    ConnsUtil.back(con);
                    con.close();//如果要把close内部的功能换成还连接，就需要我们以后的技术来实现
                }
            } catch (SQLException e) {
                throw new RuntimeException("主线程连接关闭失败!", e);
            }
		}
	}

}
