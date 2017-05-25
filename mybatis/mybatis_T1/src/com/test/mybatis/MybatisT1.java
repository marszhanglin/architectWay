package com.test.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.model.User;

public class MybatisT1 {

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
		        /*User user = (User) session.selectOne("com.mybatis.model.UserDao.selectUserByID", 4);
		        System.out.println(user.getName()+"------"+user.getBirthday());*/
		        
		        User user = (User) session.selectOne("com.mybatis.model.UserDao.selectUserByName", "name_no_002");
		        System.out.println(user.getName()+"------"+user.getBirthday());
		        
			 
		        } finally {
		        session.close();
		        }
	}

}
