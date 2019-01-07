package com.zzc.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1Select 
{
	public static void main (String[] args)
	{
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            /*
            * 加载驱动有两种方式
            *
            * 1：会导致驱动会注册两次，过度依赖于mysql的api，脱离的mysql的开发包，程序则无法编译
            * 2：驱动只会加载一次，不需要依赖具体的驱动，灵活性高
            *
            * 我们一般都是使用第二种方式
            * */

            //1.
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.
//            Class.forName("com.mysql.jdbc.Driver");

            //获取与数据库连接的对象-Connetcion
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zzc?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", "root", "zzc");

            //获取执行sql语句的statement对象
            statement = connection.createStatement();

            //执行sql语句,拿到结果集
            resultSet = statement.executeQuery("SELECT * FROM persons");

            //遍历结果集，得到数据
            while (resultSet.next()) 
            {
                System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            /*
            * 关闭资源，后调用的先关闭
            *
            * 关闭之前，要判断对象是否存在
            * */

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
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
}
