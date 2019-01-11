package com.zzc.test.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

public class StudentDaoImpl implements StudentDao {
	
	//添加两个相同的数据，查看是否两条全部回滚
	//第一条插入成功
	//查看conection的提交级别，报错null point
	public void addTwoStudentsDefault (SqlMapClient sqlMap, StudentDto studentdto) {
		try {
//			System.out.println(sqlMap.getCurrentConnection().getAutoCommit());
            sqlMap.insert("insert_student", studentdto);
            System.out.println("=========================add one success======================");
            sqlMap.insert("insert_student", studentdto);
        } catch (SQLException e) {   
            e.printStackTrace();  
        }
	}
	
	//在上例基础上，使用事务管理
	public void addTwoStudentsUseTransaction (SqlMapClient sqlMap, StudentDto studentdto) throws SQLException {
		try {
			sqlMap.startTransaction();
			System.out.println(sqlMap.getCurrentConnection().getAutoCommit());
            sqlMap.insert("insert_student", studentdto);
            System.out.println("=========================add one success======================");
            sqlMap.insert("insert_student", studentdto);
            sqlMap.commitTransaction();
        } catch (SQLException e) {   
            e.printStackTrace();
        } finally {
        	sqlMap.endTransaction();
        }
	}
	
	
	//添加student表的数据   
    public void addStudent(SqlMapClient sqlMap, StudentDto studentdto) {  
  
        try {  
            sqlMap.insert("insert_student", studentdto);  
        } catch (SQLException e) {  
  
            e.printStackTrace();  
        }  
    }  
      
    //删除student表的数据   
    public void delStudent(SqlMapClient sqlMap) {  
  
        try {  
            sqlMap.delete("delete_all_student", null);  
        } catch (SQLException e) {  
  
            e.printStackTrace();  
        }  
    }  
  
    //删除student表的指定ID数据   
    public void delStudentByID(SqlMapClient sqlMap, StudentDto studentdto) {  
  
        try {  
            sqlMap.delete("deleteByID_student",studentdto );  
        } catch (SQLException e) {  
  
            e.printStackTrace();  
        }  
    }  
  
    //更新student表的数据   
    public void updataStudent(SqlMapClient sqlMap, StudentDto studentdto) {  
  
        try {  
            sqlMap.update("updataStudent_test",studentdto );  
        } catch (SQLException e) {  
  
            e.printStackTrace();  
        }  
    }  
      
    //查询student表的所有数据   
    public ArrayList selectStudent(SqlMapClient sqlMap) {  
  
        //保存查询结果   
        ArrayList rsList = new ArrayList();  
  
        try {  
            rsList = (ArrayList)sqlMap.queryForList("select_all_student","");  
        } catch (SQLException e) {  
  
            e.printStackTrace();  
        }  
        return rsList;  
    }  
  
    //查询student表的指定ID数据   
    public StudentDto selectStudentByID(SqlMapClient sqlMap, StudentDto studentdto) {  
  
        //返回后保存在info中   
        StudentDto info = new StudentDto();  
        try {  
            info = (StudentDto)sqlMap.queryForObject("selectByID_student", studentdto);  
        } catch (SQLException e) {  
  
            e.printStackTrace();  
        }  
        return info;  
    } 
}
