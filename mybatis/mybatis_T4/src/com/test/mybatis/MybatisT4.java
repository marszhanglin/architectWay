package com.test.mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.dao.UserDao;
import com.mybatis.model.User;

public class MybatisT4 {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 SqlSession session = sqlSessionFactory.openSession();
	        try {
	        	/*User user = userDao.findByID(4); 
	            System.out.println(user.getName()+"------"+user.getBirthday());*/
	        	UserDao userDao=session.getMapper(UserDao.class);
	        	boolean succ=userDao.save(new User("name_no_005", "123456", "name_no_005@qq.com", "1990-12-03"));
	        	System.out.println(succ);
	        	List<User> users = userDao.selectListByName1("CONCAT(CONCAT('%', name), '%')");
	        	for(User user:users){ 
		            System.out.println(user.getName()+"------"+user.getBirthday());
	        	}
	        	/*List<User> users=userDao.selectListByName1("%n%");*/
	        	/*List<User> users=userDao.selectListByName2("n");*/
	        	/*List<User> users=userDao.selectListByName3("n");*/
	        	/*List<User> users=userDao.selectListByName4("3");*/
	        	/*List<User> users=userDao.selectListByName5("name_no_003",2);*/
	        	/*List<User> users=userDao.selectListByName5("name_no_003",2);
	        	for(User user:users){ 
		            System.out.println(user.getName()+"------"+user.getBirthday());
	        	}*/
	        } catch (Exception e) { 
	        	session.rollback();
			}finally {
	        	session.commit();
	            session.close();
	        }
	}

}
