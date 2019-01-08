package com.zzc.test.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zzc.test.mybatis.pojo.Student;
import com.zzc.test.mybatis.util.MybatisUtil;
import com.zzc.test.mybatis.zzz.SqlSession;

public class StudentDao 
{
	public void add(Student student) throws Exception {
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession2();
        
        System.out.println(sqlSession.getConnection().getAutoCommit());
                
        sqlSession.getConnection().setAutoCommit(false);
        
        try
        {
	        //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.sqlSessionFactory.openSession(true);
        
        System.out.println(sqlSession.getConnection().hashCode());
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        sqlSession.getConnection().setAutoCommit(false);
        
        System.out.println(sqlSession.getConnection().hashCode());
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        try
        {
	        //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
	        sqlSession.insert("StudentID.add", student);
	        
	        System.out.println("===============================================");
	        
	        student.setName("zzc-2");
	        sqlSession.insert("StudentID.add", student);
	        
	        //鎵嬪姩鎻愪氦
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        
        System.out.println(sqlSession.getConnection().getAutoCommit());
        
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL


            /**
             * 鐢变簬鎴戜滑鐨勫弬鏁拌秴杩囦簡涓や釜锛岃�屾柟娉曚腑鍙湁涓�涓狾bject鍙傛暟鏀堕泦
             * 鍥犳鎴戜滑浣跨敤Map闆嗗悎鏉ヨ杞芥垜浠殑鍙傛暟
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
            /**
             * 鐢变簬鎴戜滑鐨勫弬鏁拌秴杩囦簡涓や釜锛岃�屾柟娉曚腑鍙湁涓�涓狾bject鍙傛暟鏀堕泦
             * 鍥犳鎴戜滑浣跨敤Map闆嗗悎鏉ヨ杞芥垜浠殑鍙傛暟
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
        //寰楀埌杩炴帴瀵硅薄
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //鏄犲皠鏂囦欢鐨勫懡鍚嶇┖闂�.SQL鐗囨鐨処D锛屽氨鍙互璋冪敤瀵瑰簲鐨勬槧灏勬枃浠朵腑鐨凷QL
            /**
             * 鐢变簬鎴戜滑鐨勫弬鏁拌秴杩囦簡涓や釜锛岃�屾柟娉曚腑鍙湁涓�涓狾bject鍙傛暟鏀堕泦
             * 鍥犳鎴戜滑浣跨敤Map闆嗗悎鏉ヨ杞芥垜浠殑鍙傛暟
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
