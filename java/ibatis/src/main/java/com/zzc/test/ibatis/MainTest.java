package com.zzc.test.ibatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MainTest {
	public StudentDaoImpl impl = new StudentDaoImpl();  
    public StudentDto info = new StudentDto();  
    public static SqlMapClient sqlmapclient = null;  
    static {  
        try {  
            //读取xml文件   
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");  
            sqlmapclient = SqlMapClientBuilder.buildSqlMapClient(reader);  
            reader.close();  
        } catch (IOException e) {  
  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String []args){  
        MainTest stu = new MainTest();  
          
        System.out.println("------------------------------- start ------------------------------"); 
        StudentDto info = new StudentDto();
        info.setId(101);  
        info.setName("zz-1");  
        info.setSex("男");  
        info.setAge(24);  
        info.setAddress("上海");
        
        StudentDaoImpl dao = new StudentDaoImpl();
//        dao.addTwoStudentsDefault(sqlmapclient, info);
        try {
			dao.addTwoStudentsUseTransaction(sqlmapclient, info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //以下为各种方法测试   
          
        //添加student表的数据   
//        stu.addStudent_test();   
          
        //删除student表的数据   
        //stu.delStudent_test();   
          
        //删除student表的指定ID数据   
        //stu.delStudentByID_test();   
          
        //更新student表的数据   
        //stu.updataStudent_test();   
          
        //查询student表的所有数据   
        //stu.selectStudent_test();   
          
        //查询student表的所有数据   
        //stu.selectStudentByID_test();   
          
        System.out.println("-------------------------------  end  ------------------------------");  
      
    }  
      
    //添加student表的数据   
    public void addStudent_test(){  
          
        //把要插入的数据填入info对象中   
        info.setId(5);  
        info.setName("zh2208");  
        info.setSex("男");  
        info.setAge(24);  
        info.setAddress("上海");  
          
        impl.addStudent(sqlmapclient, info);  //参数就是sqlMap_student.xml中配置对应的id
    }  
      
    //删除student表的数据   
    public void delStudent_test(){  
        impl.delStudent(sqlmapclient);  
    }  
      
    //删除student表的指定ID数据   
    public void delStudentByID_test(){  
        //指定ID   
        info.setId(1);  
        impl.delStudentByID(sqlmapclient,info);  
          
    }  
      
    //更新student表的数据   
    public void updataStudent_test(){  
          
        //把要更新的数据填入info对象中   
        info.setId(6);  
        info.setName("zh2208up");  
        info.setSex("男");  
        info.setAge(20);  
        info.setAddress("上海up");  
        impl.updataStudent(sqlmapclient, info);  
    }  
      
    //查询student表的所有数据   
    public void selectStudent_test(){  
          
        StudentDto stu_dto = new StudentDto();  
        //检索结果保存到list中   
        ArrayList resultList = impl.selectStudent(sqlmapclient);  
        for(int i = 0; i < resultList.size();i++){  
            stu_dto = (StudentDto) resultList.get(i);  
            //打印对象中的信息   
            show(stu_dto);  
        }  
          
    }  
      
    //查询student表的指定ID数据   
    public void selectStudentByID_test(){  
        StudentDto stu_dto = new StudentDto();  
        info.setId(1);  
        stu_dto = impl.selectStudentByID(sqlmapclient,info);  
  
        if(stu_dto != null){  
            show(stu_dto);  
        }else{  
            System.out.println("no data!!!!");  
        }  
    }  
      
    //打印查询结果   
    public void show(StudentDto stu_dto){  
  
        System.out.print("学生ID :" + stu_dto.getId() + " ; ");  
        System.out.print("学生姓名 :" + stu_dto.getName() + " ; ");  
        System.out.print("学生性别 :" + stu_dto.getSex() + " ; ");  
        System.out.print("学生年龄 :" + stu_dto.getAge() + " ; ");  
        System.out.print("学生地址 :" + stu_dto.getAddress());  
        System.out.println();  
      
    }
}
