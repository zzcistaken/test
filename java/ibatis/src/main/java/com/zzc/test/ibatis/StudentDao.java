package com.zzc.test.ibatis;

import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface StudentDao {
	//添加student表的数据   
    public void addStudent(SqlMapClient sqlMap,StudentDto studentdto);  
    //删除student表的数据   
    public void delStudent(SqlMapClient sqlMap);  
    //删除student表的指定ID数据   
    public void delStudentByID(SqlMapClient sqlMap,StudentDto studentdto);  
    //更新student表的数据   
    public void updataStudent(SqlMapClient sqlMap,StudentDto studentdto);  
    //查询student表的所有数据   
    public ArrayList selectStudent(SqlMapClient sqlMap);  
    //查询student表的指定ID数据   
    public StudentDto selectStudentByID(SqlMapClient sqlMap,StudentDto studentdto);
}
