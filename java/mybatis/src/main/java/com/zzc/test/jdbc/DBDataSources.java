package com.zzc.test.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DBDataSources
{
	private static LinkedList<Connection> list = new LinkedList<Connection>();
    
	private static String  url = null;
    private static String  username = null;
    private static String password = null;
    
    //获取连接只需要一次就够了，所以用static代码块
    static 
    {
        try 
        {
        	url = "jdbc:mysql://localhost:3306/zzc?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    		username = "root";
    		password = "zzc";

            //获取多个连接，保存在LinkedList集合中
            for (int i = 0; i < 10; i++) 
            {
                Connection connection = DriverManager.getConnection(url, username, password);
                list.add(connection);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //重写Connection方法，用户获取连接应该从LinkedList中给他
//    public Connection getConnection() throws SQLException 
//    {
//        System.out.println(list.size());
//        System.out.println(list);
//
//       //先判断LinkedList是否存在连接
//       return list.size() > 0 ? list.removeFirst() : null; 
//    }
    
    public static Connection getConnection() throws SQLException 
    {
        if (list.size() > 0) 
        {
            final Connection connection = list.removeFirst();

            //看看池的大小
            System.out.println(list.size());
            System.out.println(connection.getClass().getName());

            //返回一个动态代理对象
            return (Connection) Proxy.newProxyInstance(DBDataSources.class.getClassLoader(), connection.getClass().getInterfaces(), new InvocationHandler() {

                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    //如果不是调用close方法，就按照正常的来调用
                    if (!method.getName().equals("close")) 
                    {
                    	System.out.println("method: " + method.getName());
                        return method.invoke(connection, args);
                    } else {

                        //进到这里来，说明调用的是close方法
                        list.add(connection);

                        //再看看池的大小
                        System.out.println(list.size());
                        
                        return null;
                    }
                    
                }

            });
        }
        return null;
    }
	
}
