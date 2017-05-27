package com.test.mybatis;


import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.mybatis.dao.UserMapper;
import com.mybatis.model.User;

public class MybatisT5 {
	
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
	        	UserMapper userDao=session.getMapper(UserMapper.class);
	        	User usersave=new User("name_no_007", "123456", "name_no_007@qq.com", new Date(System.currentTimeMillis()));
	        	int succ=userDao.insert(usersave);
	        	System.out.println(succ);
	        	//System.out.println(new Gson().toJson(usersave));
	        	//userDao.deleteByPrimaryKey(user.getId());
	        	//usersave.setEmail("1007@qq.com");
	        	//userDao.updateByPrimaryKeySelective(usersave);
	        	List<User> users = userDao.selectByName("n");
	        	for(User user:users){ 
		            System.out.println(new Gson().toJson(user));
		            if("name_no_007".equals(user.getName())){
		            	userDao.deleteByPrimaryKey(user.getId());
		            }
	        	}
	        	System.out.println("-------------------------");
	        	List<User> users2 = userDao.selectByName("n");
	        	for(User user:users2){ 
		            System.out.println(new Gson().toJson(user));
	        	}
	        } 
	        catch (Exception e) { 
	        	session.rollback();
			}
	        finally {
	        	session.commit();
	            session.close();
	        }
	}

}
