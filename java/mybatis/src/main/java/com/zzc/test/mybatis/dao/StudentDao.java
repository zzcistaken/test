package com.zzc.test.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;

public class StudentDao 
{
	public void add(Student student) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession2();
        
        System.out.println(sqlSession.getConnection().getAutoCommit());
                
        sqlSession.getConnection().setAutoCommit(false);
        
        try
        {
	        //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
	        sqlSession.insert("StudentID.add", student);
	        sqlSession.insert("StudentID.add", student);
	        sqlSession.commit();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            sqlSession.rollback();
            System.out.println("rollback");
            throw e;
        }
        finally
        {
        	sqlSession.close();
        	MybatisUtil.closeSqlSession();
        }
    }
	
	
	public void add2(Student student) throws Exception 
	{
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.sqlSessionFactory.openSession(true);
        
        System.out.println(sqlSession.getConnection().hashCode());
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        sqlSession.getConnection().setAutoCommit(false);
        
        System.out.println(sqlSession.getConnection().hashCode());
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        try
        {
	        //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
	        sqlSession.insert("StudentID.add", student);
	        
	        System.out.println("===============================================");
	        
	        student.setName("zzc-2");
	        sqlSession.insert("StudentID.add", student);
	        
	        //手动提交
	        sqlSession.commit();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            sqlSession.rollback();
//            sqlSession.rollback(true);
            System.out.println("rollback");
            
            System.out.println("rollback " + (findById(student.getId())==null));
            
            throw e;
        }
        finally
        {
        	System.out.println("close");
        	sqlSession.close();
//        	MybatisUtil.closeSqlSession();
        	System.out.println("close " + (findById(student.getId())==null));
        }
    }
	
	
	public Student findById(int id) throws Exception 
	{
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            return sqlSession.selectOne("StudentID.findById",id);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
	
	
	public List<Student> findAll() throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            return sqlSession.selectList("StudentID.findAll");
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
	
	
	public void update(Student student ) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.update("StudentID.update", student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
	
	
	public void delete(int id ) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.delete("StudentID.delete", id);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
	
	
	public List<Student>  pagination(int start ,int end) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL


            /**
             * 由于我们的参数超过了两个，而方法中只有一个Object参数收集
             * 因此我们使用Map集合来装载我们的参数
             */
            Map<String, Object> map = new HashMap();
            map.put("start", start);
            map.put("end", end);
            return sqlSession.selectList("StudentID.pagination", map);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
	
	
	public List<Student> findByCondition(String name,Double sal) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            /**
             * 由于我们的参数超过了两个，而方法中只有一个Object参数收集
             * 因此我们使用Map集合来装载我们的参数
             */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("sal", sal);
            return sqlSession.selectList("StudentID.findByCondition", map);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
	
	
	public void updateByConditions(int id,String name,Double sal) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            /**
             * 由于我们的参数超过了两个，而方法中只有一个Object参数收集
             * 因此我们使用Map集合来装载我们的参数
             */
            Map<String, Object> map = new HashMap();
            map.put("id", id);
            map.put("name", name);
            map.put("sal", sal);
            sqlSession.update("StudentID.updateByConditions", map);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }


	

    public static void main(String[] args) throws Exception {

        StudentDao studentDao = new StudentDao();

        Student s = new Student();
        s.setId(10000);
        s.setName("zzc");
        s.setSal(100.00);
        studentDao.add2(s);
        
//        studentDao.add(s);
//        studentDao.add(s);
        
//        Student student = studentDao.findById(1000);
//        System.out.println(student.getName());
        
//        List<Student> students = studentDao.findAll();
//        System.out.println(students.size());
        
//        Student student = studentDao.findById(1000);
//        student.setName("zzc2");
//        student.setSal(200.00);
//        studentDao.update(student);
        
//        List<Student> students = studentDao.pagination(0, 3);
//        for (Student student : students) 
//        {
//            System.out.println(student.getId());
//        }
        
        
//        List<Student> students = studentDao.findByCondition(null,400.00);
//        for (Student student : students) {
//            System.out.println(student.getId() + "---" + student.getName() + "----" + student.getSal());
//        }
        
//        studentDao.updateByConditions(1000,"zzc",100D);

    }
}
