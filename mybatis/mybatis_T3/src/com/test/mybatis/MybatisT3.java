package com.test.mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.dao.UserDao;
import com.mybatis.model.User;

public class MybatisT3 {

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
	        	UserDao userDao=session.getMapper(UserDao.class);
	            /*User user = userDao.selectUserByID(4); 
	            System.out.println(user.getName()+"------"+user.getBirthday());*/
	        	List<User> users=userDao.selectListByName("n");
	        	for(User user:users){ 
		            System.out.println(user.getName()+"------"+user.getBirthday());
	        	}
	        } finally {
	            session.close();
	        }
	}

}
